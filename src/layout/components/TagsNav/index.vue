<template>
  <div>
    <ScrollPanel>
      <router-link
          v-for="tag in allTags"
          class="tags-view-item"
          :class="isActive(tag)?'active':''"
          :to="{path: tag.path}"
          @contextmenu.prevent="openMenu(tag, $event)">
        {{ tag.title }}
        <span @click.prevent.stop="closeSelTag(tag)" v-if="tag.path!=='/home'">
          <close class="el-icon-close" style="width: 1em; height: 1em;vertical-align: middle;" />
        </span>
      </router-link>
    </ScrollPanel>

    <ul v-show="visible" :style="{ left: left + 'px', top: top + 'px' }" class="contextmenu">
      <li @click="closeOthersTags">关闭其他</li>
      <li @click="closeAllTags">全部关闭</li>
    </ul>
  </div>
</template>

<script setup>
import {onMounted, ref, getCurrentInstance,watch} from "vue";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();
const { proxy } = getCurrentInstance();

import ScrollPanel from "./ScrollPanel"



const visible = ref(false);
const top = ref(0);
const left = ref(0);
const selectedTag = ref({}); // 选中当前页签
const affixTags = ref([]);
const scrollPaneRef = ref(null);
const defaultHome = () => {
  return {title: "首页", path: "/home"}
}
const allTags = ref([
  {title: "首页", path: "/home"},
]);
const isActive = (tagItem) => {
  return tagItem.path === route.path;
}

const addTags = () => {
  const { name } = route
  if (name) {
    let isAdd = allTags.value.some(item => {
      if (route.path === item.path) {
        return true
      }
    });
    if (!isAdd) {
      let metaTitle = route.meta && route.meta.title || '';
      allTags.value.push({title: metaTitle, path: route.path});
    }
  }
  return false
}
// 关闭当前页签
const closeSelTag = (tag) => {
  if (tag.path !== '/home') {
    allTags.value = allTags.value.filter(item => item.path !== tag.path);
    toHome()
  }
}
// 关闭其他
const closeOthersTags = () => {
  allTags.value = allTags.value.filter(item => item.path === selectedTag.value.path);
  proxy.$router.push(selectedTag.value.path);
  if (allTags.value[0].path !== '/home') {
    allTags.value.push(defaultHome());
  }
}
// 全部关闭
const closeAllTags = () => {
  allTags.value = [];
  toHome();
}
const toHome = () => {
  proxy.$router.push("/")
}

// 右键事件
const openMenu = (tag, e) => {
  const menuMinWidth = 105
  const offsetLeft = proxy.$el.getBoundingClientRect().left // container margin left
  const offsetWidth = proxy.$el.offsetWidth // container width
  const maxLeft = offsetWidth - menuMinWidth // left boundary
  const l = e.clientX - offsetLeft + 15 // 15: margin right

  if (l > maxLeft) {
    left.value = maxLeft
  } else {
    left.value = l
  }

  top.value = e.clientY + 10;
  visible.value = true
  selectedTag.value = tag
}
const closeMenu = () => {
  visible.value = false
}



onMounted(() => {
  if (route.path !== '/home' && route.path !== '/') {
    addTags();
  }

})
watch(visible, (value) => { // tip:监听的值要放在定义字段的后面
  if (value) {
    document.body.addEventListener('click', closeMenu)
  } else {
    document.body.removeEventListener('click', closeMenu)
  }
})

watch(route, () => {
  addTags()
  // moveToCurrentTag()
})

</script>

<style scoped lang="scss">
.tags-view-item {
  display: inline-block;
  position: relative;
  cursor: pointer;
  height: 26px;
  line-height: 26px;
  border: 1px solid #d8dce5;
  color: #495060;
  background: #fff;
  padding: 0 8px;
  font-size: 12px;
  margin-left: 5px;
  margin-top: 4px;
  &:first-of-type {
    margin-left: 15px;
  }
  &:last-of-type {
    margin-right: 15px;
  }
  &.active {
    background-color: #ff6b81;
    color: #fff;
    border-color: #ff6b81;
    &::before {
      content: "";
      background: #fff;
      display: inline-block;
      width: 8px;
      height: 8px;
      border-radius: 50%;
      position: relative;
      margin-right: 2px;
    }
  }
}
.contextmenu {
  margin: 0;
  background: #fff;
  z-index: 3000;
  position: absolute;
  list-style-type: none;
  padding: 5px 0;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 400;
  color: #333;
  box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, 0.3);
  li {
    margin: 0;
    padding: 7px 16px;
    cursor: pointer;
    &:hover {
      background: #eee;
    }
  }
}
</style>
