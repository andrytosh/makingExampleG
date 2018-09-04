package exampleg

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        get     "/partners"(controller:"Partner", action:"getPartners")
        get     "/partners/$id"(controller:"Partner", action:"getPartnerById")
        post    "/partners"(controller:"Partner", action:"insertPartner")
        put     "/partners/$id"(controller:"Partner", action:"updatePartner")
        delete  "/partners/$id"(controller:"Partner", action:"delete")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
