<template>
  <div :id="id" ref="refsChart" :style="{ height: height, width: width }"/>
</template>

<script setup>
import {getCurrentInstance, onMounted, ref} from "vue";
import chinaData from "@/components/echartsCommon/map/json/china.json";
import worldData from "@/components/echartsCommon/map/json/world.json";
const ECharts = require("echarts");

const app = getCurrentInstance().appContext.config.globalProperties;

let myChart = null; // 这个不能监听
const refsChart = ref(null);
const defaultOptions = {
  // title: {
  //   text: '暂无数据',
  //   left: 'center',
  //   top: '45%',
  // },
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
    default: 'refsChart'
  },
  width: {
    type: String,
    default: '100%'
  },
  height: {
    type: String,
    default: '100%'
  },
  // options: {
  //   type: Object,
  //   default: ()=>({})
  // }
})

const initMap = (mapName = 'china') => {
  if (mapName === "china") {
    ECharts.registerMap("china", chinaData);
  } else {
    ECharts.registerMap("world", worldData);
  }
}

const initChart = () => {
  myChart = ECharts.init(refsChart.value);
  myChart.setOption(defaultOptions, true);
}

// 构建图
const drawChart = (options) => {
  myChart.setOption(options, true)
}

// 销毁
const disposeChart = () => {
  myChart.dispose();
  myChart = null;
}

onMounted(()=>{
  initChart(defaultOptions);
})

// 暴露出去的属性,方便父组件调用
defineExpose({
  initMap,
  drawChart,
  disposeChart
})

</script>
