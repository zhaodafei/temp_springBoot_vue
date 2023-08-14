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
  <hr>

  <div style="width: 200px;margin-top:20px">
    <h3>去掉数字框的箭头</h3>
    <el-input type="number" class="foo" v-model="feiNumber" placeholder="请输入数字"/>
    <h3>input 正则输入数字校验</h3>
    <el-input  @keyup="handleInputKeyup" v-model="feiNumber" />
    <h3>普通input 利用正则,变为数字</h3>
    <p style="color: red"> 尽量要直接在input中处理 oninput 和 onchange, 这个会与表单的v-model 出现异常空值</p>
    手机号<el-input
            v-model="feiNumber"
            placeholder="请输入手机号"
            maxlength="11"
            oninput="value=value.replace(/[^\d]/g,'')"
            @blur="e => (feiNumber.value = e.target.value)"
        />
    身份证号 <el-input
                v-model="feiNumber"
                placeholder="请输入身份证号"
                maxlength="18"
                onchange="value=value.replace(/[^\d]/g,'')"
            />

      <!-- 正确使用,但是以后项目中,尽量别使用
        <el-form-item label="手机号" prop="phoneNumber">
          <el-input
            v-model="form.phoneNumber"
            placeholder="请输入手机号"
            maxlength="11"
            oninput="value=value.replace(/[^\d]/g,'')"
            clearable
            @blur="e => (form.phoneNumber = e.target.value)"
          />
          <el-input
              v-model="form.phoneNumber"
              placeholder="请输入手机号"
              maxlength="11"
              onchange="value=value.replace(/[^\d]/g,'')"
              clearable
          />
        </el-form-item>
      -->

  </div>
  <hr>

  <div>
    <h1>树形选择</h1>
    <h3 style="color: #ff6b81"> 警告: 这个傻逼玩意,不要自定义字段,一定要使用官方的数据结构,包括字段名字,否则有各种奇怪的问题 </h3>
    <p> 当前使用版本  "element-plus": "2.0.1", ,更新后版本     "element-plus": "2.1.8",</p>
    <p>属性源码位置 node_modules/element-plus/lib/components/tree/src/tree.js </p>

    <el-tree-select v-model="selFei" :data="dataFei" />
  </div>
  <hr>

  <div>
    <h3>合并单元格</h3>
    <!--<el-table :data="tableData"
              :span-method="objectSpanMethod"
              border
              :header-cell-style="{'textAlign':'center'}"
              :cell-style="{'textAlign':'center'}"
              class="fei-table-wrapper"
              header-cell-class-name="fei-header-cell-wrapper 头部"
              cell-class-name="fei-cell-wrapper 单元格">
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
    </el-table>
    <div>
      css 样式
      .fei-table-wrapper {color: black !important;border: 1px solid black;}
      .fei-cell-wrapper, .fei-header-cell-wrapper {color: black !important;border-color: black !important;}
    </div>-->

    <h3>全选/全不选 multipleTableRef.value.toggleAllSelection(); </h3>
    <p>
      反选
      tableData.value.forEach(item => {
        multipleTableRef.value.toggleRowSelection(item, undefined);
      })
    </p>
  </div>
  <hr>

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
  <hr>

  <div>
    <h3>dialog 弹窗</h3>
    <el-button type="primary" @click="handleOpen">打开弹窗</el-button>
    <FDialog :visible="feiVisible" @close="handleClose"  />
  </div>
  <hr>

  <div>
    <h3>drawer 抽屉</h3>
    <el-button type="primary" @click="openDrawer">打开抽屉</el-button>
    <FDrawer :visible="drawerVisible" @close="closeDrawer"  />
  </div>
  <hr>

  <div>
    <h3>dialog 弹窗== 非组件</h3>
    <fDialog2 />
  </div>
  <hr>

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
  <hr>

  <div>
    <h3> 表单校验规则 </h3>
    <p> 或者参照 antdv 框架的校验 https://www.antdv.com/components/form-cn </p>
    <pre>
      const rules = {
        role: { type: 'enum', enum: ['admin', 'user', 'guest'] },
        data: [{type: 'date', required: true, trigger: 'change', message: "日期不能为空"}],
        time: [{type: 'array', required: true, trigger: 'change', message: "时间不能为空"}],
        email:[{type: 'email', required: true, trigger: ['blur', 'change'], message: "请输入正确的邮箱格式"}],
      };
    </pre>
    <h3>禁用日期范围</h3>
    <div>
      <el-date-picker
          v-model="feiTime"
          type="datetime"
          value-format="YYYY-MM-DD HH:mm:ss"
          format="YYYY-MM-DD HH:mm:ss"
          placeholder="请选择"
          :clearable="false"
          :disabled-date="disabledDate"
      />
    </div>
  </div>
  <hr>

  <div>
    <h3> 单选框,校验</h3>
    <p>
      <el-form :model="ruleForm"  ref="formRef">
        <el-form-item prop="feiName" :rules="[
            {
              required: true,
              message: 'Please select activity resource',
              trigger: 'change',
            },
          ]">
          <el-radio-group v-model="ruleForm.feiName">
            <el-radio :label="3">孔子</el-radio>
            <el-radio :label="6">孟子</el-radio>
            <el-radio :label="9">战国策</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="客户星级" prop="feiLevel" :rules="[
            {
              required: true,
              message: '请选择等级',
              type: 'enum', enum: [2, 3, 4],
              trigger: ['change','blur','focus','hoverChange','keydown']
            },
          ]">
          <el-rate v-model="ruleForm.feiLevel" clearable  :colors="['#F7BA2A', '#F7BA2A', '#F7BA2A']" />
          <span style="color: #ff6b81"> 这个校验稍微有点问题,校验后再次选择提示信息不会消失 </span>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">fei 单选框校验</el-button>
        </el-form-item>
      </el-form>
    </p>
  </div>
  <hr>

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
  <hr>

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
  <hr>

  <div>
    <h3>Feedback 反馈组件  消息提示,防止重复点击</h3>
    <el-button @click="handleMsgBox">Message Box 防止重复点击</el-button>
  </div>
  <hr>

  <div>
    <h3>Element：设置el-input宽度大小</h3>
    <p> 样式在下面 width-form </p>
    <div style="width: 500px">
      <el-form :model="widthForm" ref="widthFormRef" inline class="width-form">
        <el-form-item label="姓名" prop="feiName">
          <el-input v-model="widthForm.feiName" placeholder="请输入姓名" clearable />
        </el-form-item>

        <el-form-item label="地址" prop="feiAddress">
          <el-input v-model="widthForm.feiAddress" placeholder="请输入地址" clearable />
        </el-form-item>

        <el-form-item label="金额" prop="price">
          <el-input-number
              v-model="widthForm.price"
              :precision="2"
              placeholder="请输入金额"
              clearable
          />
        </el-form-item>

        <el-form-item label="行内长度" prop="other">
          <el-input v-model="widthForm.other" placeholder="行内长度" clearable style="width: 150px" />
        </el-form-item>
      </el-form>
    </div>
  </div>
  <hr>
  <div>
    <h3> 时间: el-time-picker 禁用范围</h3>
    <p>  demo: 如允许 17:30:00 - 18:30:00 </p>
    <div>
      <el-time-picker
          v-model="timePicker1"
          :disabled-hours="disabledHours"
          :disabled-minutes="disabledMinutes"
          :disabled-seconds="disabledSeconds"
          placeholder="任意时间点" />

    </div>
    <div>
      <el-time-picker
          v-model="timePicker2"
          is-range
          range-separator="To"
          start-placeholder="Start time"
          end-placeholder="End time"
          value-format="HH:00"
          format="HH:00"
          :disabled-minutes="disabledMinutes2"
          :disabled-seconds="disabledSeconds2"
      />
    </div>
  </div>
  <hr>


  <div class="readme-ui">
    <h3>element-plus": "2.3.7" 修复问题 </h3>
    <ol style="margin-left: 20px;">
      <li>el-input-number 可以用 @input 事件</li>
      <li>输入框 clearable 不会出现来回跳动</li>
    </ol>
    <div>
      <el-input v-model="rInputVal" placeholder="Please input" clearable style="width: 240px"  />
    </div>
    <div>
      <el-input-number v-model="rINumVal" :min="1" :max="10" @input="rChange" />
    </div>
    <div style="display: flex">
      <el-select v-model="rSel"  placeholder="请选择"  style="width: 200px">
        <el-option
            v-for="item in [{value: 'da', label: '大'},{value: 'fei', label: '飞'}]"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <div>
        <el-button @click="rSelNo">选中_no</el-button>
        <el-button @click="rSelYes">选中_ok</el-button>
      </div>
    </div>
  </div>
  <hr>

  <div>
    <h3>单张图片</h3>
    <p> 用单独的样式控制最后的加号是否显示 </p>
    <div>
      <el-upload
          :class="{ 'hide-fei': feiUrl.length >= 1 }"
          v-model:file-list="feiUrl"
          :limit="1"
          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
          list-type="picture-card"
      >
        <el-icon><Plus /></el-icon>
        <template #file="{ file }">
          <div>
            <img class="el-upload-list__item-thumbnail" :src="file.url" />
            <span class="el-upload-list__item-actions">
            <span class="el-upload-list__item-delete" @click="feiImgRemove(file)">
              <el-icon><Delete /></el-icon>
            </span>
          </span>
          </div>
        </template>
      </el-upload>
    </div>
  </div>
  <hr>

  <div>
    <h3>Vue 针对element按钮防止多次点击</h3>
    <div>
      <el-button v-reClick icon="Grid" type="primary">点击3秒后恢复</el-button>
      <el-button v-reClick="(10000)" icon="Grid" type="primary">点击10秒后恢复 </el-button>
    </div>
    <div>
      <p> 普通指令测试 </p>
      <input type="text" v-myFeiDirective>
      <input type="text" v-myFeiDirective2="{color:'blue',text:'图书_论语'}">
    </div>
  </div>
  <hr>

  <div class="readme-ui" style="margin-top: 50px;margin-bottom: 150px">
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
import {ref, reactive, onMounted, unref, getCurrentInstance, nextTick} from "vue";

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
const useInitTable = () => {
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
const {tableData, objectSpanMethod} = useInitTable();

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
const useFeiDialog = () => {
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
const {feiVisible, handleOpen, handleClose} = useFeiDialog();

// ****************************************************************************************************
import FDrawer from './components/fDrawer.vue'
// drawer  的 hooks
const useFeiDrawer = () => {
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
const {drawerVisible, openDrawer, closeDrawer} = useFeiDrawer();

// ****************************************************************************************************
// dialog 弹窗(非组件)
import fDialog2 from './components/fDialog2.vue'
// ****************************************************************************************************
const useFeiFormRadio = () => {
  const formRef = ref()
  const ruleForm = reactive({
    feiName: "",
    address: "",
    feiLevel: 1, // 这个默认值会是0
  })
  const submitForm  = async () => {
    const unrefForm = unref(formRef);
    if (!unrefForm) return;
    await unrefForm.validate((valid, invalidFields) => {
      console.log(valid, invalidFields);
      if (valid) console.log("dddd");
    })
  }

  return {formRef,ruleForm,submitForm}
}
const {formRef, ruleForm, submitForm} = useFeiFormRadio();

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
// ****************************************************************************************************
// 禁用日期范围
const feiTime = ref()
const disabledDate = (time) => {
  // 2023-01-18 之后日期都可以选
  // return time.getTime() <= new Date('2023-01-18').getTime()

  // 2023-01-18 到 2023-01-25 之间的都可以选
  return (
      time.getTime() <= new Date('2023-01-18').getTime() ||
      time.getTime() >= new Date('2023-01-25').getTime()
  )
}
// ****************************************************************************************************
// Message Box 防止重复点击
const useMsgBox = () => {
  const feiLoading = ref(false);
  const handleMsgBox = () => {
    feiLoading.value = false
    proxy.$confirm('我是内容', '标题', {
      type: 'warning',
      beforeClose: (action, instance, done) => {
        if (instance.action !== 'confirm') {
          done() // 用户直接关闭
        } else {
          if (feiLoading.value) {
            proxy.$message.warning('不要重复点击')
            return false;
          }
          feiLoading.value = true
          // 接口调用成功
          setTimeout(()=>{
            feiLoading.value = false
            done() // 调用接口ok后放行
          },3000)
        }
      }
    }).then(() => {
      feiLoading.value = false
    }).catch(() => {
      feiLoading.value = false
    })
  }
  return {handleMsgBox}
}
const {handleMsgBox} = useMsgBox();
// ****************************************************************************************************
const useWidthForm = () => {
  const widthFormRef = ref()
  const defaultForm = () => {
    return {
      feiName: undefined,
      feiAddress: undefined,
      price: 0,
      other: undefined,
    }
  }
  const widthForm = reactive(defaultForm())
  return {widthFormRef, widthForm}
}
const {widthFormRef, widthForm} = useWidthForm()
// ****************************************************************************************************
// 时间: el-time-picker 禁用范围: demo:如允许 17:30:00 - 18:30:00
const makeRange = (start, end) => {
  const result = []
  for (let i = start; i <= end; i++) {
    result.push(i)
  }
  return result
}

const timePicker1 = ref(new Date(2016, 9, 10, 18, 40))
const disabledHours = () => {
  return makeRange(0, 16).concat(makeRange(19, 23))
}
const disabledMinutes  = (hour) => {
  if (hour === 17) {
    return makeRange(0, 29)
  }
  if (hour === 18) {
    return makeRange(31, 59)
  }
}
const disabledSeconds = (hour, minute) => {
  if (hour === 18 && minute === 30) {
    return makeRange(1, 59)
  }
}
const timePicker2 = ref([])
const disabledMinutes2  = (hour) => {
  return makeRange(0, 59)
}
const disabledSeconds2 = (hour, minute) => {
  return makeRange(0, 59)
}

// ****************************************************************************************************
// element-plus 升级修复
const useVersionRepair = () => {
  const rInputVal = ref('');

  const rINumVal = ref(1);
  const rChange = (value) => {
    console.log('我改变了触发input事件',value)
  }

  const rSel = ref('')
  const rSelNo = () => {
    isSel('111111111111,这个值选不中')
  }

  const rSelYes = () => {
    isSel('fei') // 可以选中回显的一个值
  }

  const isSel = (defaultVal) => {
    let isDefaultSel = false;
    [{value: 'da', label: '大'},{value: 'fei', label: '飞'}].forEach(item => {
      if (item.value === defaultVal) {
        console.log('找到了');
        isDefaultSel = true;
      }
    })

    // tip: 先判断再赋值,避开下拉框中没有值回显value
    if (isDefaultSel) {
      rSel.value = defaultVal
    } else {
      rSel.value = undefined
    }
  }

  return {rInputVal, rINumVal, rChange, rSel, rSelNo, rSelYes}
}
const {rInputVal, rINumVal, rChange, rSel, rSelNo, rSelYes} = useVersionRepair()
// ****************************************************************************************************
// 单张图片
const useImgUpload = () => {
  const feiUrl = ref([
    {
      name:'name_01',
      url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg'
    }
  ])

  const feiImgRemove = (file) => {
    console.log('可以移除了',file)
    const fIndex = feiUrl.value.map(f => f.name).indexOf(file.name)
    if (fIndex > -1) {
      feiUrl.value.splice(fIndex,1)
      alert('删除成功了')
    }
  }

  return {feiUrl, feiImgRemove}
}

const {feiUrl, feiImgRemove} = useImgUpload()
// ****************************************************************************************************
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

<style scoped lang="scss">
.width-form{
  .el-form-item {
    .el-input {
      width: 260px;
    }
    .el-select {
      width: 260px;
    }
    .el-input-number {
      width: 260px;
    }
  }
}
</style>

<style scoped lang="scss">
// .el-upload--picture-card 控制加号部分
:deep(.hide-fei .el-upload--picture-card) {
  display: none;
}

// 去掉动画效果
:deep(.el-list-enter-active),
:deep(.el-list-leave-active) {
  transition: all 0s;
}

:deep(.el-list-enter, .el-list-leave-active) {
  opacity: 0;
  transform: translateY(0);
}

// 禁用
:deep(.disabledUpload) {
  .el-upload {
    cursor: not-allowed;
  }
}
</style>
