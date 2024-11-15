<template>
  <div style="border: 2px dotted #ff6b81" ref="fullscreen" :class="[isFullscreen ? 'fullscreen-container' : 'normal-container']">
    <div class="operation-container">
      <el-button  @click.stop="isFullscreen ? exitPage() : enterPage()">{{ isFullscreen ? '退出全屏' : '进入编辑' }}</el-button>
    </div>
    <div class="slot-container">
      <slot></slot>
    </div>
  </div>
</template>


<script setup>
import {ref} from 'vue';

const isFullscreen = ref(false);
const fullscreen = ref()

const enterPage = () => {
  isFullscreen.value = true;
}
const exitPage = () => {
  isFullscreen.value = false;
};
</script>

<style lang="scss" scoped>
.fullscreen-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 99999;
  padding: 10px 20px;
  background-color: #fff;
  overflow: auto;
}

.normal-container {
  height: 300px;
  margin: auto;
}

.operation-container {
  margin-bottom: 2px;
}

.slot-container {
  height: calc(100% - 80px);
}
</style>
