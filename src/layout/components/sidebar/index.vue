<template>
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-scrollbar data-title="使用框架的滚动条样式,但是要自己设置内容区域高度">
      <el-menu :default-openeds="['1']">
        <el-menu-item index="1">
          <el-icon><HomeFilled /></el-icon>
          <span><router-link to="/">首页</router-link></span>
        </el-menu-item>

        <el-sub-menu index="2">
          <template #title>
            <el-icon><Goods /></el-icon>商品管理
          </template>
          <el-menu-item index="4000">
            <el-icon><Goods /></el-icon>
            <span><router-link to="/goods-index">商品列表</router-link></span>
          </el-menu-item>
          <el-menu-item index="4002">
            <el-icon><Discount /></el-icon>
            <span><router-link to="/bill-list">月度结算</router-link></span>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="3">
          <template #title>
            <el-icon><Setting /></el-icon>系统管理
          </template>
          <el-menu-item index="3000">
            <el-icon><user /></el-icon>
            <span>人员管理</span>
          </el-menu-item>
          <el-menu-item index="3002">
            <el-icon><Document /></el-icon>
            <span><router-link to="/dict-list">字典管理</router-link></span>
          </el-menu-item>
          <el-menu-item index="3003">
            <el-icon><Edit /></el-icon>
            <span>日志管理</span>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="500000">
          <template #title>
            <el-icon><Help /></el-icon>其他
          </template>
          <el-menu-item index="5000011">
            <el-icon><Help /></el-icon>
            <span>
             <router-link to="/file">文件处理</router-link>
            </span>
          </el-menu-item>
          <el-menu-item index="5000001">
            <el-icon><Help /></el-icon>
            <span>
             <router-link to="/foo">其他foo</router-link>
            </span>
          </el-menu-item>
          <el-menu-item index="500002">
            <el-icon><Help /></el-icon>
            <span><router-link to="/bar">其他bar</router-link></span>
          </el-menu-item>
          <el-menu-item index="500003">
            <el-icon><Help /></el-icon>
            <span><router-link to="/about">其他about</router-link></span>
          </el-menu-item>
          <el-menu-item index="500004">
            <el-icon><Help /></el-icon>
            <span><router-link to="/fei_h">其他_滚动条</router-link></span>
          </el-menu-item>
          <el-menu-item index="500004">
            <el-icon><Help /></el-icon>
            <span><router-link to="/fei_chart">聊天消息</router-link></span>
          </el-menu-item>
        </el-sub-menu>

      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<script setup>

</script>

<style lang="scss">

</style>
