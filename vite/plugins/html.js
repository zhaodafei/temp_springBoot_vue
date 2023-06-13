// index.html 中使用环境变量
import { createHtmlPlugin } from 'vite-plugin-html'

export default function createHtml(env) {
  const { VITE_APP_TITLE,VITE_APP_ENV } = env
  return createHtmlPlugin({
    inject: {
      data: {
        //将环境变量 VITE_APP_TITLE 赋值给 title 方便 html页面使用 title 获取系统标题
        title: VITE_APP_ENV === 'development' ? ('pre' + VITE_APP_TITLE) : VITE_APP_TITLE
      }
    }
  })
}
