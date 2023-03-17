<template>
  <h3>聊天窗口 </h3>
  <div style="background-color:rgba(0, 0, 0, 0.5);height: 780px">
    <div style="background-color:#fff;">
      <div class="fei-msg-box">
        <div class="chat-box">
          <ul>
            <template v-for="(item,index) in chatData" :key="index">
              <!-- 回复消息 -->
              <li v-if="item.replyContent && item.replyContent.length>0" class="item mine">
                <div class="avatar">
                  <img :src="item.userHeadImgUrl||defaultImg" alt=".">
                  <cite>{{item.userNickname}} <i>{{item.replyTime}}</i></cite>
                </div>
                <div class="text">
                  <template v-if="item.replyMsgType=='text'">
                    {{item.replyContent}}
                  </template>
                  <template v-else>【不支持的消息】</template>
                </div>
              </li>
              <!-- 用户消息 -->
              <li v-else class="item customer">
                <div class="avatar">
                  <img :src="item.headImgUrl||userImg" alt=".">
                  <cite>{{item.nicknameStr}} <i>{{item.createTime}}</i></cite>
                </div>
                <div class="text">
                  <template v-if="item.msgType=='text'">
                    {{item.content}}
                  </template>
                  <template v-else>【不支持的消息】</template>
                </div>
              </li>

            </template>
          </ul>
        </div>
      </div>
      <div class="chat-textarea">
        <textarea placeholder="输入回复内容" v-model="reContent"></textarea>
      </div>
      <div style="text-align: right">
        <el-button type="primary">发送</el-button>
      </div>

    </div>
  </div>
</template>

<script setup>
import defaultImg from '@/assets/images/avatar/user1.jpg'
import userImg from '@/assets/images/avatar/user2.jpg'
import {ref} from "vue";
const chatData1 = [
  {
    // 客服消息-service-mine
    'replyContent': "",
    'userNickname': "",
    'userHeadImgUrl': "",
    'replyTime': "",
    'replyMsgType': "",
    // 用户消息-customer-you
    'content': "在,请问需要什么帮助?",
    'nicknameStr': "fei001",
    'headImgUrl': "", // 头像
    'createTime': "2022-05-01 12:35:00",
    'msgType': "text",
  },
  {
    // 客服消息
    'replyContent': "你好!",
    'userNickname': "daFei",
    'userHeadImgUrl': "", // 头像
    'replyTime': "2022-05-01 08:51:27",
    'replyMsgType': "text",
    // 用户消息
    'content': "",
    'nicknameStr': "",
    'headImgUrl': "",
    'createTime': "",
    'msgType': "",
  }
]

const chatData = chatData1.concat(chatData1).concat(chatData1).concat(chatData1);
const reContent = ref('');
</script>

<style scoped lang="scss">
.chat-box {
  height: 300px;
  padding: 15px 15px 5px;
  overflow-x: hidden;
  overflow-y: auto;

  li {
    position: relative;
    font-size: 0;
    margin-bottom: 10px;
    padding-left: 60px;
    min-height: 68px;

    .avatar {
      position: absolute;
      left: 3px;

      img {
        width: 40px;
        height: 40px;
        border-radius: 100%;
      }
    }

     cite {
      position: absolute;
      left: 60px;
      top: -2px;
      width: 500px;
      line-height: 24px;
      font-size: 12px;
      white-space: nowrap;
      color: #999;
      text-align: left;
      font-style: normal;

      i {
        padding-left: 15px;
        font-style: normal;
      }
    }

    .text {
      position: relative;
      line-height: 22px;
      margin-top: 25px;
      padding: 8px 15px;
      background-color: #c0c0c0;
      border-radius: 3px;
      color: #333;
      word-break: break-all;
      max-width: 462px;
      display: inline-block;
      vertical-align: top;
      font-size: 14px;
    }
  }

  li.mine {
    text-align: right;
    padding-left: 0;
    padding-right: 60px;

    .avatar {
      left: auto;
      right: 3px;
    }

    cite {
      left: auto;
      right: 60px;
      text-align: right;

      i {
        padding-left: 0;
        padding-right: 15px;
      }
    }

    .text {
      margin-left: 0;
      text-align: left;
      background-color: #ff6b81;
      color: #fff;
    }
  }
}

.chat-textarea{
  border-top: 1px solid #ccc;
  margin-left: 10px;
  outline: none;
  :focus-visible{
    outline: none;
  }

  textarea{
    display: block;
    width: 100%;
    padding: 5px 0 0;
    height: 68px;
    line-height: 20px;
    border: none;
    overflow: auto;
    resize: none;
    background: 0 0;
  }
}


</style>
