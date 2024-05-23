<template>
    <el-card style="width: 70%;">
        <el-table :data="userList" border style="width: 100%">
            <el-table-column prop="uId" label="账号" />
            <el-table-column prop="state" label="权限" />
            <el-table-column fixed="right" label="操作" width="180">
                <template #default="scope">
                    <el-button type="info" plain size="lager" style="margin-left: 30px;" @click="showDialog(scope.row)">
                        修改权限
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
    <el-dialog v-model="authClass.flag" title="修改权限" width="400" align-center>
        <el-select v-model="authClass.auth" placeholder="Select" size="large" style="width: 300px;margin-left: 30px;">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
        <template #footer>
            <el-button @click="authClass.flag = false">取消</el-button>
            <el-button type="primary" @click="update">
                确认
            </el-button>
        </template>
    </el-dialog>
</template>

<script setup>
import { getAllpeo } from "@/api/user.js"
import { ref, reactive } from "vue"
import { updateAuth } from "@/api/user.js"
import { ElMessage } from "element-plus";
let userList = ref([]);
const getUsers = () => {
    getAllpeo().then(res => {
        userList.value = res;
    }).catch(err => { console.error(err); })
}
getUsers();
const options = [
    {
        label: "系统管理员",
        value: 0,
    },
    {
        label: "驾校管理员",
        value: 1,
    },
    {
        label: "学员",
        value: 2,
    }
]
let authClass = reactive({
    flag: false,
    uId: "",
    auth: "",
})
const showDialog = (row) => {
    authClass.auth = row.auth;
    authClass.uId = row.uId;
    authClass.flag = true;
}
const update = () => {
    updateAuth(authClass.uId, authClass.auth)
        .then(res => {
            if (res == 1) {
                ElMessage.success("修改成功！");
                getUsers();
                authClass.flag = false;
            } else {
                ElMessage.error("出现异常，请重试");
            }
        })
        .catch(err => { console.error(err); })
}
</script>

<style scoped></style>