<template>
  <!-- 页面主要样式 -->
  <div class="app-container">
    <el-form ref="queryForm" :model="form.model" :inline="true" @submit.prevent @keyup.enter="handleSearch">
      <el-form-item label="字段01" prop="xx1">
        <el-input v-model="form.model.xx1" placeholder="请输入" clearable />
      </el-form-item>

      <el-form-item label="字段02" prop="xx2">
        <el-select v-model="form.model.xx2" placeholder="请选择" clearable>
          <el-option v-for="item in EXHIBITION_STATE" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>

      <el-form-item label="字段时间" prop="WEB_xx3">
        <el-date-picker
            v-model="form.model.WEB_xx3"
            type="daterange"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            format="YYYY-MM-DD HH:mm:ss"
            style="width: 230px"
            @change="xx3TimeChange"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleSearch">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10">
      <el-col :span="1.5">
        <el-button type="primary" @click="handleAdd">添加</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading"
              :data="tableData"
              border
              @selection-change="handleSelectionChange"
              :header-cell-style="{textAlign: 'center'}"
              :cell-style="{ textAlign: 'center' }"
    >
      <el-table-column type="selection" align="center" />
      <el-table-column label="序号" width="55" type="index" />
      <el-table-column label="名称" prop="label" width="185" />
      <el-table-column label="其他" prop="label" />
      <el-table-column label="未知" prop="label">
        <template #default="{ row }">
          <dict-tag :options="EXHIBITION_STATE" :value="row.label" />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="260" fixed="right">
        <template #default="{ row }">
          <el-link class="mr10" :underline="false" type="primary" @click="handleModify(row)">
            <span class="ml5">编辑</span>
          </el-link>
          <el-link class="mr10" :underline="false" type="primary" @click="handleDel(row)">
            <span class="ml5">删除</span>
          </el-link>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="暂无数据" />
      </template>
    </el-table>

    <pagination
        v-show="total > 0"
        v-model:page-num="form.current"
        v-model:page-size="form.size"
        :total="total"
        @pagination="findTableData"
    />
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useTableQuery } from './useMsgTemp.js'
const {
  form,
  handleSearch,
  resetQuery,
  xx3TimeChange,
  loading,
  total,
  tableData,
  rowData,
  findTableData,
  handleSelectionChange,
  useDictData,
  handleAdd,
  handleModify,
  handleDel
} = useTableQuery()

const { EXHIBITION_STATE } = useDictData()

onMounted(() => {
  findTableData()
})
</script>

<style scoped></style>


<!--  ************************ 外部js 文件 -->
<script>
import { ref, reactive, onMounted, unref, getCurrentInstance } from 'vue'
// import { useRoute, useRouter } from 'vue-router'
import useDebounce from '@/hooks/useDebounceSubmit'

import request from '@/utils/axios'
import { searchTemp } from '@/api/msg'
import {APILogRead, APILogSearch} from "@/api/log";
// import dayjs from 'dayjs'

export function useTableQuery() {
  // const router = useRouter()
  // const route = useRoute()
  const { proxy } = getCurrentInstance()

  // hooks 字典
  const useDictData = () => {
    const { EXHIBITION_STATE } = proxy.useDict(['EXHIBITION_STATE']) // 字典
    return {
      EXHIBITION_STATE
    }
  }

  const form = reactive({
    current: 1,
    order: 'descending',
    size: 10,
    sort: 'id',
    extra: {},
    model: {
      isDeleted: 0, // 未删除数据
      xx1: undefined, // 字段1
      xx2: undefined, // 字段2
      WEB_xx3: [],
      xx3_0: undefined, // 时间范围
      xx3_1: undefined, // 时间范围
      xx4: undefined // 字段x
    }
  })

  const handleSearch = () => {
    if (form.model) {
      form.current = 1
    }
    findTableData()
  }
  const resetQuery = () => {
    form.model.xx3_0 = undefined
    form.model.xx3_1 = undefined
    // proxy.resetForm('queryForm')
    findTableData()
  }



  const loading = ref(false)
  const multiple = ref(true)
  const single = ref(true)
  const total = ref(0)
  const ids = ref([])
  const tableData = ref([])
  const rowData = ref({}) // 行项目数据

  const initTableData = () => {
    loading.value = false
    multiple.value = true
    single.value = true
    ids.value = []
    tableData.value = []
    rowData.value = {}
  }

  const findTableData =async () => {
    initTableData()
    loading.value = true
    const res = await request.$post(searchTemp, form).finally(() => {
      loading.value = false
    })

    if (res.success) {
      const data = res.data
      tableData.value = data.records
      total.value = data.total
    }
  }

  // 多选
  const handleSelectionChange = val => {
    ids.value = val.map(v => {
      return v.id
    })
    single.value = val.length !== 1
    multiple.value = !val.length
  }
  // 其他可能有的功能 -----------------------------
  const handleAdd = row => {}
  const handleModify = row => {}
  const handleDel = row => {
    proxy
        .$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        .then(async () => {
          console.log(ids.value)

          const res = await request.$post('del')
          if (res.success) {
            proxy.$message.success('删除成功')
            handleSearch()
          }
        }).catch((e) => {
          console.log(e);
        })
  }

  const xx3TimeChange = value => {
    if (value) {
      form.model.xx3_0 = dayjs(value[0]).format('YYYY-MM-DD 00:00:00')
      form.model.xx3_1 = dayjs(value[1]).format('YYYY-MM-DD 23:59:59')
    } else {
      form.model.xx3_0 = undefined
      form.model.xx3_1 = undefined
    }
  }

  return {
    form,
    handleSearch,
    resetQuery,
    xx3TimeChange,
    loading,
    total,
    tableData,
    rowData,
    findTableData,
    handleSelectionChange,
    useDictData,
    handleAdd,
    handleModify,
    handleDel
  }
}

</script>
