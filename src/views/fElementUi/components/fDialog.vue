<template>
  <el-dialog title="新增-修改" :width="560" v-model="visible" destroy-on-close
             :close-on-click-modal="false"
             modal-class="遮罩层的自定义类名 111111111111111"
             custom-class="Drawer 的自定义类名 222222222222222"
             @opened="onOpened"
             :before-close="handleClose">

    <div v-loading="detailLoading">
      <p>
        v-loading.fullscreen="true"
        v-loading="true"
      </p>
      <p> 我是弹窗内容</p>
      <p> 使用 :close-on-click-modal="false"  禁止点击空白区域关闭</p>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleSave" :loading="saveLoading || detailLoading">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref, reactive, onMounted, unref,getCurrentInstance} from "vue";
// import {ElMessage, ElMessageBox} from "element-plus";
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
const detailLoading = ref()

const onOpened = () => {
  console.log("打开了",props.visible);
  detailLoading.value = true
  detailLoading.value = false
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
 <fDialog.vue :visible="feiVisible" @close="feiClose"  />
 import FDialog from './components/fDialog.vue'

 const feiVisible = ref();
 const handleOpen = (row) => {
    feiVisible.value = true
  }
 const handleClose = (val) => {
    feiVisible.value = false
    if (val && val.isUpdate) {
       // 其他操作
    }
  }
 ****/

</script>

<style scoped>

</style>
