(set-env!
 :source-paths #{"src_client" "src_cljc"}
 :resource-paths #{"resources"}

 :mirrors
 {#"maven-central"
  {:name "google"
   :url "https://maven-central-asia.storage-download.googleapis.com/repos/central/data/"}}

 :dependencies
 '[[org.clojure/clojure "1.10.0"]
   [org.clojure/clojurescript "1.10.439"]

   ;; `React!`
   [cljsjs/react "16.0.0-0"]
   [cljsjs/react-dom "16.0.0-0"]
   [cljsjs/react-with-addons "15.4.2-2"]

   ;; reagent : https://github.com/reagent-project/reagent
   [reagent "0.7.0" :exclusions [cljsjs/react]]
   ;; re-frame : https://github.com/Day8/re-frame
   [re-frame "0.10.6"]

   ;; boot-cljs
   [adzerk/boot-cljs "2.1.5" :scope "test"]
   [adzerk/boot-reload "0.6.0" :scope "test"]
   [pandeiro/boot-http "0.7.6" :scope "test"]

   ;; boot-cljs-repl : https://github.com/adzerk-oss/boot-cljs-repl
   [adzerk/boot-cljs-repl   "0.4.0"] ;; latest release
   [cider/piggieback        "0.3.9"  :scope "test"]
   [weasel                  "0.7.0"  :scope "test"]
   [nrepl                   "0.4.5"  :scope "test"]])

(require '[adzerk.boot-cljs :refer [cljs]])
(require '[adzerk.boot-reload :refer [reload]])
(require '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])
(require '[pandeiro.boot-http :refer [serve]])

(deftask dev []
  (comp
   (serve :port 8080 :httpkit true)
   (watch)
   (reload)
   (cljs-repl)
   (cljs :ids #{"js\\main"})))
