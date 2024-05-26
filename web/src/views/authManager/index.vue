<template>
    <el-card style="width: 100%;">
        <div style="width: 100%;height: 50px;">
            <el-input size="large" v-model="search_data.keyword" style="max-width: 600px" placeholder="输入关键词(搜索框为空默认查询所有信息)"
                class="input-with-select">
                <template #prepend>
                    <el-select size="large" v-model="search_data.type" placeholder="选择查询类型" style="width: 150px">
                        <el-option label="账号" value="u_id" />
                        <el-option label="权限" value="auth" />
                    </el-select>
                </template>
                <template #append>
                    <el-button size="large" @click="searchUser" :icon="Search" />
                </template>
            </el-input>
            <el-button type="primary" plain size="large" style="float: right;" @click="add">
                增加驾校管理员
            </el-button>
        </div>
        <el-table :data="userList" border style="width: 100%">
            <el-table-column label="头像" width="80">
                <template #default="scope">
                    <img :src="scope.row.image" style="height: 50px;width: 50px;margin-right: 5px;border-radius: 2%;">
                </template>
            </el-table-column>
            <el-table-column prop="uId" label="账号" />
            <el-table-column prop="password" label="密码" />
            <el-table-column label="权限">
                <template #default="scope">
                    <span v-if="scope.row.auth == 0">系统管理员</span>
                    <span v-else-if="scope.row.auth == 1">驾校管理员</span>
                    <span v-else>学员</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="350">
                <template #default="scope">
                    <el-button type="info" plain size="large" style="margin-left: 20px;" @click="showDialog(scope.row)">
                        修改权限
                    </el-button>
                    <el-button type="warning" plain size="large" style="margin-left: 10px;" @click="reset(scope.row)">
                        重置密码
                    </el-button>
                    <el-button type="danger" plain size="large" style="margin-left: 10px;"
                        @click="deleteUser(scope.row)">删除</el-button>
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
import { updateAuth, updatePassword, addUser, deletUser, findUser } from "@/api/user.js"
import { ElMessage } from "element-plus";
import { Search } from "@element-plus/icons-vue";

let userList = ref([]);
const getUsers = () => {
    getAllpeo().then(res => {
        userList.value = res;
        for (let i = 0; i < res.length; i++) {
            if (res[i].uId >= 100000 && res[i - 1].uId < 100000) {
                lastId = res[i - 1].uId + 1;
                break;
            }

        }
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
const reset = (row) => {
    updatePassword(row.uId + "", row.password, "123456")
        .then(res => {
            if (res == 0) {
                ElMessage.success("账号\"" + row.uId + "\"的密码已重置！");
                getUsers();
            }
        })
}

let lastId;
const add = () => {
    addUser(lastId).then(() => {
        getUsers();
        ElMessage.success("新的驾校管理员已添加！");
    })
}
const deleteUser = (row) => {
    deletUser(row.uId).then(() => {
        getUsers();
        ElMessage.success("删除成功！");
    })
}
let search_data = reactive({
    keyword: "",
    type: "",
})

const searchUser = () => {
    if (search_data.keyword != "" && search_data.type != "") {
        findUser(search_data.type, search_data.keyword).then(res => {
            userList.value = res;
            ElMessage.success("查询成功！");
            search_data.keyword = "";
            search_data.type = "";
        })
    } else
        getUsers();

}
</script>


<style scoped></style>