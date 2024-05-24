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
          <el-option label="姓名" value="c_name" />
          <el-option label="教学科目" value="c_te_type" />
          <el-option label="驾照类型" value="c_car_type" />
        </el-select>
      </template>
      <template #append>
        <el-button @click="findCoach" :icon="Search" />
      </template>
    </el-input>
    <el-button
      @click="
        () => {
          editFlag = true;
          clearCoach();
          opFlag = false;
        }
      "
      plain
      type="primary"
      size="large"
    >
      添加教练
    </el-button>
  </div>
  <el-table :data="coachList" stripe border style="width: 100%">
    <el-table-column prop="cName" label="姓名" />
    <el-table-column prop="cSex" label="性别" />
    <el-table-column prop="cAge" label="年龄" />
    <el-table-column prop="cCarType" label="驾照类型" />
    <el-table-column prop="cTeType" label="教学科目" />
    <el-table-column prop="cTel" label="联系电话" />
    <el-table-column fixed="right" label="操作" width="150">
      <template #default="data">
        <el-button
          @click="editCoach(data.row)"
          link
          type="primary"
          size="large"
        >
          详细信息
        </el-button>
        <el-button
          @click="deleteCoach(data.row)"
          link
          type="danger"
          size="large"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="editFlag" title="教练信息" width="500">
    <el-form :model="coach" label-width="auto" style="width: 100%">
      <el-form-item label="姓名">
        <el-input v-model="coach.cName" />
      </el-form-item>
      <el-form-item label="年龄">
        <el-input-number
          v-model="coach.cAge"
          :min="0"
          controls-position="right"
        />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="coach.cSex" placeholder="性别">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="教龄">
        <el-input-number
          v-model="coach.cTeachAge"
          :min="0"
          controls-position="right"
        />
      </el-form-item>
      <el-form-item label="驾照类型">
        <el-input v-model="coach.cCarType" />
      </el-form-item>
      <el-form-item label="教学科目">
        <el-select v-model="coach.cTeType" placeholder="教学科目">
          <el-option label="科二" value="科二" />
          <el-option label="科三" value="科三" />
        </el-select>
      </el-form-item>
      <el-form-item label="学员数量">
        <el-input-number
          v-model="coach.cNum"
          :min="0"
          controls-position="right"
        />
      </el-form-item>
      <el-form-item label="学员通过率">
        <el-input-number
          v-model="coach.cPass"
          controls-position="right"
          :precision="2"
          :step="0.01"
          :max="1"
          :min="0"
        />
      </el-form-item>
      <el-form-item label="联系电话">
        <el-input v-model="coach.cTel" />
      </el-form-item>
      <el-form-item label="微信">
        <el-input v-model="coach.cChat" />
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="coach.cText" type="textarea" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="editFlag = false">取消</el-button>
        <el-button
          type="primary"
          @click="
            () => {
              if (opFlag) {
                updateCoach();
              } else {
                insertCoach();
              }
            }
          "
        >
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import * as coachApi from "../../api/coach";
import useUserStore from "@/stores/modules/user.js";
import { Search } from "@element-plus/icons-vue";
const userSore = useUserStore();

let search_data = reactive({
  type: "",
  keyword: "",
});

let coach = reactive({
  cAge: 0,
  cCarType: "",
  cChat: "",
  cId: "",
  cName: "",
  cNum: 0,
  cPass: 0,
  cPic: "",
  cSex: "",
  cTeType: "",
  cTeachAge: 0,
  cTel: "",
  cText: "",
});

let editFlag = ref(false);
let opFlag = ref(false);

let coachList = ref([]);
const refresh_coach = () => {
  coachApi.getCoachInfo().then((resp) => {
    coachList.value = resp;
  });
};
refresh_coach();

const clearCoach = () => {
  Object.keys(coach).forEach((i) => {
    coach[i] = "";
  });
  coach.cAge = 0;
  coach.cTeachAge = 0;
  coach.cNum = 0;
  coach.cPass = 0;
};

const editCoach = (data) => {
  opFlag.value = true;
  editFlag.value = true;
  Object.keys(coach).forEach((i) => {
    coach[i] = data[i];
  });
};

const findCoach = () => {
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
  coachApi.findCoach(search_data).then((resp) => {
    coachList.value = resp;
  });
};

const deleteCoach = (data) => {
  coachApi.deleteCoach({ cId: data.cId }).then((resp) => {
    ElMessage({
      type: "success",
      message: resp.error_info,
    });
    refresh_coach();
  });
};

const updateCoach = () => {
  coachApi.updateCoach(coach).then((resp) => {
    ElMessage({
      type: "success",
      message: resp.error_info,
    });
    refresh_coach();
    editFlag.value = false;
  });
};

const insertCoach = () => {
  coachApi.insertCoach(coach).then((resp) => {
    ElMessage({
      type: "success",
      message: resp.error_info,
    });
    refresh_coach();
    editFlag.value = false;
  });
};
</script>

<style scoped></style>
