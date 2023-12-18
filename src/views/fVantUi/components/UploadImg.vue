<template>
  <!-- 上传多张图片
      demo1:简单基本使用
      <UploadImage v-model="uploadFileList_fei" :limit="3"/>

      demo2:
      <UploadImage
            v-model="uploadFileList_fei"
            :disabled="false"
            :isDeletable="true"
            :fileType="['png']"
            :fileSize="50"
            :isUpdateParent="true"
            :otherData="{fei:'我是数据'}"
            @updateParent="updateParent"
            :limit="3" />
  -->

  <div> 打开注释使用 </div>
 <!-- <van-uploader
      v-model="fileList"
      :max-count="limit"
      :disabled="disabled"
      :accept="acceptFileType"
      :before-read='beforeRead'
      :after-read="afterRead"
      :deletable="isDeletable"
      :before-delete="beforeDelete">

    <div v-if="listType === 'picture-card'" class="van-uploader__upload">
      <van-icon name="photograph" class-prefix="van-badge__wrapper van-icon van-icon-photograph van-uploader__upload-icon" />
    </div>
    <van-button v-else type="primary">
      <slot>上传文件</slot>
    </van-button>

  </van-uploader>-->
</template>

<script setup>
// import {computed, ref, watch} from "vue";
// import { showNotify} from 'vant'
// import {uploadAPI} from "@/api/uploadAPI.js"
//
// const props = defineProps({
//   modelValue: {
//     type: Array,
//     default: [] // [{ url:'文件地址', name:'文件名称'}]
//   },
//   // 图片数量限制
//   limit: {
//     type: Number,
//     default: 5
//   },
//   // 大小限制(MB)
//   fileSize: {
//     type: Number,
//     default: 5
//   },
//   // 文件类型,例如['png', 'jpg', 'jpeg', "doc", "xls", "ppt", "txt", "pdf"]
//   fileType: {
//     type: Array,
//     default: () => ['png', 'jpg', 'jpeg']
//   },
//   // 是否显示提示
//   isShowTip: {
//     type: Boolean,
//     default: true
//   },
//   isShowFileList: {
//     type: Boolean,
//     default: true
//   },
//   disabled: {
//     type: Boolean,
//     default: false
//   },
//   listType: {
//     type: String,
//     default: 'picture-card' // picture-card, btn
//   },
//   isUpdateParent: {
//     // 是否通知父组件 ------ 在行项目上传修改文件使用
//     type: Boolean,
//     default: false
//   },
//   otherData: {
//     // 父组件携带数据,等文件上传成功后返回给父组件 ------ 在行项目上传修改文件使用
//     type: Object,
//     default: () => {}
//   },
//   isDeletable:{
//     // 是否可以删除
//     type: Boolean,
//     default: true
//   }
// })
// const emits = defineEmits(['update:modelValue', 'updateParent', 'uploadSuccess']) // updateParent 更新父组件,父组件做其他事情
//
// // const acceptFileType = '.xls'
// const acceptFileType = computed(() => props.fileType.map(item => `.${item}`).join())
//
// const msgTip = (msg = '===',type = 'danger') => {
//   showNotify({type: type, message: msg, duration: 0});
// }
//
// // 用来反显的的数据
// const fileList = ref([
//   // {
//   //   url: 'https://fastly.jsdelivr.net/npm/@vant/assets/tree.jpeg',
//   //   name:'我是图片名字'
//   // },
//   // {
//   //   url: 'https://fastly.jsdelivr.net/npm/@vant/assets/tree.jpeg',
//   //   name:'我是图片名字_2'
//   // }
// ])
// const rawFileList = ref([]) // 用来使用的数据
//
// watch(
//     () => props.modelValue,
//     val => {
//       if (val && val.length > 0) {
//
//         // console.log("sssssssss", val);
//         if (val.length > props.limit) {
//           console.log("%c 图片数量超过限制数量,请处理", "color:#FB406BFF")
//         }
//
//         // 统一文件字段名称
//         let temp_val = val.map(item => {
//           return {
//             name: item.name,
//             url: item.url
//           }
//         });
//
//         fileList.value = temp_val
//         rawFileList.value = temp_val
//       } else {
//         fileList.value = [];
//         return [];
//       }
//     },
//     {
//       immediate: true,
//       deep: true
//     }
// )
//
// // 删除图片
// const beforeDelete = (file) => {
//   const findIndex = rawFileList.value.map(f => f.name).indexOf(file.name)
//   if (findIndex > -1) {
//     rawFileList.value.splice(findIndex, 1)
//     emits('update:modelValue', rawFileList.value)
//     return true
//   }
//
//   return false;
// }
//
// // 上传前校验数据
// const beforeRead = (file) => {
//   // console.log('这里做一些校验,文件信息',file);
//
//   let isImg = false
//   if (props.fileType.length) {
//     let fileExtension = ''
//     if (file.name.lastIndexOf('.') > -1) {
//       fileExtension = file.name.slice(file.name.lastIndexOf('.') + 1)
//     }
//     isImg = props.fileType.some(type => {
//       if (file.type.indexOf(type) > -1) return true
//       if (fileExtension && fileExtension.indexOf(type) > -1) return true
//       return false
//     })
//   } else {
//     isImg = file.type.indexOf('image') > -1
//   }
//
//   if (!isImg) {
//     msgTip(`文件格式不正确, 请上传${props.fileType.join('/')}图片格式文件!`)
//     return false
//   }
//
//   // 限制不能上传空白文件
//   if (file.size === 0) {
//     msgTip('请不要上传空白文件')
//     return false
//   }
//   if (props.fileSize) {
//     const isLt = file.size / 1024 / 1024 < props.fileSize
//     if (!isLt) {
//       msgTip(`上传头像图片大小不能超过 ${props.fileSize} MB!`)
//       return false
//     }
//   }
//
//   // 最后返回true,才会执行 afterRead 方法
//   return true
// }
//
// // 开始上传
// const afterRead = (file) => {
//   const formData = new FormData();
//   formData.append("file", file.file);
//   formData.append("bucketName", "tj_guide_h5");
//   file.status = 'uploading';
//   file.message = '上传中...';
//
//   // 接口类型改为POST: application/x-www-form-urlencoded
//   uploadAPI(formData).then(res=>{
//     if (res.code === 200) {
//       // res.data 的demo用例值
//       // let fei = {
//       //   "path": "https://fastly.jsdelivr.net/npm/@vant/assets/tree.jpeg",
//       //   "fileName": "文件名字",
//       //   "size": 24,
//       //   "subfix": ".png"
//       // }
//       file.status = 'done';
//       const resData = {
//         // ...res.data,
//         name: res.data.fileName,
//         url: res.data.path
//       };
//       rawFileList.value.push(resData)
//       emits('update:modelValue', rawFileList.value)
//
//       if (props.isUpdateParent) {
//         emits('updateParent', {
//           update: true, // 通知父组件做其他业务
//           fileData: resData, // 上传后的文件信息
//           otherData: props.otherData // 父组件携带数据
//         })
//       }
//
//       emits('uploadSuccess', props.modelValue)
//     }
//   })
// }
</script>

<style scoped>

</style>
