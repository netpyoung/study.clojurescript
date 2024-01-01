# node

Node.js® is an open-source, cross-platform JavaScript runtime environment.

- [홈페이지](https://nodejs.org/en)

- Javascript Engine
  - .js  -> V8 엔진 (컴파일) -> 기계 코드 (X86_64, MIPS, ARM 등) -> CPU에서 실행
  - 종류
    - V8 is the name of the JavaScript engine that powers Google Chrome
      - Lars Bak 창시
      - C++로 작성
      - Node.js에서 사용
    - Firefox has SpiderMonkey
    - Safari has JavaScriptCore (also called Nitro)

## ECMAScript

- ESModule
- CommonJS

## 패키지 매니저

### NPM

- npm(`N`ode `P`ackage `M`anager)
- npx(`N`ode `P`ackage `E`xecuter)
  - npx는 npm 버전 5.2.0부터 포함된 명령

#### package.json


### 기타

- pnpm
  - <https://pnpm.io/>
  - <https://pnpm.io/benchmarks>
- yarn
  - <https://classic.yarnpkg.com/en/>

``` txt
yarn에 실망한 개발자 Zoltan Kochan이 2017년 pnpm을 세상에 내놓습니다.
 pnpm은 yarn과 npm에 비해 빠릅니다. 그 이유는 패키지를 복사해서 사용하는 대신에 hard link를 사용하기 때문이죠. 또한 공간도 덜 차지합니다. 패키지의 버전 하나는 한 개의 복사본만 존재합니다. 그리고 그 파일의 링크를 node_modules 폴더에 추가합니다.

ref: https://medium.com/zigbang/패키지-매니저-그것이-궁금하다-5bacc65fb05d
```

## Ref



[NPM Package - 생활코딩](https://www.youtube.com/playlist?list=PLuHgQVnccGMB4krR04ug5nEXJ3sAEOWDL)
