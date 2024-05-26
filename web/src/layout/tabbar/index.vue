<template>
    <div class="tabbar">
        <div class="tabbar_left">
            <div v-if="st == '非学员'" @click="() => {
                router.push({ name: 'apply' })
            }">你还未报名，点此去报名</div>
            <div v-if="st == '被驳回'" @click="() => {
                router.push({ name: 'apply' })
            }">你的报名信息被驳回，请点此重填</div>
        </div>
        <div class="tabbar_right">
            <!-- 头像 -->
            <img :src="image" style="width: 24px;height: 24px;margin:0px 10px;border-radius: 50%;">
            <el-dropdown style="margin: 0 20px;">
                <span class="el-dropdown-link">
                    {{ "#" + uId }}
                    <el-icon class="el-icon--right">
                        <Arrow-down />
                    </el-icon>
                </span>

                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item @click="goTo()">个人中心</el-dropdown-item>
                    </el-dropdown-menu>
                    <el-dropdown-menu>
                        <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script setup>
import useUserStore from '@/stores/modules/user.js';
import { useRouter } from 'vue-router';
const userStore = useUserStore();
let router = useRouter();
let image = userStore.image;
let uId = userStore.uId;
let st = userStore.state;
const logout = () => {
    userStore.logout();
}
const goTo = () => {
    router.push({ name: "personalInfo" })
}
</script>

<style scoped>
.tabbar {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-between;

    .tabbar_left {
        padding-left: 1rem;
        display: flex;
        align-items: center;
    }

    .tabbar_right {
        display: flex;
        align-items: center;
    }


}

.el-dropdown-link {
    cursor: pointer;
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
}
</style>