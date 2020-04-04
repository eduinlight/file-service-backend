<<<<<<< HEAD
(ns file-service-backend.utils
  (:require [clojure.java.io :as io]))

(defn getInfo [file]
  (-> {
    :name (.getName file),
    :path (.getCanonicalPath file),
    :isFile (.isFile file),
    :isDirectory (.isDirectory file),
    :execute (.canExecute file),
    :read (.canRead file),
    :write (.canWrite file),
=======
(ns file-service-backend.utils
  (:require [clojure.java.io :as io]))

(defn getInfo [file]
  (-> {
    :name (.getName file),
    :path (.getCanonicalPath file),
    :isFile (.isFile file),
    :isDirectory (.isDirectory file),
    :execute (.canExecute file),
    :read (.canRead file),
    :write (.canWrite file),
>>>>>>> 771798138ecbec37c6e729313555ffad9f72264f
  }))