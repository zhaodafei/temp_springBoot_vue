<template>
  <VChart class="chart" :option="option" />
</template>

<script setup>
  import { ref, watchEffect, provide } from 'vue'
  import { use } from 'echarts/core'
  import { LineChart, PieChart, BarChart } from 'echarts/charts'
  import {
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    ToolboxComponent
  } from 'echarts/components'
  import VChart, { THEME_KEY } from 'vue-echarts'

  use([
    LineChart,
    PieChart,
    BarChart,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    ToolboxComponent
  ])
  provide(THEME_KEY, 'light')

  const props = defineProps({
    data: {
      type: Object,
      default: () => {
        return {
          xAxisData: [],
          seriesData0: [],
          seriesData1: []
        }
      }
    }
  })

  const option = ref({
    title: {
      text: '柱状图',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
    },
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: [120, 200, 150, 80, 70, 110, 130],
        type: 'bar',
      },
      {
        data: [140, 30, 340, 80, 58, 76, 125],
        type: 'bar',
      },
    ],
  })

  watchEffect(() => {
    setTimeout(() => {
      // tip:模拟网络请求
      option.value.xAxis.data = props.data.xAxisData
      option.value.series[0].data = props.data.seriesData0
      option.value.series[1].data = props.data.seriesData1
    }, 2000)
  })
</script>

<style scoped lang="scss">
  .chart {
    height: 400px;
  }
</style>
