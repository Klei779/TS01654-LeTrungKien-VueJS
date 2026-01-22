<template>
    <div class="container mt-4">
        <h3 class="mb-3">Bài viết mới</h3>

        <div id="newPostCarousel" class="carousel slide" data-bs-ride="false">
            <div class="carousel-inner">

                <div class="carousel-item" :class="{ active: index === 0 }" v-for="(group, index) in slides"
                    :key="index">
                    <div class="row">
                        <div class="col-md-4" v-for="post in group" :key="post.id">
                            <router-link :to="`/post/${post.id}`" class="text-decoration-none text-dark">
                                <div class="card h-100">
                                    <!-- ẢNH -->
                                    <img :src="post.image" class="card-img-top post-img" alt="post image" />

                                    <!-- NỘI DUNG -->
                                    <div class="card-body">
                                        <h6>{{ post.title }}</h6>
                                        <p class="small text-muted">{{ post.content }}</p>
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
</template>

<script setup>
import { computed } from 'vue'

const posts = [
    {
        id: 1,
        title: 'Quán ăn gần QTSC',
        content: 'Giá rẻ...',
        image: '/images/quan-an/com-tam.jpg'
    },
    {
        id: 2,
        title: 'Địa điểm quay video',
        content: 'Yên tĩnh...',
        image: '/images/quan-an/com-tam.jpg'
    },
    {
        id: 3,
        title: 'Tiệm in ấn',
        content: 'Nhanh...',
        image: '/images/quan-an/com-tam.jpg'
    },
    {
        id: 4,
        title: 'Quán ăn mở khuya',
        content: 'Deadline...',
        image: '/images/quan-an/com-tam.jpg'
    },
    {
        id: 5,
        title: 'Mua đồ handmade',
        content: 'Mô hình...',
        image: '/images/quan-an/com-tam.jpg'
    },
    {
        id: 6,
        title: 'Phòng học trống',
        content: 'Buổi tối...',
        image: '/images/quan-an/com-tam.jpg'
    }
]

// CHIA SLIDE – KHÔNG MẤT BÀI
const slides = computed(() => {
    const size = 3
    const result = []
    for (let i = 0; i < posts.length; i += size) {
        result.push(posts.slice(i, i + size))
    }
    return result
})
</script>
