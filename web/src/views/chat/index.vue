<template>
    <el-container style="height: 100%;width: 100%;border-radius:4px; border: 1px solid #e4e7ed;
    box-shadow: 0px 0px 12px rgba(0, 0, 0, .12);">
        <el-aside width="300px">
            <div class="leftSide">
                <!--header-->
                <div class="header">
                    {{ auth }}
                </div>
                <!--ChatList-->
                <div v-if="peoleList.length <= 0">当前无客服人员在线</div>
                <div class="chatlist" v-for="i, index in peoleList" @click="clickPeo(index)">
                    <div class="block" :class="{ active: index == selectFlag.index }">
                        <div class="imgbx">
                            <img :src="i.img" class="cover">
                        </div>
                        <div class="details">
                            <div class="listHead">
                                <h4>{{ "#" + i.uId }}</h4>
                                <p class="time">{{ i.time }}</p>
                            </div>
                            <div class="message_p">
                                <p v-if="i.lastMessage != ''">{{ i.lastMessage }}</p>
                                <b v-if="i.isRead > 0">{{ i.isRead }}</b>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </el-aside>
        <el-container>
            <el-main style="height: 80%">
                <div class="header">
                    <div class="imgText" v-if="selectFlag.index != -1">
                        <div class="userimg">
                            <img :src="selectFlag.img" class="cover">
                        </div>
                        <h4>{{ "#" + selectFlag.uId }}<br><span>在线</span></h4>
                    </div>
                </div>
                <!--chatbox-->
                <div class="content">
                    <el-scrollbar>
                        <div v-if="selectFlag.index != -1" class="item item-center"><span>对方已上线，现在可以开始聊天了。</span></div>
                        <div v-for="i in messagelist">
                            <div v-if="i.fromName == selectFlag.uId" class="item item-left">
                                <div class="avatar"><img :src="selectFlag.img" />
                                </div>
                                <div class="bubble">{{ i.message }}</div>
                            </div>

                            <div v-if="i.toName == selectFlag.uId" class="item item-right">
                                <div class="bubble">{{ i.message }}</div>
                                <div class="avatar"><img :src="userImg" />
                                </div>
                            </div>
                        </div>
                    </el-scrollbar>
                </div>
            </el-main>
            <el-footer height="20%">
                <div class="input-area">
                    <textarea v-model="text"></textarea>
                    <div class="button-area">
                        <button @click="sendText()">发送</button>
                    </div>
                </div>
            </el-footer>
        </el-container>
    </el-container>
</template>

<script setup>
import useUserStore from '@/stores/modules/user.js';
import { reactive, ref } from 'vue';

const userStore = useUserStore();
const userImg = userStore.image;
const auth = userStore.auth < 1 ? "在线用户列表" : "在线客服列表";
const ws = userStore.ws;
let peoleList = reactive([]);
ws.onmessage = (ev) => {
    const data = JSON.parse(ev.data);
    if (data.isSys) {
        peoleList.length = 0;
        data.message.forEach(i => {
            peoleList.push({
                uId: i.uId,
                img: i.img,
                lastMessage: "",
                isRead: 0,
                time: "",
            });
        })
    } else {
        peoleList.forEach(i => {
            if (i.uId == data.fromName) {
                i.lastMessage = data.message.substring(0, 20) + "...";
                if (selectFlag.uId != i.uId)
                    i.isRead++;
                i.time = new Date(data.time).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
            }
        })
        messagelist.push(data)
    }

}

//选中的聊天对象
let selectFlag = reactive({
    index: -1,
    uId: "",
    img: "",
});
const clickPeo = (index) => {
    selectFlag.index = index;
    selectFlag.uId = peoleList[index].uId;
    selectFlag.img = peoleList[index].img;
    peoleList[index].isRead = 0;
}
//消息列表，包括发给我的，以及我发的
let messagelist = reactive([]);
//一条消息
let text = ref("");
const sendText = () => {
    if (selectFlag.index != -1) {
        try {
            ws.send(JSON.stringify({
                toName: selectFlag.uId,
                message: text.value,

            }))
            messagelist.push({
                toName: selectFlag.uId,
                message: text.value,
            })
            text.value = "";
        } catch {
            alert("对方已经离线，无法继续发送消息");
        }

    } else {
        alert("请选择对象")
    }

}

</script>

<style scoped>
.leftSide {
    height: 100%;
    position: relative;
    flex: 30%;
    background: #fff;
    border-right: 1px solid rgba(0, 0, 0, 0.2);
}

.header {
    position: relative;
    width: 100%;
    height: 60px;
    background: #ededed;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 15px;
}

.message_p p {
    color: #aaa;
    display: -webkit-box;
    -webkit-line-break: 1;
    font-size: 0.9em;
    overflow: hidden;
    text-overflow: ellipsis;
}

.message_p b {
    background-color: red;
    color: #fff;
    min-width: 20px;
    height: 20px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.75em;
}

.cover {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.nav_icons {
    display: flex;
}

.nav_icons li {
    display: flex;
    list-style: none;
    cursor: pointer;
    color: #51585c;
    font-size: 1.5em;
    margin-left: 22px;
}

.message_p {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.chatlist {
    position: relative;
    height: calc(100%-110px);
    overflow-y: auto;
}

.chatlist .block {
    position: relative;
    width: 100%;
    display: flex;
    align-items: center;
    padding: 15px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.06);
}

.chatlist .block.active {
    background: #ebebeb;
}

.chatlist .block:hover {
    background: #f5f5f5;
}

.chatlist .block .imgbx {
    position: relative;
    min-width: 45px;
    height: 45px;
    overflow: hidden;
    border-radius: 50%;
    margin-right: 10px;
}

.chatlist .block .details {
    position: relative;
    width: 100%;
}

.chatlist .block .details .listHead {
    display: flex;
    justify-content: space-between;
    margin-bottom: 5px;
}

.chatlist .block .details .listHead h4 {
    font-size: 1.1em;
    font-weight: 600;
    color: #111;
}

.chatlist .block .details .listHead .time {
    font-size: 0.75em;
    color: #aaa;
}

.chatlist .block .details .listHead .time {
    color: #111;
}

.container {
    height: 760px;
    width: 900px;
    border-radius: 4px;
    border: 0.5px solid #e0e0e0;
    display: flex;
    flex-flow: column;
    overflow: hidden;
}

.content {
    height: calc(100% - 60px);
    padding: 20px 0 20px 20px;
    background-color: rgb(245, 245, 245);
    flex: 1;
}

.imgText {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
}

.imgText h4 {
    font-weight: 500;
    line-height: 1.2em;
    margin-left: 15px;
}

.imgText h4 span {
    font-size: 0.8em;
    color: green;
}

.userimg {
    position: relative;
    width: 40px;
    height: 40px;
    overflow: hidden;
    border-radius: 50%;
    cursor: pointer;
}

.bubble {
    max-width: 400px;
    padding: 10px;
    border-radius: 5px;
    position: relative;
    color: #000;
    word-wrap: break-word;
    word-break: normal;
}

.item-left .bubble {
    margin-left: 15px;
    background-color: #fff;
}

.item-left .bubble:before {
    content: "";
    position: absolute;
    width: 0;
    height: 0;
    border-left: 10px solid transparent;
    border-top: 10px solid transparent;
    border-right: 10.5px solid #fff;
    border-bottom: 10px solid transparent;
    left: -20px;
}

.item-right .bubble {
    margin-right: 15px;
    background-color: #9eea6a;
}

.item-right .bubble:before {
    content: "";
    position: absolute;
    width: 0;
    height: 0;
    border-left: 10.5px solid #9eea6a;
    border-top: 10px solid transparent;
    border-right: 10px solid transparent;
    border-bottom: 10px solid transparent;
    right: -20px;
}

.item {
    margin-top: 15px;
    display: flex;
    width: 100%;
}

.item.item-right {
    justify-content: flex-end;
    padding-right: 25px;
}

.item.item-center {
    justify-content: center;
}

.item.item-center span {
    font-size: 12px;
    padding: 2px 4px;
    color: #fff;
    background-color: #dadada;
    border-radius: 3px;
    -moz-user-select: none;
    /*火狐*/
    -webkit-user-select: none;
    /*webkit浏览器*/
    -ms-user-select: none;
    /*IE10*/
    -khtml-user-select: none;
    /*早期浏览器*/
    user-select: none;
}

.avatar img {
    width: 42px;
    height: 42px;
    border-radius: 50%;
}

.input-area {
    border-top: 0.5px solid #e0e0e0;
    height: 100%;
    display: flex;
    flex-flow: column;
    background-color: #fff;
}

textarea {
    flex: 1;
    padding: 5px;
    font-size: 14px;
    border: none;
    cursor: pointer;
    overflow-y: auto;
    overflow-x: hidden;
    outline: none;
    resize: none;
}

.button-area {
    display: flex;
    height: 40px;
    margin-right: 10px;
    line-height: 40px;
    padding: 5px;
    justify-content: flex-end;
}

.button-area button {
    width: 80px;
    border: none;
    outline: none;
    border-radius: 4px;
    float: right;
    cursor: pointer;
}

::v-deep .el-main,
.el-footer {
    padding: 0;
}
</style>