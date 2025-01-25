<script setup>
import Navbar from "./components/Navbar.vue";
import Footer from "./components/Footer.vue";
</script>

<template>
  <div class="container">
    <Navbar/>
    <RouterView/>
    <Footer/>
  </div>
</template>

<style scoped>
</style>
