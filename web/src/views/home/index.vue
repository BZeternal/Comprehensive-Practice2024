<template>
    <div style="width: 100%;display: flex;flex-wrap: wrap;justify-content: center;">
        <el-card style="width: 60%;margin-bottom: 20px;" v-for="i in listNotice">
            <template #header>
                <div style="width: 100%;text-align: center;font-size: 25px;">
                    <span>{{ i.nTitle }}</span>
                </div>
            </template>
            <span>{{ i.nText }}</span>
            <template #footer>
                <div style="float: right;color: rgb(36, 34, 34);">{{ "时间：" + new Date(i.nTime).toLocaleDateString() }}</div>
                <div style="color: rgb(218, 44, 44);">{{ "发布人：#" + i.uId }}</div>
            </template>
        </el-card>
    </div>
</template>

<script setup>
import { getAllNotice } from "@/api/notice.js";
import { ref } from "vue"

let listNotice = ref()
const getDate = () => {
    getAllNotice().then(res => {
        listNotice.value = res;
    })
}
getDate()
</script>

<style lang="scss" scoped></style>