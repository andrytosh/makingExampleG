package exampleg

class PartnerController {

    def partnerServ = new PartnerService();

    def index() {
        // Creating
        partnerServ.insertPartnerIndex("Example", "4857", "en_ES")
        // Rendering
        render  "New register inserted in DB";
    }

    def getPartners(){
        def responseJson = new PartnerService().getPartners(params.from, params.size)
        render(status: 200, contentType: "application/json", text: responseJson)
    }

    def getPartnerById(){
        def responseJson  = new PartnerService().getPartnerById(params.id)
        render(status: 200, contentType: "application/json", text: responseJson)
    }

    def insertPartner(){
        def responseJson  = new PartnerService().insertPartner(params.name, params.reference,
                params.locale, params.expirationTime)
        render(status: 201, contentType: "application/json", text: responseJson)
    }

    def updatePartner(){
        def responseJson  = new PartnerService().updatePartner(params.id, params.name, params.reference,
                params.locale, params.expirationTime)
        render(status: 200, contentType: "application/json", text: responseJson)
    }

    def delete(){
        def responseJson = new PartnerService().deletePartner(params.id)
        render(status: 200, contentType: "application/json", text: responseJson)
    }

}
