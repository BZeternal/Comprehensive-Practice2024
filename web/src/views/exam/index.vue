<template>
    <div style="width: 100%;display: flex;justify-content: center;margin-bottom: 10px;">
        <el-button size="large" plain style="font-size: 20px;" type="primary" @click="cj">
            查看成绩
        </el-button>
    </div>
    <div style="width: 100%;display: flex;flex-wrap: wrap;justify-content: space-around;">
        <el-card style="width: 45%;margin-bottom: 20px;" v-for="i in listType">
            <template #header>
                <div style="width: 100%;text-align: center;font-size: 25px;">
                    <span>{{ i.subName }}</span>
                </div>
            </template>
            <div style="width: 100%;display: flex;justify-content: center;">
                <img style="width: 300px;height: 300px;" :src="i.img" alt="">
            </div>
            <template #footer>
                <div style="width: 100%;display: flex;justify-content: center;">
                    <el-button size="large" plain style="font-size: 20px;" type="primary"
                        @click="signUp(i.subId, i.subName)">
                        报名
                    </el-button>
                </div>
            </template>
        </el-card>
    </div>
    <el-dialog v-model="flag" :title="km + '报名'" width="500" style="margin-top: 30vh;" center>
        <el-select v-model="adress" placeholder="选择考试地点" size="large" style="width: 450px;margin-bottom: 20px;">
            <el-option label="考场1" value="1" />
            <el-option label="考场2" value="2" />
            <el-option label="考场3" value="3" />
            <el-option label="考场4" value="4" />
            <el-option label="考场5" value="5" />
        </el-select>
        <el-date-picker v-model="examBm.bmTime" size="large" type="datetime" style="width: 450px;" placeholder="选择时间" />
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="() => {
                    examBm = {
                        uId: '',
                        bmTime: '',
                    }
                    flag = false
                }">取消</el-button>
                <el-button type="primary" @click="bm" plain>
                    确认
                </el-button>
            </div>
        </template>
    </el-dialog>
    <el-dialog v-model="flag2" title="考试成绩" width="500" style="margin-top: 30vh;" center>
        <el-table :data="cjList" border style="width: 100%;margin-bottom: 20px;">
            <el-table-column label="车型">
                <template #default="scope">
                    {{ user.aCarType }}
                </template>
            </el-table-column>
            <el-table-column label="考试名称">
                <template #default="scope">
                    {{ '科目' + kmm[scope.row.subId - 1] }}
                </template>
            </el-table-column>
            <el-table-column label="成绩">
                <template #default="scope">
                    {{ scope.row.bmScore == null ? '未考试' : scope.row.bmScore }}
                </template>
            </el-table-column>
            <el-table-column prop="bmTime" label="报名时间" />
        </el-table>
        <div style="width: 100%;display: flex;justify-content: center;">
            <div>当前进度：</div>
            <el-progress style="width: 200px;" :text-inside="true" :stroke-width="24" :percentage="jindu"
                status="success" />
        </div>

    </el-dialog>
</template>

<script setup>
import { getType, isSignUp, canSignUp, getCj } from "@/api/exam.js"
import { ref } from "vue";
import useUserStore from '@/stores/modules/user'
import { ElMessage } from "element-plus";
let user = useUserStore();
let listType = ref()
getType().then(res => {
    listType.value = res
})
let kmm = ['一', '二', '三', '四']
let flag = ref(false)
let flag2 = ref(false)
let km = ref("");
let adress = ref("")
let examBm = ref({
    subId: "",
    uId: "",
    bmTime: "",
})
const signUp = (subId, subName) => {
    isSignUp(user.uId, subId).then(res => {
        console.log(res);
        if (res == 0) {
            examBm.value.subId = subId;
            km.value = subName;
            flag.value = true;
        } else if (res == 1) {
            ElMessage.warning("已报名该考试！请不要再次报名！")
        } else if (res == 2) {
            ElMessage.success("当前考试已合格，不需要再次报名！")
        } else {
            ElMessage.info("前置考试还未合格，无法报名当前考试！")
        }
    })
}

const bm = () => {
    examBm.value.uId = user.uId
    canSignUp(examBm.value).then(() => {
        ElMessage.success(adress.value + "报名成功！")
        flag.value = false
    })
}

let cjList = ref([]);
let jindu = ref(0)
const cj = () => {
    flag2.value = true
    getCj({ uId: user.uId }).then(res => {
        cjList.value = res;
        if (res.length > 0) {
            jindu.value = (res.length - 1) * 25;
            if (res[res.length - 1] == null)
                jindu.value = jindu.value + 12
            else
                jindu.value = jindu.value + 25
        }

    })
}
</script>

<style scoped></style>