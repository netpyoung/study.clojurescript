# reagent

https://github.com/reagent-project/reagent

- cljsjs/react
- cljsjs/react-dom

``` clojure
(defn hello-component [name]
  [:p "Hello, " name "!"])

(defn say-hello []
  [hello-component "world"])
```

``` javascript
class Hello extends React.Component {
  render() {
    return <div>Hello {this.props.name}</div>;
  }
}

class SayHello extends React.Component {
  render() {
    return <Hello name"world" />;
  }
}
```

- 특성	props	state
  - parent 컴포넌트에 의해 값이 변경 될 수 있는가?	예	아니오
  - 컴포넌트 내부에서 변경 될 수 있는가?	아니오	예
  - ref: https://velopert.com/921


- `reagent.core/atom`
  - deref
- `reagent.core/create-class`
  - If you don’t use ES6 classes, you may use the create-react-class module instead. See Using React without ES6 for more information.


``` clojure
(r/create-class
 {:component-did-mout ...
  :reagent-render ...})
```

## Element

- https://reagent-project.github.io/news/news050.html

- `reagent.core/create-element`
  - <https://reactjs.org/docs/react-api.html#createelement>
- `reagent.core/as-element`
  - turns Reagent’s hiccup forms into React elements,

``` clojure
   (r/create-element "div"
                     #js{:className "foo"}
                     "Hello "
                     (r/as-element [:strong "world"]))
```

- `r/adapt-react-class`

``` clojure
(def div-adapter (r/adapt-react-class "div"))

(defn adapted []
  [div-adapter {:class "foo"}
   "Hello " [:strong "world"]])
```


- <https://reagent-project.github.io/news/news060-alpha.html>
- `reagent.core/track`
- rswap!
- `getInitialState`
- `componentWillMount`
- `reagent/dom-node`

``` clojure
this (r/current-component)
(r/props this) ;; this.props
(r/children this) ;; this.props.children
```

- r/next-tick
- r/wrap
  - The new wrap function combines two parts – a simple value, and a callback for when that value should change – into one value, that happens to look as an atom.
  - The first argument to wrap should be the value that will be returned by @the-result.
  - The second argument should be a function, that will be passed the new value whenever the result is changed (with optional extra arguments to wrap prepended, as with partial).

- `reagent.core/cursor`
  - <https://reagent-project.github.io/news/news050.html>

- Values and references
  - So what’s the difference between wraps and cursors? Why have both?
  - A wrap is just a value that happens to look like an atom. It doesn’t change unless you tell it to. It is a very lightweight combination of value and a callback to back-propagate changes to the value. It relies only on Reagent’s equality test in :should-component-update to avoid unnecessary re-rendering.
  - A cursor, on the other hand, will always be up-to-date with the value of the source atom. In other words, it acts a reference to part of the value of the source. Components that deref cursors are re-rendered automatically, in exactly the same way as if they deref a normal Reagent atom (unnecessary re-rendering is avoided by checking if the cursor's value has changed using identical?).

## `:ref`

- <https://github.com/reagent-project/reagent/blob/master/doc/FAQ/UsingRefs.md>

## `:<>`

- react fragment
  - <https://reactjs.org/docs/fragments.html>
  - [Add :<> shorthand for React Fragments (#352)](https://github.com/reagent-project/reagent/blob/master/CHANGELOG.md#080-rc1-2018-04-11)
  - <https://stackoverflow.com/questions/64181996/what-is-in-reagent-hiccup>
