<template>
  <el-drawer title="新增-修改" v-model="visible" destroy-on-close
      :close-on-click-modal="false"
      @opened="onOpened"
      :before-close="handleClose">

    <div>
      <p> 我是 Drawer 内容 </p>
      <p> 使用 :close-on-click-modal="false"  禁止点击空白区域关闭</p>
    </div>

    <template #footer>
      <div style="flex: auto">
        <el-button @click="handleClose"> 取消</el-button>
        <el-button type="primary" @click="handleSave">确认保存</el-button>
      </div>
    </template>

  </el-drawer>
</template>

<script setup>
import {ref, reactive, onMounted, unref,getCurrentInstance} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {useRoute, useRouter} from "vue-router";

const router = useRouter();
const route = useRoute();
const { proxy } = getCurrentInstance()

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
})
const emits = defineEmits(['close']);

const onOpened = () => {
  console.log("打开了",props.visible);
}
const onClose = (isUpdate) => {
  console.log("关闭销毁数据____1111111111");
  emits('close', { isUpdate })
}

const saveLoading = ref();
const handleSave = () => {
  console.log("保存____sssssssss");
  onClose(true);
}

const handleClose = () => {
  console.log("取消****************");
  onClose(false);
}

/**** 父组件中使用
  <FDrawer :visible="drawerVisible" @close="closeDrawer"  />
  import FDrawer from './components/fDrawer.vue'

   const drawerVisible = ref();
   const openDrawer = (row) => {
     drawerVisible.value = true
   }
   const closeDrawer = (val) => {
     drawerVisible.value = false
     if (val && val.isUpdate) {
       // 其他操作
     }
   }
 *****/
</script>

<style scoped>

</style>
