# Boot
- 프로젝트 관리 프로그램이 있어야겠다. [boot](http://boot-clj.com/)를 사용하자.
- 새 프로젝트 생성은 [boot-new](https://github.com/boot-clj/boot-new)를 이용하자.

        boot -d boot/new new -t app -n sample


## [Fileset](https://github.com/boot-clj/boot/wiki/Filesets)

TODO(pyoung)
boot fileset설명.
`:resource-paths` : `.jar`에 포함되는 파일.

## Task

### (serve)
fileset을 기본으로하는 http 서버

    	(serve :port 8080 :httpkit true)

### (cljs)

* [cljs.edn](https://github.com/boot-clj/boot-cljs/blob/master/docs/cljs.edn.md)
* [compiler-options](https://github.com/boot-clj/boot-cljs/blob/master/docs/compiler-options.md)
* `(cljs)`는 fileset(`:resource-paths`, `:source-paths`포함)에 있는 `*.cljs.edn`을 찾아 `*.js`를 생성한다.
* `:ids`
  - `:ids`는 boot.core/by-path를 사용한다. boot.core/by-path는 path인식시 윈도우즈(\\), xnix(/)를 다르게 인식한다.
  - ref: <https://github.com/boot-clj/boot-cljs/pull/118>

