# tailwindcss

- tailwindcss: 순풍

- [홈페이지](https://tailwindcss.com/)
  - <https://tailwindcss.com/docs/content-configuration>
  - <https://tailwindcss.com/docs/configuration>

``` bash
npx tailwindcss init
```

## tailwind.config.js

``` json
;; file: package.json
{
  "name": "app",
  "version": "0.0.1",
  "scripts": {
    "start": "npx shadow-cljs -d cider/cider-nrepl:0.44.0 watch :app",
    "styles-watch": "npx tailwindcss -i src/styles/styles.css -o resources/public/css/styles.css --watch",
    "styles": "npx tailwindcss -i src/styles/styles.css -o resources/public/css/styles.css",
    "build": "npx shadow-cljs release :app && npm run styles",
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "devDependencies": {
    "shadow-cljs": "2.26.2"
  },
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "tailwindcss": "^3.4.0"
  }
}
```

- jit
  - <https://github.com/teknql/shadow-cljs-tailwind-jit>
  - <https://v2.tailwindcss.com/docs/just-in-time-mode?source=post_page-----82add3fe1fd--------------------------------#enabling-jit-mode>
- ref
  - <https://github.com/jacekschae/shadow-cljs-tailwindcss>
  - <https://velog.io/@teo/adorablecss-better-then-tailwindcss>
