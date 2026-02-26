<template>
    <div class="container mt-4">
        <h3 class="mb-3">Lịch sử hoạt động</h3>

        <!-- ===== TABS ===== -->
        <ul class="nav nav-tabs mb-3">
            <li class="nav-item">
                <button class="nav-link" :class="{ active: activeTab === 'posts' }" @click="activeTab = 'posts'">
                    Bài viết của tôi
                </button>
            </li>
            <li class="nav-item">
                <button class="nav-link" :class="{ active: activeTab === 'comments' }" @click="activeTab = 'comments'">
                    Bình luận của tôi
                </button>
            </li>
        </ul>

        <!-- ================= TAB POSTS ================= -->
        <div v-if="activeTab === 'posts'">
            <div v-if="myPosts.length === 0" class="text-muted">
                Bạn chưa đăng bài viết nào
            </div>

            <div v-for="post in myPosts" :key="post.id" class="card mb-3">
                <div class="card-body">
                    <div class="d-flex">
                        <img :src="post.image" class="rounded me-3" style="width:200px;height:120px;object-fit:cover" />

                        <div>
                            <h5 class="mb-1">{{ post.title }}</h5>

                            <p class="small text-muted mb-1">
                                {{ post.createdAt }} •
                                {{ post.active ? 'Đang hiển thị' : 'Đã ẩn' }}
                            </p>

                            <p class="mb-2">{{ post.content }}</p>

                            <div>
                                <button class="btn btn-sm btn-warning me-2" @click="startEditPost(post)">
                                    Sửa
                                </button>

                                <button class="btn btn-sm btn-danger" @click="deletePost(post.id)">
                                    Xóa
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- ================= TAB COMMENTS ================= -->
        <div v-if="activeTab === 'comments'">
            <div v-if="myComments.length === 0" class="text-muted">
                Bạn chưa bình luận bài viết nào
            </div>

            <div v-for="c in myComments" :key="c.id" class="card mb-2">
                <div class="card-body">
                    <p class="mb-1">
                        <b>Bài viết:</b> {{ getPostTitle(c.postId) }}
                    </p>

                    <small class="text-muted">{{ c.createdAt }}</small>

                    <p v-if="editCommentId !== c.id" class="mt-2">
                        {{ c.content }}
                    </p>

                    <textarea v-if="editCommentId === c.id" v-model="editCommentContent"
                        class="form-control mb-2"></textarea>

                    <button class="btn btn-sm btn-warning me-2" @click="startEditComment(c)">
                        Sửa
                    </button>

                    <button class="btn btn-sm btn-danger" @click="deleteComment(c.id)">
                        Xóa
                    </button>

                    <button v-if="editCommentId === c.id" class="btn btn-sm btn-success ms-2" @click="saveEditComment">
                        Lưu
                    </button>
                </div>
            </div>
        </div>

        <!-- ================= MODAL EDIT POST (BOOTSTRAP) ================= -->
        <div class="modal fade show d-block" tabindex="-1" v-if="editingPost">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title">Sửa bài viết</h5>
                        <button type="button" class="btn-close" @click="cancelEditPost"></button>
                    </div>

                    <div class="modal-body">
                        <input v-model="editingPost.title" class="form-control mb-2" placeholder="Tiêu đề" />

                        <label class="form-label">Ảnh bài viết</label>
                        <input type="file" class="form-control mb-2" accept="image/*" @change="handleEditImage" />

                        <img v-if="editImagePreview" :src="editImagePreview" class="img-thumbnail mb-2"
                            style="max-height:150px" />

                        <textarea v-model="editingPost.content" class="form-control mb-2" rows="4"></textarea>

                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" v-model="editingPost.active"
                                id="activeEdit" />
                            <label class="form-check-label" for="activeEdit">
                                Hiển thị bài viết
                            </label>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-secondary btn-sm" @click="cancelEditPost">
                            Hủy
                        </button>

                        <button class="btn btn-success btn-sm" @click="saveEditPost">
                            Lưu
                        </button>
                    </div>

                </div>
            </div>
        </div>

        <!-- BACKDROP -->
        <div v-if="editingPost" class="modal-backdrop fade show"></div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { posts, comments as commentDb } from '@/services/fakeDb'
import { getCurrentUser } from '@/services/auth'

const user = getCurrentUser()
const activeTab = ref('posts')

/* ===== COMMENTS ===== */
const comments = ref(commentDb)

/* ===== POSTS ===== */
const myPosts = computed(() =>
    posts.filter(p => p.author === user.name)
)

const editingPost = ref(null)
const editImagePreview = ref('')

const startEditPost = (post) => {
    editingPost.value = { ...post }
    editImagePreview.value = post.image
}

const handleEditImage = (e) => {
    const file = e.target.files[0]
    if (!file) return
    editImagePreview.value = URL.createObjectURL(file)
    editingPost.value.image = editImagePreview.value
}

const saveEditPost = () => {
    const index = posts.findIndex(p => p.id === editingPost.value.id)
    if (index !== -1) posts[index] = editingPost.value
    cancelEditPost()
}

const cancelEditPost = () => {
    editingPost.value = null
    editImagePreview.value = ''
}

const deletePost = (id) => {
    const index = posts.findIndex(p => p.id === id)
    if (index !== -1) posts.splice(index, 1)
}

/* ===== USER COMMENTS ===== */
const myComments = computed(() =>
    comments.value.filter(c => c.author === user.name)
)

const editCommentId = ref(null)
const editCommentContent = ref('')

const startEditComment = (c) => {
    editCommentId.value = c.id
    editCommentContent.value = c.content
}

const saveEditComment = () => {
    const c = comments.value.find(c => c.id === editCommentId.value)
    if (!c) return

    c.content = editCommentContent.value
    c.createdAt = new Date().toISOString().slice(0, 10)

    editCommentId.value = null
}

const deleteComment = (id) => {
    const index = comments.value.findIndex(c => c.id === id)
    if (index !== -1) comments.value.splice(index, 1)
}

/* ===== UTIL ===== */
const getPostTitle = (postId) => {
    const post = posts.find(p => p.id === postId)
    return post ? post.title : 'Bài viết đã bị xóa'
}
</script>
