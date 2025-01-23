import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { router } from './router'
import { createPinia } from "pinia";
import { useAuthStore } from "./stores/authStore.js";

const app = createApp(App);
const pinia = createPinia();
app.use(pinia);
app.use(router);

const authStore = useAuthStore();
// authStore.InterceptorToken();
authStore.InterceptorRefreshToken();

app.mount("#app");