<template>
    <div class="container-fluid mt-4">
        <div class="row">

            <!-- ================= FORM (50%) ================= -->
            <div class="col-md-6 border-end">
                <h4 class="mb-3">Tạo bài viết</h4>

                <form @submit.prevent="handleCreate">

                    <!-- TIÊU ĐỀ -->
                    <div class="mb-3">
                        <label class="form-label">Tiêu đề</label>
                        <input v-model="title" class="form-control" placeholder="Nhập tiêu đề bài viết" required />
                    </div>

                    <!-- CHỦ ĐỀ -->
                    <div class="mb-3">
                        <label class="form-label">Chủ đề</label>
                        <select v-model="category" class="form-select" required>
                            <option value="">-- Chọn chủ đề --</option>
                            <option v-for="cat in categories" :key="cat.slug" :value="cat.slug">
                                {{ cat.name }}
                            </option>
                        </select>
                    </div>

                    <!-- ẢNH -->
                    <div class="mb-3">
                        <label class="form-label">Ảnh bài viết</label>
                        <input type="file" class="form-control" accept="image/*" @change="handleImageChange" />
                    </div>

                    <!-- NỘI DUNG -->
                    <div class="mb-3">
                        <label class="form-label">Nội dung</label>
                        <textarea v-model="content" class="form-control" rows="6" placeholder="Nhập nội dung bài viết"
                            required></textarea>
                    </div>

                    <!-- ACTIVE -->
                    <div class="form-check mb-3">
                        <input class="form-check-input" type="checkbox" v-model="active" id="activePost" />
                        <label class="form-check-label" for="activePost">
                            Hiển thị bài viết
                        </label>
                    </div>

                    <button class="btn btn-success">
                        Đăng bài
                    </button>

                    <router-link to="/posts" class="btn btn-secondary ms-2">
                        Hủy
                    </router-link>
                </form>
            </div>

            <!-- ================= PREVIEW (50%) ================= -->
            <div class="col-md-6 bg-light">
                <h4 class="mb-3">Xem trước bài viết</h4>

                <div class="card shadow-sm">
                    <img v-if="imagePreview" :src="imagePreview" class="card-img-top preview-img" />

                    <div class="card-body">
                        <h5 class="card-title">
                            {{ title || 'Tiêu đề bài viết' }}
                        </h5>

                        <p class="text-muted small mb-2">
                            {{ user?.name || 'Tác giả' }} - {{ today }}
                        </p>

                        <span class="badge bg-primary mb-2">
                            {{ categoryName }}
                        </span>

                        <p class="card-text mt-2">
                            {{ content || 'Nội dung bài viết sẽ hiển thị ở đây...' }}
                        </p>

                        <p class="text-muted small mb-0">
                            0 bình luận • 0 thích
                        </p>
                    </div>
                </div>

            </div>

        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { posts, categories } from '@/services/fakeDb'
import { getCurrentUser } from '@/services/auth'

const router = useRouter()
const user = getCurrentUser()
const title = ref('')
const category = ref('')
const content = ref('')
const active = ref(true)
const imagePreview = ref('')

const handleImageChange = (e) => {
    const file = e.target.files[0]
    if (!file) return
    imagePreview.value = URL.createObjectURL(file)
}

const today = new Date().toLocaleDateString()

const categoryName = computed(() => {
    const cat = categories.find(c => c.slug === category.value)
    return cat ? cat.name : 'Chủ đề'
})

const handleCreate = () => {
    if (!user) {
        alert('Cần đăng nhập để đăng bài')
        return
    }

    posts.push({
        id: 'P' + Date.now(),
        title: title.value,
        author: user.name,
        image: imagePreview.value || '/images/default.jpg',
        content: content.value,
        createdAt: today,
        category: category.value,
        active: active.value
    })

    router.push('/posts')
}
</script>

<style scoped>
.preview-img {
    max-height: 260px;
    object-fit: cover;
}
</style>
