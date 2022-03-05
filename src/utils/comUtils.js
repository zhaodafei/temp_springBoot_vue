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
