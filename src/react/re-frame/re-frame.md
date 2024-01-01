# re-frame


([repo][github-re-frame] / [README](https://github.com/Day8/re-frame/blob/master/docs/README.md))
- [ClojureScript][clojurescript]로 작성된 [re-frame][github-re-frame]은 [SPA][wiki-spa]만들때 쓰는 패턴입니다.
- [re-frame][github-re-frame]은 내부적으로 [reagent][github-reagent]를 활용합니다.
- [Reaktor Talks: Esko Lahti, Developing Web Applications With Reagent/re-frame](https://www.youtube.com/watch?v=-yQreNDzvdw)


https://day8.github.io/re-frame/

[github-re-frame]: https://github.com/Day8/re-frame
[github-reagent]: https://github.com/reagent-project/reagent
[wiki-spa]: https://en.wikipedia.org/wiki/Single-page_application
[clojurescript]: https://clojurescript.org/

- 간락한 개념.

``` txt
DB ->
  뷰 ->
    Reagent ->
      React ->
        렌더링.
        <- 이벤트 발생.
      <- 이벤트 큐잉
    <- 이벤트 처리 (인터셉터와 함께), 이펙트 발생.
  <- 이펙터 처리.
DB
```

- 키워드로 보는 개념.

``` txt
app-db ->
  [:hiccup] ->
    (r/render-component component dom-root) ->
    (rf/subsribe query-id) ->
      React ->
        Dom ->
          Render
          <- (rf/dispatch event)
          <- [event ...]
        <- (rf/reg-event-fx event-id [interceptor ...] (fn [cofx event]))
          <- rf.cofx/inject-db :before
          <- (rf/->interceptor :before)
          <- (fn [cofx event] ...)
          <- (rf/->interceptor :after)
          <- rf.fx/do-fx :after
          <- [effect ...]
    <- (rf/reg-fx effect-id (fn [event-value] ...)
  <- (rf/reg-sub query-id (fn [db] ...))
  <- (rf/reg-sub query-id (fn [db] ...))
app-db
```

## 용어설명

### kind `reg-*`

``` clojure
register handler
:event-db
:event-fx
:event-ctx
:sub
:sub-raw
:fx
:cofx

drawing board
reg-view
reg-interceptor

(rf/reg-event-db
   :event-id
   (fn [db event]
     (assoc db :some :thing)))

(rf/reg
  {:kind  :event-db
   :id    :event-id
   :fn    (fn [db event]
            (assoc db :some :thing))})

(rf/reg
  [{:kind :event-db ...}
   {:kind :event-db ...}
   {:kind :event-fx ...}
   {:kind :sub ...])
```

### Registrar

Each entry stored in the registrar will become a map instead of just a handler.

```
Map keys:

:kind - somehow redundant
:id
:doc
:line
:ns
:doc
:fn the handler

`re-com` is a library which supplies reusable Reagent widgets. And widgets, like a datepicker, are the simplest kind of components.
`re-com` components are reusability because they take an input stream of data and they

`def-view`: TODO
```


### DB : (`app-db` / `db`)

``` clojure
{...}
```


### event

``` clojure
[event-id & args]
```

### Event (`event`)

### Effect (`effect` / `fx`)

- https://github.com/Day8/re-frame/blob/master/docs/API.md
- 내장 이펙트

``` clojure
[:db `db`]

[:dispatch `event`]

[:dispatch-n [(nil | `event`) ...]]

[:dispatch-later [ (nil | {:ms `int` :dispatch `event`}) ...]]

[:deregister-event-handler `event-id`]

[:deregister-event-handler [`event-id` ...]]
```


### Coeffect (`coeffect` / `cofx`)

- http://tomasp.net/coeffects/
- the data your event handler requires from the world in order to do its computation (aka side-causes)
- 내장 코-이펙트

``` clojure
{
 :local-store `local-store`
 :db `db` ;; `current-app-state`
}
```

### contextmap (`context`, `ctx`)

``` clojure
{:coeffects {:event [:some-id :some-param]
             :db    <original contents of app-db>}

 :effects   {:db    <new value for app-db>
             :dispatch  [:an-event-id :param1]}

 :queue     <a collection of further interceptors>
 :stack     <a collection of interceptors already walked>}
```

-----------------------

- <https://github.com/Day8/re-frame/blob/master/docs/EventHandlingInfographic.md>


## raise

``` clojure
(rf/dispatch `event`)

(rf/dispatch-sync `event`)
```


## queueing

``` clojure
[event1 event2 event3 ...]
```


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

``` clojure
(reg-event-db
  `event-key`
  (fn [{:key [db]} `event`]
    (assoc db :a 10)))

(reg-event-fx
  `event-key`
  (fn [{:key [db]} `event`]
    {:db (assoc db :a 10)}))

(reg-event-ctx
  `event-key`
  (fn [`context-map` `event`]
    `context-map`)
```

## effect-routeter

``` clojure
[[`effect-key` `effect-value`] ...]
```

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
  - `inject-cofx`
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


# reg-sub / subscribe

- <https://github.com/Day8/re-frame/blob/master/docs/SubscriptionFlow.md>
- `(ns re-frame.subs)`

``` clojure
{:name "Bruce"}

(rf/reg-sub
  :get-name
  (fn [db _]
    (name db))) => "Bruce"

(rf/reg-sub
  :greeting
  (fn [db _]
    (rf/subscribe :get-name))
  (fn [name _]
    (st "Hello, " name))) => "Hello, Bruce"

@(rf/subscribe :greeting) => "Hello, Bruce"

  1. (reg-sub
       :test-sub
       (fn [db [_]] db))

  ;; The value in app-db is passed to the computation function as the 1st argument.

  2. (reg-sub
       :a-b-sub
       (fn [q-vec d-vec]
         [(subs/subscribe [:a-sub])
          (subs/subscribe [:b-sub])])
       (fn [[a b] [_]] {:a a :b b}))

  ;;  Two functions provided. The 2nd is computation function, as before. The 1st
  ;;  is returns what `input signals` should be provided to the computation. The
  ;;  `input signals` function is called with two arguments: the query vector
  ;;  and the dynamic vector. The return value can be singleton reaction or
  ;;  a sequence of reactions.

  3. (reg-sub
       :a-b-sub
       :<- [:a-sub]
       :<- [:b-sub]
       (fn [[a b] [_]] {:a a :b b}))```
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
```

## Ref

- <https://docs.google.com/drawings/d/1ptKAIPfb_gtwwSqYmt-JGTkwPVm_6LeWjjm-FcWznBs/edit>
- <https://github.com/Day8/re-frame/blob/master/docs/FAQs/DoINeedReFrame.md>

- Router
  - <https://github.com/Day8/re-frame/blob/master/src/re_frame/router.cljc#L8-L60>
- Subscription & EventHandler
  - <https://github.com/Day8/re-frame/blob/master/docs/FAQs/UseASubscriptionInAnEventHandler.md>
- Logger
  - <https://github.com/Day8/re-frame/blob/master/docs/FAQs/Logging.md>
- on-click.event handle
  - <https://github.com/Day8/re-frame/blob/master/docs/FAQs/Null-Dispatched-Events.md>
- WIP - EventError Handling
  - <https://github.com/Day8/re-frame/issues/231#issuecomment-249991378>
- Custom event-handler define
  - <https://github.com/Day8/re-frame/blob/master/docs/FAQs/GlobalInterceptors.md>
