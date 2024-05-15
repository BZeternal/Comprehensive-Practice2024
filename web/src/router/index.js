import { createRouter, createWebHistory } from 'vue-router';
import './menu.js';
import getMenus from './menu.js';
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
      meta: {
        requestAuth: false,
      },
    },
  ],
});

const menus = getMenus(4);
let tmp = {
  path: '/',
  component: () => import('../layout/index.vue'),
  children: [],
};
menus.forEach(i => {
  tmp.children.push({
    path: i.path,
    name: i.name,
    component: () => import(i.url),
  });
});
router.addRoute(tmp);
console.log(router.getRoutes());
export default router;
