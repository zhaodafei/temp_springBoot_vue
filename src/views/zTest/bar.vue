<template>
  <el-form ref="formRef" :model="form">
    <el-form-item label="商品名称" prop="goodsName">
      <el-input v-model="form.goodsName" placeholder="商品名称"></el-input>
    </el-form-item>

    <el-button plain @click="cancel">重置</el-button>
  </el-form>
</template>

<script setup>
import {reactive, ref} from "vue";

const formRef = ref() // 表单 ref 对象

const form = reactive({
  goodsName: '',
});

const cancel = () => {
  formRef.value.resetFields(); // 重置表单
}

</script>

<style scoped>

</style>
