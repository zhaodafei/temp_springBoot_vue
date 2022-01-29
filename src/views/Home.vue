<template>
  <layoutHeader/>
  <div class="body">
    <!--<div></div>-->
    <layoutSidebar/>
    <div class="main-content">
      {{ foo }}
      <h1>下面开始路由嵌套了</h1>
      <div>

        <el-button>Default</el-button>
        <el-button type="primary">Primary</el-button>
        <el-button type="success">Success</el-button>
        <el-button type="info">Info</el-button>
        <el-button type="warning">Warning</el-button>
        <el-button type="danger">Danger</el-button>
        <el-button>中文</el-button>
      </div>
      <p>
        <router-link to="/foo">菜单1</router-link> |
        <router-link to="/bar">菜单2</router-link>
      </p>
      <router-view/>
    </div>
  </div>
  <layoutFooter/>
</template>

<script>
import {defineComponent} from "vue"
import layoutHeader from "@/layout/components/head/index"
import layoutSidebar from "@/layout/components/sidebar/index"
import layoutFooter from "@/layout/components/foot/index"

import {useRoute} from 'vue-router'
import {useStore} from 'vuex'

export default defineComponent({
  components: {
    layoutHeader,
    layoutSidebar,
    layoutFooter
  },
  setup() {
    const route = useRoute();
    const store = useStore();
    return {
      foo: "我是主题"
    }
  }
})
</script>

<style scoped lang="scss">

</style>
