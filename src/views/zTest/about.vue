<template>
  <!-- 父组件-->
  <p>  在 3.x 中，自定义组件上的 v-model 相当于传递了 modelValue prop 并接收抛出的 update:modelValue 事件： </p>
  <p>{{feiTitle}}</p>
  <p>{{feiTitle2}}</p>

  <Bar v-model="feiTitle"/>
  <Bar v-model:title="feiTitle2"/>
</template>

<script setup>
  import Bar from './bar.vue'
  import {ref} from "vue";

  const feiTitle = ref({fei:"初始值"});
  const feiTitle2 = ref({fei:"初始值"});
</script>
