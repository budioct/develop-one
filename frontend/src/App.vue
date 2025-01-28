<script setup>
import Navbar from "./components/Navbar.vue";
import Footer from "./components/Footer.vue";
import {useCartStore} from "./stores/authStore";

const cartStore = useCartStore();
</script>

<template>
  <div class="container">
    <Navbar :list-carts="cartStore.carts" />
    <transition name="fade" mode="out-in">
      <RouterView/>
    </transition>
    <Footer/>
  </div>
</template>

<style scoped>
</style>
