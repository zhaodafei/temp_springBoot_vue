<template>
  <!-- 父组件-->
  <Bar />
</template>

<script setup>
  import Bar from './bar.vue'
  import {provide} from "vue";
  provide('list',"可以隔代传给子组件")
</script>
