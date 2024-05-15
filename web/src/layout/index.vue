<template>
    <div class="common-layout">
        <el-container>
            <el-aside width="180px">
                <Menu></Menu>
            </el-aside>
            <el-container>
                <el-header>Header</el-header>
                <el-main>
                    <RouterView></RouterView>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import Menu from './menu/index.vue';
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
            }
        }
    }
}
</style>