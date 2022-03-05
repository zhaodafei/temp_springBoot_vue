<template>
  <div>
    <div class="panel-wrapper" data-title="控制外边框">
      <div class="home-notice">
        <div class="notice-info">
          <a href="#">关于本网站介绍说明</a>
        </div>
        <div class="notice-extra">
          <em class="notice-date">2022-02-22</em>
          <a href="#">更多</a>
        </div>
      </div>
    </div>

    <div>用户登录情况</div>

    <div class="panel-wrapper">
      <h3 class="panel-title">
        快速菜单栏
      </h3>
      <div class="panel-menu">
        <!--快速菜单栏(UI平均方框现在)-->
        <div class="item">
          <div class="item-content">
            <div>title</div>
            <div>content</div>
          </div>
        </div>
        <div class="item">
          <div class="item-content">
            <div>title</div>
            <div>content</div>
          </div>
        </div>
      </div>
    </div>

    <!--<div style="width: 600px; height: 300px;background-color: #ff6b81">
      <CharsPie :cdata="cdata"  />
    </div>-->
  </div>
</template>

<script setup>
import CharsPie from "@/components/echartsCommon/chartPie";
import {reactive} from "vue";

const cdata = reactive({
  seriesTitle: "用户统计",
  seriesData: [
    { value: 100, name: '大飞' },
    { value: 50, name: 'admin' },
  ]
})
</script>

<style scoped lang="scss">
/* 24,40  padding-right: 200px; */
.panel-wrapper{
  padding: 24px 40px;
  background-color: #fff;
}


.home-notice{
  position: relative;
  height: 30px;
  border: 2px solid #ff6b81;
  padding-right: 200px;
  .notice-info{
    border: 2px solid #fff;
  }
  .notice-extra{
    position: absolute;
    right: 0;
    top: 0;
    .notice-date{
      font-style: normal;
      margin-right: 10px;
    }
  }
}


.panel-title{
  text-align: left;
}
.panel-menu {
  margin-top: 10px;
  background-color: #fff;
  height: 100px;
  display: flex;
  justify-content: space-around;
  .item {
    flex: 1;
    border: 1px dashed #C0c0c0;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 10px;
  }
  .item:last-child{
    margin-right: 0;
  }
}
</style>
