<template>
<div>
  <div style="display: flex">
    <el-select v-model="value"  placeholder="请选择"  style="width: 200px">
      <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      />
    </el-select>
    <div>
      <el-button @click="fei">选中_no</el-button>
      <el-button @click="fei2">选中_ok</el-button>
    </div>
  </div>

  <div>
    <el-input-number v-model="num" :min="1" :max="10" @input="handleChange" />
  </div>

  <div>
    <el-input v-model="input" placeholder="Please input" clearable style="width: 240px"  />
  </div>
</div>
</template>

<script setup>
import {ref, reactive, onMounted, getCurrentInstance} from "vue";

const {proxy} = getCurrentInstance();
// const value = ref('Option1')
const value = ref('')

const options = ref([
  {
    value: 'Option1',
    label: 'label_1',
  },
  {
    value: 'Option2',
    label: 'label_2',
  },
  {
    value: 'Option3',
    label: 'label_3',
  },
])

const fei = () => {
  isSel('111111111111')
}

const fei2 = () => {
  isSel('Option1')
}

const isSel = (defaultVal) => {
  let isDefaultSel = false
  options.value.forEach(item => {
    if (item.value === defaultVal) {
      console.log('找到了');
      isDefaultSel = true;
    }
  })

  console.log(value.value);

  if (isDefaultSel) {
    value.value = defaultVal
  }else{
    value.value = undefined
  }
}


const num = ref(1)
const handleChange = (value) => {
  console.log(value)
}

const input = ref('')
</script>

<style scoped>

</style>
