(defproject file-service-backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [metosin/compojure-api "1.1.11"]
                 [ring-cors "0.1.13"]]
  :ring {:handler file-service-backend.core/app}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                  :plugins [[lein-ring "0.12.5"]]}})
