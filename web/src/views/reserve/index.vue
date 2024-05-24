<template>
  <div style="display: flex">
    <div v-if="subId != null" style="margin-top: 2rem">
      <el-button
        @click="
          () => {
            dialogVisible = true;
            startTime = '';
            endTime = '';
          }
        "
        style="width: 70%"
        type="primary"
        plain
      >
        预约{{ myDate.getMonth() + 1 }}月{{ myDate.getDate() }}日
      </el-button>
      <el-button
        @click="deleteFlag = deleteFlag ? false : true"
        style="width: 25%"
        type="danger"
        plain
      >
        取消预约
      </el-button>
      <el-timeline style="width: 18rem; margin-top: 2rem">
        <el-timeline-item
          style="height: 10rem"
          v-for="(activity, index) in activities"
          :key="index"
          :timestamp="activity.timestamp"
        >
          <el-card>
            <div
              style="
                display: flex;
                justify-content: space-between;
                align-items: center;
              "
            >
              <div>
                <h4>开始时间： {{ activity.staTime }}</h4>
                <h4>结束时间： {{ activity.enTime }}</h4>
                <h4>学习时长： {{ activity.lealength }} 小时</h4>
              </div>
              <el-icon
                v-if="deleteFlag"
                style="cursor: pointer"
                @click="deleteReserve(activity)"
                size="20px"
                color="red"
                ><CircleCloseFilled
              /></el-icon>
            </div>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
    <el-calendar v-model="myDate">
      <template #header="{ date }">
        <h2>{{ text }}</h2>
        <h3>{{ date }}</h3>
        <h3>日历</h3>
      </template>
      <template #date-cell="{ data }">
        <p :class="data.isSelected ? 'is-selected' : ''">
          {{ data.day.split("-").slice(1).join("-") }}
          {{ data.isSelected ? "✔️" : "" }}
        </p>
      </template>
    </el-calendar>
  </div>
  <el-dialog v-model="dialogVisible" title="选择时间" width="600">
    <div style="display: flex; justify-content: space-around">
      <el-time-select
        v-model="startTime"
        style="width: 240px"
        :max-time="endTime"
        class="mr-4"
        placeholder="开始时间"
        start="08:00"
        step="01:00"
        end="19:00"
      />
      <el-time-select
        v-model="endTime"
        style="width: 240px"
        :min-time="startTime"
        placeholder="结束时间"
        start="09:00"
        step="01:00"
        end="20:00"
      />
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="reserveTime"> 确定 </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { ElNotification } from "element-plus";
import { judgeType } from "../../api/exbm";
import * as reserveApi from "../../api/reserve";
import useUserStore from "@/stores/modules/user.js";
const userSore = useUserStore();

let dialogVisible = ref(false);
let startTime = ref("");
let endTime = ref("");
let deleteFlag = ref(false);

const activities = reactive([]);

let myDate = ref(new Date());

let subId = ref(""); //科目
let text = ref("");

const judgeUserType = () => {
  judgeType({ uId: userSore.uId }).then((resp) => {
    subId.value = resp.subId;
    if (resp.subId == 2) text.value = "科二约车";
    if (resp.subId == 3) text.value = "科三约车";
    refresh_info();
  });
};
judgeUserType();

const refresh_info = () => {
  reserveApi
    .findReserveByUser({
      uId: userSore.uId,
      subId: subId.value,
    })
    .then((resp) => {
      activities.length = 0;
      let date = new Date();
      resp.forEach((i) => {
        let t1 = new Date(i.staTime);
        let t2 = new Date(i.enTime);
        activities.push({
          resId: i.resId,
          timestamp: t1.toLocaleDateString(),
          staTime: t1.toLocaleTimeString(),
          enTime: t2.toLocaleTimeString(),
          lealength: (t2.getTime() - t1.getTime()) / 3600000,
        });
        if (
          date.toLocaleDateString() == t1.toLocaleDateString() &&
          date.getHours() <= t1.getHours()
        ) {
          ElNotification({
            title: date.toLocaleDateString(),
            message: "您今天有学习预约，记得按时到达哦！",
            type: "info",
          });
        }
      });
    });
};

const reserveTime = () => {
  if (new Date().getTime() > myDate.value.getTime()) {
    ElMessage({
      type: "error",
      message: "请至少提前一天预约",
    });
    return;
  }

  if (
    startTime.value == "" ||
    startTime.value == undefined ||
    endTime.value == "" ||
    endTime.value == undefined
  ) {
    ElMessage({
      type: "error",
      message: "请选择预约时间",
    });
    return;
  }

  if (activities.length > 3) {
    ElMessage({
      type: "error",
      message: "最多只能同时预约3天哟!",
    });
    return;
  }
  let flag = false;
  activities.forEach((i) => {
    if (i.timestamp == myDate.value.toLocaleDateString()) {
      ElMessage({
        type: "error",
        message: "同一天最多只能预约一次哟!",
      });
      flag = true;
    }
  });
  if (flag) return;

  let year = myDate.value.getFullYear();
  let month = myDate.value.getMonth() + 1;
  if (month < 10) month = "0" + month;
  let day = myDate.value.getDate();

  reserveApi
    .insertReserve({
      uId: userSore.uId,
      subId: subId.value,
      staTime: year + "/" + month + "/" + day + " " + startTime.value,
      enTime: year + "/" + month + "/" + day + " " + endTime.value,
    })
    .then((resp) => {
      if (resp.error_info == "success") {
        ElMessage({
          type: "success",
          message: "预约成功",
        });
        refresh_info();
      }
    });

  dialogVisible.value = false;
};

const deleteReserve = (data) => {
  ElMessageBox.confirm("是否要取消预约", "警告", {
    confirmButtonText: "是",
    cancelButtonText: "否",
    type: "warning",
  })
    .then(() => {
      reserveApi.deleteReserve({ resId: data.resId }).then((resp) => {
        if (resp.error_info == "success") {
          ElMessage({
            type: "success",
            message: "删除成功",
          });
          refresh_info();
        } else {
          ElMessage({
            type: "error",
            message: "删除失败",
          });
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消删除成功",
      });
    });
};
</script>

<style scoped>
.is-selected {
  color: #1989fa;
}
</style>
