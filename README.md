# Vibe - Vue + Spring Boot 登录系统

一个前后端分离的用户登录系统，使用 Vue 3 + Spring Boot + JWT 认证。

## 技术栈

### 后端
- Spring Boot 3.2.5
- Spring Security + JWT
- Spring Data JPA
- H2 内存数据库
- Lombok

### 前端
- Vue 3 (Composition API)
- Vue Router 4
- Axios
- Vite

## 项目结构

```
vibe-csh/
├── backend/                 # Spring Boot 后端
│   ├── src/
│   │   └── main/
│   │       ├── java/com/vibe/backend/
│   │       │   ├── entity/      # 实体类
│   │       │   ├── dto/         # 数据传输对象
│   │       │   ├── repository/  # 数据访问层
│   │       │   ├── service/     # 业务逻辑层
│   │       │   ├── controller/  # 控制器
│   │       │   ├── security/    # 安全配置
│   │       │   ├── config/      # 配置类
│   │       │   └── VibeBackendApplication.java
│   │       └── resources/
│   │           └── application.yml
│   └── pom.xml
└── frontend/                # Vue 前端
    ├── src/
    │   ├── views/          # 页面组件
    │   ├── router/         # 路由配置
    │   ├── services/       # API 服务
    │   ├── App.vue
    │   ├── main.js
    │   └── style.css
    ├── index.html
    ├── vite.config.js
    └── package.json
```

## 快速开始

### 前置要求

- JDK 17+
- Node.js 18+
- Maven 3.8+

### 运行后端

1. 进入后端目录：
```bash
cd backend
```

2. 使用 Maven 运行：
```bash
mvn spring-boot:run
```

后端服务将在 `http://localhost:8080` 启动。

### 运行前端

1. 进入前端目录：
```bash
cd frontend
```

2. 安装依赖：
```bash
npm install
```

3. 启动开发服务器：
```bash
npm run dev
```

前端服务将在 `http://localhost:5173` 启动。

## 测试账户

系统启动时会自动创建两个测试账户：

| 用户名 | 密码 | 角色 | 邮箱 |
|--------|------|------|------|
| admin | admin123 | 管理员 | admin@vibe.com |
| user | user123 | 普通用户 | user@vibe.com |

## API 接口

### 登录
- **URL**: `POST /api/auth/login`
- **Body**:
```json
{
  "username": "admin",
  "password": "admin123"
}
```
- **Response**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "username": "admin",
  "email": "admin@vibe.com"
}
```

### 注册
- **URL**: `POST /api/auth/register`
- **Body**:
```json
{
  "username": "newuser",
  "password": "password123",
  "email": "newuser@example.com"
}
```

## 功能特性

- 用户注册
- 用户登录
- JWT Token 认证
- 路由守卫
- 密码加密（BCrypt）
- 跨域支持
