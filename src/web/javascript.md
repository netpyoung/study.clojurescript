# javascript


## 신텍스 하이라이트

### highlight.js

- 예제
  - <https://highlightjs.org/static/demo/>
  - 깔쌈해보이는것
    - base16-framer
    - atom-one-dark
    - atom-one-light
    - monokia-sublime
    - base16-material-darker
- highlightBlock
  - Deprecated since version 11.0: Please use highlightElement() instead.


- <https://ask.clojure.org/index.php/9068/how-to-use-highlight-js-in-a-shadow-cljs-project>

``` clojure
(defn get-highlight-code [code]
  [:pre
    [:code {:dangerouslySetInnerHTML
             {:__html (.-value (js/hljs.highlight "clojure" code))}}]])
```

- dangerouslySetInnerHTML
  - <https://react.dev/reference/react-dom/components/common#common-props>


``` txt
npm/cljsjs로 설치할 수 있긴한데, npm/cljsjs버전은 용량이 큰거같아 사이트에서 다운받고 작은거로 가자

## npm
"highlight.js": "^11.9.0",

## deps.edn
cljsjs/highlight {:mvn/version "11.7.0-0"}
```

### codemirror

- <https://codemirror.net/>
- <https://github.com/nextjournal/clojure-mode>