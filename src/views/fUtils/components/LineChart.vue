<template>
  <VChart class="chart" :option="option"/>
</template>

<script setup>
import {ref, watchEffect, provide} from 'vue'
import {use} from 'echarts/core'
import {LineChart, PieChart, BarChart} from 'echarts/charts'
import {TitleComponent, TooltipComponent, LegendComponent, ToolboxComponent} from 'echarts/components'
import VChart, {THEME_KEY} from 'vue-echarts'

use([LineChart, PieChart, BarChart, TitleComponent, TooltipComponent, LegendComponent, ToolboxComponent])
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
  legend: {
    // data: ['证件订单数量', '交易订单金额']
  },
  xAxis: {
    type: 'category',
    data: ['1日', '2日', '3日', '4日', '5日', '6日', '7日']
  },
  yAxis: {
    type: 'value',
    axisLabel: {
      formatter: `{value}`
      // formatter: `{value} ${props.unit}`
    }
  },
  series: [
    {
      name: '史记销售量',
      data: [50, 330, 224, 218, 135, 147, 260],
      // data: props.data.aaa,
      type: 'line'
    },
    {
      name: '论语销售量',
      data: [10, 300, 100, 300, 58, 300, 200],
      // data: props.data.bbb,
      type: 'line'
    }
  ]
})

watchEffect(() => {
  setTimeout(() => { // tip:模拟网络请求
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
