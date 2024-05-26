<template>
    <div style="height: 50px; text-align: center;font-size: 30px;color: aliceblue;line-height: 50px;">LOGO</div>
    <el-menu :default-active="activeIndex" class="el-menu" background-color="#545c64" text-color="#fff"
        active-text-color="#ffd04b">
        <el-menu-item :index="item.path" v-for="item in menus" @click="goRoute">
            <el-icon>
                <component :is="item.icon"></component>
            </el-icon>
            <template #title>
                <span>{{ item.label }}</span>
            </template>
        </el-menu-item>
    </el-menu>
</template>

<script setup>
import useUserStore from '@/stores/modules/user';
import getMenus from '../../router/menu.js'
import { useRouter } from 'vue-router';
import { ref } from 'vue';

let menus = getMenus(useUserStore().auth);
//获取路由器对象
let $router = useRouter();
let activeIndex = ref($router.currentRoute.value.fullPath)
//点击菜单的回调
const goRoute = (vc) => {
    //路由跳转
    $router.push(vc.index);
}
</script>

<style scoped>
.icons {
    width: 18px;
    height: 18px;
    margin-right: 4px;
}

.el-menu {
    width: 100%;
    border-right: none;
}

h3 {
    color: #fff;
    text-align: center;
    margin-top: 10px;
}
</style>