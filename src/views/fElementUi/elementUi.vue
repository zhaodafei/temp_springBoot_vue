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
    <!--<el-table :data="tableData"
              :span-method="objectSpanMethod"
              border
              :header-cell-style="{'textAlign':'center'}"
              :cell-style="{'textAlign':'center'}">
      <el-table-column label="序号"  width="55" type="index" />
      &lt;!&ndash;<el-table-column label="xxx" prop="showRow" />&ndash;&gt;
      <el-table-column label="课程名称" prop="xxx" />
      <el-table-column label="姓名" prop="xx1" />
      <el-table-column label="学习时间" prop="xx2" />
      <el-table-column label="xx3" prop="xx3" />
      <el-table-column label="xx4" prop="xx4" />
      <template #empty>
        <el-empty description="暂无数据" />
      </template>
    </el-table>-->
    <h3>全选/全不选 multipleTableRef.value.toggleAllSelection(); </h3>
    <p>
      反选
      tableData.value.forEach(item => {
        multipleTableRef.value.toggleRowSelection(item, undefined);
      })
    </p>
  </div>

  <div>
    <h3>自动补全输入框</h3>
    <p> 输入框可以输入,又可以选择 </p>
    <el-autocomplete
        v-model="autoFei"
        :fetch-suggestions="querySearch"
        clearable
        class="inline-input w-50"
        placeholder="请输入发票抬头"
        :debounce="300"
        value-key="valueFei"
        @select="handleSelect"
    />
  </div>

  <div>
    <h3>dialog 弹窗</h3>
    <el-button type="primary" @click="handleOpen">打开弹窗</el-button>
    <FDialog :visible="feiVisible" @close="handleClose"  />
  </div>

  <div>
    <h3>drawer 抽屉</h3>
    <el-button type="primary" @click="openDrawer">打开抽屉</el-button>
    <FDrawer :visible="drawerVisible" @close="closeDrawer"  />
  </div>

  <div>
    <h3>下拉框 获取key和label</h3>
    <p> const {proxy} = getCurrentInstance(); </p>
    <el-select v-model="feiVal" ref="feiRefs"  @change="handleChange">
      <el-option
          v-for="item in [{value: 'da', label: '大'},{value: 'fei', label: '飞'}]"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      />
    </el-select>
  </div>

  <div>
    <h3> 表单校验规则 </h3>
    <p> 或者参照 antdv 框架的校验 https://www.antdv.com/components/form-cn </p>
    <pre>
      const rules = {
        role: { type: 'enum', enum: ['admin', 'user', 'guest'] },
        data: [{type: 'date', required: true, trigger: 'change', message: "日期不能为空"}],
        time: [{type: 'array', required: true, trigger: 'change', message: "时间不能为空"}],
      };
    </pre>
  </div>

  <div>
    <h3> 复选框,取值不要 ture  和 false</h3>
    <p>
      <el-checkbox-group > <!-- v-model="form.spaceId" -->
        <el-checkbox value="fei" label="fei">大飞</el-checkbox>
      </el-checkbox-group>
    </p>
    <h3>开关自定义值</h3>
    <p>
      <el-switch
          v-model="feiNumber"
          :active-value="1"
          :inactive-value="0"
          active-color="#13ce66"
          inactive-color="#ff4949"
      />
    </p>
  </div>

  <div>
    <h3>  Form-Item 插槽使用 </h3>
    <p>
      <el-form>
        <el-form-item>
          <template #label>
            自定义label飞:
            <!--<el-popover placement="top" :width="50" trigger="hover" content="我是说明提示">
              <template #reference>
                <el-icon style="vertical-align: sub; cursor: pointer"><Warning/></el-icon>
              </template>
            </el-popover>-->
            <el-icon style="vertical-align: sub; cursor: pointer"><Warning/></el-icon>
          </template>
          <template #default>
            <el-input v-model="feiNumber"  style="width: 200px"/>
          </template>
        </el-form-item>
      </el-form>
    </p>
  </div>




  <div class="readme-ui" style="margin-bottom: 150px">
    <h2 style="background-color:#90ee90;color: #fff">开发注意事项:</h2>
    <dl>
      <dt>表单数据处理 </dt>
      <dd>
        <ol>
          <li>表单正常重置使用 formRef.value.resetFields(); </li>
          <li>对话框中重置初始化操作,在关闭弹窗事件中操作</li>
          <li>表单在开发阶段写好校验</li>
        </ol>
      </dd>

      <dt>抽屉必须销毁 </dt>
      <dd>
        <ol>
          <li>抽屉必须销毁 &lt;el-drawer /&gt;  destroy-on-close    同时重置表单</li>
        </ol>
      </dd>

      <dt>抽屉必须销毁 </dt>
      <dd>
        <ol>
          <li>对话框必须销毁 &lt;el-dialog /&gt;   destroy-on-close  同时重置表单</li>
        </ol>
      </dd>

      <dt>xxx </dt>
      <dd>
        <ol>
          <li>xxx</li>
        </ol>
      </dd>
    </dl>
  </div>

</template>

<script setup>
import {ref, onMounted, unref, getCurrentInstance, nextTick} from "vue";

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
const useFeiHooks = () => {
  const foo = () => {
    console.log("使用hooks,调用成功");
  }
  return {foo}
}
onMounted(()=>{
  const {foo} = useFeiHooks();
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

// ****************************************************************************************************
const autoFei = ref();
const querySearch = (val,cb) => {
  let resData = [
    {valueFei: 'vue', link: 'https://github.com/vuejs/vue'},
    {valueFei: 'element', link: 'https://github.com/ElemeFE/element'},
    {valueFei: 'cooking', link: 'https://github.com/ElemeFE/cooking'},
  ];

  cb(resData)
}
const handleSelect = (val) => {
  console.log("选中行项目___",val);
}

// ****************************************************************************************************
import FDialog from './components/fDialog.vue'
// dialog 的 hooks
const feiDialog = () => {
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

  return {feiVisible,handleOpen,handleClose}
}
const {feiVisible, handleOpen, handleClose} = feiDialog();

// ****************************************************************************************************
import FDrawer from './components/fDrawer.vue'
// drawer  的 hooks
const feiDrawer = () => {
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

  return {drawerVisible, openDrawer, closeDrawer}
}
const {drawerVisible, openDrawer, closeDrawer} = feiDrawer();

// ****************************************************************************************************
// 下拉框获取值
const feiRefs = ref();
const feiVal = ref(undefined);
const handleChange = (val) => {
  console.log("下标值__",val);
  // 01) 使用循环遍历
  // 02) 使用 proxy.$refs 获取
  nextTick(()=>{
    console.log("获取key和label",proxy.$refs.feiRefs.selected);
    console.log("获取label",proxy.$refs.feiRefs.selectedLabel);
  })
}
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

<style scoped lang="scss">
.readme-ui{
  dt {
    font-weight: bold;
  }

  dd {
    margin-left: 40px;
  }

  ol{
    li{
      list-style-type: decimal;
    }
  }
}
</style>
