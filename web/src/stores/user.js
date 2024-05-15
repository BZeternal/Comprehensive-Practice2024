import { defineStore } from 'pinia';
import router from '@/route';
import { getUserInfo } from '@/api/user';

const useUserStore = defineStore('user', {
    state: () => {
        return {
            uId: localStorage.getItem("uId"),
            auth: '',
            state: '',
            image: '',
        };
    },
    actions: {
        logout() {
            this.uId = '';
            this.auth = '';
            this.state = '';
            this.image = '';
            localStorage.removeItem('uId');
            router.push('/login');
        },

        async getUserInfo() {
            const res = await getUserInfo();
            if (res.error_info == 'success') {
                this.auth = res.auth;
                this.state = res.state;
                this.image = res.image;
                return 'success';
            } else {
                return Promise.reject(res.error_info);
            }
        },
    },
    persist: true,
});

export default useUserStore;