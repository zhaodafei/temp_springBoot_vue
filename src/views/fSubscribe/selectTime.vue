<template>

  <div class="subscript-body">
    <h3>北场馆-北门</h3>
    <div class="tip">07-22 周四</div>

    <div class="content">
      <div class="time-slot-list">
        <div v-for="item in timeSlotList" class="time-slot-item">
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
      返回,预约
    </div>
  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import seatSlotImg from '@/assets/images/seat_slot.png'
import seatSlotNoImg from '@/assets/images/seat_slot_no.png'

// const timeSlotList = ref([
//   // {time: "09:00~09:30", isHave: "有", isTrue: true},
//   // {time: "09:00~09:30", isHave: "有", isTrue: true},
//   // {time: "09:00~09:30", isHave: "有", isTrue: true},
//   // {time: "09:00~09:30", isHave: "有", isTrue: true},
//   // {time: "09:30~10:30", isHave: "无", isTrue: false},
//   // {time: "09:30~10:30", isHave: "无", isTrue: false},
//   // {time: "09:30~10:30", isHave: "无", isTrue: false},
//   // {time: "09:30~10:30", isHave: "无", isTrue: false},
//   // {time: "09:30~10:30", isHave: "无", isTrue: false},
//   // {time: "09:00~09:30", isHave: "有", isTrue: true},
//   // {time: "09:00~09:30", isHave: "有", isTrue: true},
//   // {time: "09:30~10:30", isHave: "无", isTrue: false},
//   // {time: "09:30~10:30", isHave: "无", isTrue: false},
// ])

const timeSlotList = ref([
  {time: "00:00~01:00", isHave: "美好", isTrue: true},
  {time: "01:00~02:00", isHave: "的一", isTrue: true},
  {time: "02:00~03:00", isHave: "天开", isTrue: true},
  {time: "03:00~04:00", isHave: "始了", isTrue: true},
  {time: "04:00~05:00", isHave: ",", isTrue: false},

  {time: "05:00~06:00", isHave: "请", isTrue: false},
  {time: "06:00~07:00", isHave: "大家", isTrue: false},
  {time: "07:00~08:00", isHave: "努力", isTrue: false},
  {time: "08:00~09:00", isHave: "工作", isTrue: false},
  {time: "09:00~10:00", isHave: ",", isTrue: true},

  {time: "10:00~11:00", isHave: "迎娶", isTrue: true},
  {time: "11:00~12:00", isHave: "白", isTrue: true},
  {time: "12:00~13:00", isHave: "富美", isTrue: true},
  {time: "13:00~14:00", isHave: ",", isTrue: true},
  {time: "14:00~15:00", isHave: ",", isTrue: false},

  {time: "15:00~16:00", isHave: "嫁个", isTrue: false},
  {time: "16:00~17:00", isHave: "高", isTrue: false},
  {time: "17:00~18:00", isHave: "富帅", isTrue: false},
  {time: "18:00~19:00", isHave: ",", isTrue: false},
  {time: "19:00~20:00", isHave: ",", isTrue: true},

  {time: "20:00~21:00", isHave: "踏上", isTrue: true},
  {time: "21:00~22:00", isHave: "人生", isTrue: true},
  {time: "22:00~23:00", isHave: "巅峰", isTrue: true},
  {time: "23:00~24:00", isHave: ",", isTrue: true},
  {time: "24:00~00:00", isHave: ",", isTrue: false},
])
</script>

<style scoped lang="scss">
.subscript-body {
  width: 390px;
  height: 700px;
  margin: 0 auto;
  padding: 1px 25px;
  background-color: #fff;

  .content{
    .time-slot-list{
      .time-slot-item {
        position: relative;
        //width: 100px;
        width: calc(19% - 2px);
        display: inline-block;
        margin-right: 5px;
        margin-bottom: 10px;
        .bg{
          img{width: 100%}
        }
        .time-slot-text{
          color: yellow;
          width: 100%;
          height: 100%;
          position: absolute;
          top: 0;
          .time-slot{
            color: #fff;
            position: absolute;
            top: 0;
            padding-top: 10px;
            font-size: 12px;
          }
          .time-select{
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

}

</style>
