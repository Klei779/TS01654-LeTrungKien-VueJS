<template>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card shadow">
                    <div class="card-body">
                        <h4 class="text-center mb-4">Đăng ký</h4>

                        <form @submit.prevent="handleRegister">
                            <div class="mb-3">
                                <label class="form-label">Họ tên</label>
                                <input v-model="name" class="form-control" required />
                            </div>

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
                                <button class="btn btn-success w-100">
                                    Đăng ký
                                </button>

                                <router-link to="/login" class="btn btn-outline-secondary w-100">
                                    Hủy
                                </router-link>
                            </div>

                        </form>

                        <div class="text-center mt-3">
                            <router-link to="/login">
                                Đã có tài khoản? Đăng nhập
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
import { register } from '@/services/auth'

const router = useRouter()
const name = ref('')
const email = ref('')
const password = ref('')
const error = ref('')

const handleRegister = () => {
    const ok = register(name.value, email.value, password.value)
    if (!ok) {
        error.value = 'Email đã tồn tại'
        return
    }
    router.push('/login')
}
</script>
