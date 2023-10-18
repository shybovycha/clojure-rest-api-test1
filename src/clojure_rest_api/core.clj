(ns clojure-rest-api.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes GET]]))

(defresource root []
  :available-media-types ["text/html"]
  :handle-ok (fn [_] "<html><body><h1>Hello, Internet.</h1></body></html>"))

(defresource validator [n]
  :available-media-types ["text/html"]
  :malformed? (fn [_] (odd? n))
  :handle-ok (fn [_] (format "<html><body><h1>%d is an good even number</h1></body></html>" n))
  :handle-malformed (fn [_] (format "%d is a bad number" n)))

(defroutes app
  (GET "/" [] (root))
  (GET "/validate/:n" [n] (validator (Integer/parseInt n))))

(def handler
  (-> app
      wrap-params))
