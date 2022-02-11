# sqlconnect

一个简单的Java连接数据库并查询输出的小程序，首先需要通过`maven`同步`mysql-connector`插件，然后修改`src\main\java\main.java`中数据库连接的设置。

将`user`, `passwd`修改为自己的用户名和密码，之后就可以直接运行了，这里查看的数据库是`mysql`安装自带的`world`数据库

> 推荐使用IDEA打开
> 
> mysql == 8.0.27
> 
> mysql-connector-java == 8.0.27
> 
> 这里需要特别注意`mysql`和`mysql-connector-java`的版本需要一致