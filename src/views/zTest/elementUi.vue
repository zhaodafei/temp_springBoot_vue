<template>
  <div>
    <h3>按钮加载中</h3>
    <p>
      v-loading在表单或表格上使用，（可理解为页面加载）
      :loading在按钮上使用
    </p>
    <el-button :loading="true">加载中1</el-button>
    <el-button v-loading="true">加载中1</el-button>
  </div>

  <div style="width: 200px;margin-top:20px">
    <h3>去掉数字框的箭头</h3>
    <el-input type="number" class="foo" v-model="feiNumber" placeholder="请输入数字"/>
    <h3>input 正则输入数字校验</h3>
    <el-input  @keyup="handleInputKeyup" v-model="feiNumber" />
  </div>

  <div>
    <h1>树形选择</h1>
    <h3 style="color: #ff6b81"> 警告: 这个傻逼玩意,不要自定义字段,一定要使用官方的数据结构,包括字段名字,否则有各种奇怪的问题 </h3>
    <p> 当前使用版本  "element-plus": "2.0.1", ,更新后版本     "element-plus": "2.1.8",</p>
    <p>属性源码位置 node_modules/element-plus/lib/components/tree/src/tree.js </p>

    <el-tree-select v-model="selFei" :data="dataFei" />
  </div>

  <div>
    <h3>合并单元格</h3>
    <el-table :data="tableData"
              :span-method="objectSpanMethod"
              border
              :header-cell-style="{'textAlign':'center'}"
              :cell-style="{'textAlign':'center'}">
      <el-table-column label="序号"  width="55" type="index" />
      <!--<el-table-column label="xxx" prop="showRow" />-->
      <el-table-column label="课程名称" prop="xxx" />
      <el-table-column label="姓名" prop="xx1" />
      <el-table-column label="学习时间" prop="xx2" />
      <el-table-column label="xx3" prop="xx3" />
      <el-table-column label="xx4" prop="xx4" />
    </el-table>
  </div>
</template>

<script setup>
import {getCurrentInstance} from "vue";
import {ref,onMounted,unref} from "vue";

const {proxy} = getCurrentInstance();
// ****************************************************************************************************
// 测试 proxy 编辑器代码提示
function fun1() {
  proxy.$confirm('是否确认删除?','警告',{
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(()=>{
    console.log('ok');
  }).catch(()=>{
    console.log('cancel');
  })
}

// ****************************************************************************************************
const feiNumber = ref(); // 数字输入框
const handleInputKeyup = e => {
  e.target.value = e.target.value.replace(/[^\d]/g, '').replace(/^0{1,}/g, '')
}
// ****************************************************************************************************
// 单页面中 hooks 简单使用
const feiHooks = () => {
  const foo = () => {
    console.log("使用hooks,调用成功");
  }
  return {foo}
}
onMounted(()=>{
  const {foo} = feiHooks();
  foo();
})
// ****************************************************************************************************
// 树形选择
const selFei = ref(); // 单选
const selFei_mul = ref(['1-1-1','2-1-1']); // 多选
const dataFei = [
  {
    value: '1',
    label: 'Level one 1',
    children: [
      {
        value: '1-1',
        label: 'Level two 1-1',
        children: [
          {
            value: '1-1-1',
            label: 'Level three 1-1-1',
          },
        ],
      },
    ],
  },
  {
    value: '2',
    label: 'Level one 2',
    children: [
      {
        value: '2-1',
        label: 'Level two 2-1',
        children: [
          {
            value: '2-1-1',
            label: 'Level three 2-1-1',
          },
        ],
      },
    ],
  },
  {
    value: '3',
    label: 'Level one 3',
    children: [
      {
        value: '3-1',
        label: 'Level two 3-1',
        children: [
          {
            value: '3-1-1',
            label: 'Level three 3-1-1',
          },
        ],
      }
    ],
  },
]
// ****************************************************************************************************
// 合并单元格
const InitTable = () => {
  const tableData = ref([]);
  const objectSpanMethod = (rowData) => {
    const {column, rowIndex, columnIndex,row} = rowData;
    if (columnIndex === 1) { // 对第几列准备合并
      return {
        rowspan: row.showRow, //  // undefined 默认显示一行
        colspan: 1,
      };
    }
  }

  onMounted(()=>{
    tableData.value= [
      {xxx: "fei_JavaScript", xx1: "姓名一", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei",},
      {xxx: "fei_JavaScript", xx1: "姓名一", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei",},

      {xxx: "foo_HTML", xx1: "姓名_2", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei2",},
      {xxx: "foo_HTML", xx1: "姓名_2", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei2",},
      {xxx: "foo_HTML", xx1: "姓名_2", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei2",},

      {xxx: "bar_CSS", xx1: "姓名+a", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei",},
      {xxx: "bar_CSS", xx1: "姓名+b", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei",},
      {xxx: "bar_CSS", xx1: "姓名+c", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei",},
      {xxx: "bar_CSS", xx1: "姓名+d", xx2: "2022-01-01", xx3: "2022-02-01", xx4: "daFei",},
    ]

    let len = unref(tableData).length; // 数组长度
    let start = 1; // 每次开始计数初始值
    let start_index = 0; // 每次合并开始数组索引
    for (let i = 0; i < len; i++) {
      if (i === 0) {
        tableData.value[0].showRow = 1;
      }else{
        // 判断相同行的值
        if (tableData.value[i].xxx === tableData.value[i - 1].xxx) {
          start++;
        }
        tableData.value[start_index].showRow = start;
        if (tableData.value[i].xxx !== tableData.value[i - 1].xxx) {
          start = 1;
          start_index = i;
          tableData.value[i].showRow = start;
        }
      }
    }
  })

  return {tableData,objectSpanMethod,onMounted}
}
const {tableData, objectSpanMethod} = InitTable();

</script>

<style scoped lang="scss">
:deep(.foo){ // 去掉数字框的箭头  或者使用 :deep(.foo) ::v-deep(.foo)
  input::-webkit-outer-spin-button,
  input::-webkit-inner-spin-button{
    -webkit-appearance: none !important;
    -moz-appearance: none !important;
    -o-appearance: none !important;
    -ms-appearance: none !important;
    appearance: none !important;
    margin: 0;
  }
  input[type="number"]{
    -webkit-appearance:textfield;
    -moz-appearance:textfield;
    -o-appearance:textfield;
    -ms-appearance:textfield;
    appearance:textfield;
  }
}

</style>
