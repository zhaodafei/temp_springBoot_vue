<template>
  <div>
    <h1>登录系统管理</h1>
  </div>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="form.username" placeholder="商品名称"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input show-password v-model="form.password" placeholder="密码"></el-input>
    </el-form-item>

    <el-button type="primary" @click="loginForm">登录</el-button>
  </el-form>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import interfacesUser from "@/api/user";

const app = getCurrentInstance().appContext.config.globalProperties;
const formRef = ref() // 表单 ref 对象

const form = reactive({
  username: '12345678912',
  password: '123456',
});

onMounted(()=>{
  loginOut()
})

const loginForm = () => {
  let params = {
    account_name: form.username,
    password: form.password,
  };
  app.$post(interfacesUser.login, params).then(res => {
    if (res) {
      if (res.error === 0) {
        let user = {
          token: res.data.access_token,
          username: params.account_name,
          password: params.password,
        };
        sessionStorage.setItem('user', JSON.stringify(user));
        app.$message.success("登录成功");
        app.$router.push("/")
      } else {
        app.$message.error("登录失败");
      }
    }
  })
}

const loginOut = () => {
  sessionStorage.removeItem('user');
}

</script>

<style scoped>

</style>
