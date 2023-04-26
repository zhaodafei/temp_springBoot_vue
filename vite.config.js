import { defineConfig, loadEnv } from 'vite'
import path from 'path'
import createVitePlugins from './vite/plugins'

// https://vitejs.dev/config/
export default defineConfig(({ mode, command }) => {
  // 获取环境变量
  const env = loadEnv(mode, process.cwd())

  return {
    plugins: createVitePlugins(env, command === 'build'),
    resolve: {
      // https://cn.vitejs.dev/config/#resolve-alias
      alias: {
        // 设置路径
        '~': path.resolve(__dirname, './'),
        // 设置别名 这里的./指的是 vite.config.js 的所载目录（根目录）下面的 src
        '@': path.resolve(__dirname, './src')
      },
      // https://cn.vitejs.dev/config/#resolve-extensions
      extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
    },
    build: {
      outDir: mode === 'staging' ? 'dist-staging' : 'dist'
    },
    css: {
      /*preprocessorOptions: {
        // 引入公用的样式
        scss: {
          charset: false,
          additionalData: `
                        @import "@/assets/styles/index.scss";
                        @import "@/assets/styles/mixin.scss";
                    `
        }
      }, */
      postcss: {
        plugins: [
          {
            postcssPlugin: 'internal:charset-removal',
            AtRule: {
              charset: atRule => {
                if (atRule.name === 'charset') {
                  atRule.remove()
                }
              }
            }
          }
        ]
      }
    },
    // vite 相关配置
    server: {
      port: 3401,
      host: true,
      // open: true, // 是否自动打开浏览器
      proxy: {
        '^/api': {
          // target: 'http://demo.yizheng_fei.com', // 本地 PHP
          target: env.VITE_APP_SHOW_URL,
          changeOrigin: true,
          logLevel: 'debug',
          // tip: 所有接口需要带上前缀/api
          // rewrite: path => path.replace(/^\/api/, '/api')
        }
      }
    }
  }
})
