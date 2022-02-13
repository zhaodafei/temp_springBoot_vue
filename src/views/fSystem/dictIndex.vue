<template>
  <div>
    <el-form ref="refsQueryForm" :model="queryParams" :inline="true">
      <el-form-item label="字典名称" prop="dictName">
        <el-input v-model="queryParams.dictName" placeholder="字典名称" ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleQuery">查询</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete">删除</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column prop="dictName" label="字典名称" align="center" />
    </el-table>

    <el-pagination background
                   layout="prev, pager, next"
                   :total="total"
                   @current-change="handleCurrentChange">
    </el-pagination>

    <el-dialog :title="title" v-model="isShow" width="600px" :close-on-click-modal="false">
      <el-form ref="formRef" :model="form"  label-width="120px">
        <el-form-item label="字典名称" prop="dictName">
          <el-input v-model="form.dictName" placeholder="字典名称" ></el-input>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button type="primary" plain @click="submitForm">确定</el-button>
        <el-button plain @click="cancel">取消</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import apiDict from '@/api/dict.js'

const app = getCurrentInstance().appContext.config.globalProperties;

// 搜索
const refsQueryForm = ref() // 表单 ref 对象
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  dictName: '',
});

const handleQuery = () => {
  queryParams.pageNum = 1;
  getList();
}

const resetQuery = () => {
  refsQueryForm.value.resetFields();
  handleQuery();
}

// 表格
const tableData = ref([]);
const total = ref(0);

onMounted(() => {
  getList();
});

const getList = () => {
  let params = {
    // page: queryParams.pageNum,
    // per_page: queryParams.pageSize,
    // goodsName: queryParams.goodsName
  };
}

const handleCurrentChange = (val) => {
  queryParams.pageNum = val;
  getList();
}

// 弹出层
const title = ref("")
const isShow = ref(false)
const formRef = ref() // 表单 ref 对象
const form = reactive({
  dictName: '',
});

const handleAdd = () => {
  title.value = "商品添加";
  isShow.value = true;
}

const iniForm = () => {

}

const resetForm = () => {
  formRef.value.resetFields();
}

const submitForm = () => {
  let params = {
    // dictName: form.dictName,
  };
}

const cancel = () => {
  resetForm();
  iniForm();
  isShow.value = false;
}

</script>

<style scoped>

</style>
