webpackJsonp([18],{"7WnU":function(e,t){},K31e:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o("i84Q"),s={data:function(){return{account:"",password:"",phone:"",code:"",show:!1,sendout:"发送验证码",disabled:!1,pw:""}},methods:{submit:function(){var e=this;Object(n.a)("/user/appLogin",{namePhone:this.account,password:this.password}).then(function(t){100==t.code&&(sessionStorage.token=t.object.token,sessionStorage.userId=t.object.user.userId,e.route("index"))})},register:function(){if(this.pw!=this.password)return alert("两次密码不正确"),!1;var e=this;Object(n.a)("/user/appRegist",{phone:this.phone,password:this.password,code:this.code}).then(function(t){100==t.code&&(e.show=!1)}).catch(function(e){console.log(e)})},send:function(){this.disabled=!0;var e=60,t=this;if(""==this.phone)return alert("请填写手机号"),this.disabled=!1,this.sendout="发送验证码",!1;Object(n.a)("/user/appSend",{userPhone:this.phone}).then(function(o){var n=setInterval(function(){0==e?(clearInterval(n),t.disabled=!1,t.sendout="重新发送"):t.sendout=--e},1e3)}).catch(function(e){alert("服务错误")})}}},a={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[e.show?o("div",[o("input",{directives:[{name:"model",rawName:"v-model",value:e.phone,expression:"phone"}],attrs:{type:"text",placeholder:"请输入手机号"},domProps:{value:e.phone},on:{input:function(t){t.target.composing||(e.phone=t.target.value)}}}),e._v(" "),o("button",{attrs:{disabled:e.disabled},on:{click:e.send}},[e._v(e._s(e.sendout))]),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.code,expression:"code"}],attrs:{type:"text",placeholder:"验证码"},domProps:{value:e.code},on:{input:function(t){t.target.composing||(e.code=t.target.value)}}}),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.pw,expression:"pw"}],attrs:{type:"password",placeholder:"密码"},domProps:{value:e.pw},on:{input:function(t){t.target.composing||(e.pw=t.target.value)}}}),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.password,expression:"password"}],attrs:{type:"password",placeholder:"确认密码"},domProps:{value:e.password},on:{input:function(t){t.target.composing||(e.password=t.target.value)}}})]):o("div",[o("input",{directives:[{name:"model",rawName:"v-model",value:e.account,expression:"account"}],attrs:{type:"text",placeholder:"请输入账号"},domProps:{value:e.account},on:{input:function(t){t.target.composing||(e.account=t.target.value)}}}),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.password,expression:"password"}],attrs:{type:"password",placeholder:"请输入密码"},domProps:{value:e.password},on:{input:function(t){t.target.composing||(e.password=t.target.value)}}})]),e._v(" "),e.show?o("div",[o("button",{staticStyle:{width:"100%"},on:{click:e.register}},[e._v("注册")]),e._v(" "),e.show?o("button",{staticStyle:{width:"100%"},on:{click:function(t){e.show=!1}}},[e._v("返回")]):e._e()]):o("div",[o("button",{staticStyle:{width:"100%"},on:{click:e.submit}},[e._v("登陆")]),e._v(" "),o("button",{staticStyle:{width:"100%"},on:{click:function(t){e.show=!0}}},[e._v("注册")])])])},staticRenderFns:[]};var i=o("VU/8")(s,a,!1,function(e){o("7WnU")},null,null);t.default=i.exports}});
//# sourceMappingURL=18.0dfe9718124d68bbfad1.js.map