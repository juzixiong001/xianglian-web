# 项目配置与部署指南

## 一、项目问题汇总

### 1.1 原有代码问题

| 问题 | 位置 | 严重程度 | 解决方案 |
|------|------|----------|----------|
| countAll可能返回null | StatisticsController | 中 | 已添加空值处理 |
| FavoriteMapper查询缺少排序 | FavoriteMapper.java:30 | 低 | 可补充ORDER BY |
| 缺少通义千问API model参数 | AIServiceImpl.java:122 | 中 | 已修正为qwen-turbo |

### 1.2 新增功能检查清单

| 功能 | 文件 | 状态 | 说明 |
|------|------|------|------|
| 收藏接口完善 | FavoriteController/Service/Mapper | ✅ 正常 | 已完成 |
| 智能推荐 | RecommendController/Service + TfIdfUtil | ✅ 正常 | TF-IDF算法实现 |
| 数据可视化 | StatisticsController | ✅ 正常 | 提供6个统计接口 |
| AI服务 | AIController/Service + AIConfig | ✅ 正常 | 支持豆包/通义千问 |

### 1.3 前端配合检查

| 前端需求 | 后端接口 | 状态 |
|----------|----------|------|
| 获取收藏列表 | GET /api/favorites/my | ✅ |
| 获取收藏帖子详情 | GET /api/favorites/my/posts | ✅ 新增 |
| 检查收藏状态 | GET /api/favorites/check | ✅ 新增 |
| 智能推荐 | GET /api/recommend/personalized | ✅ 新增 |
| 热门推荐 | GET /api/recommend/hot | ✅ 新增 |
| 数据统计 | GET /api/statistics/charts | ✅ 新增 |
| AI问答 | POST /api/ai/chat | ✅ 新增 |
| AI政策匹配 | POST /api/ai/policy-match | ✅ 新增 |
| AI供需分析 | POST /api/ai/supply-demand | ✅ 新增 |
| AI农业优化 | POST /api/ai/agriculture-optimize | ✅ 新增 |

---

## 二、豆包API配置指南

### 2.1 注册豆包开发者账号

1. **访问百度智能云**
   - 官网：https://cloud.baidu.com/
   - 产品 -> 人工智能 -> 智能创作/千帆AppBuilder

2. **注册账号**
   - 使用百度账号登录
   - 完成实名认证（个人/企业）

3. **创建应用**
   - 进入控制台
   - 创建"应用"获取API Key和Secret Key

### 2.2 获取API凭证

登录后，在"应用管理"中获取：

```
API Key: xxxxxxxxxxxxxxxxxxxxxxxx
Secret Key: xxxxxxxxxxxxxxxxxxxxxxxx
```

### 2.3 配置方式

**方式一：环境变量（推荐，生产环境）**

```bash
# Linux/macOS
export DOUBAO_API_KEY=您的API Key
export DOUBAO_SECRET_KEY=您的Secret Key

# Windows PowerShell
$env:DOUBAO_API_KEY="您的API Key"
$env:DOUBAO_SECRET_KEY="您的Secret Key"
```

**方式二：配置文件（开发环境）**

修改 `src/main/resources/application.properties`：

```properties
ai.provider.doubao.api-key=您的API Key
ai.provider.doubao.secret-key=您的Secret Key
```

### 2.4 免费额度说明

| 平台 | 免费额度 | 适用场景 |
|------|----------|----------|
| 豆包API | 100万token/月 | 小型网站足够 |
| 通义千问 | 100万token/月 | 备选方案 |

### 2.5 成本控制配置

```properties
# 每日最大调用次数（默认1000次）
ai.rate-limit.max-requests-per-day=1000

# 每分钟最大调用次数（默认30次）
ai.rate-limit.max-requests-per-minute=30

# 缓存过期时间（分钟，默认30分钟）
ai.cache.expire-minutes=30

# 最大响应Token数（默认1500，约500汉字）
ai.provider.doubao.max-tokens=1500
```

---

## 三、通义千问API配置指南（备选）

### 3.1 注册阿里云账号

1. **访问阿里云**
   - 官网：https://www.aliyun.com/
   - 产品 -> 人工智能 -> 通义千问

2. **获取API Key**
   - 进入DashScope控制台
   - 创建API Key

### 3.2 配置通义千问

```properties
# 切换AI提供商
ai.provider.type=tongyi

# 通义千问API Key
ai.provider.tongyi.api-key=您的API Key
```

---

## 四、云服务器部署指南

### 4.1 服务器选购建议

| 配置项 | 推荐配置 | 说明 |
|--------|----------|------|
| CPU | 2核 | 基础配置足够 |
| 内存 | 4GB | Spring Boot应用需要 |
| 系统盘 | 40GB SSD | 够用 |
| 数据盘 | 100GB | 存储图片等 |
| 带宽 | 5Mbps | 够用 |
| 操作系统 | Ubuntu 22.04 LTS | 稳定 |

**推荐云服务商**：
- 阿里云ECS（国内访问快）
- 腾讯云CVM
- 华为云ECS
- 酷盾CVM

### 4.2 服务器环境准备

```bash
# 更新系统
sudo apt update && sudo apt upgrade -y

# 安装Java 17
sudo apt install openjdk-17-jdk -y

# 验证Java安装
java -version

# 安装Maven
sudo apt install maven -y

# 安装Git
sudo apt install git -y

# 安装Docker（可选，用于容器化部署）
sudo apt install docker.io -y
```

### 4.3 项目部署步骤

**方式一：传统部署（jar包）**

```bash
# 1. 在服务器上克隆项目
git clone [项目地址]
cd xianglian-web

# 2. 设置环境变量
export DOUBAO_API_KEY=您的API Key
export DOUBAO_SECRET_KEY=您的Secret Key
export JWT_SECRET=您的JWT密钥

# 3. 打包项目
mvn clean package -DskipTests

# 4. 运行项目
java -jar target/xianglian-web-0.0.1-SNAPSHOT.jar
```

**方式二：后台运行**

```bash
# 使用nohup后台运行
nohup java -jar target/xianglian-web-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:mysql://您的数据库地址:3306/xianglian \
  --spring.datasource.username=数据库用户名 \
  --spring.datasource.password=数据库密码 \
  > app.log 2>&1 &

# 查看运行状态
tail -f app.log

# 查看进程
ps -ef | grep java
```

**方式三：Docker部署（推荐）**

```bash
# 1. 创建Dockerfile
cat > Dockerfile << 'EOF'
FROM openjdk:17-slim
WORKDIR /app
COPY target/xianglian-web-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# 2. 构建镜像
docker build -t xianglian-web .

# 3. 运行容器
docker run -d -p 8080:8080 \
  -e DOUBAO_API_KEY=您的API Key \
  -e DOUBAO_SECRET_KEY=您的Secret Key \
  --name xianglian-web \
  xianglian-web
```

### 4.4 Nginx反向代理配置

```bash
# 安装Nginx
sudo apt install nginx -y

# 配置反向代理
sudo cat > /etc/nginx/sites-available/xianglian << 'EOF'
server {
    listen 80;
    server_name your-domain.com;  # 您的域名

    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
EOF

# 启用配置
sudo ln -s /etc/nginx/sites-available/xianglian /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx
```

### 4.5 HTTPS配置（SSL证书）

```bash
# 安装Certbot
sudo apt install certbot python3-certbot-nginx -y

# 获取SSL证书
sudo certbot --nginx -d your-domain.com

# 自动续期设置
sudo certbot renew --dry-run
```

### 4.6 数据库配置

项目已使用云数据库，修改 `application.properties`：

```properties
spring.datasource.url=jdbc:mysql://您的数据库地址:3306/xianglian
spring.datasource.username=您的用户名
spring.datasource.password=您的密码
```

### 4.7 运维监控

```bash
# 安装监控工具
sudo apt install htop iftop -y

# 查看内存和CPU
htop

# 查看网络带宽
iftop

# 查看磁盘使用
df -h

# 日志管理（推荐使用logrotate）
sudo cat > /etc/logrotate.d/xianglian << 'EOF'
/path/to/app.log {
    daily
    rotate 7
    compress
    delaycompress
    missingok
    notifempty
    create 644 root root
}
EOF
```

---

## 五、生产环境检查清单

### 5.1 安全配置

- [ ] 修改默认数据库密码
- [ ] 设置强JWT密钥（至少32位）
- [ ] 启用HTTPS（SSL证书）
- [ ] 配置防火墙（仅开放80/443端口）
- [ ] API密钥使用环境变量而非配置文件

### 5.2 性能配置

- [ ] 启用Redis缓存（可选）
- [ ] 配置数据库连接池
- [ ] 开启MyBatis二级缓存
- [ ] 配置GZIP压缩

### 5.3 备份配置

- [ ] 数据库定期备份
- [ ] 配置文件备份
- [ ] 上传文件备份（如果有）

---

## 六、快速启动命令汇总

```bash
# 1. 克隆项目
git clone [项目地址] && cd xianglian-web

# 2. 配置环境变量
export DOUBAO_API_KEY=您的API Key
export DOUBAO_SECRET_KEY=您的Secret Key
export JWT_SECRET=您的32位随机字符串

# 3. 构建项目
mvn clean package -DskipTests

# 4. 启动服务
java -jar target/xianglian-web-0.0.1-SNAPSHOT.jar
```

---

## 七、常见问题

### Q1: 启动报数据库连接错误
```
检查数据库地址、端口、用户名、密码是否正确
确认数据库服务器防火墙允许外部访问
```

### Q2: AI接口返回"暂时不可用"
```
确认API Key和Secret Key正确
检查网络是否能访问百度云API
确认API免费额度是否用完
```

### Q3: 前端无法访问后端接口
```
确认后端服务已启动并监听8080端口
检查跨域配置是否正确
确认Nginx反向代理配置正确
```

### Q4: 内存不足导致OOM
```
增加服务器内存
调整JVM堆内存：java -Xmx512m -jar app.jar
```

---

## 八、联系方式

如有问题，请检查：
1. 项目日志文件
2. 云服务商控制台监控
3. API提供商状态页面