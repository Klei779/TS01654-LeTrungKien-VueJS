<template>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card shadow">
                    <div class="card-body">
                        <h4 class="text-center mb-4">Đăng nhập</h4>

                        <form @submit.prevent="handleLogin">
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input v-model="email" type="email" class="form-control" required />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Mật khẩu</label>
                                <input v-model="password" type="password" class="form-control" required />
                            </div>

                            <div v-if="error" class="alert alert-danger">
                                {{ error }}
                            </div>

                            <div class="d-flex gap-2">
                                <button class="btn btn-primary w-100">
                                    Đăng nhập
                                </button>

                                <router-link to="/" class="btn btn-outline-secondary w-100">
                                    Hủy
                                </router-link>
                            </div>
                        </form>


                        <div class="text-center mt-3">
                            <router-link to="/register">
                                Chưa có tài khoản? Đăng ký
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login } from '@/services/auth'

const router = useRouter()
const email = ref('')
const password = ref('')
const error = ref('')

const handleLogin = () => {
    const user = login(email.value, password.value)
    if (!user) {
        error.value = 'Email hoặc mật khẩu không đúng'
        return
    }
    router.push('/')
}
</script>
