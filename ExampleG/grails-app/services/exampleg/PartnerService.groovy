package exampleg

import grails.gorm.transactions.Transactional
//import src.main.groovy.exampleg.*
import groovy.json.*

class PartnerService {

    @Transactional(readOnly = true)
    def getPartners(int from = 0, int to = 10) {
        return PartnerDomain.list(offset:from, max:to)
    }

    @Transactional(readOnly = true)
    def getPartnerById(int partnerId) {
        return PartnerDomain.read(partnerId)
    }

    @Transactional
    def insertPartner(String  companyName, String  ref, String  locale) {
        // Create a Partner
        def partner = new PartnerDomain(companyName: companyName, ref: ref, locale: new Locale(locale), expires: new Date())
        partner.save()
    }

    @Transactional
    def updatePartner(int partnerId, String  companyName, String  ref, String locale) {
        // Update a Partner
        def partner = PartnerDomain.get(partnerId)
        partner.companyName = companyName
        partner.ref = ref
        partner.locale = new Locale(locale)
        partner.expires = new Date()
        partner.save()
    }

    @Transactional
    def deletePartner(int partnerId) {
        // Detele Partner
        String msg = "The partner with the given id has been deleted"
        def partner;

        try {
            partner = PartnerDomain.get(partnerId)
            partner.delete()
        }
        catch (Exception excp){
            /*CustomException partnerNotFoundExcp = new CustomException(code: "404",
                    message: "Partner with id ${partnerId} not found.")*/
            def partnerNotFoundExcp = [code: "404", message: "Partner with id ${partnerId} not found."]

            return new JsonBuilder(partnerNotFoundExcp).toPrettyString()
        }

        return msg;
    }
}