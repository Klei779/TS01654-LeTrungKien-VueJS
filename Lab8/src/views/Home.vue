<template>
  <div>
    <h1>Welcome to MyBlog</h1>
    <button @click="goBlog">Go to Blog</button>
  </div>
</template>

<script>
export default {
  methods: {
    goBlog() {
      this.$router.push({ name: 'BlogList' })
    }
  }
}
</script>
