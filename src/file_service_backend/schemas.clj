<<<<<<< HEAD
(ns file-service-backend.schemas
  (:require [schema.core :as s]))

; (s/defschema Pizza
;   {:name s/Str
;     (s/optional-key :description) s/Str
;     :size (s/enum :L :M :S)
;     :origin {:country (s/enum :FI :PO)
;             :city s/Str}})

(s/defschema SFile {
  :name String,
  :path String,
  :isFile Boolean,
  :isDirectory Boolean,
  :execute Boolean,
  :read Boolean,
  :write Boolean,
})

(s/defschema SChnageFilename {
  :file_name String,
  :new_name String,
})

(s/defschema SDirectorio {
  :dir String,
})

(s/defschema SArchivo {
  :file String,
})

(s/defschema SPath {
  :path String,
  :name String,
})
=======
(ns file-service-backend.schemas
  (:require [schema.core :as s]))

; (s/defschema Pizza
;   {:name s/Str
;     (s/optional-key :description) s/Str
;     :size (s/enum :L :M :S)
;     :origin {:country (s/enum :FI :PO)
;             :city s/Str}})

(s/defschema SFile {
  :name String,
  :path String,
  :isFile Boolean,
  :isDirectory Boolean,
  :execute Boolean,
  :read Boolean,
  :write Boolean,
})

(s/defschema SChnageFilename {
  :file_name String,
  :new_name String,
})

(s/defschema SDirectorio {
  :dir String,
})

(s/defschema SArchivo {
  :file String,
})

(s/defschema SPath {
  :path String,
  :name String,
})
>>>>>>> 771798138ecbec37c6e729313555ffad9f72264f
