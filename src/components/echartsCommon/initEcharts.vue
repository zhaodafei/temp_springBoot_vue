<template>
  <div :id="id" ref="myChart" :style="{ height: height, width: width }"/>
</template>

<script setup>
import {getCurrentInstance, onMounted, ref} from "vue";
import chinaData from "@/components/echartsCommon/map/json/china.json";
import worldData from "@/components/echartsCommon/map/json/world.json";
const ECharts = require("echarts");

const app = getCurrentInstance().appContext.config.globalProperties;

const chart = ref(null);
const myChart = ref(null);
const defaultOptions = {
  title: {
    text: '暂无数据',
    left: 'center',
    top: '45%',
  },
  graphic: {
    type: 'text',
    left: 'center',
    top: '45%',
    style: {
      text: '暂无数据',
      // fill: '#ff68b1',
      fontSize: 20,
      // fontWeight: 700
    }
  },
  // color: ['#D8D8D8'],
  series: [
    {
      name: '空数据',
      type: 'pie',
      radius: ['65%', '70%'],
    }
  ]
}


defineProps({
  id: {
    type: String,
    default: 'myChart'
  },
  width: {
    type: String,
    default: '100%'
  },
  height: {
    type: String,
    default: '100%'
  },
  options: {
    type: Object,
    default: ()=>({})
  }
})

onMounted(()=>{
  initChart(defaultOptions);
})

const initMap = (mapName = 'china') => {
  if (mapName === "china") {
    ECharts.registerMap("china", chinaData);
  } else {
    ECharts.registerMap("world", worldData);
  }
}

const initChart = () => {
  chart.value = ECharts.init(myChart.value);
  chart.value.setOption(defaultOptions, true);
}

// 构建图
const drawChart = (options) => {
  chart.value.setOption(options, true)
}

// 销毁
const disposeChart = () => {
  chart.value.dispose();
  chart.value = null;
}

const bar = (options) => {
  console.log("我是子组件",options);
  // console.log(chart.value);
  // chart.value.setOption(options)
}

// 暴露出去的属性,方便父组件调用
defineExpose({
  initMap,
  drawChart,
  disposeChart,
  bar
})

</script>
