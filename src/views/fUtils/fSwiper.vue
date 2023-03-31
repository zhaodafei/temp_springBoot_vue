<template>
<div>
  <p>
    官网参考地址：https://swiperjs.com/vue
  </p>

  <div class="swiper-box">
    <div class="swiper-wrap">
      <swiper
          :slides-per-view="sOptions.perView"
          :space-between="sOptions.spaceBetween"
          :slidesPerGroup="sOptions.slidesPerGroup"
          :loop="sOptions.loop"
          :loopFillGroupWithBlank="sOptions.loopFillGroupWithBlank"
          :navigation="sOptions.navigation"
          :pagination="sOptions.pagination"
          :modules="modules"
          @swiper="onSwiper"
          @slideChange="onSlideChange"
      >
        <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
        <swiper-slide class="swiper-slide">Slide 2</swiper-slide>
        <swiper-slide class="swiper-slide">Slide 3</swiper-slide>
        <swiper-slide>Slide 4</swiper-slide>
        <swiper-slide>Slide 5</swiper-slide>
        <swiper-slide>Slide 6</swiper-slide>
        <swiper-slide>Slide 7</swiper-slide>
        <swiper-slide>Slide 8</swiper-slide>
      </swiper>
    </div>
  </div>
</div>
</template>

<script setup>
import {reactive, ref} from "vue";

// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css'; // Import Swiper styles
import 'swiper/css/pagination'
import 'swiper/css/navigation'
import { Pagination, Navigation } from 'swiper' // import required modules
const modules = ref([Pagination, Navigation])

const sOptions = reactive({
  perView: 3,
  spaceBetween: 50,
  slidesPerGroup: 5,
  loop:true,
  loopFillGroupWithBlank:true,
  navigation:true,
  pagination:{
    clickable: true
  },
})
const onSwiper = (swiper) => {
  console.log(swiper);
};
const onSlideChange = () => {
  console.log('slide change');
};
</script>

<style scoped lang="scss">
.swiper-box {
  margin-top: 20px;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.12);;
  border-radius: 4px;
  border: 1px solid #e4e7ed;
  background-color: #ffffff;
  overflow: hidden;
  color: #303133;
  transition: 0.3s;;
  padding: 15px 20px 20px 20px !important;
}

.swiper-wrap {
  background-color: #ffffff;
}

:deep(.swiper-slide) {
  min-height: 100px;
  border: 1px solid #dcdfe6;
}
</style>

<style lang="scss">
// 将箭头放在容器外部
.swiper-wrap {
  position: relative;
  padding: 20px 50px !important;
}

.swiper-wrap .swiper {
  position: initial;
}

// 修改箭头颜色
.swiper-button-next,
.swiper-button-prev {
  color: #ff6b81 !important;
}

// 修改 Dot 颜色
.swiper-pagination-bullet-active {
  background: #ff6b81 !important;
}

.swiper-pagination {
  bottom: 3px !important;
}
</style>
