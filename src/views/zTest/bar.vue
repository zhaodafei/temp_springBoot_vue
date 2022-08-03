<template>
  <p>子组件</p>
  <p>
    <button @click="upFei"> 点击更新父组件 v-model="pageTitle"</button>
  </p>
</template>
<script setup>
const props = defineProps({
  modelValue: {
    type: Object,
    default: () => {}
  },
  title: {
    type: Object,
    default: () => {}
  },
})
const emits = defineEmits(['update:modelValue','update:title'])
const upFei = () => {
  emits('update:modelValue', {xx1: "值foo", xx2: "值bar"});
  emits('update:title', {xx1: "值foo1", xx2: "值bar1"});
}
</script>
