import './assets/main.css';

import { createApp } from 'vue';
// import { createPinia } from 'pinia';
import pinia from '@/stores/index.js'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';

import App from './App.vue';
import router from './router';

const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
app.use(pinia);
app.use(router);
app.use(ElementPlus);

app.mount('#app');
