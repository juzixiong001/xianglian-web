# xianglian-web 接口文档


**简介**:xianglian-web 接口文档


**HOST**:http://localhost:8080


**联系人**:开发团队


**Version**:1.0.0


**接口路径**:/v3/api-docs


[TOC]






# 收藏模块


## 添加收藏


**接口地址**:`/api/favorites`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 批量取消收藏


**接口地址**:`/api/favorites`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取我的收藏列表


**接口地址**:`/api/favorites/my`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取我收藏的帖子


**接口地址**:`/api/favorites/my/posts`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取收藏数量


**接口地址**:`/api/favorites/count`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 检查是否已收藏


**接口地址**:`/api/favorites/check`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 取消收藏


**接口地址**:`/api/favorites/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 根据帖子ID取消收藏


**接口地址**:`/api/favorites/post/{postId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 政策模块


## 获取政策详情


**接口地址**:`/api/policies/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 更新政策信息


**接口地址**:`/api/policies/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "title": "",
  "content": "",
  "type": "",
  "area": "",
  "money": "",
  "conditions": "",
  "materials": "",
  "process": "",
  "contact": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||
|policy|Policy|body|true|Policy|Policy|
|&emsp;&emsp;id|||false|integer(int32)||
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;type|||false|string||
|&emsp;&emsp;area|||false|string||
|&emsp;&emsp;money|||false|string||
|&emsp;&emsp;conditions|||false|string||
|&emsp;&emsp;materials|||false|string||
|&emsp;&emsp;process|||false|string||
|&emsp;&emsp;contact|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 删除政策


**接口地址**:`/api/policies/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取所有政策列表


**接口地址**:`/api/policies`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 发布政策


**接口地址**:`/api/policies`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "title": "",
  "content": "",
  "type": "",
  "area": "",
  "money": "",
  "conditions": "",
  "materials": "",
  "process": "",
  "contact": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|policy|Policy|body|true|Policy|Policy|
|&emsp;&emsp;id|||false|integer(int32)||
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;type|||false|string||
|&emsp;&emsp;area|||false|string||
|&emsp;&emsp;money|||false|string||
|&emsp;&emsp;conditions|||false|string||
|&emsp;&emsp;materials|||false|string||
|&emsp;&emsp;process|||false|string||
|&emsp;&emsp;contact|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 批量删除政策


**接口地址**:`/api/policies`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 搜索政策


**接口地址**:`/api/policies/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|title||query|false|string||
|type||query|false|string||
|area||query|false|string||
|sort||query|false|string||
|page||query|false|integer(int32)||
|size||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# AI模块


## 供需分析


**接口地址**:`/api/ai/supply-demand`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 内容概括


**接口地址**:`/api/ai/summarize`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 政策智能匹配


**接口地址**:`/api/ai/policy-match`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## AI智能问答


**接口地址**:`/api/ai/chat`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 农业优化建议


**接口地址**:`/api/ai/agriculture-optimize`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## AI服务健康检查


**接口地址**:`/api/ai/health`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 统计模块


## 获取用户30天趋势


**接口地址**:`/api/statistics/users/trend`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取帖子类型分布


**接口地址**:`/api/statistics/posts/type`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取帖子7天趋势


**接口地址**:`/api/statistics/posts/trend`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取帖子地区分布


**接口地址**:`/api/statistics/posts/area`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取数据概览


**接口地址**:`/api/statistics/overview`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取所有图表数据


**接口地址**:`/api/statistics/charts`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 认证模块


## 发送短信验证码


**接口地址**:`/api/send-sms-code`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 用户注册（用户名+密码）


**接口地址**:`/api/register`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "username": "",
  "password": "",
  "nickname": "",
  "avatar": "",
  "phone": "",
  "email": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|User|body|true|User|User|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 手机号注册（验证码）


**接口地址**:`/api/register/phone`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 用户登录（用户名-手机号+密码）


**接口地址**:`/api/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "username": "",
  "password": "",
  "nickname": "",
  "avatar": "",
  "phone": "",
  "email": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|User|body|true|User|User|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 手机号登录（验证码）


**接口地址**:`/api/login/phone`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 推荐模块


## 获取用户推荐帖子


**接口地址**:`/api/recommend/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int32)||
|limit||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取个性化推荐


**接口地址**:`/api/recommend/personalized`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|limit||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取热门帖子


**接口地址**:`/api/recommend/hot`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|limit||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 根路径接口


## 注册(根路径)


**接口地址**:`/register`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "username": "",
  "password": "",
  "nickname": "",
  "avatar": "",
  "phone": "",
  "email": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|User|body|true|User|User|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 登录(根路径)


**接口地址**:`/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "username": "",
  "password": "",
  "nickname": "",
  "avatar": "",
  "phone": "",
  "email": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|User|body|true|User|User|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 用户模块


## 获取用户个人信息


**接口地址**:`/api/user/profile`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 更新用户个人信息


**接口地址**:`/api/user/profile`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "username": "",
  "password": "",
  "nickname": "",
  "avatar": "",
  "phone": "",
  "email": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|User|body|true|User|User|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 帖子模块


## 获取所有帖子


**接口地址**:`/api/posts`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 创建帖子


**接口地址**:`/api/posts`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "userId": 0,
  "title": "",
  "content": "",
  "type": "",
  "area": "",
  "price": "",
  "contact": "",
  "images": "",
  "createTime": "",
  "updateTime": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|post|Post|body|true|Post|Post|
|&emsp;&emsp;id|||false|integer(int32)||
|&emsp;&emsp;userId|||false|integer(int32)||
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;type|||false|string||
|&emsp;&emsp;area|||false|string||
|&emsp;&emsp;price|||false|string||
|&emsp;&emsp;contact|||false|string||
|&emsp;&emsp;images|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPost|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||Post|Post|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;userId||integer(int32)||
|&emsp;&emsp;title||string||
|&emsp;&emsp;content||string||
|&emsp;&emsp;type||string||
|&emsp;&emsp;area||string||
|&emsp;&emsp;price||string||
|&emsp;&emsp;contact||string||
|&emsp;&emsp;images||string||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"userId": 0,
		"title": "",
		"content": "",
		"type": "",
		"area": "",
		"price": "",
		"contact": "",
		"images": "",
		"createTime": "",
		"updateTime": ""
	},
	"timestamp": 0,
	"traceId": ""
}
```


## 批量删除帖子


**接口地址**:`/api/posts`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ids||query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取我的帖子(兼容旧路径)


**接口地址**:`/api/posts/user/publishes`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 搜索帖子


**接口地址**:`/api/posts/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|title||query|false|string||
|content||query|false|string||
|type||query|false|string||
|sort||query|false|string||
|page||query|false|integer(int32)||
|size||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 获取我的帖子


**接口地址**:`/api/posts/my`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


## 删除帖子


**接口地址**:`/api/posts/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapStringObject|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```


# 上传模块


## 上传图片


**接口地址**:`/api/upload`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||
|timestamp||integer(int64)|integer(int64)|
|traceId||string||


**响应示例**:
```javascript
{
	"code": 0,
	"message": "",
	"data": {},
	"timestamp": 0,
	"traceId": ""
}
```