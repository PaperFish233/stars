## Android系统开发大众点评App

**2021年12月15日 已归档** 



本系统功能结构图如图所示：

![](https://s2.loli.net/2022/09/29/cF8txlA9kNXK12T.png)



### 数据库设计

用户表：

| 属性     | 列名      | 数据类型 | 长度 | 约束 |
|----------|-----------|----------|------|------|
| 用户序号 | id        | Int      |      | 主键 |
| 用户账号 | uname     | Varchar  | 20   |      |
| 用户密码 | upassword | Varchar  | 12   |      |



内容表：

| 属性     | 列名   | 数据类型 | 长度 | 约束 |
|----------|--------|----------|------|------|
| 内容序号 | id     | Int      |      | 主键 |
| 用户账户 | stop   | Varchar  | 20   |      |
| 图片链接 | simg   | Varchar  | 100  |      |
| 帖子内容 | sword  | Varchar  | 120  |      |
| 推荐地点 | send   | Varchar  | 20   |      |
| 发布时间 | stime  | Varchar  | 30   |      |
| 评分星级 | sstar  | Varchar  | 10   |      |
| 收藏状态 | sheart | Varchar  | 10   |      |



### 系统框架

大众点评App是基于Android Studio设计并制作，本软件是通过Java语言进行开发设计，其所使用的数据库是SQLite轻量级数据库。Android Studio是一个全新的Android开发环境，基于IntelliJ IDEA类似Eclipse ADT，Android Studio提供了集成的 Android开发工具。工程目录结构图如图所示：

![](https://s2.loli.net/2022/09/29/Us1RkElI5S2dXzx.png)



Stars包：

| 文件名                  | 说明               |
|-------------------------|--------------------|
| AddContentsAcivity.java | 新增内容页面实现类 |
| AddUserActivity.java    | 用户注册页面实现类 |
| DbHelper.java           | 数据库连接类       |
| HeartActivity.java      | 我的收藏页面实现类 |
| MainActivity.java           | 登录页面实现类     |
| OneActivity.java            | 推荐页面实现类     |
| QueryMeActivity.java        | 我的发布页面实现类 |
| Save.java                   | 传值实现类         |
| SplashActivity.java         | 开屏等待实现类     |
| ThreeActivity.java          | 我的页面实现类     |
| TwoActivity.java            | 随机页面实现类     |
| UpdateContentsActivity.java | 更新内容实现类     |



Layout包：

| 文件名               | 说明                         |
|----------------------|------------------------------|
| activity_1.xml       | 推荐页面布局文件             |
| activity_2.xml       | 随机页面布局文件             |
| activity_3.xml       | 我的页面布局文件             |
| activity_main.xml    | 登录页面布局文件             |
| activity_queryme.xml | 我的发布页面布局文件         |
| activity_splash.xml  | 开屏等待页面布局文件         |
| addcontents.xml      | 新增发布页面布局文件         |
| adduser.xml          | 注册页面布局文件             |
| gridxml.xml          | 网格视图布局文件             |
| heart.xml            | 我的收藏页面布局文件         |
| list_item.xml        | 推荐页面卡片视图布局文件     |
| list_meitem.xml      | 我的发布页面卡片视图布局文件 |
| list_suijiitem.xml   | 随机页面卡片视图布局文件     |
| updatecontenes.xml   | 更新内容页面布局文件         |



### 运行实例

![](https://s2.loli.net/2022/09/29/bWvqsm1ER9nSINj.png)

![](https://s2.loli.net/2022/09/29/MtO7RU4XBqwlQfF.png)

![](https://s2.loli.net/2022/09/29/4U5DMsbex2YznJF.png)

![](https://s2.loli.net/2022/09/29/BIM4CHNus3TALly.png)

![](https://s2.loli.net/2022/09/29/MSEpOHzGI3u4bil.png)

![](https://s2.loli.net/2022/09/29/Ih32HVZenrlQzpX.png)

![](https://s2.loli.net/2022/09/29/j6u3IhSJaENtU1f.png)

![](https://s2.loli.net/2022/09/29/HcPIfrV4Gvbga6T.png)

![](https://s2.loli.net/2022/09/29/hjN5ugBxnWkcQ6d.png)

