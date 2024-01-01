(def project 'sample)
(def version "0.1.0-SNAPSHOT")

(set-env!
 :source-paths   #{"client/src" "client/src_dev"}
 :resource-paths #{"resources/public"}
 :dependencies
 '[[org.clojure/clojure "1.10.0" :scope "provided"]

   ;; clojurescript
   [org.clojure/clojurescript "1.10.439" :scope "provided"]
   [adzerk/boot-cljs "2.1.5" :scope "test"]

   ;; ref: https://github.com/adzerk-oss/boot-reload
   [adzerk/boot-reload "0.6.0" :scope "test"]

   [powerlaces/boot-figreload "0.5.14" :scope "test"]
   [pandeiro/boot-http "0.7.6" :scope "test"]

   ;; clojurescript repl
   ;; ref: https://github.com/adzerk-oss/boot-cljs-repl
   [adzerk/boot-cljs-repl "0.4.0" :scope "test"] ;; latest release
   [cider/piggieback "0.3.9" :scope "test"]
   [weasel "0.7.0" :scope "test"]
   [nrepl "0.4.5" :scope "test"]

   ;; figwheel
   ;; ref: https://github.com/boot-clj/boot-figreload
   [powerlaces/boot-figreload "0.5.14" :scope "test"]

   ;; Dirac and cljs-devtoos
   ;; ref: https://github.com/binaryage/dirac
   ;; ref: https://github.com/binaryage/cljs-devtools
   [binaryage/dirac "1.3.0" :scope "test"]
   [binaryage/devtools "0.9.10" :scope "test"]
   [powerlaces/boot-cljs-devtools "0.2.0" :scope "test"]]

 ;; https://okky.kr/article/424622
 ;; https://cloudplatform.googleblog.com/2015/11/faster-builds-for-Java-developers-with-Maven-Central-mirror.html
 ;; set BOOT_MAVEN_CENTRAL_MIRROR=https://maven-central-asia.storage-download.googleapis.com/repos/central/data/
 :mirrors
 {#"maven-central"
  {:name "google"
   :url "https://maven-central-asia.storage-download.googleapis.com/repos/central/data/"}
  ;; #"clojar"
  ;; {:name "x"
  ;;  :url "https://clojars.org/repo/"}
  }
 )


(require '[adzerk.boot-cljs :refer [cljs]])
(require '[adzerk.boot-reload :refer [reload]])
(require '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])
(require '[pandeiro.boot-http :refer [serve]])
(require '[powerlaces.boot-cljs-devtools :refer [dirac cljs-devtools]])


(deftask dev []
  (comp
   (serve :port 8080 :httpkit true)
   (watch)
   (reload)
   (cljs-repl)
   (cljs :optimizations :none
         :ids #{"js\\main"})))

(deftask devtool []
  (comp
   (serve :port 8080 :httpkit true)
   (watch)
   (cljs-devtools)
   (reload)
   (dirac)
   (cljs :source-map true
         :optimizations :none
         :compiler-options {:external-config
                            {:devtools/config {:features-to-install [:formatters :hints]
                                               :fn-symbol "λ"
                                               :print-config-overrides true}}})))

(deftask prod []
  (comp
   (cljs :ids #{"js\\main"}
         :optimizations :advanced)
   #_(sift :include #{#"(^index\.html|^main\.js|^styles.css)"})
   (target :dir #{"release"})))
