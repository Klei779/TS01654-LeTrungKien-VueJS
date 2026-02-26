<template>
    <!-- ================= BANNER SLIDE ================= -->
    <div class="container-fluid p-4">
        <div id="mainCarousel" class="carousel slide" data-bs-ride="carousel" v-if="images.length">
            <div class="carousel-indicators">
                <button v-for="(img, index) in images" :key="index" type="button" data-bs-target="#mainCarousel"
                    :data-bs-slide-to="index" :class="{ active: index === 0 }"></button>
            </div>

            <div class="carousel-inner">
                <div class="carousel-item" v-for="(img, index) in images" :key="img" :class="{ active: index === 0 }">
                    <img :src="img" class="d-block w-100 banner-img" />
                </div>
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#mainCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>

            <button class="carousel-control-next" type="button" data-bs-target="#mainCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </div>
    </div>

    <!-- ================= BÀI VIẾT MỚI ================= -->
    <div class="container mt-4">
        <h3 class="mb-3">Bài viết mới</h3>

        <div v-if="slides.length === 0" class="text-muted">
            Chưa có bài viết mới
        </div>

        <div v-else id="newPostCarousel" class="carousel slide" data-bs-ride="false">
            <div class="carousel-inner">
                <div class="carousel-item" v-for="(group, index) in slides" :key="index"
                    :class="{ active: index === 0 }">
                    <div class="row">
                        <div class="col-md-4" v-for="post in group" :key="post.id">
                            <router-link :to="`/post/${post.id}`" class="text-decoration-none text-dark">
                                <div class="card h-100">
                                    <img :src="post.image" class="card-img-top post-img" />

                                    <div class="card-body">
                                        <h6>{{ post.title }}</h6>
                                        <p class="small text-muted">
                                            {{ post.content }}
                                        </p>

                                        <p class="small text-muted mb-1">
                                            {{ likeCount(post.id) }} thích •
                                            {{ commentCount(post.id) }} bình luận
                                        </p>

                                        <button class="btn btn-sm"
                                            :class="isLiked(post.id) ? 'btn-danger' : 'btn-outline-danger'"
                                            @click.stop.prevent="toggleLike(post.id)">
                                            {{ isLiked(post.id) ? 'Đã thích' : 'Thích' }}
                                        </button>
                                    </div>
                                </div>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#newPostCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>

            <button class="carousel-control-next" type="button" data-bs-target="#newPostCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </div>
    </div>

    <!-- ================= THEO CHỦ ĐỀ ================= -->
    <div class="container mt-5">
        <h3 class="mb-3">Bài viết theo chủ đề</h3>

        <div class="accordion" id="categoryAccordion">
            <div class="accordion-item" v-for="cat in categoryWithPosts" :key="cat.id">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" data-bs-toggle="collapse"
                        :data-bs-target="'#cat-' + cat.id">
                        {{ cat.name }}
                    </button>
                </h2>

                <div :id="'cat-' + cat.id" class="accordion-collapse collapse">
                    <div class="accordion-body">

                        <div v-if="cat.posts.length === 0" class="text-muted">
                            Chưa có bài viết
                        </div>

                        <div class="card mb-3" v-for="post in cat.posts" :key="post.id">
                            <router-link :to="`/post/${post.id}`" class="text-decoration-none text-dark">
                                <div class="row g-0">
                                    <div class="col-md-3">
                                        <img :src="post.image" class="img-fluid rounded-start"
                                            style="height:120px; width: 300px;object-fit:cover" />
                                    </div>

                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <h6 class="card-title">
                                                {{ post.title }}
                                            </h6>

                                            <p class="small text-muted mb-1">
                                                {{ likeCount(post.id) }} thích •
                                                {{ commentCount(post.id) }} bình luận
                                            </p>

                                            <button class="btn btn-sm"
                                                :class="isLiked(post.id) ? 'btn-danger' : 'btn-outline-danger'"
                                                @click.stop.prevent="toggleLike(post.id)">
                                                {{ isLiked(post.id) ? 'Đã thích' : 'Thích' }}
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </router-link>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import { posts, favorites as favDb, comments as commentDb, categories } from '@/services/fakeDb'
import { getCurrentUser } from '@/services/auth'

const user = getCurrentUser()

/* ===== BANNER ===== */
const images = [
    '/images/qtsc-banner1.jpg',
    '/images/qtsc-banner2.jpg',
    '/images/qtsc-banner3.jpg'
]

/* ===== REACTIVE ===== */
const favorites = ref(favDb)
const comments = ref(commentDb)

/* ===== ACTIVE POSTS ===== */
const activePosts = computed(() =>
    posts.filter(p => p.active)
)

/* ===== SLIDES ===== */
const slides = computed(() => {
    const size = 3
    const result = []
    for (let i = 0; i < activePosts.value.length; i += size) {
        result.push(activePosts.value.slice(i, i + size))
    }
    return result
})

/* ===== CATEGORY ===== */
const categoryWithPosts = computed(() =>
    categories.map(cat => ({
        ...cat,
        posts: activePosts.value.filter(p => p.category === cat.slug)
    }))
)

/* ===== COMMENT COUNT ===== */
const commentCount = (postId) =>
    comments.value.filter(c => c.postId === postId).length

/* ===== LIKE ===== */
const likeCount = (postId) =>
    favorites.value.filter(f => f.postId === postId).length

const isLiked = (postId) =>
    user && favorites.value.some(
        f => f.postId === postId && f.user === user.name
    )

const toggleLike = (postId) => {
    if (!user) {
        alert('Cần đăng nhập để thích bài viết')
        return
    }

    const index = favorites.value.findIndex(
        f => f.postId === postId && f.user === user.name
    )

    if (index === -1) {
        favorites.value.push({
            postId,
            user: user.name
        })
    } else {
        favorites.value.splice(index, 1)
    }
}
</script>

<style scoped>
.banner-img {
    height: 380px;
    object-fit: cover;
}

.post-img {
    height: 180px;
    object-fit: cover;
}
</style>
