# shadow-cljs

- [repo](https://github.com/thheller/shadow-cljs)
- [가이드](https://shadow-cljs.github.io/docs/UsersGuide.html)
  - [가이드 - repo](https://github.com/shadow-cljs/shadow-cljs.github.io)
  - ja
    - <https://t-cool.github.io/shadow-cljs-users-guide-ja/>
    - <https://github.com/t-cool/shadow-cljs-users-guide-ja>
- part
  - clojure library
  - npm package

## :dev{{-before/after}}-load{{-async}}

- shadow-cljs lifecycle
  - https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html

``` clojure
(defn ^:dev/before-load stop []
  (js/console.log "stop"))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn ^:dev/before-load-async stop [done]
  (js/console.log "stop")
  (js/setTimeout
    (fn []
      (js/console.log "stop complete")
      (done)))

(defn ^:dev/after-load-async start [done]
  (js/console.log "start")
  (js/setTimeout
    (fn []
      (js/console.log "start complete")
      (done))))
```

## init

- deps.edn
- shadow-cljs.edn
- package.json
- resources/public/index.html


``` edn
;; file: deps.edn

{:paths ["src" "resources"]

 :deps
 {reagent/reagent {:mvn/version "1.2.0"}
  re-frame/re-frame {:mvn/version "1.4.2"}

  markdown-clj/markdown-clj {:mvn/version "1.11.7"}
  metosin/reitit {:mvn/version "0.6.0"}
  org.babashka/sci {:mvn/version "0.8.41"}
  thheller/shadow-cljs {:mvn/version "2.26.2"}
  }

 :aliases
 {:dev
  {:extra-deps {cider/cider-nrepl {:mvn/version "0.44.0"}}}}}
```

``` edn
;; file: shadow-cljs.edn

{:deps {:aliases [:dev]}
 :dev-http {8080 "resources/public"}
 :builds
  {:app {:target :browser
         :output-dir "resources/public/js"
         :asset-path "/js"
         :module-hash-names false
         :modules {:app {:entries [app.core]
                         :init-fn app.core/Main}}
         :dev {:closure-defines {app.env/DEBUG true}}
         :build-hooks [(shadow.cljs.build-report/hook)]}}}
```

``` json
{
  "//": ["file: package.json"],
  "name": "helloworld",
  "version": "0.0.1",
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "tailwindcss": "^3.4.0"
  },
  "devDependencies": {
    "shadow-cljs": "2.26.2"
  },
  "scripts": {
    "start": "npx shadow-cljs --dependency cider/cider-nrepl:0.44.0 watch :app",
    "styles-watch": "npx tailwindcss -i src/styles/styles.css -o resources/public/css/styles.css --watch",
    "styles": "npx tailwindcss -i src/styles/styles.css -o resources/public/css/styles.css",
    "build": "npx shadow-cljs release :app && npm run styles",
    "test": "echo \"Error: no test specified\" && exit 1"
  }
}
```

``` html
<!-- file: index.html -->

<!doctype html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Hello World</title>
  </head>
  <body>
    <div id="app">
      Loading....
    </div>
    <!-- JS -->
    <script type="text/javascript" src="./js/app.js"></script>
  </body>
</html>
```

- (optional)[bb.edn](https://netpyoung.github.io/study.clojure/tool/babashka.html#bbedn)

``` edn
;; optional
;; file: bb.edn

{:tasks
 {
  hello {:doc "helloworld"
         :requires ([babashka.process :as process])
         :task 
         #_(process/shell "clj -M -m app.core --interactive")
         (process/shell "clj -M -m app.core")}
 }}
```

## example

``` bash
npx shadow-cljs -d cider/cider-nrepl:0.44.0 watch :app

shadow-cljs - config: /Users/pyoung/test-cljs/shadow-cljs.edn
shadow-cljs - starting via "clojure"
shadow-cljs - HTTP server available at http://localhost:8080
shadow-cljs - server version: 2.26.2 running at http://localhost:9630
shadow-cljs - nREPL server started on port 49953
shadow-cljs - watching build :app
```

## mirror

- <https://github.com/thheller/shadow-cljs/issues/364#issuecomment-413162855>

``` edn
:repositories
{
  {"google" {:url "https://maven-central-asia.storage-download.googleapis.com/repos/central/data/"}}
}
```

## compile option

- <https://shadow-cljs.github.io/docs/UsersGuide.html#compiler-options>