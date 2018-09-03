package exampleg

class PartnerDomain {

    Long    id;
    String  companyName;
    String  ref;
    Locale  locale;
    Date    expires;

    static mapping = {
        table   "TBL_PARTNER"
        id              increment: "id"
        companyName     column: "companyName"
        ref             column: "ref"
        locale          column: "locale"
        expires         column: "expires"
    }

    PartnerDomain(String companyName, String ref, Locale locale, Date expires) {
        this.companyName = companyName
        this.ref = ref
        this.locale = locale
        this.expires = expires
    }
}
