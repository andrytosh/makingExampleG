package exampleg

import grails.gorm.transactions.Transactional
import groovy.json.*
import examplegsrc.*

class PartnerService {

    @Transactional(readOnly = true)
    def getPartners(def from, def to) {
        // Default
        if(from == null || to == null){
            from    = 0
            to      = 10
        }

        // Logic
        try{
            List partnerSchemaJSON = new ArrayList();
            def partnerDomainLis = PartnerDomain.list(offset:from, max:to)
            partnerDomainLis.each{
                partnerSchemaJSON.add([id: "${it.id}", name: "${it.companyName}",
                                       reference: "${it.ref}", locale: "${it.locale}",
                                       expirationTime: "${it.expires}"]);
            }

            return  new JsonBuilder(partnerSchemaJSON).toPrettyString()
        }
        catch (Exception excp){
            // Trying to do this but the references are not OK, and I dont understand why
            /*
            CustomException partnerNotFoundExcp = new CustomException(code: "404",
                    message: "Partner with id ${partnerId} not found.")
            */
            // Alternative
            def partnerNotFoundExcp = [code: "404",
                                       message: "Partner with id ${partnerId} not found."]
            return new JsonBuilder(partnerNotFoundExcp).toPrettyString()
        }
    }

    @Transactional(readOnly = true)
    def getPartnerById(def partnerId) {
        try{
            def partner = PartnerDomain.read(partnerId)
            // Trying to do this but the references are not OK, and I dont understand why
            /*
            PartnerSchemaJSON partnerSchemaJSON = new PartnerSchemaJSON(id: partner.id, name: partner.companyName,
                    reference: partner.ref, locale: partner.locale, expirationTime: partner.expires)
            */
            // Alternative
            def partnerSchemaJSON = [id: "${partner.id}", name: "${partner.companyName}",
                                     reference: "${partner.ref}", locale: "${partner.locale}",
                                     expirationTime: "${partner.expires}"]
            return  new JsonBuilder(partnerSchemaJSON).toPrettyString()
        }
        catch (Exception excp){
            // Trying to do this but the references are not OK, and I dont understand why
            /*
            CustomException partnerNotFoundExcp = new CustomException(code: "404",
                    message: "Partner with id ${partnerId} not found.")
            */
            // Alternative
            def partnerNotFoundExcp = [code: "404",
                                       message: "Partner with id ${partnerId} not found."]
            return new JsonBuilder(partnerNotFoundExcp).toPrettyString()
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
            // Formating date
            //Date date = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'", dateStr)
            // Saving in DB
            def partner = new PartnerDomain(companyName: companyName, ref: ref,
                    locale: new Locale(locale), expires: new Date())
            partner.save(flush: true)
            // Json
            def partnerSchemaJSON = [id: "${partner.id}", name: "${partner.companyName}",
                                     reference: "${partner.ref}", locale: "${partner.locale}",
                                     expirationTime: "${partner.expires}"]
            return  new JsonBuilder(partnerSchemaJSON).toPrettyString()
        }
        catch (Exception excp){
            // Alternative
            def partnerNotFoundExcp = [code: "404",
                                       message: "A string representing the validation error that occurred."]
            return new JsonBuilder(partnerNotFoundExcp).toPrettyString()
        }
    }

    @Transactional
    def updatePartner(def partnerId, String  companyName, String  ref, String locale, String dateStr) {
        // Update a Partner
        try {
            // Formating date
            //Date date = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'", dateStr)
            // Saving in DB
            def partner         = PartnerDomain.get(partnerId)
            partner.companyName = companyName
            partner.ref         = ref
            partner.locale      = new Locale(locale)
            partner.expires     = new Date()
            partner.save(flush: true)
            // Json
            def partnerSchemaJSON = [id: "${partner.id}", name: "${partner.companyName}",
                                     reference: "${partner.ref}", locale: "${partner.locale}",
                                     expirationTime: "${partner.expires}"]
            return  new JsonBuilder(partnerSchemaJSON).toPrettyString()
        }
        catch (Exception excp){
            // Alternative
            def partnerNotFoundExcp = [code: "404",
                                       message: "Partner with id ${partnerId} not found."]
            return new JsonBuilder(partnerNotFoundExcp).toPrettyString()
        }
    }

    @Transactional
    def deletePartner(def partnerId) {
        // Detele Partner
        def partner

        try {
            partner = PartnerDomain.get(partnerId)
            partner.delete()
        }
        catch (Exception excp){
            // Trying to do this but the references are not OK, and I dont understand why
            /*
            CustomException partnerNotFoundExcp = new CustomException(code: "404",
                    message: "Partner with id ${partnerId} not found.")
            */
            // Alternative
            def partnerNotFoundExcp = [code: "404",
                                       message: "Partner with id ${partnerId} not found."]
            return new JsonBuilder(partnerNotFoundExcp).toPrettyString()
        }
    }
}