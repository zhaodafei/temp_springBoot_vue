<template>
  <InitEcharts ref="refsChartPie" style="width: 600px; height: 300px;background-color: #ff6b81"></InitEcharts>
</template>

<script setup>
import InitEcharts from "./initEcharts";
import {onMounted, ref, watchEffect} from "vue";

const props = defineProps({
  cdata:{
    type: Object,
    default: ()=>({})
  }
})

const refsChartPie = ref(null);
const defaultData = {
  seriesTitle: "",
  seriesData: [
    { value: 108, name: 'demo1' },
    { value: 735, name: 'demo2' },
    { value: 580, name: 'demo3' },
    { value: 484, name: 'demo4' },
    { value: 300, name: 'demo5' }
  ]
};


const drawPie = (newDataObj) => {
  if (!refsChartPie.value) { // 防止 watchEffect 监听出现 undefined
    return false;
  }

  let seriesTitle = '';
  let seriesData= []

  try {
    seriesTitle = newDataObj.seriesTitle;
    seriesData = newDataObj.seriesData;
  }catch (e) { // 数据异常,便于开发调试
    seriesTitle = defaultData.seriesTitle;
    seriesData = defaultData.seriesData;
  }

  let options = {
    title: {
      text: seriesTitle,
      left: "center"
    },
    tooltip: {
      show: true,
      formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
      orient: "vertical",
      left: "left"
    },
    series: [
      {
        name: "数量",
        type: "pie",
        radius: "50%",
        label: {
          //饼图图形上的文本标签
          show: true,
          position: "outside", //标签的位置
          fontWeight: 300,
          fontSize: 16, //文字的字体大小
          formatter: "{b} {d}%"
        },
        data: seriesData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)"
          }
        }
      }
    ]
  };

  refsChartPie.value.drawChart(options);

}

watchEffect(() => {
  drawPie(props.cdata)
}); // {flush: "post"}

onMounted(()=>{
  // drawPie()
})

</script>
