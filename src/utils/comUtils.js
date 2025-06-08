// js 原函数加工处理

export function str_toLocaleLowerCase(str) {
  if (Object.prototype.toString.call(str) !== "[object String]") {
    return "";
  }
  return str.toLocaleLowerCase();
}

export function str_toLocaleUpperCase(str) {
  if (Object.prototype.toString.call(str) !== "[object String]") {
    return "";
  }
  return str.toLocaleUpperCase();
}

/**
 * 是否是空数组
 * @param arr Array
 * @returns {boolean}
 */
export function isEmptyArr_utils(arr) {
  if (Object.prototype.toString.call(arr) === "[object Array]" ) {
    return arr.length !== 0;
  }else {
    return false;
  }
}

/**
 * 日期处理
 * @param date
 * @returns {string}
 */
export function defaultDate_utils(date) {
  return app.$dayjs(date).format("YYYY-MM-DD");
}

/**
 * 字符截取
 * @param str
 * @param type
 * @returns {string}
 */
export function str_trim(str, type = 1) {
  if (Object.prototype.toString.call(str) !== "[object String]") {
    return "";
  }

  let returnStr = '';
  switch (type) {
    case 1: //删除左右两端的空格
      returnStr = str.replace(/(^\s*)|(\s*$)/g, "");
      break;
    case 2: //删除左边的空格
      returnStr = str.replace(/(^\s*)/g, "");
      break;
    case 3: // 删除右边的空格
      returnStr = str.replace(/(\s*$)/g, "");
      break;
    case 4: // 删除末尾最后一个字符
      returnStr = str.slice(0, str.length - 1);
      break;
  }
  return returnStr;
}

/**
 * 获取地址中参数值
 * @param variable 参数名字
 * @returns {string} 参数值
 */
export function getQueryVariable(variable) {
  let query = window.location.search.substring(1);
  let vars = query.split("&");
  for (let i = 0; i < vars.length; i++) {
    let pair = vars[i].split("=");
    if (pair[0] === variable) {
      return pair[1];
    }
  }
  return "";
}

/**
 * 转为数字
 * @param num 一个任意类型:undefined, null, NaN, string,
 * @returns {number|number} 返回一个数字
 */
export function toNumber(num) {
  console.log("当前类型:", Object.prototype.toString.call(num));
  return isNaN(num * 1) ? 0 : num * 1
}

// 校验传为空
export function validEmpty(val) {
  // 在此处，-1被认定为无效值
  if (
    val === null ||
    val === -1 ||
    typeof val === 'undefined' ||
    (typeof val === 'string' && val === '' && val !== 'undefined')
  ) {
    return false
  }
  return true
}

// 校验大于零数字
export function isPositiveInteger(value) {
  // 将输入值转换为数字
  const num = Number(value)
  // 检查是否为数字且大于0
  let flag = !isNaN(num) && num > 0 && Math.floor(num) === num
  return flag
}

// 金额逗号分隔
export function formatAmount(str) {
  return str && str.replace(/\B(?=(\d{3})+(?!\d))/g, ',')
}

// 生成UUID
export function guid_1() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = (Math.random() * 16) | 0
    var v = c === 'x' ? r : (r & 0x3) | 0x8
    return v.toString(16)
  })
}

// 判断是不是 JSON 字符串
export function isJSON(str) {
  if (typeof str == 'string') {
    try {
      var obj = JSON.parse(str)
      if (typeof obj == 'object' && obj) {
        return true
      } else {
        return false
      }
    } catch (e) {
      console.log('error：' + str + '!!!' + e)
      return false
    }
  }
}

/**
 * 深度克隆一个对象
 * This is just a simple version of deep copy
 * Has a lot of edge cases bug
 * If you want to use a perfect deep copy, use lodash's _.cloneDeep
 * @param {Object} source
 * @returns {Object}
 */
export function deepClone(source) {
  if (!source && typeof source !== 'object') {
    throw new Error('error arguments', 'deepClone')
  }
  const targetObj = source.constructor === Array ? [] : {}
  Object.keys(source).forEach(keys => {
    if (source[keys] && typeof source[keys] === 'object') {
      targetObj[keys] = deepClone(source[keys])
    } else {
      targetObj[keys] = source[keys]
    }
  })
  return targetObj
}
