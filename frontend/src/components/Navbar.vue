<script setup>

</script>

<template>

  <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <router-link :to="{name: 'home'}" class="navbar-brand">Home</router-link>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09" aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample09">
      <ul class="navbar-nav mr-auto">
<!--        <li class="nav-item active">-->
<!--          <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>-->
<!--        </li>-->
        <li class="nav-item">
          <router-link :to="{name: 'auth-login'}" class="nav-link">Login</router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{name: 'auth-register'}" class="nav-link">Register</router-link>
        </li>
      </ul>
    </div>
  </nav>

</template>

<style scoped>
.router-link-exact-active {
  color: #817272 !important;
  font-weight: bold;
}
</style>