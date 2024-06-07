<template>
  <v-chart class="chart" :option="option" />
</template>

<script setup name="FunnelChart">
  import { ref, watchEffect, provide } from 'vue'
  import { use } from 'echarts/core'
  // 引入图表组件，echats 的图表名称首字母大写 + Chart
  import { FunnelChart } from 'echarts/charts'
  // 引入图标附加的组件 echats 的组件名称首字母大写 + Component
  import {
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    ToolboxComponent
  } from 'echarts/components'
  // 引入封装好的 VChart 组件，省去了手动操作 DOM 的情况，只需要绑定 option 即可
  import VChart, { THEME_KEY } from 'vue-echarts'
  // 注册要用的组件
  use([FunnelChart, TitleComponent, TooltipComponent, LegendComponent, ToolboxComponent])
  // 设置主题，不设置时默认为 light
  provide(THEME_KEY, 'light')
  // 设置数据项，结构和 echarts 一致
  const props = defineProps({
    data: {
      type: Object,
      default: () => {
        return {
          titleText: [],
          seriesData0: [],
        }
      }
    }
  })

  const option = ref({
    title: {
      text: '漏斗报表'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c}%'
    },
    series: [
      {
        name: 'Funnel',
        type: 'funnel',
        left: '10%',
        top: 60,
        bottom: 60,
        width: '80%',
        min: 0,
        max: 100,
        minSize: '0%',
        maxSize: '100%',
        sort: 'descending',
        gap: 2,
        // b是data中的 name，c是data中的 value
        label: {
          show: true,
          position: 'outside',
          formatter: '{b}: {c}%'
        },
        labelLine: {
          length: 10,
          lineStyle: {
            width: 1,
            type: 'solid'
          }
        },
        itemStyle: {
          borderColor: '#fff',
          borderWidth: 1
        },
        emphasis: {
          label: {
            fontSize: 20,
            formatter: '{b}: {c}%'
          }
        },
        // 会根据 value 排序
        data: [
          { value: 60, name: '名字001' },
          { value: 40, name: '名字002' },
          { value: 20, name: '名字003' },
          { value: 80, name: '名字004' },
          { value: 95, name: '名字005' }
        ]
      }
    ]
  })


  watchEffect(() => {
    setTimeout(() => {
      // tip:模拟网络请求
      option.value.title.text = props.data.titleText
      option.value.series[0].data = props.data.seriesData0
    }, 2000)
  })
</script>

<style scoped>
  .chart {
    height: 400px;
  }
</style>
