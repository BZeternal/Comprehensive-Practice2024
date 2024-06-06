<template>
    <div style="display: flex;flex-wrap: wrap; justify-content: center;">
        <div style="width: 100%;text-align: center;font-size: large;margin-bottom: 10px;color: red;">
            填写以下信息后，进行报名
        </div>
        <el-card>
            <div style=" height: 100%;width: 100%;display: flex;flex-wrap: wrap;">
                <div class="title">个人信息</div>
                <div class="row">
                    <div class="my-div"><span style="color: red;">*</span>姓名</div>
                    <el-input v-model="userInfo.aName" size="" style="width: 200px" placeholder="待填写" />
                </div>
                <div class="row-right">
                    <div class="my-div"><span style="color: red;">*</span>性别</div>
                    <el-select v-model="userInfo.aSex" placeholder="请选择" style="width: 200px">
                        <el-option v-for="item in [{ label: '男', value: '男' }, { label: '女', value: '女' }]"
                            :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div class="row">
                    <div class="my-div"><span style="color: red;">*</span>年龄</div>
                    <el-input v-model="userInfo.aAge" style="width: 200px" placeholder="待填写" />
                </div>
                <div class="row-right">
                    <div class="my-div"><span style="color: red;">*</span>电话</div>
                    <el-input v-model="userInfo.aTel" style="width: 200px" placeholder="待填写" />
                </div>
                <div class="row">
                    <div class="my-div"><span style="color: red;">*</span>身份证</div>
                    <el-input v-model="userInfo.aIdCard" style="width: 200px" placeholder="待填写" />
                </div>
                <div class="row-right">
                    <div class="my-div"><span style="color: red;">*</span>户籍</div>
                    <el-input v-model="userInfo.aNation" style="width: 200px" placeholder="待填写" />
                </div>
                <div class="row">
                    <div class="my-div"><span style="color: red;">*</span>邮寄地址</div>
                    <el-input v-model="userInfo.aAdd" style="width: 200px" placeholder="待填写" />
                </div>
                <div class="row-right">
                    <div class="my-div"><span style="color: red;">*</span>机动车类型</div>
                    <el-select v-model="userInfo.aCarType" placeholder="请选择" style="width: 200px">
                        <el-option label="a1" value="a1" />
                        <el-option label="a2" value="a2" />
                        <el-option label="b1" value="b1" />
                        <el-option label="b2" value="b2" />
                        <el-option label="c1" value="c1" />
                        <el-option label="c2" value="c2" />
                    </el-select>
                </div>
                <div class="row">
                    <div class="my-div"><span style="color: red;">*</span>登记日期</div>
                    <el-date-picker v-model="userInfo.aTime" type="date" placeholder="选择日期" />
                </div>
                <div class="row-right">
                    <div class="my-div"><span style="color: red;">*</span>备注</div>
                    <el-input v-model="userInfo.aText" style="width: 200px" placeholder="待填写" />
                </div>
            </div>

        </el-card>
        <el-card>
            <div class="title">体检表</div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>身高</div>
                <el-input v-model="applyInfo.hHeight" placeholder="待填写" />
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>辨色力</div>
                <el-input v-model="applyInfo.hSelColor" placeholder="待填写" />
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>视力</div>
                <el-input v-model="applyInfo.hSight" placeholder="待填写" />
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>视力是否矫正</div>
                <el-select v-model="applyInfo.hIsCorVision" placeholder="请选择" style="width: 200px">
                    <el-option label="是" value="是" />
                    <el-option label="否" value="否" />
                </el-select>
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>听力</div>
                <el-input v-model="applyInfo.hHearing" placeholder="待填写" />
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>躯干是否有运动障碍</div>
                <el-select v-model="applyInfo.hActionBar" placeholder="请选择" style="width: 200px">
                    <el-option label="是" value="是" />
                    <el-option label="否" value="否" />
                </el-select>
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>是否还有其他疾病</div>
                <el-input v-model="applyInfo.hIsIll" placeholder="待填写" />
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>体检医院</div>
                <el-input v-model="applyInfo.hHospital" placeholder="待填写" />
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>体检日期</div>
                <el-date-picker v-model="applyInfo.hTime" type="date" placeholder="选择日期" />
            </div>
            <div class="he-row">
                <div class="my-div"><span style="color: red;">*</span>其他病例备注</div>
                <el-input v-model="applyInfo.hOthIllText" placeholder="待填写" />
            </div>
        </el-card>
    </div>
    <div style="width: 100%;display: flex;justify-content: center;">
        <el-button @click="submit" type="success" plain>提交报名申请</el-button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { addUserInfo } from "@/api/user.js";
import useUserStore from '@/stores/modules/user';
import { ElMessage } from "element-plus";
import { useRouter } from 'vue-router';

let router = useRouter();
const user = useUserStore();
let userInfo = ref({
    uId: user.uId,
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
})

let applyInfo = ref({
    uId: user.uId,
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
})

const submit = () => {
    addUserInfo(userInfo.value, applyInfo.value).then(res => {
        ElMessage.success("提交成功，请等待审核。");
        router.push("/user/home");
    })
}
</script>

<style scoped>
.el-card {
    width: 60%;
    margin-bottom: 30px;
}

::v-deep .el-card__body {
    padding: 0;
}

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
    width: 130px;
    height: 36px;
    text-align: end;
    line-height: 36px;
    font-size: 15px;
    margin-right: 8px;
}

.row-right {
    height: 36px;
    width: 52%;
    display: flex;
}

.he-row {
    height: 36px;
    width: 100%;
    display: flex;
    margin-bottom: 15px;

    .my-div {
        width: 25%;
    }

    .el-input {
        width: 65%;
    }
}
</style>