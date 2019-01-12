(ns sample.main
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]))



(defn main-component []
  [:div
   [:h1 "HelloWorld"]
   [:button {:on-click #(rf/dispatch [:event-test-args 1 2 3])}
    "test-args"]
   [:button {:on-click #(rf/dispatch [:event-test-intercept 1 2 3])}
    "test-intercept"]
   [:button {:on-click #(rf/dispatch [:event-test-effect 1 2 3])}
    "test-effect"]
   [:h1 @(rf/subscribe [:event-get-data 4])]])


(defn ^:export main []
  (enable-console-print!)
  (if-let [node (js/document.getElementById  "app")]
    (do (rf/dispatch-sync [:event-test-sync])
        (r/render-component [main-component] node))
    (js/alert "Fail to find `app`")))

(rf/reg-event-db
 :event-test-sync
 (fn [db [event-id]]
   (println event-id)
   (assoc db :state ::initiailzed)))

(rf/reg-event-db
 :event-test-args
 (fn [db [event-id a b c]]
   (println event-id [a b c])
   (assoc db :data {:a a :b b :c c})))

(def intercept-trim-event-id
  ;; context
  ;; {:coeffects {:event [:some-id :some-param]
  ;;              :db    <original contents of app-db>}
  ;;  :effects   {:db    <new value for app-db>
  ;;              :dispatch  [:an-event-id :param1]}
  ;;  :queue     <a collection of further interceptors>
  ;;  :stack     <a collection of interceptors already walked>}
  (re-frame.core/->interceptor
   :id
   :trim-event-id

   :before
   (fn [context]
     (println :trim-event-id context)
     (update-in context [:coeffects :event]
                (fn [event]
                  (-> event (rest) (vec)))))))

(rf/reg-event-db
 :event-test-intercept
 [intercept-trim-event-id]
 (fn [db [a b c]]
   (println :event-test-intercept [a b c])
   (assoc db :data {:a a :b b :c c})))

(rf/reg-event-fx
 :event-test-effect
 [intercept-trim-event-id]
 (fn [db [a b c]]
   (println :event-test-effect [a b c])
   {:effect-id [a b c]}))

(rf/reg-fx
 :effect-id
 (fn [a b c]
   (println 1 2 3)))

(rf/reg-sub
 :event-get-data
 (fn [db [event-id val]]
   (let [{:keys [a b c]} (:data db)]
     (println :event-get-data [a b c val] db)
     (str a b c val))))
