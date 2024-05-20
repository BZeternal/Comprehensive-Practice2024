<template>
    <div class="common-layout">
        <el-container>
            <el-aside width="180px">
                <Menu></Menu>
            </el-aside>
            <el-container>
                <el-header>
                    <Tabbar></Tabbar>
                </el-header>
                <el-main>
                    <RouterView></RouterView>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import Menu from './menu/index.vue';
import Tabbar from './tabbar/index.vue';
</script>

<style scoped>
.common-layout {
    height: 100%;

    &>.el-container {
        height: 100%;

        &>.el-aside {
            height: 100%;
            background: #545c64;
        }

        &>.el-container {
            &>.el-header {
                padding: 0%;
                border-bottom: 1px rgb(0, 0, 0, 0.08) solid;
                box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.08);
            }
        }
    }
}
</style>