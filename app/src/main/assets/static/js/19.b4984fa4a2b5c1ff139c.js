webpackJsonp([19],{"66f8":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=s("i84Q"),n={data:function(){return{city:"省、市、区",check:!1,info:{}}},methods:{picker:function(){var t=new mui.PopPicker({layer:3});t.setData(cityData3);var e=this;t.show(function(t){e.city=t[0].text+" "+t[1].text+" "+t[2].text})},init:function(){var t=this;this.$store.state.isLoading=!0,Object(i.a)("/useraddress/getUserAddressByIdApi",{UserAddressID:this.$route.query.id}).then(function(e){t.$store.state.isLoading=!1,t.city=e.userAddress.province+" "+e.userAddress.city+" "+e.userAddress.district,t.info=e.userAddress,t.check=0!=e.userAddress.isDefaultAddress})},submit:function(){var t=this;this.$store.state.isLoading=!0;var e=this.city.split(" ");Object(i.a)("/useraddress/updUserAddressApi",{UserAddressID:this.$route.query.id,userId:sessionStorage.userId,consignee:this.info.consignee,contactWay:this.info.contactWay,province:e[0],city:e[1],district:e[2],contactAddress:this.info.contactAddress,isDefaultAddress:this.check?1:0}).then(function(e){t.$store.state.isLoading=!1,1==e.code?t.$router.goBack():alert(e.msg)})},deleted:function(t){var e=this;this.$store.state.isLoading=!0,Object(i.a)("/useraddress/delUserAddressApi",{UserAddressID:t}).then(function(t){e.$store.state.isLoading=!1,1==t.code?e.$router.goBack():alert(t.msg)})}},mounted:function(){this.init(),mui(".mui-input-row input").input()}},a={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("div",{staticClass:"header"},[i("div",{staticClass:"icon-left",on:{click:function(e){t.$router.goBack()}}},[i("img",{attrs:{src:s("U1zf")}})]),t._v("\n\t\t修改收货地址\n\t\t"),i("div",{staticClass:"gray",on:{click:t.submit}},[t._v("保存")])]),t._v(" "),i("div",{staticClass:"header-null"}),t._v(" "),i("div",{staticClass:"mui-input-group"},[i("div",{staticClass:"mui-input-row"},[i("label",[t._v("收货人")]),t._v(" "),i("input",{directives:[{name:"model",rawName:"v-model",value:t.info.consignee,expression:"info.consignee"}],staticClass:"mui-input-clear",attrs:{type:"text",name:"",id:"",value:""},domProps:{value:t.info.consignee},on:{input:function(e){e.target.composing||t.$set(t.info,"consignee",e.target.value)}}})]),t._v(" "),i("div",{staticClass:"mui-input-row"},[i("label",[t._v("联系电话")]),t._v(" "),i("input",{directives:[{name:"model",rawName:"v-model",value:t.info.contactWay,expression:"info.contactWay"}],staticClass:"mui-input-clear",attrs:{type:"number",name:"",id:"",value:""},domProps:{value:t.info.contactWay},on:{input:function(e){e.target.composing||t.$set(t.info,"contactWay",e.target.value)}}})]),t._v(" "),i("div",{ref:"cityPicker",staticClass:"mui-input-row",on:{click:t.picker}},[i("div",{staticClass:"mui-table-view-cell"},[i("a",{staticClass:"mui-navigate-right"},[t._v(t._s(t.city))])])]),t._v(" "),i("div",{staticClass:"mui-input-row"},[i("label",[t._v("街道")]),t._v(" "),i("input",{directives:[{name:"model",rawName:"v-model",value:t.info.contactAddress,expression:"info.contactAddress"}],staticClass:"mui-input-clear",attrs:{type:"text",name:"",id:"",value:""},domProps:{value:t.info.contactAddress},on:{input:function(e){e.target.composing||t.$set(t.info,"contactAddress",e.target.value)}}})])]),t._v(" "),i("div",{staticClass:"deleted-address",on:{click:function(e){t.deleted(t.info.UserAddressID)}}},[t._v("\n\t\t删除地址\n\t")]),t._v(" "),i("div",{staticClass:"address"},[i("div",{on:{click:function(e){t.check=!t.check}}},[t.check?i("img",{attrs:{src:s("p9Wu")}}):i("img",{attrs:{src:s("tF9D")}}),t._v("\n\t\t\t设为默认收货地址\n\t\t")])])])},staticRenderFns:[]};var c=s("VU/8")(n,a,!1,function(t){s("Q9OU")},"data-v-784726da",null);e.default=c.exports},Q9OU:function(t,e){}});
//# sourceMappingURL=19.b4984fa4a2b5c1ff139c.js.map