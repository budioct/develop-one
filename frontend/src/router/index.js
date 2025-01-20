import {createRouter, createWebHistory} from "vue-router";

export const router = createRouter({
    routes: [
        {
            path: '/',
            component: () => import('../views/Home.vue'),
            name: 'home'
        },
        {
            path: '/login',
            component: () => import('../views/Login.vue'),
            name: 'auth-login'
        },
        {
            path: '/register',
            component: () => import('../views/Register.vue'),
            name: 'auth-register'
        }
    ],
    history: createWebHistory()
})