# re-frame
- `re-frame` is a pattern for writing SPAs in ClojureScript, using Reagent.
- https://github.com/Day8/re-frame
- [Reaktor Talks: Esko Lahti, Developing Web Applications With Reagent/re-frame](https://www.youtube.com/watch?v=-yQreNDzvdw)


https://opengisgal.wordpress.com/2018/05/19/re-frame-clojurescript-tutorial-for-beginners/




```
app-db ->
  view ->
    Reagent ->
      React ->
        Dom ->
          Render
        <- raise `event`
      <- queueing
    <- handle `event`, raise `effect`
  <- handle `effect`
app-db
```

```
app-db ->
  [:hiccup] ->
    `r/render-component` ->
      React ->
        Dom ->
          Render
        <- (`rf/dispatch` `event`)
      <- [`event` ...]
    <- (`rf/reg-event-fx` `event-id` [`interceptor` ...])
  <- (`rf/reg-fx` `effect-id`)

app-db
```


### db
``` clojure
{...}
```


### event
``` clojure
[event-id & args]
```

### Effect (`effect` / `fx`)
- what your event handler does to the world (aka side-effects)
- https://github.com/Day8/re-frame/blob/master/docs/API.md
- built-in effect

``` clojure
:db `db`

:dispatch `event`

:dispatch-n [(nil | `event`) ...]

:dispatch-later [ (nil | {:ms `int` :dispatch `event`}) ...]

:deregister-event-handler `event-id`

:deregister-event-handler [`event-id` ...]
```


### Coeffect (`coeffect` / `cofx`)
- http://tomasp.net/coeffects/
- the data your event handler requires from the world in order to do its computation (aka side-causes)
- built-in coeffect
``` clojure
{
 :local-store `local-store`
 :db `db` ;; `current-app-state`
}
```

### contextmap
``` clojure
{:coeffects {:event [:some-id :some-param]
             :db    <original contents of app-db>}

 :effects   {:db    <new value for app-db>
             :dispatch  [:an-event-id :param1]}

 :queue     <a collection of further interceptors>
 :stack     <a collection of interceptors already walked>}
```


==================================================
https://github.com/Day8/re-frame/blob/master/docs/EventHandlingInfographic.md


## raise
``` clojure
(rf/dispatch `event`)

(rf/dispatch-sync `event`)
```


## queueing
[event1 event2 event3 ...]


## event-routeter
``` clojure
(rf/reg-event-fx
  `event-key`
  (fn [`cofx` `event`]
    `effect`))
;;=> {...} => [[`effect-key` `effect-value`] ...]

(rf/reg-event-fx
  `event-key`
  [`interceptor` ...]
  (fn [`cofx` `event`]
    `effect`))
;;=> {...} => [[`effect-key` `effect-value`] ...]

(reg-event-db
  `event-key`
  (fn [`db` `event`]
    `new-db`))
;;=> {:db `new-db`} => [[:db `new-db`]]
```

(reg-event-db
  `event-key`
  (fn [{:key [db]} `event`]
    (assoc db :a 10)))

(reg-event-fx
  `event-key`
  (fn [{:key [db]} `event`]
    {:db (assoc db :a 10)}))


## effect-routeter
[[`effect-key` `effect-value`] ...]


## apply effect
- built-in : `:db`, `:dispatch`, ``
``` clojure
(rf/reg-fx
  `effect-key`
  (fn [`effect-value`]
     ...))
```


## interceptor
- built-in
  - `debug`
  - `trim-v`
- built-in factory
  - `enrich`
  - `path`
  - `after`
- built-in more : `std_interceptors.clj`

``` clojure
{:id      `cofx-id`
 :before  (fn [context] ...)
 :after   (fn [context] ...)}

(rf/inject-cofx `cofx-id` arg)

(rf/->interceptor
 :id     `cofx-id`
 :before handle-context
 :after  handle-context)

(reg-cofx
   `cofx-id`
   (fn [`cofx` arg]
      `cofx`))
```


==================================================

# reg-sub / subscribe
(let [value @(rf/subsribe `query-id`)])



(rf/reg-sub
  `query-id`
  (fn [db event] ; computation-fn
    value))

(rf/reg-sub
  `query-id`
  (fn [query-vec dynamic-vec]
    [(subscribe [:a-sub])
     (subscribe [:b-sub])])
  (fn [[a b] query-vec]
    ....))





`reg-fx`
`reg-cofx` / `inject-cofx`
