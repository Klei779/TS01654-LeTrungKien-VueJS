<template>
  <div>
    <h2>Blog Post ID: {{ id }}</h2>
    <p>This is content of blog post {{ id }}</p>
  </div>
</template>

<script>
export default {
  props: ['id']
}
</script>
