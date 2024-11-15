<template>
  <!-- 图片预览 -->
  <el-dialog v-model="previewImgVisible" title="预览" width="800px" append-to-body destroy-on-close>
    <img :src="previewImageUrl" style="display: block; max-width: 100%; margin: 0 auto" />
  </el-dialog>
  <!-- word预览 -->
  <el-dialog v-model="previewWordVisible" title="预览" width="800px" append-to-body destroy-on-close>
    <el-alert title="下载后预览" type="warning" show-icon :closable="false" />
    <div ref="wordRef"></div>
  </el-dialog>
</template>

<script setup>
import { reactive, ref} from "vue";
import axios from 'axios'
import { renderAsync } from 'docx-preview'
import { saveAs } from 'file-saver'
import { ElMessageBox, ElMessage } from 'element-plus'

const getSuffix = fileUrl => {
  const lastIndex = fileUrl.lastIndexOf('.')
  return fileUrl.substring(lastIndex, fileUrl.length) // 返回文件后缀名字
}

const previewImgVisible = ref(false)
const previewImageUrl = ref('')
const previewWordVisible = ref(false)
const wordRef = ref(null)
/**
 *  传递当前点击的数据项
 * @param row  数据
 * @param keyName  字段名称
 */
const handlePreview = (row, keyName) => {
  // 预览图片
  const urlKeyName = keyName ?? 'fileUrl'
  const fileSuffix = getSuffix(row.fileUrl)

  if (row.fileUrl.includes('.pdf')) {
    // 预览 PDF
    window.open(row.fileUrl, '_blank')
  } else if (['.doc', '.docx'].includes(fileSuffix)) {
    // 预览 Word
    previewWordVisible.value = true
    axios({
      method: 'get',
      // contentType: 'application/octet-stream',
      contentType: 'application/msword',
      responseType: 'blob', // 设置响应文件格式
      url: row.fileUrl // http://baidu.com/123/aaaa.docx
    })
        .then(({ data }) => {
          // Blod对象的 type 属性：1、"application/octet-stream" （可以预览）
          // 2、"application/vnd.openxmlformats-officedocument.wordprocessingml.document" 预览不了：
          // Uncaught (in promise) Error: Can't find end of central directory : is this a zip file ? If it is, see https://stuk.github.io/jszip/documentation/howto/read_zip.html
          // 渲染到页面预览
          renderAsync(data, wordRef.value)
              .then(res => {
                console.log('预览成功', res)
              })
              .catch(err => {
                // 如果预览不了，就下载后进行预览
                ElMessageBox.confirm('当前文件超出限制，下载后预览', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  saveAs(data, row.fileAllName)
                }).catch(() => {
                  ElMessage({type: 'info', message: '已取消下载'})
                }).finally(() => {
                  previewWordVisible.value = false
                })
              })
        })
        .catch(err => {
          console.log('err', err)
        })
  } else if (['.png', '.jpg', '.jpeg'].includes(fileSuffix)) {
    // 预览图片
    previewImgVisible.value = true
    previewImageUrl.value = row.fileUrl
  } else if (['.rar','.zip'].includes(fileSuffix)) {
    ElMessage.warning('压缩包无法进行预览，下载后查看')
  }
}

defineExpose({ handlePreview })
</script>



<style scoped>

</style>
