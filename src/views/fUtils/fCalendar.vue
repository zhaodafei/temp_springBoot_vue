<!-- 学生教室预约 classroom book -->
<template>
  <div>
    <!--
    <p> import '@fullcalendar/core/vdom' 解决插件在顶级库前加载的报错 </p>
    -->
    <!--<div>
      <img :src="calendar_01" alt="最终实现效果图">
    </div>-->

    <div class='demo-app-main'>
      <FullCalendar
          ref="fullCalendar"
          v-loading="loading"
          class="calendar"
          :options='calendarOptions'
      >
        <!--<template #eventContent="arg">
          <b tip-fei="这里面参数很多,一般用event值就可以">{{ arg }}</b>
        </template>-->

         <template #eventContent="{ event }">
           <div>
             <p>{{event.extendedProps.daFei_name}}</p>
             <p>{{event.extendedProps.daFei_status}}</p>
             <p>{{event.extendedProps.daFei_location}}</p>
           </div>
        </template>
      </FullCalendar>
    </div>

  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import resourceTimelinePlugin from '@fullcalendar/resource-timeline'
import {API_phpData} from './fCalendar_eventUtils.js'

import calendar_01 from '@/assets/images/calendar/calendar_01.png'
import interfacesCalendar from "@/api/calendar.js";
const app = getCurrentInstance().appContext.config.globalProperties;

const calendarColor = {
  // 给日历标记颜色
  a01: '#eee8aa',
  a02: '#dda0dd',
  a03: '#90ee90',
  a04: '#afeeee',
  a05: '#ff6b81',
}
let calendarApi = null
const fullCalendar = ref(null)
const eventList = ref([]) // 缓存的全部事件的列表

// 视口大小变化调整日历高度
const handleWindowResize = () => {
  fullCalendar.value.options.height = window.screen.height - 46
}
const handleEventClick = (clickInfo) => {
  console.log("点击信息",clickInfo);
}

// 清空日历事件：切换视图、更新事件时
const clearCalendarEvent = () => {
  let getEvents = calendarApi.getEvents() //获取事件
  if (getEvents && getEvents.length > 0) {
    //循环删除数据（通过ID）
    getEvents.forEach(item => {
      calendarApi.getEventById(item.id).remove()
    })
  }
}

// 添加日历事件
const addEventToCalendar = (eventList) => {
  // 先清空日历事件
  clearCalendarEvent()
  eventList.forEach(event => {
    calendarApi.addEvent(event)
  })
}


const calendarOptions = reactive({
  //设置产品**不设置左下角会出现链接
  schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
  plugins: [
    resourceTimelinePlugin
  ],
  headerToolbar: { // 顶部工具栏
    // 01)每次加一个空格会多一个空白按钮 02)每个逗号分隔一个按钮
    left: 'prevYear,prev,next,nextYear today',
    center: 'title', // 显示中间时间
    right: 'resourceTimelineMonth,resourceTimelineWeek,resourceTimelineDay'
  },
  buttonText: { // 修改按钮为汉字
    today: '今天',
    month: '月',
    week: '周',
    day: '日'
  },
  // views:{ // 视图设置
  // },
  initialView: 'resourceTimelineMonth', // 初始视图
  locale: 'zh-cn', //中文
  firstDay: 1, // 周视图中 设置星期一为第一天
  resourceAreaWidth: '15%', // 资源区宽度
  // slotMinWidth: 150,
  height: window.screen.height - 46, // 设置高度
  windowResize: handleWindowResize,
  resourceAreaColumns: [
    {
      field: 'title', // 对应resources列表项要显示的字段
      headerContent: '场地' // 资源的左上角的名称
    }
  ],
  resources: [],
  eventClick: handleEventClick  // 事件
})


// 查询接口数据
const loading = ref(false)
const findDataList = () => {
  const params = {
    fei: 123,
  }
  app.$get(interfacesCalendar.getCalendarList,params).then(res=>{
    // 接口返回格式,参考 API_phpData 文件中数据
    const resData = res.data
    console.log(resData);

    // 开始对日历数据赋值
    let resources = [];
    resData.forEach(item=>{
      resources.push({id: item.id, title: item.classroomName });
    })

    // 添加左侧资源名字( 对应 resourceAreaColumns 中的 headerContent 数据 )
    calendarOptions.resources = resources

    // 添加事件到日历( 为日历中添加数据显示 )
    // 参考地址: https://fullcalendar.io/docs/event-source-object
    eventList.value = resData.map(row => {
      return {
        resourceId: row.id, // 这个 id 要和 resources 中的 id 对应
        id: row.id,
        start: row.startDate,
        end: row.endDate,
        allDay: false,

        // 日历四个颜色
        // color: 'yellow',
        // textColor: 'black',
        // backgroundColor: calendarColor[row.status],
        borderColor: calendarColor[row.status],

        title: "课程内容: " + row.classroomName,
        // 额外字段: 默认添加到 extendedProps 中,也可以直接 extendedProps 中定义
        // 额外字段: https://fullcalendar.io/docs/event-object
        daFei_name: "大飞",
        daFei_age: "18",
        daFei_gender: "男",
        extendedProps: {
          daFei_name2: "飞哥",
          daFei_age2: "20",
          daFei_gender2: "不提供",
          daFei_name: row.classroomName, // 名字
          daFei_status: "无状态"+row.status, // 状态
          daFei_location: row.location, // 位置
          daFei_startDate: "", // 预约时间
          daFei_endDate: "", // 预约时间
        }
      }
    })

    // 添加事件到日历
    addEventToCalendar(eventList.value)

  })
}


onMounted(() => {
  // 获取操作日历的 API
  calendarApi = fullCalendar.value.getApi()

  findDataList()
})
</script>

<style>
.demo-app-main {
  border: 2px solid #ff6b81;
}
</style>
