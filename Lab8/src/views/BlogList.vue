<template>
  <div>
    <h2>Blog List</h2>
    <ul>
      <li v-for="post in posts" :key="post.id">
        <router-link :to="{ name: 'BlogPost', params: { id: post.id } }">
          {{ post.title }}
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      posts: [
        { id: 1, title: 'Vue Basics' },
        { id: 2, title: 'Vue Router' },
        { id: 3, title: 'Composition API' }
      ]
    }
  }
}
</script>
