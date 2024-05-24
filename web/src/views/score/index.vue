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
        <el-button :icon="Search" />
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
        >
          填写成绩
        </el-button>
      </template>
    </el-table-column>
  </el-table>
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

let exbmList = ref([]);
const refresh_exbm = () => {
  exbmApi.getInfo().then((resp) => {
    exbmList.value = resp;
    console.log(exbmList.value);
  });
};
refresh_exbm();
</script>

<style scoped></style>
