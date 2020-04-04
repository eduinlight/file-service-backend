<<<<<<< HEAD
(ns file-service-backend.core
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [file-service-backend.routes :refer [my-routes]]
            [file-service-backend.schemas :as schemas]
            [ring.middleware.cors :refer [wrap-cors]]
            ))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "File-service-backend"
                    :description "File Service for Backend"}
             :tags [{:name "api", :description "some apis"}]}}}
    
    (my-routes)

    ;AGREGANDO SOPORTE PARA CORS UFF QUE TRABAJITO
    (wrap-cors routes
      :access-control-allow-origin [#".*"])
  )
)
=======
(ns file-service-backend.core
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [file-service-backend.routes :refer [my-routes]]
            [file-service-backend.schemas :as schemas]
            [ring.middleware.cors :refer [wrap-cors]]
            ))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "File-service-backend"
                    :description "File Service for Backend"}
             :tags [{:name "api", :description "some apis"}]}}}
    
    (my-routes)

    ;AGREGANDO SOPORTE PARA CORS UFF QUE TRABAJITO
    (wrap-cors routes
      :access-control-allow-origin [#".*"])
  )
)
>>>>>>> 771798138ecbec37c6e729313555ffad9f72264f
