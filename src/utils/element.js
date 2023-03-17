import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn  from 'element-plus/lib/locale/lang/zh-cn'

// 全部注册
import * as iconModules from '@element-plus/icons-vue'

const iconInstall = function (app) {
  for (const iconName in iconModules) {
    app.component(iconName, iconModules[iconName]);
  }
};

export {iconInstall,zhCn,ElementPlus}
