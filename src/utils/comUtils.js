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
