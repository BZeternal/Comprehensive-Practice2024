<template>
    <div class="tabbar">
        <div class="tabbar_left">
            此处还有功能
        </div>
        <div class="tabbar_right">
            <!-- 头像 -->
            <img src="https://pic1.zhimg.com/v2-b26a719da72b24b921f20f7174c11ed0_ipico.jpg"
                style="width: 24px;height: 24px;margin:0px 10px;border-radius: 50%;">
            <el-dropdown style="margin: 0 20px;">
                <span class="el-dropdown-link">
                    管理员
                    <el-icon class="el-icon--right">
                        <Arrow-down />
                    </el-icon>
                </span>

                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item @click="logout">个人中心</el-dropdown-item>
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
const logout = () => {
    alert("等个有缘人来把这个功能写了");
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