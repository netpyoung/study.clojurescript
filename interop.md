(set! js/a 3)

(def arr (array 1 2 3))
;; #js [1 2 3]

(def obj (js-obj "x" 1 "y" 2))
;; #js {:x 1, :y 2}

(aget arr 1)
(aset obj "z" 3


If you do need to access object properties dynamically, then all ClojureScript core provides are aget and aset, so what other option do we have? If you look a bit further you’ll find that ClojureScript comes bundled with the Google Closure Library, which is a bit like the missing “standard library” for JavaScript. The goog.object namespace has a few functions specifically for dealing with object properties.

There’s goog.object/get and goog.object/set, corresponding with aset and aget, but also goog.object/add, which is like a safer version of set, in that it will only set a property if it’s not present yet, or otherwise throw an exception.


(js/document.body.lastChild.innerHTML.chatAt 7)

(js/Date "2016-05-19")
(js/Date. "2016-05-19")


(clj->js )
(js->clj )
:keywordize-keys true


https://lambdaisland.com/episodes/clojurescript-interop


(-> evt .-target .-value)
(.. evt -target -value)
