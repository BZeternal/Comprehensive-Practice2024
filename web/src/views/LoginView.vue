<template>
    <div class="content">
        <div ref="box" class="box">
            <div ref="left" class="left"></div>
            <div class="right">
                <h4 ref="h4" v-if="flag" class="tips">登 录</h4>
                <h4 v-else class="tips">注册</h4>
                <img ref="img" src="../../public/cqjtu.ico" style="width: 30px; height: 30px; transition: 1s;">
                <div ref="input" v-if="flag">
                    <input v-model="user.uId" class="acc" type="text" placeholder="账号">
                    <input v-model="user.password" class="acc" type="password" placeholder="密码">
                    <button @click="login"  class="submit">Login</button>
                    <h5 @click="go_register">注册</h5>
                </div>
                <div v-else>
                    <input v-model="user.test" class="acc" type="text" placeholder="用户名">
                    <input v-model="user.password" class="acc" type="password" placeholder="密码">
                    <input v-model="user.rePassword" class="acc" type="password" placeholder="确认密码">
                    <button @click="register" class="submit">确定</button>
                    <h5 @click="go_login">已有账号，前往登录</h5>
                </div>
            </div>
        </div>
        <ul class="bg-squares">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</template>


<script setup>
import { reactive, ref } from 'vue';
import * as userApi from '../api/user'
import { ElMessage,ElMessageBox} from 'element-plus'
import router from '@/router';

let flag = ref(true)
let user = reactive({
    uId:'',
    password:'',
    rePassword:'',
    test:''
})

const clearUser = () =>{
    Object.keys(user).forEach(key=>{
        user[key] = ''
    })
}

let box = ref()
const go_register = () => {
    box.value.style.margin = '7% auto'
    box.value.style.height = '31.2rem'
    clearUser()
    flag.value = false;
}

const go_login = () => {
    box.value.style.margin = '10% auto'
    box.value.style.height = '26rem'
    clearUser()
    flag.value = true;
}

let left = ref()
let h4 = ref()
let input = ref()
let img = ref()
const login = () => {
    userApi.login(user)
    .then(resp=>{
        if(resp.error_info == 'success')
        {
            input.value.style.display='none'
            left.value.style.width = '40%'
            left.value.style.height = '100%'
            h4.value.innerText = 'Welcome!';
            h4.value.classList.add('loginDp')
            h4.value.style.fontSize = '2.5rem'
            img.value.style.height = '60px'
            img.value.style.width = '60px'
            localStorage.setItem("uId",resp.uId);
            if(resp.auth == 1 || resp.auth == 0){
                router.push({name:"home"})
            }
            else{
                router.push({name:"admineHome"})
            }
        }
        else{
            ElMessage({
                message: resp.error_info,
                type: 'error',
            })
        }
    })
    
}

const register = () => {
    userApi.register(user)
    .then(resp=>{
        if(resp.error_info == "success")
        {
            ElMessageBox.confirm(
                '您的账号为' + resp.uId,
                '用户创建成功！',
                {
                    confirmButtonText: '前往登录',
                    cancelButtonText: '关闭',
                    type: 'success',
                }
            )
            .then(() => {
                go_login()
            })
            .catch(() => {
                clearUser()
            })
        }
        else{
            ElMessage({
                message: resp.error_info,
                type: 'error',
            })
        }
    })
}

</script>



<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.loginDp{
    transform: translateY(8rem);
}

.content {

    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    font-size: 10px;
    overflow: hidden;
}

.content::before {
    content: '';
    width: 100%;
    height: 100%;
    position: fixed;
    z-index: -1;
    background: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%) no-repeat;
}

::selection {
    color: #fff;
    background-color: rgb(144, 129, 241);

}


.box {
    display: flex;
    overflow: hidden;
    width: 40rem;
    height: 26rem;
    background-color: rgba(255, 255, 255, 60%);
    border-radius: 1.5rem;
    margin: 10% auto;
    box-shadow: 0 0 1rem 0.2rem rgb(0 0 0 / 10%);
    z-index: 2;
    transition: 1s;
}


.box .left {
    position: relative;
    width: 35%;
    height: 100%;
    background-color: skyblue;
    transition: 1s;
}

.box .left::before {
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;
    background-image: url(../assets/image/login.jpg);
    background-size: cover;
    opacity: 80%;
}

.box .right {
    z-index: 2;
    display: flex;
    width: 65%;
    flex-direction: column;
    align-items: center;
}

.box .right h4 {
    color: rgb(144, 129, 241);
    font-size: 2.2rem;
    margin-top: 3rem;
    cursor: pointer;
    transition: 1.5s;
}

.box .right div {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

.box .right div .acc {
    outline: none;
    width: 80%;
    height: 5rem;
    font-size: 1.1rem;
    margin-top: 0.2rem;
    padding: 1rem 0 0 1.6rem;
    border: none;
    border-bottom: 1px solid rgb(144, 129, 241);
    color: rgb(144, 129, 241);
    background-color: rgba(0, 0, 0, 0);
    transition: 0.3s;
}

.box div h5 {
    margin-top: 0.2rem;
    font-size: 1.1rem;
    cursor: pointer;
    color: rgb(184, 176, 235);
}

.box div h5:hover {
    color: rgb(144, 129, 241);
}

.right div .acc:focus {
    outline: none;
    color: rgb(144, 129, 241);
    padding: 1rem 0 0 1.6rem;
}

.right div .acc:hover {
    transform: scale(1.02);
}

.right .submit {
    width: 60%;
    height: 3rem;
    color: #f6f6f6;
    background-image: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
    font-size: 1.25rem;
    border: none;
    border-radius: 0.5rem;
    margin: 1.5rem 0 0 50%;
    cursor: pointer;
    transform: translateX(-50%);
    outline: none;
    transition: 0.25s;
}

.tips {
    font-weight: 550;
    background: rgb(144, 129, 241) linear-gradient(-120deg,
            transparent 25%,
            transparent 40%,
            rgba(255, 255, 255) 40%,
            rgba(255, 255, 255) 60%,
            transparent 60%,
            transparent);
    background-size: 28px 55px;
    background-repeat: no-repeat;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    animation: scratchy 6s linear infinite;
}

@keyframes scratchy {
    0% {
        background-position: -100% 0;
    }

    100% {
        background-position: 130% 0;
    }
}

.right .submit:hover {
    box-shadow: 0 0 2rem -0.5rem rgb(0 0 0 / 15%);
    color: #666;
}


.bg-squares {
    list-style: none;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
}

.bg-squares li {
    width: 40px;
    height: 40px;
    background-color: rgba(255, 255, 255, 0.15);
    position: absolute;
    bottom: -160px;
    animation: square 2s linear infinite;
    border-radius: 20%
}

.bg-squares li:nth-child(1) {
    left: 10%;
}

.bg-squares li:nth-child(2) {
    left: 20%;
    width: 140px;
    height: 140px;
    animation-delay: 2s;
    animation-duration: 17s;
    background-color: rgba(255, 255, 255, 0.35);
    border-radius: 50%
}

.bg-squares li:nth-child(3) {
    left: 25%;
    animation-delay: 4s;
}

.bg-squares li:nth-child(4) {
    left: 40%;
    width: 60px;
    height: 60px;
    background-color: rgba(255, 255, 255, 0.25);
    animation-duration: 22s;
}

.bg-squares li:nth-child(5) {
    left: 70%;
    background-color: rgba(255, 255, 255, 0.45);
    animation-delay: 2s;
    animation-duration: 1s;
}

.bg-squares li:nth-child(6) {
    left: 80%;
    width: 120px;
    height: 120px;
    background-color: rgba(255, 255, 255, 0.25);
    animation-delay: 3s;
    border-radius: 50%
}

.bg-squares li:nth-child(7) {
    left: 32%;
    width: 160px;
    height: 160px;
    animation-delay: 7s;
}

.bg-squares li:nth-child(8) {
    left: 55%;
    width: 20px;
    height: 20px;
    animation-delay: 15s;
    animation-duration: 40s;
}

.bg-squares li:nth-child(9) {
    left: 25%;
    width: 10px;
    height: 10px;
    background-color: rgba(255, 255, 255, 0.6);
    animation-delay: 2s;
    animation-duration: 40s;
}

.bg-squares li:nth-child(10) {
    left: 90%;
    width: 100px;
    height: 100px;
    animation-delay: 11s;
}

.bg-squares li:hover {
    animation-play-state: paused;
}

@keyframes square {
    0% {
        transform: translateY(-120vh);
    }

    100% {
        transform: translateY(0vh) rotate(600deg);
    }

}
</style>