// 用于前后端之间数据交互

import { sm2 } from 'sm-crypto';

// 生成公钥和私钥
export const getGenerateKeyPairHex = () => {
  let keypair = sm2.generateKeyPairHex()
  let publicKey = keypair.publicKey // 公钥
  let privateKey = keypair.privateKey // 私钥
  let verifyResult = sm2.verifyPublicKey(publicKey) // 验证公钥返回true,false

  return {publicKey, privateKey, verifyResult}
}


// 公钥-示例
const define_publicKey = '047b8a760757c22985b321744639c2331e813ea2e641e59b02e889cf4a2ab783dd9a658f4b329389fe62727e816c4eeadd740068a53e1b363a4f656b7a6e75b2f1';
// 私钥-示例
const define_privateKey = '79d37982c4ca218ac14237dae7d0e41ef6b8555cf50888df8a0fb29a321bcebe';

export const encryptSM2 = (msg, publicKey, cipherMode = 1) => {
  let pKey = publicKey ? publicKey : define_publicKey;
  return sm2.doEncrypt(msg, pKey, cipherMode);
}

export const decryptSM2 = (msg, privateKey, cipherMode = 1) => {
  let pKey = privateKey ? privateKey : define_privateKey;
  return sm2.doDecrypt(msg, pKey, cipherMode);
}


// 测试方法
export function feiTestSM() {
  const {publicKey, privateKey, verifyResult} = getGenerateKeyPairHex()
  console.log("公钥" + publicKey);
  console.log("私钥" + privateKey);
  console.log(verifyResult);

  const msg = 'daFei' // 可以为 utf8 串或字节数组
  let encryptData  = encryptSM2(msg)
  let decryptData = decryptSM2(encryptData)
  console.log("解密后的字符串",decryptData);

  let pri = "79d37982c4ca218ac14237dae7d0e41ef6b8555cf50888df8a0fb29a321bcebe"; // 后端的私钥
  let str = "6ccc91ff322b067501f482b0ff049be295b32f5dc76fcc3b423414ffa46aba55b3be4ae1df7658748deacdcbd46243155062a8efb6f0d70e4237568e71c6b1af15008a6e48497f093ce0f8eba8fd30073fd58f27cec169b56b15289050c37a5533f11f5cf2e2372141ae8c3c997b2ea5c7c3d9";
  let decryptData2 = decryptSM2(str,pri)
  console.log("解密后的字符串",decryptData2); // 输出:  13600001111_fei_飞
}
