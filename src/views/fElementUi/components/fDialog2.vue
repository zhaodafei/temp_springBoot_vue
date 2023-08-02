<template>
  <el-button @click="handleFeiNo">打开弹窗</el-button>

  <el-dialog title="在当前页面中使用弹窗" :width="560" v-model="visibleNo" v-if="visibleNo" destroy-on-close
             :close-on-click-modal="false"
             @opened="onOpenedNo"
             :before-close="handleCloseNo">

    <div v-loading="detailLoadingNo">
      直接卸载某个具体页面中,不当做组件使用
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCloseNo">取消</el-button>
        <el-button type="primary" @click="handleSaveNo" :loading="saveLoadingNo || detailLoadingNo">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref, getCurrentInstance} from "vue";

const {proxy} = getCurrentInstance()


const useNoDialog = () => {
  const visibleNo = ref(false)
  const detailLoadingNo = ref(false)

  const handleFeiNo = () => {
    visibleNo.value = true
  }

  const onOpenedNo = () => {
    console.log("打开了onOpened", visibleNo.value);
    detailLoadingNo.value = true
    detailLoadingNo.value = false
  }
  const onCloseNo = (isUpdate = false) => {
    console.log("关闭销毁数据____1111111111", isUpdate);
    visibleNo.value = false
  }

  const saveLoadingNo = ref(false);
  const handleSaveNo = () => {
    console.log("保存____sssssssss");
    onCloseNo(true);
  }

  const handleCloseNo = () => {
    console.log("取消****************");
    onCloseNo(false);
  }


  return {visibleNo, detailLoadingNo, handleFeiNo, onOpenedNo, saveLoadingNo, handleSaveNo, handleCloseNo}
}
const {visibleNo, detailLoadingNo, handleFeiNo, onOpenedNo, saveLoadingNo, handleSaveNo, handleCloseNo} = useNoDialog()

</script>
