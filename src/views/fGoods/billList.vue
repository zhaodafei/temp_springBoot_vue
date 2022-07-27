<template>
<div>
  <el-form ref="refsQueryForm" :model="queryParams" :inline="true">

    <el-form-item label="查询时间" prop="WEB_searchTime">
      <el-date-picker v-model="queryParams.WEB_searchTime"
                      type="daterange"
                      value-format="YYYY-MM-DD"
                      start-placeholder="开始时间"
                      end-placeholder="结束时间"
                      range-separator="To"
      ></el-date-picker>
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
    <!--<el-col :span="1.5">
      <el-button type="success" plain icon="Edit">修改</el-button>
    </el-col>-->
    <el-col :span="1.5">
      <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDel">删除</el-button>
    </el-col>
  </el-row>

  <el-table :data="tableData" stripe style="width: 100%"  @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55" align="center"/>
    <el-table-column prop="id" label="ID" align="center" />
    <el-table-column prop="startTime" label="开始时间" align="center" />
    <el-table-column prop="endTime" label="结束时间" align="center" />
    <el-table-column prop="allCount" label="总额度" align="center" />
  </el-table>

  <el-pagination background
                 layout="prev, pager, next"
                 :total="total"
                 @current-change="handleCurrentChange">
  </el-pagination>

  <el-dialog :title="title" v-model="isShow" width="600px" :close-on-click-modal="false"  destroy-on-close @close="cancel">
    <el-form ref="formRef" :model="form" class="demo-form-inline" label-width="120px">
      <el-form-item label="入库时间" prop="consumeTime" required>
        <el-date-picker v-model="form.consumeTime"
                        type="daterange"
                        value-format="YYYY-MM-DD 08:00:00"
                        start-placeholder="开始时间"
                        end-placeholder="结束时间"
                        range-separator="To"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="渠道" prop="consumeWay">
        <el-radio v-model="form.consumeWay" label="全部" size="large">全部</el-radio>
        <el-radio v-model="form.consumeWay" label="线下" size="large">线下</el-radio>
        <el-radio v-model="form.consumeWay" label="线上" size="large">线上</el-radio>
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button type="primary" plain @click="handleBudget">月预算</el-button>
      <el-button type="success" plain @click="submitForm">月统计</el-button>
      <el-button plain @click="isShow=false">取消</el-button>
    </template>
  </el-dialog>
</div>
</template>

<script setup>
import {onMounted, getCurrentInstance, ref, reactive} from 'vue'
import dayjs from "dayjs";
import apiGoods from '@/api/goods.js'
import {isEmptyArr_utils} from "@/utils/comUtils";

// 用那个都行
const app = getCurrentInstance().appContext.config.globalProperties;
const { proxy } = getCurrentInstance();

// 搜索
const refsQueryForm = ref() // 表单 ref 对象
const queryParams = reactive({
  pageNum: 1,
  pageSize: 20,
  WEB_searchTime: [],
  delNum: '0',
});

// 表格
const tableData = ref([]);
const total = ref(0);
// 非单个禁用
const multipleSelection = ref([]); // 选中 数据
const ids = ref([]); // 选中ids
const single = ref(true); // 单选
const multiple = ref(true); // 多选


const handleQuery = () => {
  queryParams.pageNum = 1;
  getList();
}

const resetQuery = () => {
  refsQueryForm.value.resetFields();
  handleQuery();
}

const getList = () => {
  let params = {
    ...queryParams,
    fei:"dddd",
    WEB_searchTime: [1,2],

  };
  if (queryParams.WEB_searchTime && queryParams.WEB_searchTime.length > 0) {
    params.startTime = dayjs(queryParams.WEB_searchTime[0]).format("YYYY-MM-DD")
    params.endTime = dayjs(queryParams.WEB_searchTime[1]).format("YYYY-MM-DD")
  }
  app.$get(apiGoods.getBillList, params).then(res => {
    tableData.value = res.rows;
    total.value = res.total;
  });
}

const handleCurrentChange = (val) => {
  queryParams.pageNum = val;
  getList();
}

const handleSelectionChange = (selection) => {
  multipleSelection.value = selection;
  ids.value = selection.map(item => item.id);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

const handleDel = (row) => {
  const id = row.id || ids;
  app.$confirm('是否确认删除?', "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(()=>{
    app.$post(apiGoods.billDel, {bill_count_ids: id}).then(()=>{
      getList();
      app.$message('删除成功');
    });``
  }).catch(()=>{
  })
}

// 弹出层
const title = ref("")
const isShow = ref(false)
const formRef = ref() // 表单 ref 对象
const defaultForm = () => {
  return {
    consumeTime: [],
    consumeWay: '全部',
  }
}
const form = reactive(defaultForm());

const handleAdd = () => {
  title.value = "账单统计";
  isShow.value = true;
}

const resetForm = () => {
  formRef.value.resetFields();
  Object.assign(form, defaultForm());
}

const handleBudget = () => {

  if (!form.consumeTime || form.consumeTime && form.consumeTime.length === 0) {
    app.$message.warning('时间不能为空');
    return ;
  }
  let params = {
    consumeWay: form.consumeWay,
    startTime: dayjs(form.consumeTime[0]).format("YYYY-MM-DD 00:00:00"),
    endTime: dayjs(form.consumeTime[1]).format("YYYY-MM-DD 23:59:59"),
  };
  app.$get(apiGoods.billBudget,params).then(res=>{
    if (res.error === 200) {
      app.$message.warning(`月预算金额: ${res.allCount} 元`);
    } else {
      app.$message.error("月预算失败");
    }
  })
}
const submitForm = () => {
  if (!form.consumeTime || form.consumeTime && form.consumeTime.length === 0) {
    app.$message.warning('时间不能为空');
    return ;
  }

  let params = {
    consumeWay: form.consumeWay,
    startTime: dayjs(form.consumeTime[0]).format("YYYY-MM-DD 00:00:00"),
    endTime: dayjs(form.consumeTime[1]).format("YYYY-MM-DD 23:59:59"),
  };
  app.$post(apiGoods.billAdd, params).then(res => {
    if (res.error === 200) {
      app.$message.success("统计成功");
      getList();
    } else {
      app.$message.error("月预算失败");
    }
    cancel();
  })
}

const cancel = () => {
  resetForm();
  isShow.value = false;
}

onMounted(() => {
  getList();
});
</script>

<style scoped>

</style>
