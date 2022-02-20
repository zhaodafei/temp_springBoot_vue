module.exports = {
  lintOnSave: false,
  publicPath: process.env.NODE_ENV === "production" ? "./." : "/",
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
  },
  css: {
    // 开启 CSS source maps?
    sourceMap: false,
  },
  configureWebpack:{
    performance: { // 关闭性能提示, 以后可以优化
      hints: false,
    },
    optimization:{
      minimize:true,
      splitChunks:{
        chunks: 'all',
      }
    }
  },
  chainWebpack:(config)=>{
    config.plugin('html').tap(option => {
      option[0].title = "大飞" // 默认 页面中使用 <%= htmlWebpackPlugin.options.title %>
      return option;
    })
  }
}
