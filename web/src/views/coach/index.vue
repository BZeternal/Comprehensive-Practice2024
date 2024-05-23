<template>
  <div v-if="showCoach">
    <el-descriptions title="教练信息" :column="2" size="large" border>
      <el-descriptions-item label="姓名">{{
        coach.cName
      }}</el-descriptions-item>
      <el-descriptions-item label="性别">{{ coach.cSex }}</el-descriptions-item>
      <el-descriptions-item label="年龄">{{ coach.cAge }}</el-descriptions-item>
      <el-descriptions-item label="教龄">{{
        coach.cTeachAge
      }}</el-descriptions-item>
      <el-descriptions-item label="驾照类型">{{
        coach.cCarType
      }}</el-descriptions-item>
      <el-descriptions-item label="教学科目">{{
        coach.cTeType
      }}</el-descriptions-item>
      <el-descriptions-item label="学员数量">{{
        coach.cNum
      }}</el-descriptions-item>
      <el-descriptions-item label="学员通过率">{{
        coach.cPass
      }}</el-descriptions-item>
      <el-descriptions-item label="电话">{{ coach.cTel }}</el-descriptions-item>
      <el-descriptions-item label="微信">{{
        coach.cChat
      }}</el-descriptions-item>
      <el-descriptions-item label="简述">
        <div style="display: flex">
          <img
            :src="coach.cPic"
            style="width: 40%; height: 20rem; margin-right: 0.5rem"
          />
          <el-tag size="large" style="font-size: 1rem">{{
            coach.cText
          }}</el-tag>
        </div>
      </el-descriptions-item>
    </el-descriptions>
  </div>
  <div
    v-else
    style="display: flex; justify-content: space-around; flex-wrap: wrap"
  >
    <el-card
      v-for="coach in coachList"
      :key="coach.cId"
      style="width: 340px; margin: 0 1rem 1rem 0"
    >
      <template #header>
        <h3>{{ coach.cName }}</h3>
      </template>
      <img :src="coach.cPic" style="width: 100%; height: 12rem" />
      <div style="display: flex">
        <h4 style="flex: 1">性别：{{ coach.cSex }}</h4>
        <h4 style="flex: 1">年龄：{{ coach.cAge }}</h4>
      </div>
      <div style="display: flex">
        <h4 style="flex: 1">教龄：{{ coach.cTeachAge }}</h4>
        <h4 style="flex: 1">学员数量：{{ coach.cNum }}</h4>
      </div>
      <div style="display: flex">
        <h4 style="flex: 1">驾照类型：{{ coach.cCarType }}</h4>
        <h4 style="flex: 1">教学科目：{{ coach.cTeType }}</h4>
      </div>

      <h4>学员通过率：{{ coach.cPass }}</h4>
      <h5 style="font-weight: bold">简述：{{ coach.cText }}</h5>
      <template #footer>
        <div style="width: 100%; display: flex; justify-content: center">
          <el-button
            v-if="subId != null"
            style="width: 60%; font-size: large"
            type="primary"
            text
            @click="userChooseCoach(coach)"
          >
            选择
          </el-button>
        </div>
      </template>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import * as coachApi from "../../api/coach";
import { judgeType } from "../../api/exbm";
import { chooseCoach } from "../../api/selTe";
import useUserStore from "@/stores/modules/user.js";
const userSore = useUserStore();

let coachList = ref([]);
let subId = ref(""); //科目
let coach = reactive({});
let showCoach = ref(false); //是否显示选择的教练信息

const refresh_all_coach = () => {
  coachApi.getCoachInfo().then((resp) => {
    coachList.value = resp;
  });
};

const judgeUserType = () => {
  judgeType({ uId: userSore.uId }).then((resp) => {
    subId.value = resp.subId;
    if (resp.subId == null) {
      refresh_all_coach();
    } else {
      selectCoach();
    }
  });
};
judgeUserType();

const selectCoach = () => {
  coachApi
    .selectCoach({
      uId: userSore.uId,
      selType: subId.value,
    })
    .then((resp) => {
      if (resp != "") {
        coach = resp;
        showCoach.value = true;
      } else {
        showCoach.value = false;
        refresh_coach();
      }
    });
};

const refresh_coach = () => {
  coachApi
    .chooseCoachList({
      cCarType: userSore.aCarType,
      cTeType: subId.value,
    })
    .then((resp) => {
      coachList.value = resp;
    });
};

const userChooseCoach = (data) => {
  ElMessageBox.confirm("教练选择后将不可更改？是否确认选择教练", "警告", {
    confirmButtonText: "是",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      ElMessage({
        type: "success",
        message: "选择教练成功",
      });
      chooseCoach({
        uId: userSore.uId,
        cId: data.cId,
        selType: subId.value,
      }).then((resp) => {
        if (resp.error_info == "success") {
          location.reload();
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消选择成功",
      });
    });
};
</script>

<style scoped></style>
