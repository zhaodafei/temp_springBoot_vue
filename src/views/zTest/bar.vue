<template>
  <!-- 子组件 -->
  <p>{{fei}}</p>
</template>
<script setup>
  import {inject} from "vue";
  const fei = inject("list")
</script>
