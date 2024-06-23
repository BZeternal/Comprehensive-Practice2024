<template>
    <div style="height: 50px; text-align: center;font-size: 30px;line-height: 50px;color: rgb(153, 155, 157);">
        <span class="high-light">驾校报名</span>
    </div>
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

let menus = getMenus(useUserStore().auth, sessionStorage.getItem("st"));
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

.high-light {
    font-weight: 900;
    background: rgb(1, 208, 255) linear-gradient(-135deg,
            transparent 25%,
            transparent 40%,
            rgba(255, 255, 255) 40%,
            rgba(255, 255, 255) 60%,
            transparent 60%,
            transparent);
    background-size: 60px 60px;
    background-repeat: no-repeat;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    animation: scratchy 6s linear infinite;
}

@keyframes scratchy {
    0% {
        background-position: -100% 0;
    }

    100% {
        background-position: 130% 0;
    }
}
</style>