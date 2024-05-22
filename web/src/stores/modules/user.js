import { defineStore } from 'pinia';
import router from '@/router';
import { getUserInfo } from '@/api/user';

const useUserStore = defineStore('user', {
  state: () => {
    return {
      uId: sessionStorage.getItem('uId'),
      auth: '',
      state: '',
      image: '',
      ws: null,
    };
  },
  actions: {
    logout() {
      this.uId = '';
      this.auth = '';
      this.state = '';
      this.image = '';
      this.ws.close();
      this.ws = null;
      sessionStorage.clear();
      router.push('/login');
    },

    getInfo(success) {
      getUserInfo(sessionStorage.getItem('uId')).then(res => {
        if (res.error_info == 'success') {
          this.uId = res.uId;
          this.auth = res.auth;
          this.state = res.state;
          this.image = res.image;
          this.ws = new WebSocket(`ws://localhost:3000/websocket/${res.uId}`);
          this.ws.onopen = () => {};
          success();
        }
      });
    },
  },
  persist: true,
});
export default useUserStore;
