<template>
    <el-card>
        <div style="width: 100%;height: 50px;">
            <el-button style="float: right;" type="success" plain @click="flag = true">发布公告</el-button>
        </div>
        <el-dialog v-model="flag" :title="notice.nId == '' ? '发布公告' : '修改公告'" width="500" center>
            <el-input v-model="notice.nTitle" style="width: 460px;margin-bottom: 20px;" placeholder="输入标题" />
            <el-input v-model="notice.nText" style="width: 460px" :autosize="{ minRows: 12, maxRows: 50 }" type="textarea"
                placeholder="输入内容" />
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="() => {
                        notice = {
                            nId: '',
                            uId: userStore.uId,
                            nTitle: '',
                            nText: '',
                        }
                        flag = false
                    }">取消</el-button>
                    <el-button v-if="notice.nId == ''" type="primary" @click="add">
                        发布
                    </el-button>
                    <el-button v-else type="primary" @click="update">
                        修改
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item v-for=" i  in  listNotice " :name="i.nId">

                <template #title>
                    <div style="width: 100%;height: 50px;padding: 5px 10px 0 10px;display: flex;align-items: center;">
                        <el-icon class="header-icon">
                            <info-filled />
                        </el-icon>
                        <span style="float: left;font-size: 20px;">{{ i.nTitle }}</span>
                        <div style="flex: auto;"></div>
                        <span style="font-size: 17px;">发布人：</span>
                        <div style="width: 100px;font-size: 17px;text-align: left;">{{ "#" + i.uId }}</div>
                    </div>
                </template>
                <div v-if="i.uId == userStore.uId" style="display: flex;">
                    <div style="width:93%;font-size: 16px;">
                        {{ i.nText }}
                        <div style="text-align: left;margin-top: 10px;font-size: 15px;color: gray;">{{ '发布时间：' + new
                            Date(i.nTime).toLocaleDateString() }}</div>
                    </div>
                    <div style="width: 7%;display: flex;flex-wrap: wrap;justify-content: center;padding-top: 10px;">
                        <Edit style="width: 25px; height: 25px;cursor: pointer;margin-right: 5px;" @click="() => {
                            notice.nId = i.nId
                            notice.nTitle = i.nTitle
                            notice.nText = i.nText
                            flag = true
                        }" />
                        <Delete style="width: 25px; height: 25px;cursor: pointer;color: red;" @click="deleteN(i.nId)" />
                    </div>
                </div>
                <div v-else style="font-size: 16px;">
                    {{ i.nText }}
                    <div style="text-align: left;margin-top: 10px;font-size: 15px;color: gray;">{{ '发布时间：' + new
                        Date(i.nTime).toLocaleDateString() }}</div>
                </div>
            </el-collapse-item>
        </el-collapse>
    </el-card>
</template>

<script setup>
import { ref } from 'vue';
import { addNotice, getAllNotice, updateNotice, deleteNotice } from "@/api/notice.js";
import { ElMessage } from 'element-plus';
import useUserStore from '@/stores/modules/user';
let flag = ref(false);
const userStore = useUserStore();
let notice = ref(
    {
        nId: "",
        uId: userStore.uId,
        nTitle: "",
        nText: ""
    }
)

const add = () => {
    if (notice.value.nTitle == "" || notice.value.nText == "") {
        ElMessage.warning("请输入完整信息")
    }
    else {
        addNotice(notice.value).then(() => {
            ElMessage.success("发布成功")
            notice.value.nTitle = ""
            notice.value.nText = ""
            flag.value = false
            getDate()
        })
    }

}
let listNotice = ref();
const getDate = () => {
    getAllNotice().then(res => {
        listNotice.value = res;
    })
}
getDate()


const update = () => {
    updateNotice(notice.value).then(() => {
        ElMessage.success("修改成功")
        notice.value.nId = ""
        notice.value.nTitle = ""
        notice.value.nText = ""
        flag.value = false
        getDate()
    })
}

const deleteN = (nId) => {
    deleteNotice(nId).then(() => {
        ElMessage.success("删除成功")
        getDate()
    })
}
</script>

<style scoped></style>