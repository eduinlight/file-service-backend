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
  }))