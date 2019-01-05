자꾸 까먹고, 자꾸 바뀐다. 기록하여 살펴보도록 하자.

# Boot
- 프로젝트 관리 프로그램이 있어야겠다. [boot](http://boot-clj.com/)를 사용하자.
- 새 프로젝트 생성은 [boot-new](https://github.com/boot-clj/boot-new)를 이용하자.

        boot -d boot/new new -t app -n sample


# [Fileset](https://github.com/boot-clj/boot/wiki/Filesets)
TODO(pyoung)
boot fileset설명.
`:resource-paths` : `.jar`에 포함되는 파일.

# Task

## (serve)
fileset을 기본으로하는 http 서버

    	(serve :port 8080 :httpkit true)

## (cljs)
* https://github.com/boot-clj/boot-cljs/blob/master/docs/cljs.edn.md
* https://github.com/boot-clj/boot-cljs/blob/master/docs/compiler-options.md
* `(cljs)`는 fileset(`:resource-paths`, `:source-paths`포함)에 있는 `*.cljs.edn`을 찾아 `*.js`를 생성한다.
* `:ids`
  - `:ids`는 boot.core/by-path를 사용한다. boot.core/by-path는 path인식시 윈도우즈(\\), xnix(/)를 다르게 인식한다.
  - ref: https://github.com/boot-clj/boot-cljs/pull/118



# Closure 컴파일러의 이해.
* ref: https://clojurescript.org/about/closure

 clojurescript는 [google의 closure 도구](https://developers.google.com/closure/)를 이용하여 clojurescript파일을 javascript파일로 만든다.
 이 도구는, `closure compiler`와 `closure library` 등을 포함하고 있으며,  clojurescript의 코어는 `closure library`기능을 이용하여 만들어져있다.
 따라서 closure의 `addDependency`, `provide`, `require`를 이용하여, 컴파일시 어떤게 포함될지에 대한 의존성 관리를 할 수 있게 되었다.
 `closure compiler`를 이용하여, 공백제거, 코멘트제거 및 이름축약 등, 최적화된 javascript파일을 얻을 수 있다.


# ClojureScript 컴파일 옵션.
* ref: https://clojurescript.org/reference/compiler-options

## 주의점.
* `:optimizations :none`시 `goog/base.js`을 추가시켜줘야 한다.
  - https://stackoverflow.com/questions/26253958/understanding-relationship-between-clojurescript-and-google-closure-compiler



# Ref.
* https://adambard.com/blog/clojurescript-boot-fireplace/
* https://lambdaisland.com/episodes/javascript-libraries-clojurescript
* http://upgradingdave.com/blog/pages/006_boot_cljs.html
* https://github.com/magomimmo/modern-cljs
* https://github.com/binaryage/cljs-oops
