# fei1
Project setup

```html
npm install
npm run dev
```

### 环境说明

```wiki
v16.16.0
```

### 飞 --------------

```shell
#vite 搭建vue3
npm init @vitejs/app --template vue
或者
npm init vite-app <project-name>

cd xxx
npm install (or `yarn`)
npm run dev (or `yarn dev`)


### Vue3 手册
https://v3.cn.vuejs.org/guide/migration/introduction.html#快速开始


```

### 其他文档

```wiki
#element-plus
https://element-plus.gitee.io/zh-CN/component/button.html

#Ant Design Vue
https://next.antdv.com/docs/vue/introduce-cn
```

### 项目思路

首页:  消息通知
首页:  用户登录情况
首页:  快速菜单栏(UI平均方框现在)

### 警告

如果出现下面报错,就把`package.json`中 `"vue": "^3.0.0",` 改为 `"vue": "3.0.0",`

```html
ERROR  10:38:37 [vite] Internal server error: v-model cannot be used on a prop, because local prop bindings are not writable.
Use a v-bind binding combined with a v-on listener that emits update:x event instead.
  Plugin: vite:vue
  File: E:/home/fei/src/views/foo/Edit.vue
```

### 库说明

1. lodash 常见工具函数( 字符串,数组 )
2. crypto-js 常见加密工具库(尽量不用)
2. sm-crypto  常见加密工具库(优先考虑)
4. file-saver 下载文件流保存为文件
5. vite-plugin-static-copy 拷贝源文件到打包目录文件下
4. xxx
