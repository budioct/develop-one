<script setup>
import {ref, onMounted, computed} from "vue";
import {useAuthStore, useCartStore} from "../stores/authStore";
import {useToast} from 'vue-toast-notification';
import axios from "axios";

const authStore = useAuthStore();
const cartStore = useCartStore();
const $toast = useToast();
const carts = ref([]);

function setCarts(data) {
  carts.value = data;
  cartStore.setCarts(carts.value);
}

onMounted(async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/v1/keranjangs/fetch",
        {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${authStore.token}`,
          },
        }
    );
    if (response.data.keranjangs === null) {
      $toast.error("Belum ada <b>Pesanan!!!</b>", {
        type: "error",
        position: "top-right",
        duration: 6000,
        dismissible: true
      });
    }
    setCarts(response.data.keranjangs);
  } catch (error) {
    if (error.response.data.status_code === 404) {
      $toast.error("Belum ada <b>Pesanan!!!</b>", {
        type: "error",
        position: "top-right",
        duration: 6000,
        dismissible: true
      });
    }
    // console.error(error);
  }
});

function cartRemove(productId, cartId) {
  axios.delete(`http://localhost:8080/api/v1/product/${productId}/keranjangs/${cartId}/remove`,
      {
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${authStore.token}`,
        },
      }).then(() => {
    $toast.success("Pesanan Berhasil <b>Di Hapus!!!</b>", {
      type: "error",
      position: "top-right",
      duration: 5000,
      dismissible: true
    });

    axios.get("http://localhost:8080/api/v1/keranjangs/fetch",
        {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${authStore.token}`,
          },
        }).then((response) => {
      if (response.data && response.data.keranjangs) {
        setCarts(response.data.keranjangs);
      } else {
        console.error("rest api do not collect data with structure expected.");
      }
    });
  }).catch((error) => console.error(error));
}

const totalHarga = computed(() => {
  return carts.value.reduce((items, data) => {
    return items + data.products.harga * data.jumlah_pemesanan;
  }, 0);
});

</script>

<template>
  <div class="keranjang">
    <div class="row mt-4">
      <div class="col">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              Home
            </li>
            <li class="breadcrumb-item">
              Product
            </li>
            <li class="breadcrumb-item active" aria-current="page">
              Cart
            </li>
          </ol>
        </nav>
      </div>
    </div>

    <div class="row">
      <div class="col">
        <h2>
          Keranjang
          <strong>Saya</strong>
        </h2>
        <div class="table-responsive mt-3">
          <table class="table">
            <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Foto</th>
              <th scope="col">Makanan</th>
              <th scope="col">Keterangan</th>
              <th scope="col">Jumlah</th>
              <th scope="col">Harga</th>
              <th scope="col">Total Harga</th>
              <th scope="col">Hapus</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(cart, index) in carts" :key="cart.id">
              <td>{{ index + 1 }}</td>
              <td>
                <img v-bind:src="`../assets/images/${cart.products.gambar}`" class="img-fluid shadow" width="120"/>
              </td>
              <td><strong>{{ cart.products.nama }}</strong></td>
              <td>{{ cart.keterangan ? cart.keterangan : "-" }}</td>
              <td>{{ cart.jumlah_pemesanan }}</td>
              <td>Rp. {{ cart.products.harga }}</td>
              <td><strong>Rp. {{ cart.products.harga * cart.jumlah_pemesanan }}</strong></td>
              <td class="text-danger" style="cursor: pointer">
                <i @click="cartRemove(cart.products.id, cart.id)" class="bi bi-trash"></i>
              </td>
            </tr>
            <tr>
              <td>Daftar Total Harga :</td>
              <td style="color: #6CB52D; font-weight: bold; text-decoration: underline; text-underline-offset: 0.4rem;">
                <template v-if="totalHarga !== 0">
                  Rp. {{ totalHarga }}
                </template>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="row justify-content-end">
      <div class="col-md-4">
        <form @submit.prevent="" class="mt-4">
          <!-- Nama Pesan Field -->
          <div>
            <label for="nama" class="sr-only">Nama Pemesan</label>
            <!--                v-model=""-->
            <input
                type="text"
                id="nama"
                placeholder="Nama Pemesan"
                class="form-control"
            >
          </div>
          <!-- Nama Pesan Field -->
          <!-- No Meja Field -->
          <div>
            <label for="no_meja" class="sr-only mt-3">Keterangan</label>
            <!--                v-model=""-->
            <textarea
                type="text"
                id="no_meja"
                placeholder="No Meja Pemesan"
                class="form-control"
            />
          </div>
          <!-- No Meja Field -->

          <button class="btn btn-success mt-3" type="submit">
            <i class="bi bi-cart2"></i>
            Pesan
          </button>
        </form>
      </div>
    </div>

  </div>
</template>

<style scoped>

</style>