<template>
  <div>
    <div>
      消息通知
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
