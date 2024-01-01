;; (ns sample.main)
(ns sample.main
 (:require [devtools.core :as devtools]))

(devtools/install!)

(.log js/console (range 5))
(.log js/console {:a 10 :b 20})

(defn x []
  (println "x"))

(defn ^:export main
  []
  (let [node (-> js/document (.getElementById  "app"))]
    (if-not node
      (js/alert "Fail to find `app`")
      (-> js/document
          (.getElementById "app")
          (.-innerHTML)
          (set! "Hello Clojure!")))))
