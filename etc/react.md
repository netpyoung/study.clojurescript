react개인적으로 정리. 자세한건 facebook공식 사이트에 있으니,  한눈에 필요한 것만 빠르게 윤곽을 잡을 수 있는게 필요!

## 1. 시작, 왜 React가 나왔는가?
facebook이  사이트를 개발하면서 데이터 변경과 ui변경을 동기화 시키는데 어려움을 격음.

### 1.1 어떻게 동기화를 시킬 것인가.
데이터를 변경할때마다 다시 새로 그리면 비용이 큼으로, 바뀌어야 하는 부분만 변경하고 싶음.브라우저는 DOM을 이용하여 표현하는데, DOM이 눈에 보이도록 렌더링될때에는 비용(시간)이 많이 듬.Virtual DOM이라고 눈에 보이지 않는 DOM을 만들어, 변경사항을 적용하고, 최소한에 변경사항을 눈에 보이도록 실제DOM에 반영하면 어떨까.

### 1.2 React는 어떠한 데이터 흐름을 가지며 어떻게 UI(dom)을 갱신하는가.
가상 Dom의 변경사항을 검사하여, 실제 Dom에 적용한다(변경에 필요한 최소한의 dom만 갱신)단방향 데이타 흐름.여기서 `Dom갱신`, `데이터 흐름`에 주의해야 한다 .dom갱신부터 보자

```
컴포넌트컴포넌트 생성 constructor
 ---- 렌더전 초기화  UNSAFE_componentWillMount
렌더링 render
렌더후 초기화 componentDidMount
```

### 갱신.

Reconciliation 작업은 Virtual DOM과 DOM을 비교해 DOM을 갱신하는 작업이다

### Dom Element
* Dom속성 : Camel-case
* 이벤트 헨들러 : Camel-case
* style 속성 : camelCase
```
<div className='a'>
<label htmlFor='name'>
```

### reconciliation
* https://d2.naver.com/helloworld/9297403
* https://www.robinwieruch.de/redux-mobx-confusion

```js
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}
```

```js
// constructor HelloMessage(name) { } 이런식으로 해야 나중에 햇갈리지 않지 이런 인터페이스는 에러!!class HelloMessage extends React.Component {  render() {    return <div>Hello {this.props.name}</div>;  }}ReactDOM.render(<HelloMessage name="Jane" />, mountNode);``````jsclass HelloMessage extends React.Component {  render() {    return React.createElement(      "div",      null,      "Hello ",      this.props.name    );  }}ReactDOM.render(React.createElement(HelloMessage, { name: "Jane" }), mountNode);```Props 속성- immutable- passed in from parent- can not change it- can be defaulted & validated## stateclass Clock extends React.Component {  constructor(props) {    super(props);    this.state = {date: new Date()};  }  render() {    return (      <div>        <h1>Hello, world!</h1>        <h2>It is {this.state.date.toLocaleTimeString()}.</h2>      </div>    );  }}내부 상태(state) 저장this.state.comment = "hello";  // Wrongthis.setState({comment: "hello"});  //Correctthis.state = // only constructorstate와 props 는 비동기적으로 업데이트 됨으로```js// Wrongthis.setState({counter:this.state.counter + this.props.increment});// Correctthis.setState((prevState,props)=>({counter:prevState.counter + props.increment}));```componentDidMount() { }componentWillUnmount() { }this.state = {posts: [],comments: []};componentDidMount() {fetchPosts().then(response=>{this.setState({posts: response.posts    });  });fetchComments().then(response=>{this.setState({comments: response.comments    });  });}this.hello 처럼 변수를 만들어 무언가를 저장할 수 있다(보여지는 파트가 아닌것)State 상태- private- maintained by component- mutableReactDOM.render() 로 렌더된 결과를 바꾼다.state: this.setState() - 새로운 값으로 변경 - component renderstate - lifecycle - https://facebook.github.io/react/docs/state-and-lifecycle.htmllifting-state-up : https://facebook.github.io/react/docs/lifting-state-up.htmlprops.children<FancyBorder color="blue"><h1 className="Dialog-title">Welcome</h1><p className="Dialog-message">Thank you for visiting our spacecraft!</p></FancyBorder>{props.left}<SplitPane left={ <Contacts /> } right={ <Chat /> } />결과적으로 이걸 이해https://facebook.github.io/react/docs/thinking-in-react.htmlstate와 props를 적절히 활용.- 좀 더 깔끔한 방법은 없을까?-- FilterableProductTable의 콜백을 계속 물고와서, SearchBar에서 input의 변경 콜백과 연결시켜주는 것## mixin예전에 있었던 기능으로 복잡함.https://facebook.github.io/react/blog/2016/07/13/mixins-considered-harmful.htmlhttps://en.wikipedia.org/wiki/MixinIn object-oriented programming languages, a mixin is a class that contains methods for use by other classes without having to be the parent class of those other classes자바스크립트 코드로 mixin배우려니 빡쳐서 찾아보니http://blog.saltfactory.net/ruby/understanding-mixin-using-with-ruby.htmljs에서 mixin가지고 노는것은 미친짓.fb에선 mixin이 암시적인 의존성을 가진다고 생각.이름 충돌, 복잡성증가## [HOC(Higher Order Component)](https://facebook.github.io/react/docs/higher-order-components.html)HOCs are not part of the React APIconst EnhancedComponent = higherOrderComponent(WrappedComponent);HOCs are common in third-party React libraries, such as Redux's connect and Relay's createContainer.python decorator랑 비슷하게 구현.## this.props.key : https://facebook.github.io/react/docs/lists-and-keys.htmllist의 자식엘리먼트(li)같은걸 코드로 생성할때 const todoItems = todos.map((todo) =><li key={todo.id}>{todo.text} </li>);이런식으로 uniqid를 쥐어준다.(유니크하지 않으면 나중에 렌더링시 순서가 꼬일 수 있다)ㅡㅡ## this.props.refIn the typical React dataflow, props are the only way that parent components interact with their children. To modify a child, you re-render it with new props. However, there are a few cases where you need to imperatively modify a child outside of the typical dataflow. The child to be modified could be an instance of a React component, or it could be a DOM element. For both of these cases, React provides an escape hatch.When to Use Refs#
	* Managing focus, text selection, or media playback.
	* Triggering imperative animations.
	* Integrating with third-party DOM libraries.

class CustomTextInput extends React.Component {constructor(props){super(props);this.focus=this.focus.bind(this);  }focus(){  this.textInput.focus();  }render(){  return(<div><inputtype="text"ref={(input) => { this.textInput = input; }} /><inputtype="button"value="Focus the text input"onClick={this.focus}/></div>);  }}# toolhttps://github.com/facebook/react-devtoolshttps://facebook.github.io/react/docs/perf.html



virtual dom
speed


props
internal component state

parent = data => child
* data flow one way from top to down
* immutable

PropTypes
static typechecking

SFC: Stateless Functional Component



# props 와 state

state는 독립적인 컴포넌트의 상태고, prop은 외부(부모) 컴포넌트에게 받은 속성이다

Redux 프로젝트에서 prop는 애플리케이션에서 전역으로 관리하는 상태라고 생각하면 된다.

요약하면 컴포넌트 자체의 상태(색상, 애니메이션 등)는 state로 처리하고, 전체적으로 관리해야 하는 상태(서버와 동기화하는 데이터 등)는 부모 컴포넌트에서 prop으로 받아 처리한다. 물론 prop을 갱신하면 App 컴포넌트가 전반적으로 갱신되기 때문에 state를 갱신하는 것보다는 비교적 느리다. 효과적으로 state를 사용하면 좋지만, prop으로 처리해야 하는 작업을 state로 처리하면 오히려 코드 작성이 더 어려워진다.
state와 prop의 차이에 관한 더 자세한 내용은 "props vs state"를 참고한다. React에서 구분하는 prop와 state의 차이에 관해서는 "Thinking in React"의 'Step 3: Identify The Minimal (but complete) Representation Of UI State'를 참고한다.





# Ref.
React 적용 가이드 - React와 Redux
https://d2.naver.com/helloworld/1848131


---------------------------------------------------------------

# redux
* Redux 프로젝트에서 prop는 애플리케이션에서 전역으로 관리하는 상태라고 생각하면 된다.
* https://github.com/reactjs/redux

## 핫 코드 리로드를 위해
flux store와는 달리, 로직과 상태를 분리

## 타임 트래블
action이 store로 전달시, 기존 상태 복사 후 복사본을 수정.
앱에서 사용하는 모든 state를 하나의 tree로 관리 - 하나의 트리라서 관리 문제 발생!



---------------------------------------------------------------

# flux
* https://facebook.github.io/flux/
* flux store
  1. 상태 변환을 위한 로직
  2. 현재 어플리케이션 상태
