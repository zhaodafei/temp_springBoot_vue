<template>
  <InitEcharts ref="myChartMapPie" :options="options"></InitEcharts>
</template>

<script setup>
import InitEcharts from "./initEcharts";
import {onMounted, ref, watch, watchEffect} from "vue";

const props = defineProps({
  cdata:{
    type: Object,
    default: ()=>({})
  }
})



const myChartMapPie = ref();
const options = ref({});
const defaultData = {
  seriesTitle: "",
  seriesData: [
    { value: 1048, name: 'Search Engine' },
    { value: 735, name: 'Direct' },
    { value: 580, name: 'Email' },
    { value: 484, name: 'Union Ads' },
    { value: 300, name: 'Video Ads' }
  ]
};

onMounted(()=>{
  // console.log(props.cdata);
  // drawPie()
})


// todo:监听执行后 initEcharts 中的 drawChart 会一直循环
const drawPie = (newDataObj) => {
  console.log("ok___",newDataObj);
  let seriesTitle = '';
  let seriesData= []

  try {
    console.log("ok");
    seriesTitle = newDataObj.seriesTitle;
    seriesData = newDataObj.seriesData;
  }catch (e) { // 数据异常,便于开发调试
    seriesTitle = defaultData.seriesTitle;
    seriesData = defaultData.seriesData;
  }

  // options.value = {
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

  console.log(myChartMapPie.value);
  console.log(options);
  if (myChartMapPie.value) { // 防止 watchEffect 监听出现 undefined
    // myChartMapPie.value.drawChart(options.value);
    // myChartMapPie.value.drawChart(options);
    myChartMapPie.value.bar(options);
  }

}



const fei = () => {
  console.log("不调用我??????");
}

// const stop = watchEffect(() => {
//   console.log("????", props.cdata);
//   fei();
//
//   // drawPie(props.cdata)
//
//   if (myChartMapPie.value) {
//     console.log("--------",myChartMapPie.value);
//     drawPie(props.cdata)
//   }
//   // onInvalidate(() => {
//     console.log("????执行多少次");
//     // stop();
//   // })
//
//
// },) // {flush:"post"}

</script>
