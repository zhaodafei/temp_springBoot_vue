<template>
<div id="main" style="width: 300px; height: 500px"></div>
</template>

<script setup>
import {onMounted, ref, watchEffect} from "vue";
import * as echarts from 'echarts'
import ECharts from "echarts";

const props = defineProps({
  cdata:{
    type: Object,
    default: ()=>({})
  }
})

// const chartEL = ref(null);
const myChart = ref(null);
onMounted(()=>{
  // const myChart = echarts.init(document.getElementById("main"));

  // 绘制图表
  // myChart.setOption({
  //   title: {
  //     text: 'ECharts 入门示例'
  //   },
  //   tooltip: {},
  //   xAxis: {
  //     data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
  //   },
  //   yAxis: {},
  //   series: [
  //     {
  //       name: '销量',
  //       type: 'bar',
  //       data: [5, 20, 36, 10, 10, 20]
  //     }
  //   ]
  // });

  // myChart.setOption({
  //   title: {
  //     text: '暂无数据',
  //     left: 'center',
  //     top: '45%',
  //   },
  //   graphic: {
  //     type: 'text',
  //     left: 'center',
  //     top: '45%',
  //     style: {
  //       text: '暂无数据',
  //       // fill: '#ff68b1',
  //       fontSize: 20,
  //       // fontWeight: 700
  //     }
  //   },
  //   // color: ['#D8D8D8'],
  //   series: [
  //     {
  //       name: '空数据',
  //       type: 'pie',
  //       radius: ['65%', '70%'],
  //     }
  //   ]
  // })
  //
  // // 监听数据
  // watchEffect(() => {
  //   myChart.setOption(props.cdata);
  // });

  initChart()
  drawChart(props.cdata)
})

const initChart = () => {
  myChart.value = echarts.init(document.getElementById("main"));
}

// 构建图
const drawChart = (options) => {

  let fei = {
    title: {
      text: '暂无数据111',
      left: 'center',
      top: '45%',
    },
    graphic: {
      type: 'text',
      left: 'center',
      top: '45%',
      style: {
        text: '暂无数据222',
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

  if (options) {
    console.log("******eee*sss******");
    console.log(options);
    myChart.value.setOption(options, true)
    // myChart.value.setOption(fei, true)
  }

}

defineExpose({
  drawChart
})

</script>

<style scoped>

</style>
