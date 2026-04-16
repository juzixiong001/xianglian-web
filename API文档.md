# 项目接口文档

## 1. 接口概览

### 1.1 基础信息
- **API基础路径**: `https://12283is2aw205.vicp.fun/api`
- **认证方式**: JWT Token
- **请求格式**: JSON
- **响应格式**: 统一JSON格式

### 1.2 响应格式

#### 成功响应
```json
{
  "code": 200,
  "message": "成功",
  "data": {...}
}
```

#### 失败响应
```json
{
  "code": 错误码,
  "message": "错误信息"
}
```

## 2. 认证接口

### 2.1 注册接口
- **路径**: `/register`
- **方法**: POST
- **是否需要token**: 否
- **请求体**:
  ```json
  {
    "username": "testuser",
    "password": "123456",
    "phone": "13800138000"
  }
  ```
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "token": "eyJhbGciOiJIUzI1NiJ9...",
      "user": {
        "id": 1,
        "username": "testuser",
        "phone": "13800138000"
      }
    }
  }
  ```

### 2.2 登录接口
- **路径**: `/login`
- **方法**: POST
- **是否需要token**: 否
- **请求体**:
  ```json
  {
    "username": "testuser",
    "password": "123456"
  }
  ```
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "token": "eyJhbGciOiJIUzI1NiJ9...",
      "user": {
        "id": 1,
        "username": "testuser",
        "phone": "13800138000"
      }
    }
  }
  ```

## 3. 用户接口

### 3.1 获取用户资料
- **路径**: `/user/profile`
- **方法**: GET
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "username": "testuser",
      "phone": "13800138000"
    }
  }
  ```

### 3.2 更新用户资料
- **路径**: `/user/profile`
- **方法**: PUT
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求体**:
  ```json
  {
    "phone": "13900139000"
  }
  ```
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "username": "testuser",
      "phone": "13900139000"
    }
  }
  ```

## 4. 供需接口

### 4.1 获取所有供需
- **路径**: `/posts`
- **方法**: GET
- **是否需要token**: 否
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "total": 10,
      "list": [
        {
          "id": 1,
          "userId": 1,
          "title": "测试标题",
          "content": "测试内容",
          "type": "供应",
          "area": "北京市",
          "price": "100",
          "contact": "13800138000",
          "images": "image1.jpg,image2.jpg",
          "createTime": "2026-04-15 10:00:00",
          "updateTime": "2026-04-15 10:00:00"
        }
      ]
    }
  }
  ```

### 4.2 发布供需
- **路径**: `/posts`
- **方法**: POST
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求体**:
  ```json
  {
    "title": "测试标题",
    "content": "测试内容",
    "type": "供应",
    "area": "北京市",
    "price": "100",
    "contact": "13800138000",
    "images": "image1.jpg,image2.jpg"
  }
  ```
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "userId": 1,
      "title": "测试标题",
      "content": "测试内容",
      "type": "供应",
      "area": "北京市",
      "price": "100",
      "contact": "13800138000",
      "images": "image1.jpg,image2.jpg",
      "createTime": "2026-04-15 10:00:00",
      "updateTime": "2026-04-15 10:00:00"
    }
  }
  ```

### 4.3 删除供需
- **路径**: `/posts/{id}`
- **方法**: DELETE
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "message": "删除成功"
    }
  }
  ```

### 4.4 批量删除供需
- **路径**: `/posts`
- **方法**: DELETE
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求参数**: `ids=1,2,3`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "ids": "1,2,3",
      "deletedCount": 3,
      "message": "批量删除成功"
    }
  }
  ```

### 4.5 获取我的供需
- **路径**: `/posts/my` 或 `/posts/user/publishes`
- **方法**: GET
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "total": 5,
      "list": [
        {
          "id": 1,
          "userId": 1,
          "title": "测试标题",
          "content": "测试内容",
          "type": "供应",
          "area": "北京市",
          "price": "100",
          "contact": "13800138000",
          "images": "image1.jpg,image2.jpg",
          "createTime": "2026-04-15 10:00:00",
          "updateTime": "2026-04-15 10:00:00"
        }
      ]
    }
  }
  ```

### 4.6 搜索供需
- **路径**: `/posts/search`
- **方法**: GET
- **是否需要token**: 否
- **请求参数**:
    - `title`: 标题关键词（可选）
    - `content`: 内容关键词（可选）
    - `type`: 类型（供应/需求，可选）
    - `sort`: 排序方式（time_desc/time_asc，默认time_desc）
    - `page`: 页码（默认1）
    - `size`: 每页数量（默认10）
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "total": 20,
      "list": [
        {
          "id": 1,
          "userId": 1,
          "title": "测试标题",
          "content": "测试内容",
          "type": "供应",
          "area": "北京市",
          "price": "100",
          "contact": "13800138000",
          "images": "image1.jpg,image2.jpg",
          "createTime": "2026-04-15 10:00:00",
          "updateTime": "2026-04-15 10:00:00"
        }
      ],
      "pageNum": 1,
      "pageSize": 10,
      "pages": 2
    }
  }
  ```

## 5. 政策接口

### 5.1 获取所有政策
- **路径**: `/policies`
- **方法**: GET
- **是否需要token**: 否
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "total": 10,
      "list": [
        {
          "id": 1,
          "title": "测试政策",
          "content": "政策内容",
          "type": "补贴",
          "area": "北京市",
          "money": "10000",
          "conditions": "条件",
          "materials": "材料",
          "process": "流程",
          "contact": "联系方式",
          "createTime": "2026-04-15 10:00:00",
          "updateTime": "2026-04-15 10:00:00"
        }
      ]
    }
  }
  ```

### 5.2 获取政策详情
- **路径**: `/policies/{id}`
- **方法**: GET
- **是否需要token**: 否
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "title": "测试政策",
      "content": "政策内容",
      "type": "补贴",
      "area": "北京市",
      "money": "10000",
      "conditions": "条件",
      "materials": "材料",
      "process": "流程",
      "contact": "联系方式",
      "createTime": "2026-04-15 10:00:00",
      "updateTime": "2026-04-15 10:00:00"
    }
  }
  ```

### 5.3 发布政策
- **路径**: `/policies`
- **方法**: POST
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求体**:
  ```json
  {
    "title": "测试政策",
    "content": "政策内容",
    "type": "补贴",
    "area": "北京市",
    "money": "10000",
    "conditions": "条件",
    "materials": "材料",
    "process": "流程",
    "contact": "联系方式"
  }
  ```
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "title": "测试政策",
      "content": "政策内容",
      "type": "补贴",
      "area": "北京市",
      "money": "10000",
      "conditions": "条件",
      "materials": "材料",
      "process": "流程",
      "contact": "联系方式",
      "createTime": "2026-04-15 10:00:00",
      "updateTime": "2026-04-15 10:00:00"
    }
  }
  ```

### 5.4 修改政策
- **路径**: `/policies/{id}`
- **方法**: PUT
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求体**:
  ```json
  {
    "title": "修改后的政策",
    "content": "修改后的内容",
    "type": "补贴",
    "area": "北京市",
    "money": "20000",
    "conditions": "修改后的条件",
    "materials": "修改后的材料",
    "process": "修改后的流程",
    "contact": "修改后的联系方式"
  }
  ```
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "title": "修改后的政策",
      "content": "修改后的内容",
      "type": "补贴",
      "area": "北京市",
      "money": "20000",
      "conditions": "修改后的条件",
      "materials": "修改后的材料",
      "process": "修改后的流程",
      "contact": "修改后的联系方式",
      "createTime": "2026-04-15 10:00:00",
      "updateTime": "2026-04-15 11:00:00"
    }
  }
  ```

### 5.5 删除政策
- **路径**: `/policies/{id}`
- **方法**: DELETE
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "message": "删除成功"
    }
  }
  ```

### 5.6 批量删除政策
- **路径**: `/policies`
- **方法**: DELETE
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求参数**: `ids=1,2,3`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "ids": "1,2,3",
      "deletedCount": 3,
      "message": "批量删除成功"
    }
  }
  ```

### 5.7 搜索政策
- **路径**: `/policies/search`
- **方法**: GET
- **是否需要token**: 否
- **请求参数**:
    - `title`: 标题关键词（可选）
    - `type`: 类型（可选）
    - `area`: 地区（可选）
    - `sort`: 排序方式（time_desc/time_asc，默认time_desc）
    - `page`: 页码（默认1）
    - `size`: 每页数量（默认10）
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "total": 20,
      "list": [
        {
          "id": 1,
          "title": "测试政策",
          "content": "政策内容",
          "type": "补贴",
          "area": "北京市",
          "money": "10000",
          "conditions": "条件",
          "materials": "材料",
          "process": "流程",
          "contact": "联系方式",
          "createTime": "2026-04-15 10:00:00",
          "updateTime": "2026-04-15 10:00:00"
        }
      ],
      "pageNum": 1,
      "pageSize": 10,
      "pages": 2
    }
  }
  ```

## 6. 收藏接口

### 6.1 添加收藏
- **路径**: `/favorites`
- **方法**: POST
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求体**:
  ```json
  {
    "postId": 1
  }
  ```
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "id": 1,
      "userId": 1,
      "postId": 1,
      "createTime": "2026-04-15 10:00:00"
    }
  }
  ```

### 6.2 删除收藏
- **路径**: `/favorites/{id}`
- **方法**: DELETE
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "message": "删除成功"
    }
  }
  ```

### 6.3 批量删除收藏
- **路径**: `/favorites`
- **方法**: DELETE
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **请求参数**: `ids=1,2,3`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": {
      "ids": "1,2,3",
      "deletedCount": 3,
      "message": "批量删除成功"
    }
  }
  ```

### 6.4 获取我的收藏
- **路径**: `/favorites/my`
- **方法**: GET
- **是否需要token**: 是
- **请求头**: `Authorization: Bearer <token>`
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": [
      {
        "id": 1,
        "userId": 1,
        "postId": 1,
        "createTime": "2026-04-15 10:00:00"
      }
    ]
  }
  ```

## 7. 文件上传接口

### 7.1 上传图片
- **路径**: `/upload`
- **方法**: POST
- **是否需要token**: 否
- **请求参数**: `file` (multipart/form-data)
- **响应**:
  ```json
  {
    "code": 200,
    "message": "成功",
    "data": "/upload/123456.jpg"
  }
  ```