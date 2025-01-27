<script setup>
import {useRouter} from "vue-router";

const router = useRouter();

function goTo() {
  router.push({ name: 'products' });
}
</script>

<template>
  <div class="hero">
    <!--  Desktop  -->
    <div class="d-none d-md-block">
      <div class="row mt-4">
        <div class="col-md-6">
          <div class="d-flex h-100">
            <div class="justify-content-center align-self-center">
              <h2><strong>Rasakan Nikmatnya Hidangan Favorit,</strong> <br> Langsung dari Genggaman Anda!</h2>
              <p>Jangan lewatkan kesempatan untuk menikmati makanan lezat pilihan Anda. Dengan sekali sentuh, semua kelezatan tersedia untuk Anda! Pesan sekarang dan rasakan kemudahannya.</p>
              <button @click="goTo" class="btn btn-lg btn-success">
                <i class="bi bi-arrow-right-circle"></i>
                Pesan
              </button>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <img :src="'../assets/images/hero.jpg'" alt="hero.jpg" width="100%">
        </div>
      </div>
    </div>

    <!--  Mobile  -->
    <div class="d-sm-block d-md-none">
      <div class="row mt-4">
        <div class="col-md-6 mb-3">
          <img src="../../public/assets/images/hero.jpg" width="100%">
        </div>
        <div class="col-md-6">
          <div class="d-flex h-100">
            <div class="justify-content-center align-self-center">
              <h2><strong>Rasakan Nikmatnya Hidangan Favorit,</strong> <br> Langsung dari Genggaman Anda!</h2>
              <p>Jangan lewatkan kesempatan untuk menikmati makanan lezat pilihan Anda. Dengan sekali sentuh, semua kelezatan tersedia untuk Anda! Pesan sekarang dan rasakan kemudahannya.</p>
              <button @click="goTo" class="btn btn-lg btn-success">
                <i class="bi bi-arrow-right-circle"></i>
                Pesan
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<style scoped>

</style>