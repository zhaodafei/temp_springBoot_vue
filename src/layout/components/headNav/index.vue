<template>
  <div class="header-nav">
    <div class="header-logo">
      <h1>
        <a href="/" title="系统logo" class="header-logo-url"></a>
      </h1>
    </div>
    <div class="header-right">
      <div class="header-account">
        <a href="Javascript:void(0)" data-title="消息图标" >
          <el-icon class="header-extra-msg" style="color: #000;font-size: 28px"><bell /></el-icon>
        </a>
        <span style="margin-left: 10px">
          <el-popover :width="160">
            <template #reference>
              <span>
                <el-avatar :size="32" :src="avatarImg"></el-avatar>
                <el-icon style="position: relative;left: 6px;top: -6px"><arrow-down-bold/></el-icon>
              </span>
            </template>
            <template #default>
              <div class="account-menu">
                <ul>
                  <li class="account-item">
                    <a href="Javascript:void(0)">
                      <el-icon><User/></el-icon>
                      账号信息
                    </a>
                  </li>
                  <li class="account-item">
                    <a href="Javascript:void(0)">
                      <el-icon><CircleCheck/></el-icon>
                      认证详情
                    </a>
                  </li>
                  <li class="account-item">
                    <a href="Javascript:void(0)">
                      <el-icon><Setting/></el-icon>
                      功能设置
                    </a>
                  </li>
                  <li class="account-item">
                    <router-link to="/login">
                       <el-icon><SwitchButton/></el-icon>
                      退出登录
                    </router-link>
                  </li>
                </ul>
              </div>
            </template>
          </el-popover>
        </span>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive, toRefs} from 'vue'
import avatarImg from '@/assets/images/avatar/user1.jpg'

// const state = reactive({
//   avatarImg: require('@/assets/images/avatar/user1.jpg')
// })
//
// const {avatarImg} = toRefs(state)

</script>


<style lang="scss" scoped>
.header-nav {
  flex-shrink: 0;
  height: var(--el-header-height);
  padding: 0 48px;
  background-color: #fff;
  color: var(--el-text-color-primary);

  .header-logo{
    float: left;
    height: 100%;
    h1{
      height: 100%;
      display: flex;
      align-items: center;
    }

    .header-logo-url{
      width: 195px;
      height: 32px;
      background-image: url("/images/logo.svg");
      display: block;
      font-size: 0;
      background-repeat: no-repeat;
      background-size: contain;
    }
  }

  .header-right {
    float: right;
    height: 100%;

    .header-account {
      height: 100%;
      display: flex;
      align-items: center;

      .header-extra-msg {
        border: 2px solid #e3e4e5;
        border-radius: 50%;
      }
    }

  }
}

.account-menu {
  ul {
    width: 160px;
    background-color: #fff;
    text-align: left;
  }

  .account-item {
    padding: 10px 16px;
  }

  a {
    color: #1a1b1c;
    .el-icon {
      position: relative;
      top: 2px;
    }
  }
}

</style>
