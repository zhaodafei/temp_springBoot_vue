<template>
  <!-- 说明: 参数 uploadFileList 为图片地址:  const uploadFileList = ref('')
    demo1:
    <FeiUpload v-model="uploadFileList"
               list-type="text"
               :isShowTip="false"
               :isShowFileList="false"
               :isUpdateParent="true"
               :otherData="{}"
               @updateParent="updateParent"
               :fileType="['jpeg', 'rar', 'zip', 'doc', 'docx', 'pdf']"
               :fileSize="50">
      表格列表中行项目上传修改数据使用demo
    </FeiUpload>
  -->

  <div class="component-upload-image">
    <!-- :list-type 的取值为 "text" | "picture" | "picture-card" 三个中的一个  -->
    <el-upload
      :accept="accept"
      :action="uploadImgUrl"
      :list-type="!listType ? 'text' : listType"
      :on-success="handleUploadSuccess"
      :before-upload="handleBeforeUpload"
      :limit="limit"
      :on-error="handleUploadError"
      :on-exceed="handleExceed"
      name="file"
      :on-remove="handleRemove"
      :show-file-list="isShowFileList"
      :headers="headers"
      :data="uploadData"
      :file-list="fileList"
      :on-preview="handlePreview"
      :disabled="disabled"
      :class="{ hide: fileList.length >= limit, disabledUpload: disabled }"
    >
      <!-- 图片 card 上传 -->
      <el-icon v-if="listType === 'picture-card'" class="avatar-uploader-icon">
        <plus />
      </el-icon>
      <!-- 文本格式上传 -->
      <el-button v-else-if="listType === 'text'" type="text">
        <slot>点击上传</slot>
      </el-button>
      <!-- 按钮上传 -->
      <el-button v-else type="primary" icon="upload">
        <slot>点击上传</slot>
      </el-button>
    </el-upload>

    <!-- 上传提示 -->
    <div v-if="showTip" class="el-upload__tip">
      请上传
      <template v-if="fileSize">
        大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b>
      </template>
      <template v-if="fileType">
        格式为 <b style="color: #f56c6c">{{ fileType.join('/') }}</b>
      </template>
      的文件
    </div>

    <!-- 预览 -->
    <el-dialog v-model="dialogVisible" title="预览" width="800px" append-to-body>
      <img :src="dialogImageUrl" style="display: block; max-width: 100%; margin: 0 auto" />
    </el-dialog>
  </div>
</template>

<script setup>
  import { getCurrentInstance, ref, watch, computed } from 'vue'
  import { ElMessage, ElLoading } from 'element-plus'
  // import { getToken } from '@/utils/auth'

  // 预览
  const dialogImageUrl = ref('')
  const dialogVisible = ref(false)
  const handlePreview = uploadFile => {
    dialogImageUrl.value = uploadFile.url
    dialogVisible.value = true
  }

  const accept = ref('') // 接受上传的文件类型(打开上传时候减少多余文件) .jpeg,.jpg,.png,.gif,.bmp
  const props = defineProps({
    modelValue: {
      type: String,
      default: '' // url:'文件地址'
    },
    // 图片数量限制
    limit: {
      type: Number,
      default: 1 // 这里限制为1个
    },
    // 大小限制(MB)
    fileSize: {
      type: Number,
      default: 5
    },
    // 文件类型,例如['png', 'jpg', 'jpeg', "doc", "xls", "ppt", "txt", "pdf"]
    fileType: {
      type: Array,
      default: () => ['png', 'jpg', 'jpeg']
    },
    // 是否显示提示
    isShowTip: {
      type: Boolean,
      default: true
    },
    isShowFileList: {
      type: Boolean,
      default: true
    },
    disabled: {
      type: Boolean,
      default: false
    },
    listType: {
      type: String, // 'text' | 'picture' | 'picture-card'
      default: '' // picture-card,text
    },
    isUpdateParent: {
      // 是否通知父组件 ------ 在行项目上传修改文件使用
      type: Boolean,
      default: false
    },
    otherData: {
      // 父组件携带数据,等文件上传成功后返回给父组件 ------ 在行项目上传修改文件使用
      type: Object,
      default: () => {}
    }
  })

  const { proxy } = getCurrentInstance()
  const emit = defineEmits(['update:modelValue', 'updateParent', 'uploadSuccess']) // updateParent 更新父组件,父组件做其他事情
  const uploadImgUrl = ref(import.meta.env.VITE_APP_BASE_API + '/file/upload-file') // 上传的图片服务器地址
  // const headers = ref({ token: 'Bearer ' + getToken() })
  const uploadData = ref({
    bucketName: 'pvenue',
    feiParams_01: '上传参数fei',
    feiParams_02: '上传参数fei'
  })
  const fileList = ref([]) // 用来反显的的数据
  const rawFileList = ref([]) // 用来使用的数据
  const showTip = computed(() => props.isShowTip && (props.fileType || props.fileSize))

  watch(
    () => props.modelValue,
    imgUrl => {
      accept.value = props.fileType.length > 0 ? props.fileType.map(item => '.' + item).toString() : ''

      if (imgUrl && imgUrl.length > 0) {
        let temp_val = [
          {
            name: 'temp_name_' + new Date().getTime(),
            url: imgUrl
          }
        ]

        fileList.value = temp_val
        rawFileList.value = temp_val
      } else {
        fileList.value = []
        rawFileList.value = []
        return []
      }
    },
    {
      immediate: true,
      deep: true
    }
  )

  const msgTip = (msg = '===', type = 'warning') => {
    ElMessage({
      type: type, // info, success, warning, info, error
      message: msg
    })
  }

  // 删除图片
  function handleRemove(file, files) {
    rawFileList.value = []
    emit('update:modelValue', '')
  }

  // 上传成功回调
  function handleUploadSuccess(res) {
    console.log('成功了', res)
    // let fei = {
    //   path: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg',
    //   size: '24',
    //   fileName: 'z_fei001',
    //   subfix: '.png'
    // }
    const resData = {
      name: res.data.fileName,
      url: res.data.path
    }

    rawFileList.value = [resData]
    emit('update:modelValue', resData.url)

    if (props.isUpdateParent) {
      emit('updateParent', {
        update: true, // 通知父组件做其他业务
        fileData: resData, // 上传后的文件信息
        otherData: props.otherData // 父组件携带数据
      })
    }

    emit('uploadSuccess', props.modelValue)
  }

  // 上传前loading加载, 上传前校检格式和大小
  function handleBeforeUpload(file) {
    let isImg = false
    // 校检文件类型
    if (props.fileType.length) {
      let fileExtension = ''
      if (file.name.lastIndexOf('.') > -1) {
        fileExtension = file.name.slice(file.name.lastIndexOf('.') + 1)
      }
      isImg = props.fileType.some(type => {
        if (file.type.indexOf(type) > -1) return true
        if (fileExtension && fileExtension.indexOf(type) > -1) return true
        return false
      })
    } else {
      isImg = file.type.indexOf('image') > -1
    }
    if (!isImg) {
      msgTip(`文件格式不正确, 请上传${props.fileType.join('/')}图片格式文件!`)
      return false
    }

    // 限制不能上传
    console.log('文件信息', file)
    if (file.name.includes('.xlsx')) {
      msgTip('文件格式不正确')
      return false
    }
    // 限制不能上传空白文件
    if (file.size === 0) {
      msgTip('请不要上传空白文件')
      return false
    }
    if (props.fileSize) {
      // console.log('文件大小', props.fileSize)
      const isLt = file.size / 1024 / 1024 < props.fileSize
      if (!isLt) {
        msgTip(`上传头像图片大小不能超过 ${props.fileSize} MB!`)
        return false
      }
    }
  }

  // 文件个数超出
  function handleExceed() {
    msgTip(`上传文件数量不能超过 ${props.limit} 个!`)
  }

  // 上传失败
  const handleUploadError = response => {
    response.toString().replace(/\{(.*?)\}/, str => {
      const data = JSON.parse(str)
      if (data.code === 1002 || data.code === 1003) {
        msgTip('请按照要求上传文件')
      } else {
        msgTip(data.msg || data.errorMsg)
      }
    })
  }
</script>

<style scoped lang="scss">
  // .el-upload--picture-card 控制加号部分
  :deep(.hide .el-upload--picture-card) {
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
