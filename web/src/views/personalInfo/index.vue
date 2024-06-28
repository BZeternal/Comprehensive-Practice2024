<template>
    <el-dialog v-model="img.flag" title="修改图片" width="500" style="margin-top: 30vh;" center>
        <el-input v-model="img.url" style="width: 470px;" size="large" placeholder="输入图片地址" />
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="() => {
                    img.url = ''
                    img.flag = false
                }">取消</el-button>
                <el-button type="primary" @click="updateImg" plain>
                    确认
                </el-button>
            </div>
        </template>
    </el-dialog>
    <div style="display: flex;flex-wrap: wrap;justify-content: center;">
        <el-card style="width: 60%;margin-bottom: 20px;">
            <div class="title">
                账号信息
            </div>
            <div class="area">
                <div style="width: 55%;">
                    <div class="item" style="height: 100%; align-items: center;">
                        <div class="left" style="line-height: 156px;">头像：</div>
                        <div style="height: 100%;">
                            <img :src="userStore.image"
                                style="height: 155px;width: 155px;margin-right: 5px;border-radius: 2%;cursor: pointer;"
                                @click="img.flag = true">
                        </div>
                    </div>
                </div>
                <div class="right-area">
                    <div class="item">
                        <div class="left">账号：</div>
                        <div class="right">{{ userStore.uId }}</div>
                    </div>

                    <div class="item">
                        <div class="left">状态：</div>
                        <div class="right" v-if="userStore.auth == 0">系统管理员</div>
                        <div class="right" v-if="userStore.auth == 1">驾校管理员</div>
                        <div v-if="userStore.auth == 2" class="right">{{ userStore.state }}</div>
                    </div>
                    <div class="item">
                        <div class="left">密码：</div>
                        <el-button type="primary" plain @click="passwordClass.flag = true">修改密码</el-button>
                        <el-dialog v-model="passwordClass.flag" title="修改密码" width="500" align-center>
                            <div style="padding-left: 30px;">
                                <div style="height: 20px;"></div>
                                <span style="font-size: 15px;">原密码：</span>
                                <el-input v-model="passwordClass.oldpass" style="width: 350px;" size="large"
                                    placeholder="请输入" />
                                <div style="height: 20px;"></div>
                                <span style="font-size: 15px;">新密码：</span>
                                <el-input v-model="passwordClass.newpass" style="width: 350px" size="large"
                                    placeholder="请输入" />
                                <div style="height: 20px;"></div>
                            </div>
                            <template #footer>
                                <el-button @click="passwordClass.flag = false">取消</el-button>
                                <el-button type="primary" @click="updatePassword">
                                    确认
                                </el-button>
                            </template>
                        </el-dialog>
                    </div>
                </div>
            </div>
        </el-card>
        <div v-if="userStore.state == '被驳回'"
            style="width: 100%;height: 50px;font-size: 20px;color: red;text-align: center;">当前信息已被驳回，请重新填写
        </div>
        <div v-else-if="userStore.state == '审核中'"
            style="width: 100%;height: 50px;font-size: 20px;color: rgb(255, 217, 0);text-align: center;">当前信息正在审核中</div>
        <div v-else style="width: 100%;height: 50px;text-align: center;">
            <el-button type="success" plain @click="exportPDF">导出个人信息</el-button>
        </div>
        <div id="pdfDom" style="width: 60%;display: flex;justify-content: center;flex-wrap: wrap;">
            <el-card style="width: 100%;margin-bottom: 20px;" v-if="userStore.auth == 2 && userStore.state != '非学员'">
                <div style=" height: 100%;width: 100%;display: flex;flex-wrap: wrap;">
                    <div class="title">个人信息</div>
                    <div class="row">
                        <div class="my-div">姓名：</div>
                        <div class="my-div-right">{{ applyList.aName }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">性别：</div>
                        <div class="my-div-right">{{ applyList.aSex }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">年龄：</div>
                        <div class="my-div-right">{{ applyList.aAge }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">电话：</div>
                        <div class="my-div-right">{{ applyList.aTel }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">身份证：</div>
                        <div class="my-div-right">{{ applyList.aIdCard }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">户籍：</div>
                        <div class="my-div-right">{{ applyList.aNation }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">邮寄地址：</div>
                        <div class="my-div-right">{{ applyList.aAdd }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">机动车类型：</div>
                        <div class="my-div-right">{{ applyList.aCarType }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">登记日期：</div>
                        <div class="my-div-right">{{ new Date(applyList.aTime).toLocaleDateString([], {
                            year: 'numeric',
                            month: '2-digit',
                            day: '2-digit'
                        }) }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">备注：</div>
                        <div class="my-div-right">{{ applyList.aText }}</div>
                    </div>
                </div>

            </el-card>
            <el-card style="width: 100%;" v-if="userStore.auth == 2 && userStore.state != '非学员'">
                <div class="title">体检表</div>
                <div style="width: 100%;flex-wrap: wrap;display: flex;">
                    <div class="row">
                        <div class="my-div">身高：</div>
                        <div class="my-div-right">{{ heaList.hHeight }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">辨色力：</div>
                        <div class="my-div-right">{{ heaList.hSelColor }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">视力：</div>
                        <div class="my-div-right">{{ heaList.hSight }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">视力是否矫正：</div>
                        <div class="my-div-right">{{ heaList.hIsCorVision }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">听力：</div>
                        <div class="my-div-right">{{ heaList.hHearing }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">躯干是否有运动障碍：</div>
                        <div class="my-div-right">{{ heaList.hActionBar }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">是否还有其他疾病：</div>
                        <div class="my-div-right">{{ heaList.hIsIll }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">体检医院：</div>
                        <div class="my-div-right">{{ heaList.hHospital }}</div>
                    </div>
                    <div class="row">
                        <div class="my-div">体检日期：</div>
                        <div class="my-div-right">{{ new Date(heaList.hTime).toLocaleDateString([], {
                            year: 'numeric',
                            month: '2-digit',
                            day: '2-digit'
                        }) }}</div>
                    </div>
                    <div class="row-right">
                        <div class="my-div">其他病例备注：</div>
                        <div class="my-div-right">{{ heaList.hOthIllText }}</div>
                    </div>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script setup>
import useUserStore from '@/stores/modules/user';
import { reactive, ref } from 'vue';
import * as userApi from "@/api/user.js"
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

let userStore = useUserStore();
const router = new useRouter();
let passwordClass = reactive({
    flag: false,
    oldpass: "",
    newpass: "",
})
const updatePassword = () => {
    if (passwordClass.oldpass != "" && passwordClass.newpass != "")
        userApi.updatePassword(userStore.uId, passwordClass.oldpass, passwordClass.newpass)
            .then(res => {
                if (res == 0) {
                    ElMessage.success("修改成功！");
                    passwordClass.flag = false;
                    setTimeout(() => {
                        userStore.logout();
                        router.push("/login");
                        ElMessage.info("登录已失效，请重新登录");
                    }, 1000)
                } else if (res == 1) {
                    ElMessage.error("原密码错误，请重新输入");
                } else {
                    ElMessage.error("出现异常，请重试");
                }
            })
            .catch(err => { console.error(err); })
    else
        ElMessage.warning("请确保输入完全！");
}

let applyList = ref({
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
});
let heaList = ref(
    {
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
    }
);
userApi.getApply(userStore.uId).then(res => {
    applyList.value = res[0];
})
userApi.getHea(userStore.uId).then(res => {
    heaList.value = res[0];
})

const exportPDF = () => {
    const ele = document.getElementById('pdfDom');
    html2canvas(ele, {
        dpi: 96, // 分辨率
        scale: 10, // 设置缩放
        useCORS: true, // 允许canvas画布内 可以跨域请求外部链接图片, 允许跨域请求。,
        // backgroundColor:'#ffffff',这样背景还是黑的
        bgcolor: '#ffffff', // 应该这样写
        logging: false, // 打印日志用的 可以不加默认为false
    }).then((canvas) => {
        const contentWidth = canvas.width;
        const contentHeight = canvas.height;
        // 一页pdf显示html页面生成的canvas高度;
        const pageHeight = (contentWidth / 592.28) * 841.89;
        // 未生成pdf的html页面高度
        let leftHeight = contentHeight;
        // 页面偏移
        let position = 0;
        // a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
        const imgWidth = 595.28;
        const imgHeight = (595.28 / contentWidth) * contentHeight;
        const pageData = canvas.toDataURL('image/jpeg', 2.0);
        const pdf = new jsPDF('', 'pt', 'a4');
        if (leftHeight < pageHeight) {
            // 在pdf.addImage(pageData, 'JPEG', 左，上，宽度，高度)设置在pdf中显示；
            pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
        } else {
            // 分页
            while (leftHeight > 0) {
                pdf.addImage(
                    pageData,
                    'JPEG',
                    0,
                    position,
                    imgWidth,
                    imgHeight
                );
                leftHeight -= pageHeight;
                position -= 841.89;
                // 避免添加空白页
                if (leftHeight > 0) {
                    pdf.addPage();
                }
            }
        }
        // 可动态生成
        pdf.save("#" + userStore.uId + "的个人信息.pdf");
    });
}

let img = ref({
    flag: false,
    url: ''
})
const updateImg = () => {
    if (img.value.url != '')
        userApi.upImg(userStore.uId, img.value.url).then(res => {
            location.reload();
            ElMessage.success("修改成功！");
            img.value.url = '';
        })
    else
        ElMessage.warning("请输入要更改的图片地址！");
}
</script>

<style scoped>
.title {
    height: 50px;
    font-size: 25px;
    color: black;
}

.area {
    width: 100%;
    display: flex;
    flex-wrap: wrap;

    .right-area {
        width: 45%;
    }

}

.item {
    width: 100%;
    height: 50px;
    display: flex;
    align-items: center;

    .left {
        height: 100%;
        width: 35%;
        font-size: 20px;
        line-height: 50px;
        text-align: right;
    }

    .right {
        height: 100%;
        width: 65%;
        font-size: 20px;
        line-height: 50px;
        padding-left: 10px;
    }
}

::v-deep .avatar-uploader {
    width: 178px;
    height: 178px;
    display: block;
}

::v-deep .el-icon {
    font-size: 28px;
    color: #8c939d;
    width: 155px;
    height: 155px;
    text-align: center;
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
    width: 52%;
    display: flex;
    margin-bottom: 15px;
}

.my-div {
    width: 180px;
    height: 36px;
    text-align: end;
    line-height: 36px;
    font-size: 15px;
    margin-right: 8px;
}

.my-div-right {
    height: 36px;
    line-height: 35px;
    font-size: 14px;
    font-weight: 600;
    max-width: 200px;
}

.row-right {
    height: 36px;
    width: 48%;
    display: flex;
}
</style>
<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}
</style>