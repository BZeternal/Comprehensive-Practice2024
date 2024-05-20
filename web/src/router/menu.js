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
    path: '/user/chat',
    name: 'chat',
    label: '联系客服',
    url: '../views/chat/index.vue',
    icon: 'service',
    auth: 2,
  },
];

const getMenus = auth => {
  const menus = [];
  allMenus.forEach(i => {
    if (auth == i.auth || auth == 4) menus.push(i);
  });
  return menus;
};

export default getMenus;
