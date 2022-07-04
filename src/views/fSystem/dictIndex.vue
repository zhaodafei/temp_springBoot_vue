<template>
  <div>
    <el-form ref="refsQueryForm" :model="queryParams" :inline="true">
      <el-form-item prop="dictName">
        <el-input v-model="queryParams.dictName" placeholder="字典名称" ></el-input>
      </el-form-item>

      <el-form-item prop="dictType">
        <el-input v-model="queryParams.dictType" placeholder="字典类型" ></el-input>
      </el-form-item>

      <el-form-item prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择字典状态">
          <el-option
              v-for="item in statusOptions"
              :key="item.value"
              :label="item.name"
              :value="item.value"
          >
          </el-option>
        </el-select>
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
        <el-button type="success" plain icon="Edit" @click="handleEdit">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" @click="handleDel">删除</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" stripe style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column prop="dictName" label="字典名称" align="center" />
      <el-table-column prop="dictType" label="字典类型" align="center" />
      <el-table-column prop="status" label="字典状态" align="center" :formatter="stateFormat"/>
      <el-table-column prop="remark" label="备注" align="center" />
      <el-table-column prop="remark" label="备注" align="center">
        <template #default="scope">
          <!--<router-link to="/dict-data"> 字典内容 </router-link>-->
          <!--<router-link :to="{ name: 'dict-data', params: { dictId: scope.row.dictId }}"> 字典内容 </router-link>-->
          <router-link class="aLink" :to="{ name: 'dict-data', query: { dictId: scope.row.dictType }}"> 字典内容 </router-link>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination background
                   layout="prev, pager, next"
                   :total="total"
                   :pageSize="queryParams.pageSize"
                   @current-change="handleCurrentChange">
    </el-pagination>

    <el-dialog :title="title" v-model="isShow" width="600px" :close-on-click-modal="false">
      <el-form ref="formRef" :model="form"  label-width="120px">
        <el-form-item label="字典名称" prop="dictName">
          <el-input v-model="form.dictName" placeholder="字典名称" ></el-input>
        </el-form-item>

        <el-form-item label="字典类型" prop="dictType">
          <el-input v-model="form.dictType" placeholder="字典类型" ></el-input>
        </el-form-item>

        <el-form-item label="字典状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
                v-for="dict in statusOptions"
                :key="dict.value"
                :label="dict.value"
            >{{dict.name}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="备注" prop="dictName">
          <el-input v-model="form.remark" placeholder="备注" ></el-input>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button type="primary" plain @click="submitForm">确定</el-button>
        <el-button plain @click="cancel">取消</el-button>
        <el-button plain @click="resetForm">重置_fei</el-button>
        <el-button plain @click="foo">赋值_fei</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import apiDictType from '@/api/dict.js'

const app = getCurrentInstance().appContext.config.globalProperties;
const { proxy } = getCurrentInstance()

// 搜索
const refsQueryForm = ref() // 表单 ref 对象
const queryParams = reactive({
  pageNum: 1,
  pageSize: 20,
  dictName: '',
  dictType: '',
  status: '0',
});

const statusOptions = [
  {name:"正常", value: '0'},
  {name:"删除", value: '1'},
]

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
const ids = ref([]); // 选中数组
const single = ref(true); // 选中非单个禁用
const multiple = ref(true); // 选中非多个禁用


onMounted(() => {
  getList();
});

const getList = () => {
  let params = {
    ...queryParams
  };
  app.$get(apiDictType.getDictTypeList, params).then(res => {
    tableData.value = res.rows;
    total.value = res.total;
  })
}

const handleCurrentChange = (val) => {
  queryParams.pageNum = val;
  getList();
}

const stateFormat = (row, column) => {
  return  Number(row.status) === 1 ? "停用" : "正常";
}

// 多选框选中数据
const handleSelectionChange = (selection) => {
  ids.value = selection.map(item => item.dictId)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}


// 弹出层
const title = ref("")
const isShow = ref(false)
const formRef = ref() // 表单 ref 对象
const defaultForm = () => {
  return {
    dictId: undefined,
    dictName: '',
    dictType: '',
    status: '0',
    remark: "无",
  }
}
const form = reactive(defaultForm());

// const statusOptions = ref([
//   {name: "正常", value: '0'},
//   {name: "停用", value: '1'},
// ]);

const handleAdd = () => {
  title.value = "商品添加";
  isShow.value = true;
}

const handleEdit = (row) => {
  const id = row.dictId || ids.value;
  app.$get(apiDictType.getDictTypeDetail, {dictId: id.toString()}).then(res=>{
    isShow.value = true;
    Object.assign(form, res.data);
    title.value = "商品修改";
  })
}

const handleDel = (row) => {
  console.log(proxy);
  proxy.$confirm('是否确认删除?','警告',{
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(()=>{
    const id = row.dictId || ids.value;
    app.$get(apiDictType.dictTypeDel, {dictIds: id.toString()}).then(res=>{
      app.$message.success("删除成功");
      getList();
    })
  }).catch(()=>{
  })
}

const delLoading = ref(false);

const initForm = () => {
  Object.assign(form, defaultForm());
}

const foo = () => {
  form.dictName = "测试aaa";
  form.dictType = '测试bbb';
}
const resetForm = () => {
  formRef.value.resetFields(); // 对于重新赋值的不友好
  initForm()
}

const submitForm = () => {
  let params = {
    ...form
  };
  if (form.dictId) {
    app.$post(apiDictType.dictTypeUpdate, params).then(res => {
      if (Number(res.error) === 200) {
        app.$message.success("修改成功");
        getList();
      } else {
        app.$message.error("修改失败");
      }
    }).finally(()=>{
      cancel()
    });
  }else{
    app.$post(apiDictType.dictTypeAdd, params).then(res => {
      if (Number(res.error) === 200) {
        app.$message.success("添加成功");
        getList();
      } else {
        app.$message.error("添加失败");
      }
    }).finally(()=>{
      cancel()
    });
  }

}

const cancel = () => {
  resetForm();
  isShow.value = false;
}

</script>

<style scoped lang="scss">
.aLink{
  color: #1890ff;
}
</style>
