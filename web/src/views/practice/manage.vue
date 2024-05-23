<template>
  <el-tabs v-loading="loading" v-model="activeName" @tab-change="handleChange">
    <el-tab-pane name="first">
      <template #label>
        <span>学员练习记录 </span>
        <el-icon style="vertical-align: middle; margin-left: 2px"
          ><Tickets
        /></el-icon>
      </template>
      <div
        style="
          display: flex;
          justify-content: space-between;
          margin-bottom: 0.5rem;
        "
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
              <el-option label="姓名" value="a_name" />
              <el-option label="账号" value="practice_answear.u_id" />
            </el-select>
          </template>
          <template #append>
            <el-button @click="findPracticeInfo()" :icon="Search" />
          </template>
        </el-input>
        <el-select
          @change="changeSub"
          v-model="subject_type"
          clearable
          placeholder="科目"
          style="width: 150px"
        >
          <el-option label="科目1" value="1" />
          <el-option label="科目4" value="4" />
        </el-select>
      </div>
      <el-table :data="practice_info" stripe border style="width: 100%">
        <el-table-column prop="uId" label="用户" />
        <el-table-column prop="aName" label="姓名" />
        <el-table-column prop="prScore" label="分数" />
        <el-table-column prop="prSubject" label="科目" />
      </el-table>
    </el-tab-pane>
    <el-tab-pane name="second">
      <template #label>
        <span>学员练习统计</span>
        <el-icon style="vertical-align: middle; margin-left: 2px"
          ><TrendCharts
        /></el-icon>
      </template>
      <div
        style="
          display: flex;
          justify-content: space-between;
          margin-bottom: 0.5rem;
        "
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
              <el-option label="姓名" value="a_name" />
              <el-option label="账号" value="pa.u_id" />
            </el-select>
          </template>
          <template #append>
            <el-button @click="findPracticeInfo()" :icon="Search" />
          </template>
        </el-input>
        <el-select
          @change="changeSub1"
          v-model="subject_type"
          clearable
          placeholder="科目"
          style="width: 150px"
        >
          <el-option label="科目1" value="1" />
          <el-option label="科目4" value="4" />
        </el-select>
      </div>
      <el-table :data="pracou_info" stripe border style="width: 100%">
        <el-table-column prop="uId" label="用户" />
        <el-table-column prop="aName" label="姓名" />
        <el-table-column prop="maxValue" label="最高成绩" />
        <el-table-column prop="avgValue" label="平均成绩" />
        <el-table-column prop="quaNum" label="合格次数" />
        <el-table-column prop="count" label="答题次数" />
        <el-table-column prop="prSubject" label="科目" />
      </el-table>
    </el-tab-pane>
  </el-tabs>
</template>

<script setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import * as practiceApi from "../../api/practice";
import { Search } from "@element-plus/icons-vue";

let loading = ref(true);

let activeName = ref("first"); //标签页选中标志

let practice_info = ref([]);
let pracou_info = ref([]);
let search_data = reactive({
  type: "",
  keyword: "",
});
let subject_type = ref("");

const refresh_Info = () => {
  practiceApi
    .getPracticeInfo()
    .then((resp) => {
      practice_info.value = resp;
      loading.value = false;
    })
    .catch(() => {
      loading.value = false;
    });
};
refresh_Info();

const refresh_count = () => {
  practiceApi
    .getPraCouInfo()
    .then((resp) => {
      pracou_info.value = resp;
    })
    .catch(() => {
      loading.value = false;
    });
};
refresh_count();

const findPracticeInfo = () => {
  if (
    search_data.type != "a_name" &&
    search_data.type != "practice_answear.u_id" &&
    search_data.type != "pa.u_id"
  ) {
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
  loading.value = true;
  if (activeName.value == "first") {
    practiceApi
      .findPracticeInfo(search_data)
      .then((resp) => {
        practice_info.value = resp;
        loading.value = false;
      })
      .catch(() => {
        loading.value = false;
      });
  } else {
    practiceApi
      .findPraCouInfo(search_data)
      .then((resp) => {
        pracou_info.value = resp;
        loading.value = false;
      })
      .catch(() => {
        loading.value = false;
      });
  }
};

const changeSub = () => {
  search_data.keyword = "";
  search_data.type = "";
  loading.value = true;
  if (subject_type.value == undefined) {
    practiceApi
      .findPracticeInfo({
        type: "pr_subject",
        keyword: "",
      })
      .then((resp) => {
        practice_info.value = resp;
        loading.value = false;
      })
      .catch(() => {
        loading.value = false;
      });
  } else {
    practiceApi
      .findPracticeInfo({
        type: "pr_subject",
        keyword: subject_type.value,
      })
      .then((resp) => {
        practice_info.value = resp;
        loading.value = false;
      })
      .catch(() => {
        loading.value = false;
      });
  }
};

const changeSub1 = () => {
  search_data.keyword = "";
  search_data.type = "";
  loading.value = true;
  if (subject_type.value == undefined) {
    practiceApi
      .findPraCouInfo({
        type: "pa.pr_subject",
        keyword: "",
      })
      .then((resp) => {
        pracou_info.value = resp;
        loading.value = false;
      })
      .catch(() => {
        loading.value = false;
      });
  } else {
    practiceApi
      .findPraCouInfo({
        type: "pa.pr_subject",
        keyword: subject_type.value,
      })
      .then((resp) => {
        pracou_info.value = resp;
        loading.value = false;
      })
      .catch(() => {
        loading.value = false;
      });
  }
};

const handleChange = () => {
  search_data.keyword = "";
  search_data.type = "";
  subject_type.value = "";
};
</script>

<style scoped></style>
