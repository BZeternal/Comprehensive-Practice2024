<template>
    <div style="width: 100%;display: flex; justify-content: space-between;">
        <el-card style="width: 49%;">
            <div style="height: 50px;width: 100%;font-size: 25px;text-align: center;color: burlywood;">待审核人员</div>
            <el-table :data="userList" border style="width: 100%">
                <el-table-column label="头像" width="105">
                    <template #default="scope">
                        <img :src="scope.row.image" style="height: 75px;width: 75px;border-radius: 2%;">
                    </template>
                </el-table-column>
                <el-table-column prop="uId" label="用户">
                    <template #default="scope">
                        <span style="font-size: 20px;">{{ "#" + scope.row.uId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="130">
                    <template #default="scope">
                        <el-button type="info" plain size="large" @click="showDialog(scope.row)">
                            进行审核
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-card style="width: 49%;">
            <div style="height: 50px;width: 100%;font-size: 25px;text-align: center;color: green;">审核通过</div>
            <el-table :data="userList2" border style="width: 100%">
                <el-table-column label="头像" width="105">
                    <template #default="scope">
                        <img :src="scope.row.image" style="height: 75px;width: 75px;border-radius: 2%;">
                    </template>
                </el-table-column>
                <el-table-column prop="uId" label="用户">
                    <template #default="scope">
                        <span style="font-size: 20px;">{{ "#" + scope.row.uId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="149">
                    <template #default="scope">
                        <el-button type="primary" plain size="large" @click="showDialog(scope.row)">
                            查看相关信息
                        </el-button>
                        <!-- <el-button type="warning" plain size="large" @click="() => {
                            tmpInfo.user = scope.row;
                            checkInfo(1);
                        }">驳回重填</el-button> -->
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
    <el-dialog v-model="tmpInfo.flag" width="50vw" center style="margin: 50px auto;">
        <el-card style="width: 100%;margin-bottom: 20px;">
            <div style=" height: 100%;width: 100%;display: flex;flex-wrap: wrap;">
                <div class="title">个人信息</div>
                <div class="row">
                    <div class="my-div">姓名：</div>
                    <div class="my-div-right">{{ applyList.aName }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">性别：</div>
                    <div class="my-div-right">{{ applyList.aSex }}</div>
                </div>
                <div class="row">
                    <div class="my-div">年龄：</div>
                    <div class="my-div-right">{{ applyList.aAge }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">电话：</div>
                    <div class="my-div-right">{{ applyList.aTel }}</div>
                </div>
                <div class="row">
                    <div class="my-div">身份证：</div>
                    <div class="my-div-right">{{ applyList.aIdCard }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">户籍：</div>
                    <div class="my-div-right">{{ applyList.aNation }}</div>
                </div>
                <div class="row">
                    <div class="my-div">邮寄地址：</div>
                    <div class="my-div-right">{{ applyList.aAdd }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">机动车类型：</div>
                    <div class="my-div-right">{{ applyList.aCarType }}</div>
                </div>
                <div class="row">
                    <div class="my-div">登记日期：</div>
                    <div class="my-div-right">{{ new Date(applyList.aTime).toLocaleDateString([], {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit'
                    }) }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">备注：</div>
                    <div class="my-div-right">{{ applyList.aText }}</div>
                </div>
            </div>

        </el-card>
        <el-card style="width: 100%;">
            <div class="title">体检表</div>
            <div style="width: 100%;flex-wrap: wrap;display: flex;">
                <div class="row">
                    <div class="my-div">身高：</div>
                    <div class="my-div-right">{{ heaList.hHeight }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">辨色力：</div>
                    <div class="my-div-right">{{ heaList.hSelColor }}</div>
                </div>
                <div class="row">
                    <div class="my-div">视力：</div>
                    <div class="my-div-right">{{ heaList.hSight }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">视力是否矫正：</div>
                    <div class="my-div-right">{{ heaList.hIsCorVision }}</div>
                </div>
                <div class="row">
                    <div class="my-div">听力：</div>
                    <div class="my-div-right">{{ heaList.hHearing }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">躯干是否有运动障碍：</div>
                    <div class="my-div-right">{{ heaList.hActionBar }}</div>
                </div>
                <div class="row">
                    <div class="my-div">是否还有其他疾病：</div>
                    <div class="my-div-right">{{ heaList.hIsIll }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">体检医院：</div>
                    <div class="my-div-right">{{ heaList.hHospital }}</div>
                </div>
                <div class="row">
                    <div class="my-div">体检日期：</div>
                    <div class="my-div-right">{{ new Date(heaList.hTime).toLocaleDateString([], {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit'
                    }) }}</div>
                </div>
                <div class="row-right">
                    <div class="my-div">其他病例备注：</div>
                    <div class="my-div-right">{{ heaList.hOthIllText }}</div>
                </div>
            </div>
        </el-card>
        <template #footer v-if="tmpInfo.user.state == '审核中'">
            <div class="dialog-footer">
                <el-button type="warning" @click="checkInfo(1)">驳回</el-button>
                <el-button type="success" @click="checkInfo(2)">通过</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from "vue"
import { getAllpeo, getApply, getHea, check } from "@/api/user.js"
import { ElMessage } from "element-plus";
let userList = ref();
let userList2 = ref();
const getUsers = () => {
    getAllpeo().then(res => {
        userList.value = [];
        userList2.value = [];
        res.forEach(el => {
            if (el.state == "审核中")
                userList.value.push(el);
            if (el.state == "学员")
                userList2.value.push(el);
        });
    }).catch(err => { console.error(err); })
}
getUsers();

let applyList = ref({
    aName: "",
    aSex: "",
    aAge: "",
    aTel: "",
    aIdCard: "",
    aNation: "",
    aCarType: "",
    aAdd: "",
    aTime: "",
    aText: "",
});
let heaList = ref(
    {
        hHeight: "",
        hSelColor: "",
        hSight: "",
        hIsCorVision: "",
        hHearing: "",
        hActionBar: "",
        hIsIll: "",
        hHospital: "",
        hTime: "",
        hOthIllText: "",
    }
);
let tmpInfo = ref({
    flag: false,
    user: {
        state: ""
    },
})
const showDialog = (row) => {
    getApply(row.uId).then(res => {
        applyList.value = res[0];
    })
    getHea(row.uId).then(res => {
        heaList.value = res[0];
    })
    tmpInfo.value.user = row;
    tmpInfo.value.flag = true;
}
const checkInfo = (t) => {
    if (t == 1) {
        check(tmpInfo.value.user.uId, "被驳回").then(() => {
            ElMessage.warning("已驳回");
            getUsers();
            tmpInfo.value.flag = false;
        });
    } else {
        check(tmpInfo.value.user.uId, "学员").then(() => {
            ElMessage.success("已通过");
            getUsers();
            tmpInfo.value.flag = false;
        });
    }
}
</script>

<style scoped>
.title {
    width: 100%;
    height: 50px;
    font-size: 30px;
    line-height: 50px;
    text-align: center;
    margin-bottom: 10px;
}

.row {
    height: 36px;
    width: 48%;
    display: flex;
    margin-bottom: 15px;
}

.my-div {
    width: 180px;
    height: 36px;
    text-align: end;
    line-height: 36px;
    font-size: 15px;
    margin-right: 8px;
}

.my-div-right {
    height: 36px;
    line-height: 35px;
    font-size: 14px;
    font-weight: 600;
    max-width: 200px;
}

.row-right {
    height: 36px;
    width: 52%;
    display: flex;
}
</style>