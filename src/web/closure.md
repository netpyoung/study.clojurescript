# Closure

- <https://clojurescript.org/about/closure>
  - clojurescript는 [google의 closure 도구](https://developers.google.com/closure/)를 이용하여 clojurescript파일을 javascript파일로 만듬.
  - 이 도구는, `closure compiler`와 `closure library` 등을 포함하고 있으며, clojurescript의 코어는 `closure library`기능을 이용하여 만들어져 있음.
  - 따라서 closure의 `addDependency`, `provide`, `require`를 이용하여, 컴파일시 어떤게 포함될지에 대한 의존성 관리를 할 수 있음.
  - `closure compiler`를 이용하여, 공백제거, 코멘트제거 및 이름축약 등, 최적화된 javascript파일을 얻을 수 있다.


- [ClojureScript 컴파일 옵션](https://clojurescript.org/reference/compiler-options)

## 주의점.

- `:optimizations :none`시 `goog/base.js`을 추가시켜줘야 한다.
  - <https://stackoverflow.com/questions/26253958/understanding-relationship-between-clojurescript-and-google-closure-compiler>

## Ref.

- <https://adambard.com/blog/clojurescript-boot-fireplace/>
- <https://lambdaisland.com/episodes/javascript-libraries-clojurescript>
- <http://upgradingdave.com/blog/pages/006_boot_cljs.html>
- <https://github.com/magomimmo/modern-cljs>
- <https://github.com/binaryage/cljs-oops>
