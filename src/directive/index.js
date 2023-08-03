import repeatClick from './repeatClick'

export default function directive(app) {
  app.directive('reClick', repeatClick)

  //指令--1; demo: <input type="text" v-myFeiDirective>
  app.directive('myFeiDirective', {
    mounted: function (el, binging, vnode, oldVnode) {
      el.focus();
      el.style.borderColor = "red";
      el.value = "我是fei";
    }
  });

  //指令--2; demo: <input type="text" v-myFeiDirective2="{color:'blue',text:'图书_论语'}">
  app.directive('myFeiDirective2', {
    mounted: function (el, binging, vnode, oldVnode) {
      console.log(binging.value);
      el.focus();
      el.style.borderColor = binging.value.color;
      el.value = binging.value.text;
    }
  });
}
