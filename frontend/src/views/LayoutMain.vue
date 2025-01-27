<script setup>
import Hero from "../components/Hero.vue";
import ListProductsCustom from "../components/ListProductsCustom.vue";
import {reactive} from "vue";

const products = reactive({
  data: [
    {
      id: "1",
      kode: "K-01",
      nama: "Sate Ayam",
      harga: 16000,
      is_ready: true,
      gambar: "sate-ayam.jpg"
    },
    {
      id: "2",
      kode: "K-02",
      nama: "Nasi Goreng Telur",
      harga: 14000,
      is_ready: true,
      gambar: "nasi-goreng-telor.jpg"
    },
    {
      id: "3",
      kode: "K-03",
      nama: "Nasi Rames",
      harga: 12000,
      is_ready: true,
      gambar: "nasi-rames.jpg"
    }
  ]
})

</script>

<template>
  <div class="home">
    <!-- component hero -->
    <Hero/>
    <!-- component hero -->
    <div class="row mt-4">
      <div class="col">
        <h2>Best <strong>Foods</strong></h2>
      </div>
    </div>

    <div class="row mb-4">
      <div class="col-md-4 mt-4" v-for="product in products.data" :key="product.id">
        <!-- component list product -->
        <ListProductsCustom :product="product"/>
        <!-- component list product -->
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>