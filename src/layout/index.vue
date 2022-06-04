<template>
  <el-container class="layout-container-demo fei100h" data-title="fei100h 这个高度很重要">
    <Sidebar />

    <el-container>
      <el-header>
        <HeadNav />
      </el-header>

      <el-scrollbar data-title="使用框架的滚动条样式,但是要自己设置内容区域高度"> <!-- 有时候这里需要设置 height: 100%"-->
        <el-main>
          <div class="main-body">
            <router-view />
          </div>
        </el-main>
      </el-scrollbar>

      <Footer />

    </el-container>
  </el-container>
</template>

<script setup>
import Sidebar from './components/sidebar'
import HeadNav from './components/headNav'
import Footer from './components/foot'

</script>

<style lang="scss">
.layout-container-demo {
  .el-header {
    padding: 0;
  }

  .el-main {
    background-color: var(--main-bg-color);
    .main-body{
      padding: 0 10px;
    }
  }

}
</style>
