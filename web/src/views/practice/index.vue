<template>
    <div v-loading="loading" v-if="display_flag" style="display: flex; height: 100%;">
        <el-card style="flex: 0.8; ">
            <el-scrollbar height="80vh">
                <el-tag v-for="flag in flags" :key="flag.id" :type="flag.type" effect="light" round @click="selectPro(flag)"
                    style="cursor: pointer; margin: 0.2rem 0.25rem; width: 3rem; height: 3rem;">
                    {{ flag.id }}
                </el-tag>
            </el-scrollbar>
        </el-card>
        <el-card style="flex: 2.2; height: 100%;">
            <div style="display: flex; flex-direction: column; margin: 2% 0 0 8%; height: 33.5rem;">
                <h2>{{ problem.id }}. {{ problem.question }} <el-icon v-if="problem.right == 'success'" color="green">
                        <SuccessFilled />
                    </el-icon> <el-icon v-if="problem.right === 'danger'" color="red">
                        <CircleCloseFilled />
                    </el-icon></h2>
                <el-button @click="open" style="font-size: 1.05rem;margin: 3px 1%; width: 120px; height: 35px;"
                    key="primary" type="primary" text bg v-if="problem.right === 'danger'">查看题解</el-button>
                <el-checkbox-group :disabled="problem.disable" style="display: flex; flex-direction: column;"
                    v-model="problem.answers" size="large">
                    <el-checkbox :label="problem.item1" value="1" />
                    <el-checkbox :label="problem.item2" value="2" />
                    <el-checkbox v-if="problem.item3 != ''" :label="problem.item3" value="3" />
                    <el-checkbox v-if="problem.item4 != ''" :label="problem.item4" value="4" />
                </el-checkbox-group>
                <el-image v-if="problem.url != ''" style="margin-top:2% ;margin-left: 20%; width: 200px; height: 200px"
                    :src="problem.url" fit="fill" />
            </div>
            <el-button v-if="rework" style="font-size: 1.05rem;margin-left: 1%; width: 120px; height: 35px;" key="primary"
                type="primary" text bg @click="redo">再做一次</el-button>
            <el-button style="font-size: 1.05rem;margin-left: 70%; width: 120px; height: 35px;" key="primary" type="primary"
                text bg @click="ok">确定</el-button>
        </el-card>
    </div>
    <div v-else style="display: flex; flex-direction: column; align-items: center;">
        <el-select v-model="subject" placeholder="选择科目" size="large" style="width: 350px; margin-top: 5%;">
            <el-option key="1" label="科目1" value="1"></el-option>
            <el-option key="4" label="科目4" value="4"></el-option>
        </el-select>
        <el-button @click="start_practice" style="width: 12rem; height: 12rem; margin-top: 2rem; font-size: 1.5rem;"
            type="primary" circle><el-icon size="2.6rem">
                <Edit />
            </el-icon>开始刷题</el-button>
    </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import * as practiceApi from '../../api/practice'
import { Edit } from '@element-plus/icons-vue'

let display_flag = ref(false)

let loading = ref(false) //等待标志
let score = 0;
let rework = ref(false)
let flags = reactive([])
let problem = reactive({
    id: "",
    question: "",
    answer: "",
    item1: "",
    item2: "",
    item3: "",
    item4: "",
    explains: "",
    url: "",
    answers: [],
    disable: false,
    right: ''
})
let problems = reactive([])

let subject = ref('') //测试科目
const refresh_practice = () => {
    loading.value = true
    practiceApi.getQuestion(subject.value, 'c1').then(resp => {
        if (resp.data.reason == 'ok') {
            // console.log(resp.data.result)
            problems.length = 0;
            flags.length = 0;
            for (let i = 0; i < resp.data.result.length; i++) {
                problems.push({ ...resp.data.result[i] })
                problems[problems.length - 1].id = i + 1;
                if (problems[problems.length - 1].item1 == '') problems[problems.length - 1].item1 = '对'
                if (problems[problems.length - 1].item2 == '') problems[problems.length - 1].item2 = '错'
                flags.push({
                    id: i + 1,
                    type: 'info',
                    answers: [],
                    disable: false
                })
            }
            let t = resp.data.result[0];
            Object.keys(t).forEach(key => {
                problem[key] = t[key]
            })
            problem.id = 1;
            loading.value = false
        } else {
            ElMessage({
                type: 'info',
                message: '今日做题次数已达上限',
            })
            loading.value = false
        }
    }).catch(() => {
        ElMessage({
            type: 'error',
            message: '请检查网络',
        })
        loading.value = false
    })
}

const selectPro = (flag) => {
    let t = problems[flag.id - 1]
    if (problem.id == flag.id) return;
    Object.keys(t).forEach(key => {
        problem[key] = t[key]
    })
    problem.answers.length = 0
    for (let i = 0; i < flag.answers.length; i++) {
        problem.answers[i] = flag.answers[i]
    }
    problem.right = flag.type
    problem.disable = flag.disable
}

const ok = () => {
    let flag = flags[Number(problem.id) - 1]
    let a1 = problem.answers
    let a2 = problem.answer
    problem.disable = true
    flag.disable = true
    if (a1.length != a2.length) {
        flag.type = 'danger'
        problem.right = 'danger'
    } else {
        let f = true;
        for (let i = 0; i < problem.answers.length; i++) {
            if (a1[i] != a2[i]) f = false;
        }
        if (f) {
            flag.type = 'success'
            problem.right = 'success'
            score++;
        } else {
            flag.type = 'danger'
            problem.right = 'danger'
        }
    }
    for (let i = 0; i < a1.length; i++) {
        flag.answers[i] = a1[i]
    }
    // console.log(problem)

    if (problem.id == 100) {
        rework.value = true;
        ElMessageBox.confirm(
            '恭喜你已完成所有测试，本次测试: ' + score + '分',
            '成绩',
            {
                confirmButtonText: '继续练习',
                cancelButtonText: '改日再做',
                type: 'success',
            }
        )
            .then(() => {
                location.reload()
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '再接再厉',
                })
            })
    }

}

const open = () => {
    ElMessageBox.alert(problem.explains + '   正确答案：' + problem.answer, '题目解析', {
        confirmButtonText: '知道了',
        callback: () => {
            ElMessage({
                type: 'info',
                message: `继续努力吧`,
            })
        },
    })
}

const redo = () => {
    location.reload()
}


const start_practice = () => {
    if (subject.value == '') {
        ElMessage({
            type: 'error',
            message: '请先选择练习科目',
        })
        return;
    }
    display_flag.value = true;
    refresh_practice()
}
</script>

<style scoped></style>