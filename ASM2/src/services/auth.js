import { users } from './fakeDb'

// đăng nhập
export const login = (email, password) => {
  const user = users.find(
    u => u.email === email && u.password === password
  )
  if (!user) return null

  localStorage.setItem('user', JSON.stringify(user))
  window.dispatchEvent(new Event('auth-changed'))
  return user
}

// đăng ký
export const register = (name, email, password) => {
  const exists = users.some(u => u.email === email)
  if (exists) return false

  users.push({
    id: 'U' + Date.now(),
    name,
    email,
    password,
    role: 'user'
  })

  return true
}

// đăng xuất
export const logout = () => {
  localStorage.removeItem('user')
  window.dispatchEvent(new Event('auth-changed'))
}

// lấy user hiện tại
export const getCurrentUser = () => {
  const data = localStorage.getItem('user')
  return data ? JSON.parse(data) : null
}
