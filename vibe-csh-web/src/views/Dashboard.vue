<template>
  <div class="dashboard-container">
    <div class="dashboard-card">
      <div class="header">
        <h1>🎉 欢迎回来！</h1>
        <button @click="handleLogout" class="btn-logout">退出登录</button>
      </div>
      
      <div class="user-info">
        <div class="avatar">{{ username.charAt(0).toUpperCase() }}</div>
        <div class="info">
          <h2>{{ username }}</h2>
          <p class="email">{{ email }}</p>
        </div>
      </div>
      
      <div class="welcome-content">
        <h3>登录成功！</h3>
        <p>您已成功登录 Vibe 系统。</p>
        <p>这是一个前后端分离的示例项目，使用 Vue 3 + Spring Boot + JWT 认证。</p>
      </div>
      
      <div class="token-info">
        <h4>当前 Token（部分显示）</h4>
        <div class="token-box">{{ tokenPreview }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const username = ref(localStorage.getItem('username') || '')
const email = ref(localStorage.getItem('email') || '')
const token = ref(localStorage.getItem('token') || '')

const tokenPreview = computed(() => {
  if (token.value.length > 50) {
    return token.value.substring(0, 50) + '...'
  }
  return token.value
})

const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('username')
  localStorage.removeItem('email')
  router.push('/login')
}
</script>

<style scoped>
.dashboard-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}

.dashboard-card {
  background: white;
  padding: 40px;
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
  width: 100%;
  max-width: 500px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 32px;
}

.header h1 {
  color: #333;
  font-size: 28px;
}

.btn-logout {
  padding: 10px 20px;
  background: #e74c3c;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  cursor: pointer;
  transition: background 0.3s;
}

.btn-logout:hover {
  background: #c0392b;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 32px;
  padding: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 12px;
}

.avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  background: white;
  color: #667eea;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 28px;
  font-weight: bold;
}

.info h2 {
  color: white;
  font-size: 22px;
  margin-bottom: 4px;
}

.info .email {
  color: rgba(255, 255, 255, 0.8);
  font-size: 14px;
}

.welcome-content {
  margin-bottom: 24px;
}

.welcome-content h3 {
  color: #333;
  margin-bottom: 12px;
  font-size: 18px;
}

.welcome-content p {
  color: #666;
  line-height: 1.6;
  margin-bottom: 8px;
  font-size: 14px;
}

.token-info h4 {
  color: #333;
  margin-bottom: 12px;
  font-size: 14px;
}

.token-box {
  background: #f5f5f5;
  padding: 12px;
  border-radius: 8px;
  font-family: monospace;
  font-size: 12px;
  color: #666;
  word-break: break-all;
}
</style>
