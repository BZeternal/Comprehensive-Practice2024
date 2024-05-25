<template>
  <div
    style="display: flex; justify-content: space-between; margin-bottom: 0.5rem"
  >
    <el-input
      v-model="search_data.keyword"
      style="max-width: 600px"
      placeholder="输入关键词(搜索框为空默认查询所有信息)"
      class="input-with-select"
    >
      <template #prepend>
        <el-select
          v-model="search_data.type"
          placeholder="选择查询类型"
          style="width: 135px"
        >
          <el-option label="学员" value="a_name" />
          <el-option label="考试科目" value="sub_id" />
          <el-option label="驾照类型" value="a_car_type" />
        </el-select>
      </template>
      <template #append>
        <el-button @click="findInfo" :icon="Search" />
      </template>
    </el-input>
  </div>
  <el-table :data="exbmList" stripe border style="width: 100%">
    <el-table-column prop="uId" label="账号" />
    <el-table-column prop="aName" label="学员" />
    <el-table-column prop="aCarType" label="驾照类型" />
    <el-table-column prop="bmTime" label="考试时间" />
    <el-table-column prop="subId" label="考试科目" />
    <el-table-column label="考试成绩">
      <template #default="scope">
        <div v-if="scope.row.bmScore">
          <el-tag
            v-if="
              ((scope.row.subId == 1 || scope.row.subId == 4) &&
                scope.row.bmScore >= 90) ||
              ((scope.row.subId == 2 || scope.row.subId == 3) &&
                scope.row.bmScore >= 80)
            "
            size="large"
            >{{ scope.row.bmScore }}</el-tag
          >
          <el-tag v-else type="danger" size="large">{{
            scope.row.bmScore
          }}</el-tag>
        </div>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="120">
      <template #default="scope">
        <el-button
          v-if="scope.row.bmScore == null"
          link
          type="primary"
          size="large"
          @click="
            () => {
              updateFlag = true;
              Object.keys(exbm).forEach((i) => {
                exbm[i] = scope.row[i];
              });
            }
          "
        >
          填写成绩
        </el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="updateFlag" title="输入成绩" width="500">
    <el-form :model="exbm">
      <el-form-item label="成绩">
        <el-input-number
          v-model="exbm.bmScore"
          :min="0"
          :max="100"
          controls-position="right"
          size="large"
          style="width: 100%"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateFlag = false">取消</el-button>
        <el-button type="primary" @click="updateScore"> 确定 </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import * as exbmApi from "../../api/exbm";
import { Search } from "@element-plus/icons-vue";

let search_data = reactive({
  type: "",
  keyword: "",
});

let updateFlag = ref(false);

let exbm = reactive({
  uId: "",
  subId: "",
  bmScore: 0,
});

let exbmList = ref([]);
const refresh_exbm = () => {
  exbmApi.getInfo().then((resp) => {
    exbmList.value = resp;
  });
};
refresh_exbm();

const updateScore = () => {
  exbmApi.updateScore(exbm).then((resp) => {
    ElMessage({
      type: "success",
      message: "成绩输入成功",
    });
    console.log(resp);
    updateFlag.value = false;
    refresh_exbm();
  });
};

const findInfo = () => {
  if (search_data.type == "") {
    ElMessage({
      type: "error",
      message: "请先选择查询类型",
    });
    return;
  }
  if (
    search_data.keyword == null ||
    search_data.keyword == "" ||
    search_data.keyword == undefined
  ) {
    search_data.type = "";
  }
  exbmApi.findInfo(search_data).then((resp) => {
    exbmList.value = resp;
  });
};
</script>

<style scoped></style>
