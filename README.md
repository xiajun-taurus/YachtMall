# YachtMall
游艇销售系统，包含商城和后台管理

# 需求分析

## 1.角色

管理员、企业会员、个人会员。

1. 管理员：负责审核游艇产品，对企业会员要发布的产品信息进行审核
2. 企业会员：可以申请发布产品，填写相关信息后由管理员审核；还可以发布合作信息
3. 个人会员：可以购买、租赁游艇，还可以发布求购、求租信息

## 2.界面原型

首页界面上有：

1. 游艇列表
2. 登录、注册
3. 游艇配件列表
   1. 购买
   2. 租艇
4. 游艇服务
   1. 求购（文字信息）
   2. 求租 （文字信息）
   3. 合作（文字信息）

## 3.界面逻辑

### 3.1售卖平台

企业和个人若不登录只能搜索和查看产品信息，没有权限进行交易和查看求购、求租信息。

### 3.2管理平台

管理员可以登录后台对所有用户进行管理

1. 限制用户发表信息
2. 审批企业用户出售、出租的商品信息（配件只可出售不能出租）
3. 审批企业用户发表的合作信息

### 3.3购物车

1. 用户在租赁或购买产品后跳转到购物车界面，用户可以在此页面取消订单，付款
2. 用户确定付款后跳转到订单列表页面，此时状态是**等待发货**，商家发货之后才会变成完成订单状态

# 表结构设计

id一律使用uuid32位，用户名、密码varchar（20）

用户表——tb_user

| 字段名         | 类型     | 含义         | 值（例子）                          |
| -------------- | -------- | ------------ | ----------------------------------- |
| **id**         | varchar  | id           | uuid字符串                          |
| usr            | varchar  | 用户名       | admin                               |
| pwd            | varchar  | 密码         | 000000                              |
| gender         | int      | 性别         | 1男 2女                             |
| name           | varchar  | 姓名         | 张三                                |
| enable_status  | int      | 是否可用     | 0 不可用 1可用                      |
| user_type      | int      | 用户角色     | 1.顾客 2.企业 3.管理员 4.超级管理员 |
| create_time    | datetime | 创建时间     |                                     |
| last_edit_time | datetime | 最后修改时间 |                                     |

收货地址表——tb_addr

| 字段名         | 类型       | 含义                       | 值（例子）          |
| -------------- | ---------- | -------------------------- | ------------------- |
| id             | varchar    | 地址id                     | uuid字符串          |
| addressee      | varchar    | 收件人                     | 张三                |
| addr           | varchar    | 收货地址                   | 北京市北京大学***号 |
| phone          | char（11） | 收货人手机号               | 13000000000         |
| user_id        | varchar    | 该地址对应的用户  **外键** | 外键，关联tb_user   |
| enable_status  | int        | 地址可否使用               | 0不可用1可用        |
| create_time    | datetime   | 创建时间                   |                     |
| last_edit_time | datetime   | 最后修改时间               |                     |

​     购物车表——tb_cart

| 字段名         | 类型     | 含义         | 值（例子）           |
| -------------- | -------- | ------------ | -------------------- |
| id             | varchar  |              |                      |
| user_id        | varchar  | 所属用户     | 外键，关联tb_user    |
| product_id     | varchar  | 商品id       | 外键，关联tb_product |
| unit_price     | decimal  | 单价         |                      |
| number         | int      | 件数         |                      |
| total_price    | decimal  | 总价         |                      |
| enable_status  | int      | 是否可用     |                      |
| create_time    | datetime | 创建时间     |                      |
| Last_edit_time | datetime | 上次修改时间 |                      |

商品信息表——tb_product

| 字段名         | 类型     | 含义         | 值（例子）        |
| -------------- | -------- | ------------ | ----------------- |
| id             | varchar  | 商品id       |                   |
| product_name   | varchar  | 商品名称     |                   |
| type           | int      | 类别         | 游艇、零件        |
| description    | varchar  | 说明         |                   |
| img_url        | varchar  | 图片路径     |                   |
| enable_status  | int      | 状态         | 1在售0停售        |
| create_time    | datetime | 创建日期     |                   |
| last_edit_time | datetime | 最后修改时间 |                   |
| shop_id        | varchar  | 所属商店     | 外键，关联tb_shop |

库存表——tb_stock

| 字段名         | 类型     | 含义     | 值（例子）                     |
| -------------- | -------- | -------- | ------------------------------ |
| id             | varchar  | id       |                                |
| product_id     | varchar  | 商品id   | 外键，关联tb_shop              |
| surplus        | int      | 剩余     |                                |
| price          | decimal  | 价格     |                                |
| create_time    | datetime | 创建时间 |                                |
| enable_status  | int      | 状态     | 1可用0不可用，库存为0时状态为0 |
| last_edit_time | datetime | 更新时间 |                                |
|                |          |          |                                |

订单表——tb_order 每个商品id生成一个订单

| 字段名       | 类型     | 含义     | 值（例子）                                                   |
| ------------ | -------- | :------- | ------------------------------------------------------------ |
| id           | varchar  | id       |                                                              |
| user_id      | varchar  | 用户id   | 外键，关联tb_user                                            |
| order_no     | varchar  | 订单编号 | 自动生成的订单号                                             |
| shop_id      | varchar  | 商店id   | 外键，关联tb_shop                                            |
| order_status | int      | 订单状态 | 未付款、已付款、已发货、已签收、取消订单、申请退货、退货中、已退货 |
| product_name | varchar  | 商品名称 | 这里要记录，商品可能被删除                                   |
| count        | int      | 数量     |                                                              |
| total_price  | decimal  | 总价     |                                                              |
| create_time  | datetime | 创建时间 |                                                              |
| pay_time     | datetime | 支付时间 |                                                              |

信息发布表——tb_news

| 字段名         | 类型     | 含义     | 值（例子）        |
| -------------- | -------- | -------- | ----------------- |
| id             | varchar  | id       |                   |
| editer_id      | varchar  | 发布者id | 外键，关联tb_user |
| title          | varchar  | 信息标题 |                   |
| content        | text     | 信息内容 |                   |
| enable_status  | int      | 状态     |                   |
| create_time    | datetime | 创建时间 |                   |
| last_edit_time | datetime | 修改时间 |                   |
| publish_time   | datetime | 发布时间 |                   |
|                |          |          |                   |

商店信息表——tb_shop

| 字段名         | 类型     | 含义     | 值（例子）        |
| -------------- | -------- | -------- | ----------------- |
| id             | varchar  | id       |                   |
| owner          | varchar  | 企业id   | 外键，关联tb_user |
| shop_name      | varchar  | 商店名称 |                   |
| shop_desc      | varchar  | 商店简介 |                   |
| create_time    | datetime | 创建时间 |                   |
| enable_status  | Int      | 店铺状态 |                   |
| last_edit_time | datetime | 修改时间 |                   |
|                |          |          |                   |
|                |          |          |                   |

## 数据逻辑

1. 用户下单，要选择地址。没有地址要创建
2. 企业用户可以新建商铺，仅能创建一次
3. 一般来说，购物车里面的商品价格从库存表获取
4. 加购物车的时候先看有没有相同商品，有则追加数量
5. 购买商品，遍历商品id，相同id生成一个订单