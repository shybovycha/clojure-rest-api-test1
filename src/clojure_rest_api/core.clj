(ns clojure-rest-api.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes GET]]))

(defresource root []
  :available-media-types ["text/html"]
  :handle-ok (fn [_] "<html><body><h1>Hello, Internet.</h1></body></html>"))

(defroutes app
  (GET "/" [] (root)))

(def handler
  (-> app
      wrap-params))
