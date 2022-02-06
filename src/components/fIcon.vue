<template>
  <el-icon :size="size">
    <component :is="icon"/>
  </el-icon>
</template>

<script setup>
defineProps({
  size: {
    type: String,
    default: "default" // large / default /small
  },
  icon: { // icon具体名字
    type: String,
    default: "ArrowDown"
  }
})
</script>

<style scoped>

</style>
