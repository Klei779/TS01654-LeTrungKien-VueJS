<template>
  <div>
    <h2>Login</h2>
    <button @click="handleLogin">Login</button>
  </div>
</template>

<script>
import { login } from '../router'

export default {
  methods: {
    handleLogin() {
      login()
      this.$router.push({ name: 'Dashboard' })
    }
  }
}
</script>
