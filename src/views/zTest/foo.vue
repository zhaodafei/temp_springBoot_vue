<template>
  <div>foo</div>
</template>

<script setup>
import {ref, reactive, onMounted, getCurrentInstance} from "vue";

const {proxy} = getCurrentInstance();
const foo1 = () => {
  proxy.$message.warning('不要重复点击')
}

</script>

<style scoped>

</style>
