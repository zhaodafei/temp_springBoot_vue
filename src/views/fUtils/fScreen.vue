<template>
  <div>
    <el-button @click="screenfull_click">所有内容全屏</el-button>
  </div>

  <div>
    <FullscreenPage>
      <div style="border: 2px solid blue">
        我是内容
      </div>
    </FullscreenPage>
  </div>
</template>

<script setup>
import {ref, reactive, onMounted, getCurrentInstance} from "vue";
import screenfull from 'screenfull' //引入screenfull

import FullscreenPage from './components/FullscreenPage.vue'


const {proxy} = getCurrentInstance();
const foo1 = () => {
  proxy.$message.warning('不要重复点击')
}

const fei = ref()
//保存是否全屏的状态
const isFullScreen = ref(false);
//当全屏按钮被点击
const screenfull_click  = () => {
  if (screenfull.isEnabled) {
    //切换全屏状态
    screenfull.toggle()
    isFullScreen.value = !screenfull.isFullscreen
  }
};
</script>

<style scoped>

</style>
