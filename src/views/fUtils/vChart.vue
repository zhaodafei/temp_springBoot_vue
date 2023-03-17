<template>
  <div>
    <h3>图表--需要在main.js中引入</h3>
    <div>
      <LineChart :data="fData"/>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import LineChart from './components/LineChart'

const fData = ref({
  xAxisData: ['7日', '1日', '2日', '3日', '4日', '5日', '6日'],
  seriesData0: [650, 330, 224, 218, 135, 120, 60],
  seriesData1: [340, 300, 300, 300, 58, 300, 100]
})

</script>

<style scoped>

</style>
