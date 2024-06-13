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
          legendData: [],
          seriesData0: [],
          seriesData1: []
        }
      }
    }
  })

  const option = ref({
    title: {
      text: '示例饼图',
      left: 'center'
      // 控制上下距离
      // orient: 'horizontal',
      // x: 'center',
      // y: '90px'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎'],
      // 控制上下距离
      // orient: 'horizontal',
      // x: 'center',
      // y: '90px'
    },
    series: [
      {
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        data: [
          { value: 335, name: '直接访问' },
          { value: 310, name: '邮件营销' },
          { value: 234, name: '联盟广告' },
          { value: 135, name: '视频广告' },
          { value: 1548, name: '搜索引擎' }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  })

  watchEffect(() => {
    setTimeout(() => {
      // tip:模拟网络请求
      option.value.legend.data = props.data.legendData
      option.value.series[0].data = props.data.seriesData0
    }, 2000)
  })
</script>

<style scoped lang="scss">
  .chart {
    height: 400px;
  }
</style>
