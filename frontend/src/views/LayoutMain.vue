<script setup>
import Hero from "../components/Hero.vue";
import ListProducts from "../components/ListProducts.vue";
import {reactive} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();

function goTo() {
  router.push('/salah');
}

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
    <div class="container">
    <!-- component hero -->
      <Hero/>
    <!-- component hero -->

      <div class="row mt-4">
        <div class="col">
          <h2>Best <strong>Foods</strong></h2>
        </div>
        <!--        <div class="col">-->
        <!--          <button @click="goTo" class="btn btn-success float-right">-->
        <!--            <i class="bi bi-eye"></i>-->
        <!--            Lihat Semua-->
        <!--          </button>-->
        <!--        </div>-->
      </div>

      <div class="row mb-4">
        <div class="col-md-4 mt-4" v-for="product in products.data" :key="product.id">
          <ListProducts :product="product"/>
        </div>
      </div>

    </div>
  </div>
</template>

<style scoped>

</style>