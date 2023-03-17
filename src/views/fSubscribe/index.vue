<template>
  <div class="subscript-body">
    <div class="logo">
      <h3>
        欢迎使用图书预约系统
      </h3>
    </div>
    <div class="ipt">
      <div class="tip">用户名</div>
      <div class="ipt-input">
        <input type="text" maxlength="11" placeholder="请输入用户名">
      </div>
    </div>
    <div class="ipt"> <!-- 系统统一发放验证码 -->
      <div class="tip">验证码</div>
      <div class="ipt-input">
        <input type="text" maxlength="4" placeholder="请输入验证码">
      </div>
    </div>
    <button class="btn-primary">预约</button>
    <div class="footer-tip">
      提示：目前开放时间段 <span>9:00-18:00</span> 的预约
    </div>
  </div>
</template>

<script>
export default {
  name: "index"
}
</script>

<style scoped lang="scss">
.subscript-body {
  width: 390px;
  height: 700px;
  margin: 0 auto;
  padding: 0 25px;
  background-color: #fff;

  .logo {
    width: auto;
    height: 68px;
    background-image: linear-gradient(to right, #ff6b81, #c0c0c0);
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 -25px;
    animation: mymove 5s infinite;
    -webkit-animation: mymove 5s infinite;
  }

  .ipt{
    margin: 10px 0;
    .ipt-input {
      border-bottom: 1px solid #dedede;
      input{
        outline: none;
        border: none;
        height: 40px;
      }
    }
  }

  .btn-primary{
    margin-top: 20px;
    background-color: #409eff;
    border: 0;
    width: 100%;
    height: 40px;
    border-radius: 4px;
    cursor: pointer;
    color: #fff;
    font-size: 20px;
  }

  .footer-tip {
    margin: 10px 0;
    span {
      color: #ff6b81;
    }
  }
}


@keyframes mymove
{
  from {-webkit-filter: hue-rotate(0deg);}
  to {-webkit-filter: hue-rotate(-360deg);}
}
</style>

