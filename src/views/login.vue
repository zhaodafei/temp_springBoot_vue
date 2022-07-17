<template>
  <div class="login-body">
    <div class="login-panel">
      <div class="header">
        <h1>登录系统管理</h1>
      </div>
      <div class="login-url">
        <img :src="avatarImg" alt="." />
      </div>
      <el-form ref="formRef" :model="form" class="wrapper-body">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="用户名" />
        </el-form-item>

        <el-form-item prop="password">
          <el-input show-password v-model="form.password" placeholder="密码" />
        </el-form-item>

        <el-form-item prop="code">
          <el-input v-model="form.code" placeholder="验证码" style="width: 63%" />
          <div class="login-code">
            <img :src="captchaImg" @click="getCaptcha" class="login-code-img"/>
            <!--<img :src="captchaImg_api" @click="getCaptcha" class="login-code-img"/>-->
          </div>
        </el-form-item>

        <el-button type="primary" :loading="loginLoading" @click="loginForm">登录</el-button>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import interfacesUser from "@/api/user";
import avatarImg from '@/assets/images/logo_in.svg'
import captchaImg from '@/assets/images/captchaImage.jpg'

const { proxy } = getCurrentInstance()
const formRef = ref() // 表单 ref 对象

const captchaImg_api = ref(); // 验证码
const loginLoading = ref(false);
const form = reactive({
  username: 'dafei',
  password: '123456',
  code: '',
});

onMounted(()=>{
  loginOut()
  getCaptcha()
})

const getCaptcha = () => {
  proxy.$get(interfacesUser.APIGetCaptcha,{}).then(res=>{
    console.log(res);
    captchaImg_api.value = "data:image/gif;base64," + res.img;
  })
}

const loginForm = () => {
  loginLoading.value = true;
  let params = {
    username: form.username,
    password: form.password,
  };
  proxy.$post(interfacesUser.login, params).then(res => {
    if (res) {
      if (res.error === 200) {
        loginLoading.value = false;
        let user = {
          token: res.data.access_token,
          username: params.username,
        };
        sessionStorage.setItem('user', JSON.stringify(user));
        proxy.$message.success("登录成功");
        proxy.$router.push("/")
      } else {
        proxy.$message.error(res.msg);
      }
    }
  })
}

const loginOut = () => {
  sessionStorage.removeItem('user');
}

</script>

<style scoped lang="scss">
.login-body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;

  .login-panel {
    width: 400px;
    background-color: #fff;
    padding: 16px 32px;
  }

  .header {
    h1 {
      margin: 8px 0 24px;
      text-align: center;
      color: #393b3d;
      font-weight: 300;
    }
  }
  .login-url{
    margin: 8px 0 24px;
    text-align: center;
  }

  .wrapper-body {
    text-align: center;

    .login-code{
      width: 33%;
      height: 38px;
      float: right;
      img {
        cursor: pointer;
        vertical-align: middle;
      }
      .login-code-img{
        height: 33px;
      }
    }
  }
}

</style>
