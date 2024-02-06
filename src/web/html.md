# html


``` html
<!-- file: resources/public/index.html -->

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
    </script>
  </body>
</html>
```

``` clojure
;; file: src/app/core.cljs

(defn ^:export main []
  (->> "app"
    (js/document.getElementById)
    (render-app-element)))
```
