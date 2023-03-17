<template>
  <div class="subscript-body">
    <h3 class="title">北场馆-北门</h3>
    <div class="tip">
      <span><el-icon style="top: 2px"><Bell /></el-icon> 请务必在预约时间段前后</span>
      <span style="color: #ff6b81;font-size: 16px">10分钟</span>
      <span>内进馆。</span>
    </div>

    <div class="content">
      <div v-for="row in timeSlotList" class="time-slot-list">
        <div class="date-title">
          <div class="text">{{ row.dayData }}</div>
          <div class="icon-img">
            <div class="img"></div>
          </div>
        </div>
        <div v-for="item in row.rowData" class="time-slot-item">
          <div class="bg">
            <img v-if="item.isTrue" :src="seatSlotImg" alt="." />
            <img v-else :src="seatSlotNoImg" alt="." />
          </div>
          <div class="time-slot-text">
            <div class="time-slot">{{item.time}}</div>
            <div class="time-select">{{item.isHave}}</div>
          </div>
        </div>
      </div>
    </div>

    <div class="btn">
      <el-button size="large">返回</el-button>
      <el-button size="large" type="primary">预约</el-button>
    </div>
  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import seatSlotImg from '@/assets/images/seat_slot.png'
import seatSlotNoImg from '@/assets/images/seat_slot_no.png'

const timeSlotList = ref([
  {
    dayData: '07-18 周一',
    rowData: [
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
    ]
  },
  {
    dayData: '07-19 周二',
    rowData: [
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
    ]
  },
  {
    dayData: '07-20 周三',
    rowData: [
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:00~09:30", isHave: "有", isTrue: true},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
      {time: "09:30~10:30", isHave: "无", isTrue: false},
    ]
  },
])
</script>

<style scoped lang="scss">
.subscript-body {
  width: 390px;
  height: 700px;
  margin: 0 auto;
  padding: 1px 25px;
  background-color: #fff;
  overflow-y: scroll;

  display: flex;
  flex-direction: column;

  .title{
    flex: 0.1 0 0;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: -1px -25px;
    background: rgb(238, 241, 246);
  }
  .tip{
    display: flex;
    align-items: center;
    flex: 0.1 0 0;
    background: #f5e9ce;
    margin: -1px -25px;
    padding-left: 15px;
  }

  .content {
    margin-bottom: 60px;

    .time-slot-list {
      margin-top: 17px;
      font-size: 18px;
      color: #222;

      .icon-img {
        height: 6px;
        margin-bottom: 13px;

        .img {
          width: 50px;
          height: 3px;
          background: linear-gradient(to right, #ff6b81, #fff);
        }
      }

      .time-slot-item {
        position: relative;
        //width: 100px;
        width: calc(19% - 2px);
        display: inline-block;
        margin-right: 5px;
        margin-bottom: 10px;

        .bg {
          img {
            width: 100%
          }
        }

        .time-slot-text {
          color: yellow;
          width: 100%;
          height: 100%;
          position: absolute;
          top: 0;

          .time-slot {
            color: #fff;
            position: absolute;
            top: 0;
            padding-top: 10px;
            font-size: 12px;
          }

          .time-select {
            text-align: center;
            width: 100%;
            color: #fff;
            font-size: 20px;
            position: absolute;
            bottom: 10px;
          }
        }
      }
    }
  }

  .btn {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;

    ::v-deep(.el-button) {
      width: 50%;
      font-size: 18px;
    }
  }

}

</style>
