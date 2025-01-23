<script setup>
import {computed} from "vue";
import {useRouter} from "vue-router";
import {useAuthStore} from "../stores/authStore.js";
import axios from "axios";

const router = useRouter();
const authStore = useAuthStore();
const isAuthenticated = computed(() => authStore.isAuthenticated);

async function logout() {
  try {
    const response = await axios.post(
        "http://localhost:8080/api/v1/auth/logout",
        {}, // request body
        {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${authStore.token}`, // insert token from store (pinia global state)
          },
        }
    );
    // console.log(response); // debugging response logout
  } catch (error) {
    console.log("Headers:", error.response?.headers);
    console.error("Logout failed:", error.response?.data?.message || error.message);
  } finally {
    authStore.clearToken(); // delete token from store (pinia global state) and localstorage
    await router.push({name: "auth-login"}); // redirect to login
  }
}

</script>

<template>

  <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <div v-if="isAuthenticated">
      <router-link :to="{name: 'home'}" class="navbar-brand">Home</router-link>
    </div>
    <div class="collapse navbar-collapse" id="navbarsExample09">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link :to="{name: 'auth-login'}" class="nav-link">Login</router-link>
        </li>
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link :to="{name: 'auth-register'}" class="nav-link">Register</router-link>
        </li>
      </ul>
      <div v-if="isAuthenticated">
        <div class="form-inline my-2 my-md-0">
          <button @click="logout" type="button" class="btn btn-outline-danger">Logout</button>
        </div>
      </div>
    </div>
  </nav>

</template>

<style scoped>

</style>