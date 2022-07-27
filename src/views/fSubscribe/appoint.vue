<template>
  <div class="subscript-body">
    <div class="banner">
      <h3>场馆预约说明</h3>
      <p>
        为广大观众提供揭示国家图书馆精品馆藏、展示中华典籍、弘扬中华文化的各种展览。
        提供自助还书服务。提供综合业务咨询，解答读者的一般咨询，辅导查找馆藏文献。
      </p>
    </div>

    <div class="tip">
      <div class="ico">
        <p>广播</p>
        <p>通知</p>
      </div>
      <div class="info">
        <div>每日 <span>21:00-08:00</span> 系统维护,期间系统无法使用。</div>
        <div>每日 <span>09:00-18:00</span> 开馆预约。 </div>
      </div>
    </div>

    <div class="venue-box">
      <div class="venue-tip">选择场馆</div>

      <div class="venue-list">
        <div v-for="(item,index) in venueList" class="venue-item" :class="'venue-'+index">
          <div class="venue" @click="toSelectTime">
            <div>{{ item.name }}</div>
            <div>{{ item.point }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, getCurrentInstance, ref, reactive, unref} from 'vue'
import { useRoute, useRouter } from "vue-router";
const router = useRouter();
const route = useRoute();
const { proxy } = getCurrentInstance();

const venueList = ref([
  {name: "北侧场馆", point: "北门口"},
  {name: "东侧场馆", point: "东门口"},
  {name: "南侧场馆", point: "南门口"},
])

const toSelectTime = () => {
  router.push({
    path: `/subscribe-selectTime`,
    query: {
      xxx: 123
    }
  })
}
</script>

<style scoped lang="scss">
.subscript-body {
  width: 390px;
  height: 700px;
  margin: 0 auto;
  padding: 1px 25px;
  background-color: #fff;

  .banner {
    width: auto;
    height: 187px;
    margin-top: 10px;
    margin-bottom: 20px;
    padding: 0 20px;
    background-color: #efd3d2;
    background-image: linear-gradient(to right, #ff6b81, #efd3d2);
    border-radius: 15px;
    box-shadow: 0 5px 10px #efd3d2;
    color: #666;
    h3{
      font-size: 22px;
    }
  }

  .tip{
    display: flex;
    justify-content: space-around;
    box-shadow: 0 0 10px rgb(44 68 91 / 10%);
    padding: 10px 0;
    border-radius: 4px;

    .ico{
      border-right: 1px solid #dedede;
      padding-right: 20px;
      font-weight: 800;
    }
    .info{
      color: #555;
      span{
        color: #ff6b81;
      }
    }
  }

  .venue-box {
    margin-top: 20px;
    padding: 1px 16px;
    box-shadow: 0 0 10px rgb(44 68 91 / 10%);

    .venue-tip {
      line-height: 52px;
      font-weight: 700;
      font-size: 18px;
    }

    .venue-list {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      color: #fff;
      margin-bottom: 20px;

      .venue-item {
        background-color: #c0c0c0;
        width: 167px;
        height: 62px;
        margin-top: 10px;
        border-radius: 5px;

        .venue {
          padding: 9px 10px;
          cursor: pointer;
        }
      }
      .venue-0{
        background-color: #efd3d2;
      }
      .venue-1{
        background-color: #dfa7a6;
      }
      .venue-2{
        background-color: #ff6b81;
      }

    }
  }
}

</style>
