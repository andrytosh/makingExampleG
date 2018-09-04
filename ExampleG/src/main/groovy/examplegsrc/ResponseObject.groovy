package examplegsrc

class ResponseObject {
    def objectJson
    CustomMsg customMsg

    ResponseObject(CustomMsg customMsg, objectJson = null) {
        this.objectJson = objectJson
        this.customMsg = customMsg
    }
}
