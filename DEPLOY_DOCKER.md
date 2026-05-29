# Docker 部署指南

本文档说明如何将 Vibe CSH 项目部署到 Docker 容器中。

## 前置条件

- 虚拟机已安装 Docker 和 Docker Compose
- 如果没有安装，请先安装：

### Docker 安装（Ubuntu/Debian）
```bash
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# 安装 Docker Compose
sudo apt install docker-compose
```

### Docker 安装（CentOS/RHEL）
```bash
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# 安装 Docker Compose
sudo yum install docker-compose
```

### 启动 Docker 服务
```bash
sudo systemctl start docker
sudo systemctl enable docker
```

## 部署步骤

### 1. 克隆或上传代码到虚拟机

```bash
# 如果仓库已公开，直接克隆
git clone https://github.com/m289133994/vibe-csh.git
cd vibe-csh

# 或者通过其他方式上传整个项目文件夹到虚拟机
```

### 2. 启动服务

```bash
# 进入项目目录
cd /path/to/vibe-csh

# 构建并启动所有服务（MySQL + 后端）
docker-compose up -d

# 查看日志
docker-compose logs -f
```

### 3. 验证部署

打开浏览器访问：
- **后端 API**: http://虚拟机IP:8080/api/auth/login

## 常用命令

### 查看服务状态
```bash
docker-compose ps
```

### 查看日志
```bash
# 查看所有服务日志
docker-compose logs -f

# 只查看后端日志
docker-compose logs -f backend

# 只查看 MySQL 日志
docker-compose logs -f mysql
```

### 停止服务
```bash
docker-compose down
```

### 停止并删除数据卷（谨慎使用）
```bash
docker-compose down -v
```

### 重新构建并启动
```bash
# 修改代码后重新构建
docker-compose up -d --build
```

### 进入容器
```bash
# 进入后端容器
docker exec -it vibe-backend sh

# 进入 MySQL 容器
docker exec -it vibe-mysql mysql -uroot -proot123456
```

## 配置说明

### 修改数据库密码

编辑 `docker-compose.yml` 中的环境变量：
```yaml
mysql:
  environment:
    MYSQL_ROOT_PASSWORD: 你的新密码
    MYSQL_USER: vibe
    MYSQL_PASSWORD: vibe123

backend:
  environment:
    SPRING_DATASOURCE_PASSWORD: vibe123
```

### 映射其他端口

如果 8080 端口被占用，修改端口映射：
```yaml
backend:
  ports:
    - "8081:8080"  # 左边是宿主机端口，右边是容器端口
```

## 数据持久化

MySQL 数据通过 Docker Volume 持久化，即使容器删除，数据也不会丢失。

### 备份数据
```bash
docker exec vibe-mysql mysqldump -uroot -proot123456 vibedb > backup.sql
```

### 恢复数据
```bash
docker exec -i vibe-mysql mysql -uroot -proot123456 vibedb < backup.sql
```

## 测试账号

部署成功后，系统会自动创建以下测试用户：

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | admin | admin123 |
| 普通用户 | user | user123 |

## 故障排查

### 端口被占用
```bash
# 查看端口占用
sudo netstat -tlnp

# 修改 docker-compose.yml 中的端口映射
```

### 容器无法启动
```bash
# 查看详细日志
docker-compose logs backend
```

### 数据库连接失败
```bash
# 等待 MySQL 完全启动
# 查看 MySQL 容器状态
docker-compose ps mysql
```

### 权限问题
```bash
# 确保当前用户有 docker 权限
sudo usermod -aG docker $USER
# 重新登录后生效
```
