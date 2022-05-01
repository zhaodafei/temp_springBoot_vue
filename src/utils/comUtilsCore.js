// 本项目公用功能业务逻辑处理

/**
 * 二进制流文件下载
 * @param res 接口返回所有对象 {config:{},data{},headers:{},request:{},status:"",statusText:{}}
 * @param file_name 自定义文件名字
 * @returns {boolean}
 */
export function comDownload(res, file_name) {
  let fileName = ""; // 文件名字
  try {
    fileName = file_name ? file_name : res.headers['content-disposition'].split('fileName=')[1];
  } catch (e) {
    fileName = Math.floor(Math.random() * 10000); // 随机文件名字
  }

  let fileType = "text/plain"; // 文件类型
  try {
    fileType = res.headers['web-file-type'];
    if (!res.headers['web-file-type']) {
      console.error("接口没有返回文件类型");
      return false;
    }
  } catch (e) {
    console.error("接口没有返回文件类型", e);
    return false;
  }

  //常见 MIME 类型列表 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
  let blob = new Blob([res.data], {
    // type: 'image/png',
    // type: 'image/jpeg',
    type: fileType,
  });


  let objectUrl = URL.createObjectURL(blob);
  if ('download' in document.createElement('a')) { // 非IE下载
    let aElement = document.createElement('a');
    aElement.href = objectUrl;
    aElement.download = fileName; //下载后文件名
    document.body.appendChild(aElement);
    aElement.click(); //点击下载
    document.body.removeChild(aElement); //下载完成移除元素
  } else { // IE10+下载
    navigator.msSaveBlob(blob, fileName)
  }
  window.URL.revokeObjectURL(objectUrl); //释放blob对象
}
