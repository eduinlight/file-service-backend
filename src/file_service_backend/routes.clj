(ns file-service-backend.routes
  (:require [compojure.api.sweet :refer :all]
            [clojure.java.io :as io]
            [ring.util.http-response :refer :all]
            [file-service-backend.schemas :as schemas]
            [file-service-backend.utils :as utils]
            [schema.core :as s]
            )
)

(defn my-routes []
  (context "/" []
    :tags ["api"]

    ;;OBTENER DIRECTORIO INICIAL
    ;;/start_paths => GET => {} => listado de carpetas hasta la carpeta del server
    (GET "/start_paths" []
      :return [schemas/SPath]
      :query-params []
      :summary "obtener listado de directorios iniciales"
      (ok (
        do
          (def tmp (atom (.getCanonicalFile (io/file "."))))
          (def res (atom []))
          (while (not= nil @tmp)
            (do
              (swap! res #(conj % { :path (.getCanonicalPath @tmp)
                                    :name (.getName @tmp)}))
              (swap! tmp #(.getParentFile %))
            ))
          @res
        )))
    ;;LISTAR ARCHIVOS DE UN DIRECTORIO
    ;;/files/list => GET => {dir: ''} => lista de directorios
    (GET "/files/list" []
      :return [schemas/SFile]
      :query-params [dir :- String]
      :summary "listar archivos de un directorio"
      (ok (map #(
        utils/getInfo %
      ) (.listFiles (-> dir io/file .getCanonicalFile))))
    )
    ;;CAMBIAR NOMBRE
    ;;/files/changename => PUT => {file_name: '', new_name: ''} => ok => ocurrió un error intentando cambiar el nombre al archivo
    (POST "/files/changename" []
      :return String
      :body [s schemas/SChnageFilename]
      :summary "cambiar nombre a archivo o directorio"
      (ok (let [{
          old_name :file_name, 
          new_name :new_name, 
        } s]
          (try
            (do
              (def f_old (io/file old_name))
              (def f_new (io/file new_name))
              (.renameTo f_old f_new)
              (-> "ok"))
            (catch Exception e
              (-> "ocurrió un error intentando cambiar el nombre al archivo")))
        ))
    )
    ;;OBTENER INFORMACIÓN DE ARCHIVO O DIRECTORIO
    ;;/files/get_info => GET => {path: ''} => información del archivo
    (GET "/files/get_info" []
      :return schemas/SFile
      :query-params [path :- String]
      :summary "obtener información de archivo o directorio"
      (ok (
          utils/getInfo (io/file path)
        ))
    )
    ;;VER CONTENIDO DE ARCHIVO
    ;;/files/content => GET => {file: ''} => retorna contenido de archivo
    (GET "/files/content" []
      :return String
      :query-params [path :- String]
      :summary "obtener contenido de archivo"
      (ok (
          slurp (io/file path)
        ))
    )
    ;;ELIMINAR DIRECTORIO
    ;;/files/delete/dir => DELETE => {dir: ''} => ok => error elminando directorio
    (DELETE "/files/delete/dir" []
      :return String
      :body [s schemas/SDirectorio]
      :summary "eliminar directorio"
      (ok (let [{
          dir :dir, 
        } s]
          (try
            (do
              (.delete (io/file dir))
              (-> "ok"))
            (catch Exception e
              (-> "error elminando directorio")))
        ))
    )
    ;;ELIMINAR ARCHIVO
    ;;/files/delete/file => DELETE => {file: ''} => ok => error elminando archivo
    (DELETE "/files/delete/file" []
      :return String
      :body [s schemas/SArchivo]
      :summary "eliminar archivo"
      (ok (let [{
          f :file, 
        } s]
          (try
            (do
              (.delete (io/file f))
              (-> "ok"))
            (catch Exception e
              (-> "error elminando archivo")))
        ))
    )
    ;;CREAR DIRECTORIO
    ;;/files/create/dir => POST => {dir: ''} => schemas/SFile => error creando directorio
    (POST "/files/create/dir" []
      :return schemas/SFile
      :body [s schemas/SDirectorio]
      :summary "crear directorio"
      (ok (let [{
          dir :dir, 
        } s]
          (try
            (do
              (def f_dir (io/file dir))
              (.mkdir f_dir)
              (utils/getInfo f_dir))
            (catch Exception e
              (-> "error creando directorio")))
        ))
    )
    ;;CREAR ARCHIVO TEXTO
    ;;/files/create/file => POST => {file: ''} => schemas/SFile => error creando archivo
    (POST "/files/create/file" []
      :return schemas/SFile
      :body [s schemas/SArchivo]
      :summary "crear archivo"
      (ok (let [{
          f :file,
        } s]
          (try
            (do
              (def f_dir (io/file f))
              (.createNewFile f_dir)
              (utils/getInfo f_dir))
            (catch Exception e
              (-> "error creando archivo")))
        ))
    )
    ;;DESCARGAR ARCHIVO (SOLO PARA ARCHIVOS)
    ;;/files/download => GET => {path: ''} => genera un response de descarga
    (GET "/files/download" []
      :summary "descargar archivo"
      :query-params [path :- String]
      (file-response (.getName (io/file path)) {:root (.getCanonicalPath (io/file path))})
      :headers {}
      :status 200
      :body (io/file path)
    )
  )
)