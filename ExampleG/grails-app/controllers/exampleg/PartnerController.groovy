package exampleg

import groovy.json.*

class PartnerController {

    def partnerServ = new PartnerService();

    def index() {
        // Creating
        partnerServ.insertPartnerIndex("Example", "4857", "en_ES")
        // Rendering
        render  "New register inserted in DB";
    }

    // Getting partners
    def getPartners(){
        // Getting partners
        def responseObj = new PartnerService().getPartners(params.from, params.size)
        // Transforming the response
        def objectJson      = responseObj.objectJson
        def responseCode    = responseObj.customMsg.code
        def responseJson    = new JsonBuilder(objectJson).toPrettyString()

        // Rendering
        render(status: responseCode, contentType: "application/json", text: responseJson)

    }

    // Get Partner by ID
    def getPartnerById(){
        // Getting partner by ID
        def responseObj  = new PartnerService().getPartnerById(params.id)
        // Transforming the response
        def objectJson      = responseObj.objectJson
        def customMsg       = responseObj.customMsg
        def responseCode    = customMsg.code
        def responseJson    = new JsonBuilder(("200" == responseCode) ? objectJson : customMsg).toPrettyString()

        // Rendering
        render(status: responseCode, contentType: "application/json", text: responseJson)
    }

    // Create Partner
    def insertPartner(){
        // Insert Register
        def responseObj  = new PartnerService().insertPartner(params.name, params.reference,
                params.locale, params.expirationTime)
        // Transforming the response
        def objectJson      = responseObj.objectJson
        def customMsg       = responseObj.customMsg
        def responseCode    = customMsg.code
        def responseJson    = new JsonBuilder(("201" == responseCode) ? objectJson : customMsg).toPrettyString()

        // Rendering
        render(status: responseCode, contentType: "application/json", text: responseJson)
    }

    // Update a Partner
    def updatePartner(){
        // Getting jsonParam
        def jsonParams = request.JSON
        // Update Register
        def responseObj     = new PartnerService().updatePartner(params.id, jsonParams.name, jsonParams.reference,
                jsonParams.locale, jsonParams.expirationTime)
        // Transforming the response
        def objectJson      = responseObj.objectJson
        def customMsg       = responseObj.customMsg
        def responseCode    = customMsg.code
        def responseJson    = new JsonBuilder(("200" == responseCode) ? objectJson : customMsg).toPrettyString()

        // Rendering
        render(status: responseCode, contentType: "application/json", text: responseJson)
    }

    // Delete a Partner by ID
    def delete(){
        // Deleting Partner
        def responseObj     = new PartnerService().deletePartner(params.id)
        // Transforming the response
        def customMsg       = responseObj.customMsg
        def responseCode    = customMsg.code
        def responseJson    = new JsonBuilder(customMsg).toPrettyString()

        // Rendering
        render(status: responseCode, contentType: "application/json", text: responseJson)
    }

    /**
     * If any method in this controller invokes code that
     * will throw a Exception then this method
     * is invoked.
     */
    def exceptionHandler(Exception exception){
        def excpJson = [code: "500",
                        message: exception.message/*"An internal error occurred"*/]
        def responseJson = new JsonBuilder(excpJson).toPrettyString()
        render(status: 500, contentType: "application/json", text: responseJson)
    }

}
