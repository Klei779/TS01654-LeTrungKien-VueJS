<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark border-bottom">
        <div class="container">

            <!-- LOGO -->
            <router-link class="navbar-brand fw-bold text-white" to="/">
                QTSC News
                <span v-if="user" class="fw-normal">
                    - Xin chào {{ user.name }}!
                </span>
            </router-link>


            <!-- TOGGLE -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- MENU -->
            <div class="collapse navbar-collapse" id="mainNavbar">
                <ul class="navbar-nav ms-auto">

                    <!-- HOME -->
                    <li class="nav-item">
                        <router-link class="nav-link" to="/">
                            Trang chủ
                        </router-link>
                    </li>

                    <!-- POSTS -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                            Bài viết
                        </a>

                        <ul class="dropdown-menu">
                            <li>
                                <router-link class="dropdown-item" to="/posts">
                                    Danh sách bài viết
                                </router-link>
                            </li>

                            <li>
                                <router-link v-if="user" class="dropdown-item" to="/posts/create">
                                    Đăng bài
                                </router-link>

                                <a v-else class="dropdown-item" href="#" @click.prevent="alertLogin">
                                    Đăng bài
                                </a>
                            </li>

                            <li>
                                <router-link v-if="user" class="dropdown-item" to="/posts/history">
                                    Lịch sử bài đăng
                                </router-link>

                                <a v-else class="dropdown-item" href="#" @click.prevent="alertLogin">
                                    Lịch sử bài đăng
                                </a>
                            </li>
                        </ul>
                    </li>

                    <!-- FAVORITES -->
                    <li class="nav-item">
                        <router-link v-if="user" class="nav-link" to="/favorites">
                            Bài viết yêu thích
                        </router-link>

                        <a v-else class="nav-link" href="#" @click.prevent="alertLogin">
                            Bài viết yêu thích
                        </a>
                    </li>

                    <!-- ACCOUNT -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                            Tài khoản
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end">

                            <li v-if="!user">
                                <router-link class="dropdown-item" to="/login">
                                    Đăng nhập
                                </router-link>
                            </li>

                            <li v-if="!user">
                                <router-link class="dropdown-item" to="/register">
                                    Đăng ký
                                </router-link>
                            </li>

                            <li>
                                <router-link class="dropdown-item" to="/forgot-password">
                                    Thông tin cá nhân
                                </router-link>
                            </li>

                            <li v-if="user">
                                <button class="dropdown-item text-danger" @click="handleLogout">
                                    Đăng xuất
                                </button>
                            </li>



                        </ul>

                    </li>

                </ul>
            </div>
        </div>
    </nav>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'
import { getCurrentUser, logout } from '@/services/auth'

const router = useRouter()

const user = ref(getCurrentUser())

const updateUser = () => {
    user.value = getCurrentUser()
}

onMounted(() => {
    window.addEventListener('auth-changed', updateUser)
})

onBeforeUnmount(() => {
    window.removeEventListener('auth-changed', updateUser)
})

// ===== FUNCTIONS =====
const alertLogin = () => {
    alert('Chức năng này cần đăng nhập')
    router.push('/login')
}

const handleLogout = () => {
    logout()
    // logout() đã dispatch auth-changed
    router.push('/login')
}
</script>


<style scoped>
.navbar-brand span {
    font-size: 14px;
}
</style>
