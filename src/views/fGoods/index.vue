<template>
<div>
  <el-form ref="refsQueryForm" :model="queryParams" :inline="true">
    <el-form-item label="商品名称" prop="goodsName">
      <el-input v-model="queryParams.goodsName" placeholder="商品名称" clearable ></el-input>
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
      <el-button type="success" plain icon="Edit" @click="handleEdit" :disabled="single">修改</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button type="danger" plain icon="Delete" @click="handleDel" :disabled="multiple">删除</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button type="success" plain icon="View" @click="handleDetail" :disabled="single">详细</el-button>
    </el-col>
  </el-row>

  <el-table  ref="multipleTable" :data="tableData" v-loading="tableLoading" stripe style="width: 100%" @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55" align="center"/>
    <el-table-column prop="id" label="ID" align="center" />
    <el-table-column prop="goodsName" label="商品名称" align="center" />
    <el-table-column prop="unitPrice" label="单价(元)" align="center" />
    <el-table-column prop="goodsNumber" label="商品数量" align="center" />
    <el-table-column prop="countPrice" label="总价格" align="center" />
    <el-table-column prop="consumeTime" label="创建时间" align="center" />
    <el-table-column prop="consumeWay" label="渠道" align="center" />
    <el-table-column prop="goodsComment" label="备注" align="center" />
  </el-table>

  <el-pagination background
                 layout="prev, pager, next"
                 :total="total"
                 @current-change="handleCurrentChange">
  </el-pagination>

  <el-dialog :title="title" v-model="isShow" width="600px" :close-on-click-modal="false" destroy-on-close @close="cancel">
    <el-form ref="formRef" :model="form" :rules="rules" class="demo-form-inline" label-width="120px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input v-model="form.goodsName" placeholder="商品名称" />
      </el-form-item>
      <el-form-item label="单价" prop="unitPrice">
        <el-input-number v-model="form.unitPrice" :precision="2" />
      </el-form-item>
      <el-form-item label="数量" prop="goodsNumber">
        <el-input-number v-model="form.goodsNumber" />
      </el-form-item>
      <el-form-item label="入库时间" prop="consumeTime">
        <el-date-picker v-model="form.consumeTime" placeholder="选择时间" type="date" />
      </el-form-item>
      <el-form-item label="渠道" prop="consumeWay">
        <el-radio v-model="form.consumeWay" label="线下" size="large">线下</el-radio>
        <el-radio v-model="form.consumeWay" label="线上" size="large">线上</el-radio>
      </el-form-item>
      <el-form-item label="备注" prop="goodsComment">
        <el-input v-model="form.goodsComment" placeholder="备注" :rows="2" type="textarea" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button type="primary" plain @click="submitForm">确定</el-button>
      <el-button plain @click="isShow=false">取消</el-button> <!-- tip: 直接设置false,会调用 cancel() 方法 -->
      <!--<el-button plain @click="resetForm">重置测试</el-button>-->
    </template>
  </el-dialog>

</div>
</template>

<!-- setup 组合式 api -->
<script setup>
import {onMounted, getCurrentInstance, ref, reactive, unref} from 'vue'
import apiGoods from '@/api/goods.js'
import apiDictType from "@/api/dict";

// currentPage: 1;
// const proxy = getCurrentInstance().appContext.config.globalProperties;
const { proxy } = getCurrentInstance();

// 搜索
const refsQueryForm = ref() // 表单 ref 对象
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  goodsName: undefined,
  delNum: '0',
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
const tableLoading = ref(false);
const ids = ref([]); // 选中数组
const single = ref(true); // 选中非单个禁用
const multiple = ref(true); // 选中非多个禁用
const latestData = reactive({
  consumeTime: "0000-00-00"
});

onMounted(() => {
  getList();
});

const getList = () => {
  tableLoading.value = true;
  let params = {
    ...queryParams
  };
  proxy.$get(apiGoods.getGoodsList, params).then(res => {
    tableData.value = res.rows;
    total.value = res.total;

    // 获取第一条数据时间
    if (params.pageNum === 1) {
      latestData.consumeTime = defaultDate(res.rows[0].consumeTime);
    }

    tableLoading.value = false;
  })
}

const handleCurrentChange = (val) => {
  queryParams.pageNum = val;
  getList();
}

// 多选框选中数据
const handleSelectionChange = (selection) => {
  ids.value = selection.map(item => item.id)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}

// 弹出层
const title = ref("")
const isShow = ref(false)
const formRef = ref() // 表单 ref 对象
const multipleTable = ref() // 表单 ref 对象
const defaultForm = () => {
  return {
    id: undefined,
    goodsName: undefined,
    unitPrice: 0,
    goodsNumber: 1,
    consumeTime: '',
    goodsComment: '无',
    consumeWay: '线下',
  }
}
const form = reactive(defaultForm());
const rules = {
  goodsName: {required: true, message: "不能为空", trigger: "blur"}
}

const handleAdd = () => {
  title.value = "商品添加";
  isShow.value = true;
  iniForm();
}

const handleEdit = (row) => {
  const id = row.id || ids.value;
  proxy.$get(apiGoods.getGoodsDetail, {id: id.toString()}).then(res => {
    isShow.value = true;
    Object.assign(form, res.data);
    title.value = "商品修改";
  })
}

const handleDel = (row) => {
  const id = row.id || ids.value;
  proxy.$get(apiGoods.goodsDel, {ids: id.toString()}).then(res => {
    proxy.$message.success("删除成功");
    getList();
  })
}

const handleDetail = (row) => {
  const id = row.id || ids.value;
  proxy.$get(apiGoods.getGoodsDetail,{id: id.toString()}).then(res=>{
    console.log(res.data);
  })
}

const defaultDate = (date) => {
  return proxy.$dayjs(date).format("YYYY-MM-DD");
}
const iniForm = () => {
  // form.consumeTime = proxy.$dayjs("2019-06-23 16:30:00").format("YYYY-MM-DD") + " 08:00:00";
  form.consumeTime = latestData.consumeTime;
}

const resetForm = () => {
  formRef.value.resetFields(); // 这个写在前面
  Object.assign(form, defaultForm()); // 重置表单 使用 Object.assign 赋值后的数据 resetFields();这个方法不能重置
}

const submitForm =  async () => {
  const unrefForm = unref(formRef);
  if (!unrefForm) return;

  await unrefForm.validate(valid=>{
    if (valid) {
      let params = {
        ...form,
        consumeTime: proxy.$dayjs(form.consumeTime).format("YYYY-MM-DD 08:00:00"),
      };
      if (form.id) {
        proxy.$post(apiGoods.goodsUpdate, params).then(res => {
          if (Number(res.error) === 200) {
            proxy.$message.success("修改成功");
            getList();
          } else {
            proxy.$message.error("修改失败");
          }
        }).finally(()=>{
          cancel()
        });
      }else{
        proxy.$post(apiGoods.goodsAdd, params).then(res => {
          if (Number(res.error) === 200) {
            proxy.$message.success("添加成功");
            getList();
          } else {
            proxy.$message.error("添加失败");
          }
          cancel()
        });
      }
    }
  });
}

const cancel = () => {
  resetForm();
  iniForm();
  isShow.value = false;
}


</script>

<style scoped>

</style>
