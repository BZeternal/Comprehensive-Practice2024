import { createRouter, createWebHistory } from 'vue-router';
import './menu.js';
import getMenus from './menu.js';
import useUserStore from '@/stores/user.js';
import pinia from '@/stores';
import { ElNotification } from 'element-plus'

let userStore = useUserStore(pinia)


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


router.beforeEach((to, _from, next) => {
  if (sessionStorage.getItem("uId")) {
    if (to.path == '/login') {
      next({ path: _from.path })
    }
    else {
      userStore.getInfo(() => {
        let time = '';
        let text = '';
        let myDate = new Date;
        let h = myDate.getHours();
        if (h >= 5 && h <= 8) {
          time = '早上';
          text = '新的一天已经开始，记得吃早饭哦！';
        }
        else if (h >= 9 && h <= 11) {
          time = '上午';
          text = '上午的问候准时到达，愿好心情伴你一整天！';
        }
        else if (h == 12) {
          time = '中午';
          text = '祝你享受美味的午餐，保持精力充沛！'
        }
        else if (h >= 13 && h <= 18) {
          time = '下午';
          text = '愿你一天愉悦美妙！'
        }
        else {
          time = '晚上';
          text = '长夜漫漫，祝你今晚好梦相随！';
        }
        if (_from.path == '/login' && to.path == '/user/home') {
          ElNotification({
            title: myDate.toLocaleDateString(),
            message: sessionStorage.getItem("uId") + "用户" + time + "好！" + text,
            type: 'info',
          })
        }
        next()
      });
    }

  } else {
    if ((_from.path == '/login' && to.path == '/login') || (_from.path == '/' && to.path == '/login')) {
      next()
    } else {
      console.log(11)
      next({ path: '/login' })
    }
  }


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
export default router;
