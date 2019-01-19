# CusSSystem
基于WebSocket的在线客服系统
前端页面采用Amaze框架开发
### 完整下载后导入到eclipse中

```
用户页面
http://localhost:8080/kfxt/index.jsp?acceptname=kf&sendname=yh
客服页面
http://localhost:8080/kfxt/kfym.jsp?acceptname=yh&sendname=kf
```

### 环境

<ul>
  <li>jdk1.7</li>
  <li>tomcat7.0</li>
  <li>SqlServer</li>
</ul>

### 用户登录检查

```
系统会自动检查当前用户是否登录，如未登录则禁止其发送消息
```

### 用户与客服关系

```
普通用户对客服为1对1
客服对普通用户为1对多
```

### 功能实现
- [x] 用户对客服发起会话
- [x] 一个客服与多个用户通话
- [x] 离线消息推送
- [x] 客服管理
- [x] 服务器状态实时查看
- [x] 日志系统
- [ ] 文件实时发送和图片实时发送
- [ ] 文件离线发送和图片离线发送
- [ ] 聊天群组（聊天室）
- [ ] 公告管理
- [ ] 管理员管理聊天系统状态

### 注意
<ul>
  <li>tomcat使用7.0及以上、SqlServer2012以上、jdk1.7以上</li>
</ul>
