package exampleg

class PartnerController {

    def partnerServ = new PartnerService();

    def index() {
        // Creating
        //partnerServ.insertPartner("Isban", "4857", "en_ES")
        /*for(int i = 0; i < 30; i++){
            partnerServ.insertPartner("Isban " + i, "4857", "en_ES")
        }*/
        // Updating
        //partnerServ.updatePartner(2, "Iberdrola", "4857", "en_ES")
        // Deleting
        def str = partnerServ.deletePartner(200)
        // Getting partner by ID
        //def partner = partnerServ.getPartnerById(200)
        // Getting partner FROM - TO
        //def partners = partnerServ.getPartners()
        //def partners = partnerServ.getPartners(0, 500)

        /*def testStr = "";

        partners.each {
            testStr += " ${it.id} - ${it.companyName}"
        }

        // Rendering
        render "Hello Word! " + testStr + " size: " + partners.size()
        */

        // Rendering
        render  str;
    }

}
