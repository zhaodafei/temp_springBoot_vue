<template>
  <div>
    <h3>图表--需要在main.js中引入</h3>
    <div class="fei-char-body">
      <LineChart :data="fData"/>
    </div>

    <div class="fei-char-body">
      <PieChart :data="fPieData"/>
    </div>

    <div class="fei-char-body">
      <BarChart :data="fBarData" />
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import LineChart from './components/LineChart.vue'
import PieChart from './components/PieChart.vue'
import FunnelChart from './components/FunnelChart.vue'
import BarChart from './components/BarChart.vue'

const fData = ref({
  xAxisData: ['7日', '1日', '2日', '3日', '4日', '5日', '6日'],
  seriesData0: [650, 330, 224, 218, 135, 120, 60],
  seriesData1: [340, 300, 300, 300, 58, 300, 100]
})

const fPieData = ref({
  xAxisData: ['史记', '论语', '孟子', '汉书', '战国策'],
  seriesData0: [
    { value: 100, name: '史记' },
    { value: 200, name: '论语' },
    { value: 234, name: '孟子' },
    { value: 460, name: '汉书' },
    { value: 800, name: '战国策' }
  ]
})

const fFunnelData = ref({
  titleText: "图书报表",
  seriesData0: [
    {value: 60, name: '史记'},
    {value: 40, name: '论语'},
    {value: 20, name: '孟子'},
    {value: 80, name: '汉书'},
    {value: 95, name: '战国策'}
  ]
})

const fBarData = ref({
  xAxisData: ['7日', '1日', '2日', '3日', '4日', '5日', '6日'],
  seriesData0: [650, 330, 224, 218, 135, 120, 60],
  seriesData1: [340, 300, 300, 300, 58, 300, 100]
})

</script>

<style scoped lang="scss">
.fei-char-body{
  width: 500px;
  height: 500px;
}
</style>
