// 常见正则
export function regExpStr(str) {
  return {
    ExpPassword: {
      reg: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/,
      errMsg: '密码由英文大小写和数字组成'
    },
    ExpNum: {
      reg: /^\d+$/,
      errMsg: '只能是正整数'
    },
    ExpNumVal: {
      reg: /^[-|+]?\d+(\.\d+)?$/,
      errMsg: '只能是数值'
    },
    ExpPNum: {
      reg: /^\d+(\.\d+)?$/,
      errMsg: '只能是正数'
    },
    ExpNotZeroPNumber: {
      reg: /^[1-9]+|[0-9]\.\d+$/,
      errMsg: '只能是不为零的正数'
    },
    ExpModel: {
      reg: /^[a-zA-Z0-9-]+$/,
      errMsg: '只能输入英文、数字和\'-\''
    },
    ExpEnNum: {
      reg: /^[a-zA-Z0-9]+$/,
      errMsg: '只能输入英文和数字'
    },
    ExpEnNum_: {
      reg: /^[a-zA-Z0-9_://]+$/,
      errMsg: '只能输入英文、数字和\'_\''
    },
    ExpCn: {
      reg: /^[\u4E00-\u9FA5]+$/,
      errMsg: '只能输入中文'
    },
    ExpEn: {
      reg: /^[a-zA-Z]+$/,
      errMsg: '只能输入英文'
    },
    ExpCnEn: {
      reg: /^[a-zA-Z\u4E00-\u9FA5]+$/,
      errMsg: '只能输入中、英文'
    },
    ExpCnEnNum: {
      reg: /^[a-zA-Z0-9\u4E00-\u9FA5]+$/,
      errMsg: '只能输入中文、英文和数字'
    },
    ExpCnEnNumSpace: {
      reg: /^[a-zA-Z0-9\u4E00-\u9FA5\s]+$/,
      errMsg: '只能输入中文、英文、数字和空格'
    },
    ExpEnSpace: {
      reg: /^[a-zA-Z\s]+$/,
      errMsg: '只能输入英文和空格'
    },
    ExpEnNumSpace: {
      reg: /^[a-zA-Z0-9\s]+$/,
      errMsg: '只能输入英文、数字和空格'
    },
    ExpCnEnSpace: {
      reg: /^[a-zA-Z\s\u4E00-\u9FA5]+$/,
      errMsg: '只能输入中、英文、空格'
    },
    ExpPhone: {
      reg: /^(\d{11})|^(([\d/(/)]{7,10})|([\d/(/)]{6}|[\d/(/)]{5}|[\d/(/)]{4}|[\d/(/)]{3}|[\d/(/)]{2})-([\d/(/)]{7,10})|([\d/(/)]{6}|[\d/(/)]{5}|[\d/(/)]{4}|[\d/(/)]{3}|[\d/(/)]{2})-([\d/(/)]{7,10})-([\d/(/)]{6}|[\d/(/)]{5}|[\d/(/)]{4}|[\d/(/)]{3}|[\d/(/)]{2}|[\d/(/)]{1})|([\d/(/)]{7,10})-([\d/(/)]{6}|[\d/(/)]{5}|[\d/(/)]{4}|[\d/(/)]{3}|[\d/(/)]{2}|[\d/(/)]{1}))+$/,
      errMsg: '请输入正确的号码'
    },
    ExpPhoneSimple: {
      reg: /^1{1,}/,
      errMsg: '请输入正确的手机号码'
    },
    ExpEmail: {
      reg: /^[a-zA-Z0-9]+([._\\-]*[a-zA-Z0-9])*@([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+.){1,63}[a-zA-Z0-9]+$/,
      errMsg: '请输入正确的邮箱地址'
    },
    ExpPostCode: {
      reg: /^[0-9]{6}$/,
      errMsg: '请输入正确的邮政编号'
    },
    ExpCellphone: {
      reg: /^([0-9]{3,4}-)?[0-9]{7,8}$/,
      errMsg: '请输入正确的座机号'
    },
    ExpIdentity: {
      reg: /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/,
      errMsg: '请输入正确的身份证号'
    },
    ExpMoney: {
      reg: /(^[1-9](\d+)?(\.\d{1,2})?$)|(^[1-9]$)|(^\d\.[1-9]{1,2}$)|(^\d\.[0]{1}[1-9]{1}$|(^\d\.[1-9]{1}[0]{1}$)$)/,
      errMsg: '请输入正确的金额'
    },
    ExpDomain: {
      reg: /^(?=^.{3,255}$)(http(s)?:\/\/)?(www\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\d+)*(\/\w+\.\w+)*([\?&]\w+=\w*)*$/,
      errMsg: '请输入正确的浏览器地址'
    }
  }
}
