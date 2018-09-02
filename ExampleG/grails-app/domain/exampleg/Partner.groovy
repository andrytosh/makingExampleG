package exampleg

class Partner {

    Long    id;
    String  companyName;
    String  ref;
    Locale  locale;
    Date    expires;

    static mapping = {
        table   "tblPartner"
        id              column: "id"
        companyName     column: "companyName"
        ref             column: "ref"
        locale          column: "locale"
        expires         column: "expires"
    }
}
