<template>
    <el-card>
        <div class="title">
            账号信息
        </div>
        <div class="area">
            <div style="width: 50%;padding-left: 60px;">
                <div class="item" style="height: 100%; align-items: center;">
                    <div class="left" style="line-height: 156px;">头像：</div>
                    <div style="height: 100%;">
                        <img :src="userStore.image" style="height: 155px;width: 155px;margin-right: 5px;border-radius: 2%;">
                    </div>
                    <el-upload class="avatar-uploader" action="#">
                        <el-icon>
                            <Plus class="avatar-uploader-icon" />
                        </el-icon>
                    </el-upload>
                </div>
            </div>
            <div class="right-area">
                <div class="item">
                    <div class="left">账号：</div>
                    <div class="right">{{ userStore.uId }}</div>
                </div>

                <div class="item">
                    <div class="left">身份：</div>
                    <div class="right">{{ userStore.state }}</div>
                </div>
                <div class="item">
                    <div class="left">密码：</div>
                    <el-button type="primary" plain @click="passwordClass.flag = true">修改密码</el-button>
                    <el-dialog v-model="passwordClass.flag" title="修改密码" width="500" align-center>
                        <div style="padding-left: 30px;">
                            <div style="height: 20px;"></div>
                            <span style="font-size: 15px;">原密码：</span>
                            <el-input v-model="passwordClass.oldpass" style="width: 350px;" size="large"
                                placeholder="请输入" />
                            <div style="height: 20px;"></div>
                            <span style="font-size: 15px;">新密码：</span>
                            <el-input v-model="passwordClass.newpass" style="width: 350px" size="large" placeholder="请输入" />
                            <div style="height: 20px;"></div>
                        </div>
                        <template #footer>
                            <el-button @click="passwordClass.flag = false">取消</el-button>
                            <el-button type="primary" @click="updatePassword">
                                确认
                            </el-button>
                        </template>
                    </el-dialog>
                </div>
            </div>

        </div>
        <div></div>
    </el-card>
</template>

<script setup>
import useUserStore from '@/stores/modules/user';
import { reactive } from 'vue';
import * as userApi from "@/api/user.js"
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';

let userStore = useUserStore();
const router = new useRouter();
let passwordClass = reactive({
    flag: false,
    oldpass: "",
    newpass: "",
})
const updatePassword = () => {
    if (passwordClass.oldpass != "" && passwordClass.newpass != "")
        userApi.updatePassword(userStore.uId, passwordClass.oldpass, passwordClass.newpass)
            .then(res => {
                if (res == 0) {
                    ElMessage.success("修改成功！");
                    passwordClass.flag = false;
                    setTimeout(() => {
                        userStore.logout();
                        router.push("/login");
                        ElMessage.info("登录已失效，请重新登录");
                    }, 1000)
                } else if (res == 1) {
                    ElMessage.error("原密码错误，请重新输入");
                } else {
                    ElMessage.error("出现异常，请重试");
                }
            })
            .catch(err => { console.error(err); })
    else
        ElMessage.warning("请确保输入完全！");
}
</script>

<style scoped>
.title {
    height: 50px;
    font-size: 25px;
    color: black;
}

.area {
    width: 100%;
    display: flex;
    flex-wrap: wrap;

    .right-area {
        width: 50%;
    }

}

.item {
    width: 100%;
    height: 50px;
    display: flex;
    align-items: center;

    .left {
        height: 100%;
        width: 20%;
        font-size: 20px;
        line-height: 50px;
        text-align: right;
    }

    .right {
        height: 100%;
        width: 80%;
        font-size: 20px;
        line-height: 50px;
        padding-left: 10px;
    }
}

::v-deep .avatar-uploader {
    width: 178px;
    height: 178px;
    display: block;
}

::v-deep .el-icon {
    font-size: 28px;
    color: #8c939d;
    width: 155px;
    height: 155px;
    text-align: center;
}
</style>
<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}
</style>