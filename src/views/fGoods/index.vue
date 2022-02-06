<template>
<div>
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
    <el-table-column prop="goodsName" label="商品名称" align="center" />
    <el-table-column prop="unitPrice" label="单价" align="center" />
    <el-table-column prop="goodsNumber" label="商品数量" align="center" />
    <el-table-column prop="consumeTime" label="创建时间" align="center" />
    <el-table-column prop="goodsComment" label="备注" align="center" />
    <el-table-column prop="consumeWay" label="渠道" align="center" />
  </el-table>

  <el-pagination background
                 layout="prev, pager, next"
                 :total="total"
                 @current-change="handleCurrentChange">
  </el-pagination>

  <el-dialog :title="title" v-model="isShow" width="600px" :close-on-click-modal="false">
    <el-form ref="formRef" :model="form" class="demo-form-inline" label-width="120px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input v-model="form.goodsName" placeholder="商品名称" ></el-input>
      </el-form-item>
      <el-form-item label="单价" prop="unitPrice">
        <el-input-number v-model="form.unitPrice" :precision="2"></el-input-number>
      </el-form-item>
      <el-form-item label="数量" prop="goodsNumber">
        <el-input-number v-model="form.goodsNumber"></el-input-number>
      </el-form-item>
      <el-form-item label="入库时间" prop="consumeTime">
        <el-date-picker v-model="form.consumeTime" placeholder="选择时间" type="date"></el-date-picker>
      </el-form-item>
      <el-form-item label="渠道" prop="consumeWay">
        <el-radio v-model="form.consumeWay" label="线下" size="large">线下</el-radio>
        <el-radio v-model="form.consumeWay" label="线上" size="large">线上</el-radio>
      </el-form-item>
      <el-form-item label="备注" prop="goodsComment">
        <el-input v-model="form.goodsComment" placeholder="备注" :rows="2" type="textarea"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button type="primary" plain @click="submitForm">确定</el-button>
      <el-button plain @click="cancel">取消</el-button>
    </template>
  </el-dialog>

</div>
</template>

<!-- setup 组合式 api -->
<script setup>
import {onMounted, getCurrentInstance, ref, reactive} from 'vue'
import apiGoods from '@/api/goods.js'

// currentPage: 1;
const app = getCurrentInstance().appContext.config.globalProperties;

// 表格
const tableData = ref([]);
const total = ref(0);
const page = ref(1);
const pageSize = ref(15);
const latestData = reactive({
  consumeTime: "0000-00-00"
});

onMounted(() => {
  getList();
});

const getList = () => {
  let params = {page: page.value, per_page: pageSize.value};
  app.$get(apiGoods.getGoodsList, params).then(res => {
    tableData.value = res.data;
    total.value = res.page_count;

    // 获取第一条数据时间
    if (params.page === 1) {
      latestData.consumeTime = defaultDate(res.data[0].consumeTime);
    }

  })
}

const handleCurrentChange = (val) => {
  page.value = val;
  getList();
}

// 弹出层
const title = ref("")
const isShow = ref(false)
const formRef = ref() // 表单 ref 对象
const form = reactive({
  goodsName: '',
  unitPrice: 0,
  goodsNumber: 1,
  consumeTime: '',
  goodsComment: '无',
  consumeWay: '线下',
});

const handleAdd = () => {
  title.value = "商品添加";
  isShow.value = true;
  iniForm();
}

const defaultDate = (date) => {
  return app.$dayjs(date).format("YYYY-MM-DD");
}
const iniForm = () => {
  // form.consumeTime = app.$dayjs("2019-06-23 16:30:00").format("YYYY-MM-DD") + " 08:00:00";
  form.consumeTime = latestData.consumeTime;
}

const resetForm = () => {
  formRef.value.resetFields();
}

const submitForm = () => {

  let params = {
    goodsName: form.goodsName,
    unitPrice: form.unitPrice,
    goodsNumber: form.goodsNumber,
    consumeWay: form.consumeWay,
    goodsComment: form.goodsComment,
    consumeTime: app.$dayjs(form.consumeTime).format("YYYY-MM-DD") + " 08:00:00"
  };

  app.$post(apiGoods.goodsAdd, params).then(res => {
    if (res.error === 0) {
      app.$message.success("添加成功");
    } else {
      app.$message.error("添加失败");
    }
     cancel()
  })
}

const cancel = () => {
  resetForm();
  iniForm();
  isShow.value = false;
}

</script>

<style scoped>

</style>
