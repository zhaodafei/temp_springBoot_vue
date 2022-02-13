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
