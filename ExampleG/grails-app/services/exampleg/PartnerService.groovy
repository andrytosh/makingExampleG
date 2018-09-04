package exampleg

import grails.gorm.transactions.Transactional
import examplegsrc.*

class PartnerService {

    @Transactional(readOnly = true)
    def getPartners(def from, def to) {
        // Default
        if(from == null || to == null){
            from    = 0
            to      = 10
        }

        List partnerSchemaJSON = new ArrayList();
        def partnerDomainLis = PartnerDomain.list(offset:from, max:to)
        partnerDomainLis.each{
            partnerSchemaJSON.add([id: "${it.id}", name: "${it.companyName}",
                                   reference: "${it.ref}", locale: "${it.locale}",
                                   expirationTime: "${it.expires}"]);
        }

        // Return response OK
        CustomMsg customMsg = new CustomMsg("200", "Getting a Partners.")
        return new ResponseObject(customMsg, partnerSchemaJSON)

    }

    @Transactional(readOnly = true)
    def getPartnerById(def partnerId) {
        try{
            def partner = PartnerDomain.read(partnerId)
            // Validate partner
            this.validatePartner(partner)
            // Schema
            def partnerSchemaJSON = [id: "${partner.id}", name: "${partner.companyName}",
                                     reference: "${partner.ref}", locale: "${partner.locale}",
                                     expirationTime: "${partner.expires}"]
            // Return response OK
            CustomMsg customMsg = new CustomMsg("200", "Getting a Partner by id ${partnerId}.")
            return new ResponseObject(customMsg, partnerSchemaJSON)
        }
        catch (Exception excp){
            // Return response KO
            CustomMsg customMsg = new CustomMsg("404", "Partner with id ${partnerId} not found.")
            return new ResponseObject(customMsg, null)
        }
    }

    @Transactional
    def insertPartnerIndex(String  companyName, String  ref, String  locale) {
        // Saving in DB
        def partner = new PartnerDomain(companyName: companyName, ref: ref,
                locale: new Locale(locale), expires: new Date())
        partner.save(flush: true)
    }

    @Transactional
    def insertPartner(String  companyName, String  ref, String  locale, String dateStr) {
        // Create a Partner
        try {
            // Saving in DB
            def partner = new PartnerDomain(companyName: companyName, ref: ref,
                    locale: new Locale(locale), expires: new Date())
            partner.save(flush: true)
            // Json
            def partnerSchemaJSON = [id: "${partner.id}", name: "${partner.companyName}",
                                     reference: "${partner.ref}", locale: "${partner.locale}",
                                     expirationTime: "${partner.expires}"]
            // Return response OK
            CustomMsg customMsg = new CustomMsg("200", "New Partner CREATED.")
            return new ResponseObject(customMsg, partnerSchemaJSON)
        }
        catch (Exception excp){
            // Return response KO
            CustomMsg customMsg = new CustomMsg("404", "A string representing the validation error that occurred.")
            return new ResponseObject(customMsg, null)
        }
    }

    // Update a Partner
    @Transactional
    def updatePartner(def partnerId, String  companyName, String  ref, String locale, String dateStr) {
        try {
            // Saving in DB
            def partner         = PartnerDomain.get(partnerId)
            // Validate partner
            this.validatePartner(partner)
            // Setting values
            partner.companyName = companyName
            partner.ref         = ref
            partner.locale      = new Locale(locale)
            partner.expires     = new Date()
            partner.save(flush: true)
            // JsonShema
            def partnerSchemaJSON = [id: "${partner.id}", name: "${partner.companyName}",
                                     reference: "${partner.ref}", locale: "${partner.locale}",
                                     expirationTime: "${partner.expires}"]
            // Return response OK
            CustomMsg customMsg = new CustomMsg("200", "Partner with id ${partnerId} has been UPDATE.")
            return new ResponseObject(customMsg, partnerSchemaJSON)
        }
        catch (RuntimeException excp){
            // Return response KO
            CustomMsg customMsg = new CustomMsg("404", "Partner with id ${partnerId} not found.")
            return new ResponseObject(customMsg, null)
        }
    }

    // Delete a Partner
    @Transactional
    def deletePartner(def partnerId) {
        // Detele Partner
        def partner

        try {
            // Getting a partner
            partner = PartnerDomain.get(partnerId)
            // Validate partner
            this.validatePartner(partner)
            // Delete
            partner.delete()

            // Return response OK
            CustomMsg customMsg = new CustomMsg("200", "Partner with id ${partnerId} has been DELETE.")
            return new ResponseObject(customMsg, null)
        }
        catch (RuntimeException excp){
            // Return response KO
            CustomMsg customMsg = new CustomMsg("404", "Partner with id ${partnerId} not found.")
            return new ResponseObject(customMsg, null)
        }
    }

    // Validations
    def validatePartner(def partner){
        if(partnerId == null)
            throw new RuntimeException()
    }
}