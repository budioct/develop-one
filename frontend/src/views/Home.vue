<script setup>
import { useAuthStore } from "../stores/authStore";

const authStore = useAuthStore();

// State for showed login info
const isLoggedIn = authStore.isAuthenticated;
const token = authStore.token;

</script>

<template>
  <div class="container">
    <h1>Login Status</h1>
    <p v-if="isLoggedIn">You are logged in! 🎉</p>
    <p v-else>You are not logged in.</p>

    <div v-if="token">
      <h3>Your Token:</h3>
      <p>{{ token }}</p>
    </div>
  </div>
</template>

<style scoped>

</style>