<template>
  <div
    style="
      display: flex;
      flex-direction: column;
      width: 100%;
      align-items: center;
    "
  >
    <div
      style="
        display: flex;
        justify-content: center;
        width: 100%;
        margin-bottom: 2rem;
      "
    >
      <img
        :src="userSore.image"
        style="height: 3rem; width: 3rem; border-radius: 50%"
      />
      <el-input
        style="width: 70%; margin-left: 2rem"
        placeholder="发表新鲜事"
        @click="submitFlag = true"
      />
    </div>

    <el-dialog v-model="submitFlag" title="发表新鲜事" width="800">
      <div style="display: flex; justify-content: center">
        <el-form style="width: 70%" :model="forum">
          <el-form-item label="标题">
            <el-input v-model="forum.fTitle" />
          </el-form-item>
          <el-form-item label="内容">
            <el-input
              :autosize="{ minRows: 10, maxRows: 30 }"
              type="textarea"
              placeholder="分享一下趣事吧！"
              v-model="forum.fContent"
            />
          </el-form-item>

          <el-form-item label="分享照片"
            ><el-upload
              action="#"
              list-type="picture-card"
              :auto-upload="false"
            >
              <el-icon><Plus /></el-icon>
            </el-upload>
          </el-form-item>
        </el-form>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="submitFlag = false">取消</el-button>
          <el-button type="primary" @click="insertForum"> 发表 </el-button>
        </div>
      </template>
    </el-dialog>

    <el-card
      v-for="forum in forums"
      :key="forum.fId"
      shadow="hover"
      style="width: 80%"
    >
      <template #header>
        <div style="display: flex; justify-content: space-between">
          <h4 style="color: gray">新鲜事</h4>
          <div>
            <el-icon
              v-if="userSore.uId == forum.uId"
              size="1.2rem"
              style="margin-right: 0.5rem"
              ><Edit
            /></el-icon>
            <el-icon
              @click="deleteForum(forum)"
              v-if="userSore.uId == forum.uId"
              size="1.2rem"
              ><Delete
            /></el-icon>
          </div>
        </div>
      </template>
      <div style="display: flex; flex-direction: column; align-items: center">
        <div style="display: flex; align-items: center; width: 100%">
          <img
            :src="forum.image"
            style="
              height: 2rem;
              width: 2rem;
              border-radius: 50%;
              margin-right: 0.5rem;
            "
          />
          <h4>用户{{ forum.uId }}</h4>
          <span style="flex-grow: 0.45"></span>
          <h3 style="justify-self: flex-end; font-weight: 700">
            {{ forum.fTitle }}
          </h3>
        </div>
        <h4
          style="
            margin-left: 2.5rem;
            color: rgb(153, 184, 216);
            align-self: start;
          "
        >
          {{ forum.fTime }}
        </h4>
        <div class="content">{{ forum.fContent }}</div>
        <img
          v-if="forum.fImg != ''"
          :src="forum.fImg"
          style="height: 20rem; width: 50%; margin: 1rem 0.5rem"
        />
      </div>

      <template #footer>
        <div style="display: flex">
          <el-button @click="updateForum(forum)" style="flex: 1" link>
            <el-icon
              v-if="forum.flag"
              color="yellow"
              style="vertical-align: middle"
              size="1.4rem"
              ><StarFilled
            /></el-icon>
            <el-icon v-else style="vertical-align: middle" size="1.4rem"
              ><StarFilled /></el-icon
            >{{ forum.fNum }}
          </el-button>
          <el-button style="flex: 1" link>
            <el-icon color="rgb(64,158,255)" size="1.4rem"><Comment /></el-icon
            >{{ forum.comments.length }}
          </el-button>
          <el-button style="flex: 1" link>
            <el-icon size="1.2rem"><Share /></el-icon>
          </el-button>
        </div>
        <el-collapse>
          <el-collapse-item @click="comment.coContent = ''" name="1">
            <div
              style="display: flex; flex-direction: column; align-items: center"
            >
              <div
                style="
                  display: flex;
                  justify-content: center;
                  width: 100%;
                  margin-bottom: 2rem;
                "
              >
                <img
                  :src="userSore.image"
                  style="height: 2rem; width: 2rem; border-radius: 50%"
                />
                <el-input
                  v-model="comment.coContent"
                  style="width: 70%; margin-left: 2rem"
                  placeholder="发表评论"
                />
                <el-button @click="insertComment(forum)" type="primary">
                  发表
                </el-button>
              </div>
              <div
                v-for="comment in forum.comments"
                :key="comment.coId"
                style="
                  display: flex;
                  flex-direction: column;
                  align-items: center;
                  width: 100%;
                  margin-bottom: 2rem;
                "
              >
                <div style="align-self: flex-start; display: flex">
                  <img
                    :src="comment.image"
                    style="
                      height: 2rem;
                      width: 2rem;
                      border-radius: 50%;
                      margin-left: 5rem;
                      margin-right: 0.5rem;
                    "
                  />
                  <h3>用户{{ comment.uId }}</h3>
                  <el-icon
                    @click="deleteComment(comment)"
                    v-if="userSore.uId == comment.uId"
                    size="1.2rem"
                    style="margin-left: 2rem"
                    ><Delete
                  /></el-icon>
                </div>
                <h4
                  style="
                    margin-left: 7.5rem;
                    color: rgb(153, 184, 216);
                    align-self: start;
                  "
                >
                  {{ comment.coTime }}
                </h4>
                <h4 style="width: 80%; margin-left: 8rem">
                  {{ comment.coContent }}
                </h4>
              </div>
            </div>
          </el-collapse-item>
        </el-collapse>
      </template>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import useUserStore from "@/stores/modules/user.js";
import * as forumApi from "@/api/forum";
import { ElMessage, ElMessageBox } from "element-plus";
const userSore = useUserStore();

let submitFlag = ref(false);
let forum = reactive({
  uId: userSore.uId,
  fContent: null,
  fTitle: "",
  fNum: "",
  fId: null,
  fImg: null,
  fTime: null,
});

let comment = reactive({
  coId: null,
  uId: "",
  fId: "",
  coContent: "",
});

let forums = ref([]);

const refresh_Info = () => {
  forumApi.getInfo().then((resp) => {
    forums.value = resp;
    forums.value.forEach((i) => {
      i.flag = false;
    });
  });
};
refresh_Info();

const insertForum = () => {
  forum.uId = userSore.uId;
  forumApi.insertForum(forum).then((resp) => {
    if (resp.error_info == "success") {
      ElMessage({
        type: "success",
        message: "发表成功",
      });
      Object.keys(forum).forEach((i) => {
        forum[i] = "";
      });
      refresh_Info();
      submitFlag.value = false;
    }
  });
};

const deleteForum = (data) => {
  forumApi.deleteForum({ fId: data.fId }).then((resp) => {
    if (resp.error_info == "success") {
      ElMessage({
        type: "success",
        message: "删除成功",
      });
      refresh_Info();
    }
  });
};

const insertComment = (data) => {
  comment.uId = userSore.uId;
  comment.fId = data.fId;
  forumApi.insertComment(comment).then((resp) => {
    if (resp.error_info == "success") {
      ElMessage({
        type: "success",
        message: "评论成功",
      });
      comment.coContent = "";
      refresh_Info();
    }
  });
};

const deleteComment = (data) => {
  forumApi.deleteComment({ coId: data.coId }).then((resp) => {
    if (resp.error_info == "success") {
      ElMessage({
        type: "success",
        message: "删除成功",
      });
      refresh_Info();
    }
  });
};

const updateForum = (forum) => {
  if (forum.flag) {
    forum.fNum -= 1;
    forum.flag = false;
  } else {
    forum.fNum += 1;
    forum.flag = true;
  }
  forumApi.updateForum(forum).then((resp) => {
    // refresh_Info();
  });
};
</script>

<style scoped>
.content {
  /* border-style: solid; */
  width: 100%;
  padding: 0.5rem 2rem;
  /* border-width: 1px; */
}
</style>
