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

- ~~Figwheel~~
  - 예전에는 Figwheel을 많이 사용했지만, 현재는 shadow-cljs를 많이 사용한다.
  - <https://figwheel.org/>
    - <https://github.com/bhauman/figwheel-main>
    - <https://github.com/bhauman/lein-figwheel>


- shadow-cljs lifecycle
  - https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html

``` json
:modules {:app {:init-fn example.app/init}}
```

## :dev{{-before/after}}-load{{-async}}

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

## shadow-cljs.edn

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
