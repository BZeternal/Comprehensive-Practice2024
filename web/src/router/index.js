import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import("../views/LoginView.vue"),
      meta: {
        requestAuth: false,
      },
    },
    {
      path: '/',
      redirect: '/login',
    }
  ]
})

export default router
