// 用于前后端之间数据交互

import CryptoJS from 'crypto-js'

let keyStr = 'da@fei-1234-keys'
// 默认的 KEY 与 iv 如果没有给
const KEY = CryptoJS.enc.Utf8.parse(keyStr)
const IV = CryptoJS.enc.Utf8.parse(keyStr)

/**
 * 加密方法
 */
export function WebEncryptAES(word, keyStr, ivStr) {
  let key = KEY
  let iv = IV

  let srcStr = CryptoJS.enc.Utf8.parse(word)
  let encrypted = CryptoJS.AES.encrypt(srcStr, key, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.ZeroPadding
  })

  return CryptoJS.enc.Base64.stringify(encrypted.ciphertext)
}

/**
 * 解密方法
 */
export function WebDecryptAES(word, keyStr, ivStr) {
  let key = KEY
  let iv = IV

  let base64 = CryptoJS.enc.Base64.parse(word)
  let srcStr = CryptoJS.enc.Base64.stringify(base64)

  let decrypt = CryptoJS.AES.decrypt(srcStr, key, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.ZeroPadding
  })

  let decryptedStr = decrypt.toString(CryptoJS.enc.Utf8)
  return decryptedStr.toString()
}

// 测试方法
export function feiTest() {
  let feiStr = WebEncryptAES('13600001111')

  console.log('加法： ', feiStr)
  console.log('减法： ', WebDecryptAES(feiStr))
}
