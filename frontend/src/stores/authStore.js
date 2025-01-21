import {defineStore} from 'pinia';
import axios from 'axios';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: localStorage.getItem("authToken") || null, // collect token from LocalStorage
        isAuthenticated: !!localStorage.getItem("authToken"), // check does token there is
    }),
    actions: {
        setToken(token) {
            this.token = token;
            this.isAuthenticated = !!token; // true if token there is

            // save token to LocalStorage
            if (token) {
                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`; // Set header Authorization
                localStorage.setItem("authToken", token);
            } else {
                localStorage.removeItem("authToken");
            }
        },
        clearToken() {
            this.token = null;
            this.isAuthenticated = false;
            delete axios.defaults.headers.common['Authorization'];

            // delete header Authorization  and token from LocalStorage
            delete axios.defaults.headers.common["Authorization"];
            localStorage.removeItem("authToken");
        },
        InterceptorToken() {
            // add interceptor for include token to each request
            axios.interceptors.request.use(
                (config) => {
                    if (this.token) {
                        config.headers["Authorization"] = `Bearer ${this.token}`;
                    }
                    return config;
                },
                (error) => {
                    return Promise.reject(error);
                }
            );
        }
    },
});
