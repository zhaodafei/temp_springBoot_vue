import { createApp } from 'vue'
import App from './App.vue'


const app = createApp(App)

app.mount('#app')


console.log("环境变量",import.meta.env);
