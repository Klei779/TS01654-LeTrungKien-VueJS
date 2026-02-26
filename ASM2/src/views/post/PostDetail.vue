<template>
    <div class="container mt-4" v-if="post">
        <div class="row">

            <div class="col-md-7">
                <h3>{{ post.title }}</h3>

                <p class="text-muted">
                    {{ post.author }} • {{ post.createdAt }}
                </p>

                <img v-if="post.image" :src="post.image" class="img-fluid mb-3"
                    style="max-height:420px; object-fit:cover" />

                <p>{{ post.content }}</p>
            </div>

            <div class="col-md-5">

                <h5 class="mt-4 mb-2">Bình luận</h5>

                <div v-if="postComments.length === 0" class="text-muted">
                    Chưa có bình luận
                </div>

                <div v-for="c in postComments" :key="c.id" class="border-bottom pb-2 mb-2">

                    <div class="d-flex justify-content-between">
                        <strong>{{ c.author }}</strong>
                        <small class="text-muted">{{ c.createdAt }}</small>
                    </div>

                    <!-- VIEW MODE -->
                    <p v-if="editCommentId !== c.id" class="mb-1">
                        {{ c.content }}
                    </p>

                    <!-- EDIT MODE -->
                    <textarea v-else v-model="editCommentContent" class="form-control mb-2" rows="2"></textarea>

                    <!-- ACTIONS (chỉ hiện với comment của mình) -->
                    <div v-if="user && c.author === user.name">
                        <button v-if="editCommentId !== c.id" class="btn btn-sm btn-warning me-2"
                            @click="startEditComment(c)">
                            Sửa
                        </button>

                        <button class="btn btn-sm btn-danger" @click="deleteComment(c.id)">
                            Xóa
                        </button>

                        <button v-if="editCommentId === c.id" class="btn btn-sm btn-success me-2"
                            @click="saveEditComment">
                            Lưu
                        </button>

                        <button v-if="editCommentId === c.id" class="btn btn-sm btn-secondary"
                            @click="cancelEditComment">
                            Hủy
                        </button>
                    </div>
                </div>


                <div v-if="user" class="mt-3">
                    <textarea v-model="newComment" class="form-control mb-2" rows="2"
                        placeholder="Viết bình luận..."></textarea>

                    <button class="btn btn-primary btn-sm" @click="addComment">
                        Gửi bình luận
                    </button>
                </div>

                <div v-else class="text-muted">
                    Vui lòng đăng nhập để bình luận
                </div>

            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { useRoute } from 'vue-router'
import { posts, comments as commentDb } from '@/services/fakeDb'
import { getCurrentUser } from '@/services/auth'


const route = useRoute()
const user = getCurrentUser()

const post = ref(null)

const loadPost = () => {
    post.value = posts.find(p => p.id == route.params.id)
}

loadPost()

watch(
    () => route.params.id,
    () => {
        loadPost()
        newComment.value = ''
        window.scrollTo({ top: 0, behavior: 'smooth' })
    }
)

const relatedPosts = computed(() =>
    posts
        .filter(p => p.id != route.params.id)
        .slice(0, 3)
)

const comments = ref(commentDb)
const newComment = ref('')

const postComments = computed(() =>
    comments.value.filter(c => c.postId == route.params.id)
)

const addComment = () => {
    if (!newComment.value.trim()) return

    comments.value.push({
        id: 'C' + Date.now(),
        postId: route.params.id,
        author: user.name,
        content: newComment.value,
        createdAt: new Date().toLocaleDateString()
    })

    newComment.v
    alue = ''
}

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
    c.createdAt = new Date().toLocaleDateString() // ngày sửa

    editCommentId.value = null
    editCommentContent.value = ''
}

const cancelEditComment = () => {
    editCommentId.value = null
    editCommentContent.value = ''
}

const deleteComment = (id) => {
    const index = comments.value.findIndex(c => c.id === id)
    if (index !== -1) comments.value.splice(index, 1)
}

</script>
