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
          <el-option label="学员账号" value="1" />
          <el-option label="学习科目" value="2" />
        </el-select>
      </template>
      <template #append>
        <el-button @click="findPro" :icon="Search" />
      </template>
    </el-input>
  </div>
  <el-table :data="pros" stripe border style="width: 100%">
    <el-table-column prop="uId" label="学员" />
    <el-table-column prop="subId" label="当前学习科目" />
    <el-table-column prop="totalHours" label="学习时长(h)" />
    <el-table-column fixed="right" label="操作" width="150">
      <template #default="data">
        <el-button @click="remind(data.row)" link type="primary" size="large">
          提醒该学员
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import * as reserveApi from "../../api/reserve";
import { Search } from "@element-plus/icons-vue";

let search_data = reactive({
  type: "",
  keyword: "",
});

let proList = ref([]);
let pros = ref([]);

const refresh_pro = () => {
  reserveApi.getPro().then((resp) => {
    proList.value = resp;
    pros.value = resp;
  });
};
refresh_pro();

const findPro = () => {
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
    pros.value = proList.value;
    return;
  }
  if (search_data.type == 1) {
    let t = [];
    proList.value.forEach((i) => {
      if (i.uId.toString().includes(search_data.keyword)) {
        t.push(i);
      }
    });
    pros.value = t;
    return;
  } else if (search_data.type == 2) {
    let t = [];
    proList.value.forEach((i) => {
      if (i.subId.toString().includes(search_data.keyword)) {
        t.push(i);
      }
    });
    pros.value = t;
    return;
  }
};

const remind = (data) => {
  ElMessage({
    type: "success",
    message: "提醒学员" + data.uId + "成功",
  });
};
</script>

<style scoped></style>
