<template>
  <div>
    <div class="file-item">
      <strong> 文件上传1 </strong>
      <input
          type="file"
          id="fileUpload"
          name="fileUpload"
          accept=".xls, .xlsx, .pdf, .txt, .doc, .docx, .jpg, .gif, .png, .ppt, .pptx"
          multiple
          @change="changesFile"
      />
    </div>

    <div class="file-item">
      <strong> 文件上传2 </strong>
      <!--<input type="file" id="fileUpload2" name="fileUpload2" accept=".jpg, .gif, .png" multiple @change="changesFile2"/>-->
    </div>

    <div class="file-item">
      <strong> 文件上传3 </strong>
      <!--<input type="file" id="fileUpload3" name="fileUpload3" accept=".jpg, .gif, .png" multiple @change="changesFile3"/>-->
    </div>

    <!--<el-button @click="addFiles">上传</el-button>-->


    <div class="file-item">
      <strong> 文件上传4 </strong>
      <el-upload action :http-request="changesFile4">
        <el-button type="primary">
          element 上传文件
        </el-button>
      </el-upload>
    </div>

    <div class="file-item" style="display: flex;justify-content: space-between;width: 150px">
      <button @click="fileDownload">下载222</button>
    </div>
  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import {comDownload} from "@/utils/comUtilsCore";

const app = getCurrentInstance().appContext.config.globalProperties;

const addFiles = () => {
  // $("#fileUpload").click();
}
// 文件上传接口1
const changesFile = () => {
  const formData = new FormData();
  const fileObj = document.getElementById("fileUpload").files[0];
  if (fileObj != "undefined") {
    formData.append("file", fileObj);
    formData.append("fileId", "100");
    formData.append("fileFoo", "xxx");
    app.$post("file/upload", formData).then(res=>{
      console.log(res);
    })
  }
}

// 文件上传接口2
/*const changesFile2 = () => {
  const formData = new FormData();
  const fileObj = document.getElementById("fileUpload2").files[0];
  if (fileObj != "undefined") {
    formData.append("file", fileObj);
    formData.append("fileId", "200");
    formData.append("fileFoo", "xxx");

    app.$post("file/upload2", formData).then(res=>{
      console.log(res);
    })
  }
}*/

// 文件上传接口3
/*const changesFile3 = () => {
  const formData = new FormData();
  const fileObj = document.getElementById("fileUpload3").files[0];
  if (fileObj != "undefined") {
    formData.append("file", fileObj);
    formData.append("fileId", "300");
    formData.append("fileFoo", "xxx");

    app.$post("file/upload3", formData).then(res=>{
      console.log(res);
    })
  }
}*/

const changesFile4 = (req) => {
  let fd = new FormData()
  fd.append('file', req.file)
  fd.append('FileName', req.file.name)
  app.$post("file/upload", fd).then(res => {
    console.log(res);
  })
}

const fileDownload = () => {
  app.$fPost("file/download", {fileId: 222}).then(res => {
    comDownload(res);
  })
}

</script>

<style scoped>
.file-item {
  margin: 10px 0;
}
</style>
