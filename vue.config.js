module.exports = {
  lintOnSave: false,
  devServer: {
    host: '0.0.0.0',
    port: 8090,
    open: true,
    // https: false,
    // hotOnly: false,
    proxy: {// 设置代理,解决跨域问题
      [process.env.VUE_APP_BASE_URL]: {
        // target: 'https://www.xxx.com/',
        target: process.env.VUE_APP_SHOW_URL,
        changeOrigin: true,
        // pathRewrite: {
        //     '^/api': '/api'
        // }
      }
    },
  }
}
