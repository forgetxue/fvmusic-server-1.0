(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-dff1f740"],{"14c3":function(t,e,n){var r=n("c6b6"),o=n("9263");t.exports=function(t,e){var n=t.exec;if("function"===typeof n){var a=n.call(t,e);if("object"!==typeof a)throw TypeError("RegExp exec method returned something other than an Object or null");return a}if("RegExp"!==r(t))throw TypeError("RegExp#exec called on incompatible receiver");return o.call(t,e)}},"4c8a":function(t,e,n){"use strict";var r=n("cf8e"),o=n.n(r);o.a},"4d63":function(t,e,n){var r=n("83ab"),o=n("da84"),a=n("94ca"),i=n("7156"),c=n("9bf2").f,s=n("241c").f,l=n("44e7"),u=n("ad6d"),f=n("9f7f"),d=n("6eeb"),g=n("d039"),p=n("69f3").set,m=n("2626"),v=n("b622"),h=v("match"),x=o.RegExp,E=x.prototype,b=/a/g,y=/a/g,$=new x(b)!==b,w=f.UNSUPPORTED_Y,R=r&&a("RegExp",!$||w||g((function(){return y[h]=!1,x(b)!=b||x(y)==y||"/a/i"!=x(b,"i")})));if(R){var I=function(t,e){var n,r=this instanceof I,o=l(t),a=void 0===e;if(!r&&o&&t.constructor===I&&a)return t;$?o&&!a&&(t=t.source):t instanceof I&&(a&&(e=u.call(t)),t=t.source),w&&(n=!!e&&e.indexOf("y")>-1,n&&(e=e.replace(/y/g,"")));var c=i($?new x(t,e):x(t,e),r?this:E,I);return w&&n&&p(c,{sticky:n}),c},_=function(t){t in I||c(I,t,{configurable:!0,get:function(){return x[t]},set:function(e){x[t]=e}})},S=s(x),A=0;while(S.length>A)_(S[A++]);E.constructor=I,I.prototype=E,d(o,"RegExp",I)}m("RegExp")},5319:function(t,e,n){"use strict";var r=n("d784"),o=n("825a"),a=n("7b0b"),i=n("50c4"),c=n("a691"),s=n("1d80"),l=n("8aa5"),u=n("14c3"),f=Math.max,d=Math.min,g=Math.floor,p=/\$([$&'`]|\d\d?|<[^>]*>)/g,m=/\$([$&'`]|\d\d?)/g,v=function(t){return void 0===t?t:String(t)};r("replace",2,(function(t,e,n,r){var h=r.REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE,x=r.REPLACE_KEEPS_$0,E=h?"$":"$0";return[function(n,r){var o=s(this),a=void 0==n?void 0:n[t];return void 0!==a?a.call(n,o,r):e.call(String(o),n,r)},function(t,r){if(!h&&x||"string"===typeof r&&-1===r.indexOf(E)){var a=n(e,t,this,r);if(a.done)return a.value}var s=o(t),g=String(this),p="function"===typeof r;p||(r=String(r));var m=s.global;if(m){var y=s.unicode;s.lastIndex=0}var $=[];while(1){var w=u(s,g);if(null===w)break;if($.push(w),!m)break;var R=String(w[0]);""===R&&(s.lastIndex=l(g,i(s.lastIndex),y))}for(var I="",_=0,S=0;S<$.length;S++){w=$[S];for(var A=String(w[0]),F=f(d(c(w.index),g.length),0),B=[],P=1;P<w.length;P++)B.push(v(w[P]));var U=w.groups;if(p){var T=[A].concat(B,F,g);void 0!==U&&T.push(U);var k=String(r.apply(void 0,T))}else k=b(A,g,F,B,U,r);F>=_&&(I+=g.slice(_,F)+k,_=F+A.length)}return I+g.slice(_)}];function b(t,n,r,o,i,c){var s=r+t.length,l=o.length,u=m;return void 0!==i&&(i=a(i),u=p),e.call(c,u,(function(e,a){var c;switch(a.charAt(0)){case"$":return"$";case"&":return t;case"`":return n.slice(0,r);case"'":return n.slice(s);case"<":c=i[a.slice(1,-1)];break;default:var u=+a;if(0===u)return e;if(u>l){var f=g(u/10);return 0===f?e:f<=l?void 0===o[f-1]?a.charAt(1):o[f-1]+a.charAt(1):e}c=o[u-1]}return void 0===c?"":c}))}}))},"61f7":function(t,e,n){"use strict";n.d(e,"a",(function(){return r})),n.d(e,"c",(function(){return o})),n.d(e,"d",(function(){return a})),n.d(e,"b",(function(){return i}));n("4d63"),n("ac1f"),n("25f0"),n("5319");function r(t){for(var e=new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&;—|{ }【】‘；：”“'。，、？]"),n="",r=0;r<t.length;r++)n+=t.substr(r,1).replace(e,"");return n}function o(t){var e=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;return!e.test(t)}function a(t){var e=/^(?!\D+$)(?![^a-zA-Z]+$)\S{6,20}$/;return!e.test(t)}function i(t){var e=/^[A-Za-z0-9]{6}$/;return!e.test(t)}},6547:function(t,e,n){var r=n("a691"),o=n("1d80"),a=function(t){return function(e,n){var a,i,c=String(o(e)),s=r(n),l=c.length;return s<0||s>=l?t?"":void 0:(a=c.charCodeAt(s),a<55296||a>56319||s+1===l||(i=c.charCodeAt(s+1))<56320||i>57343?t?c.charAt(s):a:t?c.slice(s,s+2):i-56320+(a-55296<<10)+65536)}};t.exports={codeAt:a(!1),charAt:a(!0)}},7156:function(t,e,n){var r=n("861d"),o=n("d2bb");t.exports=function(t,e,n){var a,i;return o&&"function"==typeof(a=e.constructor)&&a!==n&&r(i=a.prototype)&&i!==n.prototype&&o(t,i),t}},"8aa5":function(t,e,n){"use strict";var r=n("6547").charAt;t.exports=function(t,e,n){return e+(n?r(t,e).length:1)}},9263:function(t,e,n){"use strict";var r=n("ad6d"),o=n("9f7f"),a=RegExp.prototype.exec,i=String.prototype.replace,c=a,s=function(){var t=/a/,e=/b*/g;return a.call(t,"a"),a.call(e,"a"),0!==t.lastIndex||0!==e.lastIndex}(),l=o.UNSUPPORTED_Y||o.BROKEN_CARET,u=void 0!==/()??/.exec("")[1],f=s||u||l;f&&(c=function(t){var e,n,o,c,f=this,d=l&&f.sticky,g=r.call(f),p=f.source,m=0,v=t;return d&&(g=g.replace("y",""),-1===g.indexOf("g")&&(g+="g"),v=String(t).slice(f.lastIndex),f.lastIndex>0&&(!f.multiline||f.multiline&&"\n"!==t[f.lastIndex-1])&&(p="(?: "+p+")",v=" "+v,m++),n=new RegExp("^(?:"+p+")",g)),u&&(n=new RegExp("^"+p+"$(?!\\s)",g)),s&&(e=f.lastIndex),o=a.call(d?n:f,v),d?o?(o.input=o.input.slice(m),o[0]=o[0].slice(m),o.index=f.lastIndex,f.lastIndex+=o[0].length):f.lastIndex=0:s&&o&&(f.lastIndex=f.global?o.index+o[0].length:e),u&&o&&o.length>1&&i.call(o[0],n,(function(){for(c=1;c<arguments.length-2;c++)void 0===arguments[c]&&(o[c]=void 0)})),o}),t.exports=c},"9f7f":function(t,e,n){"use strict";var r=n("d039");function o(t,e){return RegExp(t,e)}e.UNSUPPORTED_Y=r((function(){var t=o("a","y");return t.lastIndex=2,null!=t.exec("abcd")})),e.BROKEN_CARET=r((function(){var t=o("^r","gy");return t.lastIndex=2,null!=t.exec("str")}))},ac1f:function(t,e,n){"use strict";var r=n("23e7"),o=n("9263");r({target:"RegExp",proto:!0,forced:/./.exec!==o},{exec:o})},cf8e:function(t,e,n){},d784:function(t,e,n){"use strict";n("ac1f");var r=n("6eeb"),o=n("d039"),a=n("b622"),i=n("9263"),c=n("9112"),s=a("species"),l=!o((function(){var t=/./;return t.exec=function(){var t=[];return t.groups={a:"7"},t},"7"!=="".replace(t,"$<a>")})),u=function(){return"$0"==="a".replace(/./,"$0")}(),f=a("replace"),d=function(){return!!/./[f]&&""===/./[f]("a","$0")}(),g=!o((function(){var t=/(?:)/,e=t.exec;t.exec=function(){return e.apply(this,arguments)};var n="ab".split(t);return 2!==n.length||"a"!==n[0]||"b"!==n[1]}));t.exports=function(t,e,n,f){var p=a(t),m=!o((function(){var e={};return e[p]=function(){return 7},7!=""[t](e)})),v=m&&!o((function(){var e=!1,n=/a/;return"split"===t&&(n={},n.constructor={},n.constructor[s]=function(){return n},n.flags="",n[p]=/./[p]),n.exec=function(){return e=!0,null},n[p](""),!e}));if(!m||!v||"replace"===t&&(!l||!u||d)||"split"===t&&!g){var h=/./[p],x=n(p,""[t],(function(t,e,n,r,o){return e.exec===i?m&&!o?{done:!0,value:h.call(e,n,r)}:{done:!0,value:t.call(n,e,r)}:{done:!1}}),{REPLACE_KEEPS_$0:u,REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE:d}),E=x[0],b=x[1];r(String.prototype,t,E),r(RegExp.prototype,p,2==e?function(t,e){return b.call(t,this,e)}:function(t){return b.call(t,this)})}f&&c(RegExp.prototype[p],"sham",!0)}},d80d:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"form_box",attrs:{"element-loading-text":"登陆中...."}},[n("el-form",{ref:"loginForm",attrs:{model:t.loginForm,rules:t.rules,"label-height":"110","label-width":"100"}},[n("el-form-item",{staticClass:"email_input",attrs:{prop:"email"}},[n("label",{attrs:{for:"email"}},[t._v("邮箱")]),n("el-input",{attrs:{id:"email","auto-complete":"off",maxlength:"20"},model:{value:t.loginForm.email,callback:function(e){t.$set(t.loginForm,"email",e)},expression:"loginForm.email"}})],1),n("el-form-item",{attrs:{prop:"code"}},[n("label",{attrs:{for:"code"}},[t._v("验证码")]),n("el-input",{attrs:{id:"code","auto-complete":"off",maxlength:"6"},model:{value:t.loginForm.code,callback:function(e){t.$set(t.loginForm,"code",e)},expression:"loginForm.code"}})],1)],1),n("el-button",{staticStyle:{width:"275px",margin:"10px auto"},attrs:{type:"success",round:"",disabled:t.codeButton.status},on:{click:t.getEmailCode}},[t._v(t._s(t.codeButton.text))]),n("el-button",{staticStyle:{width:"275px",margin:"10px auto"},attrs:{type:"success",round:""},on:{click:t.handleLogin}},[t._v(" 登 录")])],1)},o=[],a=(n("ac1f"),n("5319"),n("61f7")),i=n("7d9b"),c={name:"EmailLogin",data:function(){var t=this,e=function(t,e,n){""===e?n(new Error("请输入邮箱")):Object(a["c"])(e)?n(new Error("邮箱格式有误")):n()},n=function(e,n,r){t.loginForm.code=t.loginForm.code.replace(" ",""),""===n?r(new Error("验证码不能为空")):Object(a["b"])(n)?r(new Error("验证码只能是六位的数字或字母")):r()};return{loading:!1,loginForm:{email:"forgetxue@163.com",code:""},rules:{email:[{validator:e,trigger:"blur"}],code:[{validator:n,trigger:"blur"}]},codeButton:{text:"获取验证码",status:!1},timer:null}},methods:{getEmailCode:function(){var t=this;""!==this.loginForm.email?Object(a["c"])(this.loginForm.email)?this.$message({message:"邮箱格式有误请重新输入",type:"warning"}):(this.codeButton.text="发送中",this.codeButton.status=!0,i["a"].commonUrlEncodingPost("/code",{email:this.loginForm.email}).then((function(e){200===e.status?(t.$message({message:"验证码已成功发送至您的邮箱",type:"success"}),t.countDown(60),t.codeButton.text="再次获取"):t.$message({message:e.data,type:"error"})})).catch((function(e){t.$message({message:e,type:"error"}),t.codeButton.text="再次获取",t.codeButton.status=!1}))):this.$message({message:"邮箱不能为空",type:"warning"})},countDown:function(t){var e=this;null!==this.timer&&clearInterval(this.timer);var n=t;this.timer=setInterval((function(){n--,0===n?(clearInterval(e.timer),e.codeButton.status=!1,e.codeButton.text="再次获取"):e.codeButton.text="".concat(n,"秒后重新获取")}),1e3)},handleLogin:function(){var t=this;this.loading=!0,this.codeButton.status=!1,this.codeButton.text="获取验证码",this.$refs.loginForm.validate((function(e){if(!e)return!1;i["a"].commonUrlEncodingPost("/loginByEmail",t.loginForm).then((function(e){if(200===e.status&&200===e.data.code){console.log(e),t.$store.commit("setToken",e.data.data.token);var n={email:t.loginForm.email,userId:e.data.data.userInfo.id};t.$store.commit("setUserLoginInfo",n),t.$store.commit("setUserInfo",e.data.data.userInfo),t.$router.push("/")}else t.$message({message:e.data.message,type:"waring"});t.loading=!1})).catch((function(e){t.$message({message:e,type:"error"})}))}))}}},s=c,l=(n("4c8a"),n("2877")),u=Object(l["a"])(s,r,o,!1,null,"6e4043e8",null);e["default"]=u.exports}}]);