<template>
  <el-container
      class="layout-container-demo"
      style="height: 960px; min-height: 270px; border: 1px solid #eee"
  >
    <Sidebar />

    <el-container>
      <el-header>
        <HeadNav />
      </el-header>

      <el-main>
        <div class="main-body">
          <router-view />
        </div>
      </el-main>

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
    /* 90 = navbar + tags-view = 60 + 30 */
    min-height: calc(100vh - 90px);
    .main-body{
      padding: 0 10px;
    }
  }

}
</style>
