const allMenus = [
  //样例
  //加一个菜单的页面就这样写一个
  {
    path: '/user/home', //路由
    name: 'home', //页面名字
    label: '首页', //功能的名字，中文
    url: '../views/home/index.vue', //你vue文件的位置
    icon: 'house', //菜单左边的图标，element里面找，直接填名字，没想好直接复制这个
    auth: 2, //权限
  },
  {
    path: '/admine/home',
    name: 'admineHome',
    label: '管理员首页',
    url: '../views/admineHome/index.vue',
    icon: 'house',
    auth: 2,
  },
  {
    path: '/user/coach',
    name: 'select_coach',
    label: '选择教练',
    url: '../views/coach/index.vue',
    icon: 'avatar',
    auth: 2,
  },
  {
    path: '/user/chat',
    name: 'chat',
    label: '联系客服',
    url: '../views/chat/index.vue',
    icon: 'service',
    auth: 2,
  },
  {
    path: '/user/practice',
    name: 'practice',
    label: '考题练习',
    url: '../views/practice/index.vue',
    icon: 'checked',
    auth: 2,
  },
  {
    path: '/admine/practice',
    name: 'admine_practice',
    label: '学员练习情况',
    url: '../views/practice/manage.vue',
    icon: 'list',
    auth: 1,
  },
  {
    path: '/user/reserve',
    name: 'reserve',
    label: '预约学车',
    url: '../views/reserve/index.vue',
    icon: 'calendar',
    auth: 1,
  },
  {
    path: '/admine/auth',
    name: 'auth',
    label: '权限管理',
    url: '../views/authManager/index.vue',
    icon: 'lock',
    auth: 1,
  }

];

const getMenus = auth => {
  const menus = [];
  allMenus.forEach(i => {
    if (auth == i.auth || auth == 4) menus.push(i);
  });
  return menus;
};

export default getMenus;
