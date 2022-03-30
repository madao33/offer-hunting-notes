# JavaWeb后端

## CS和BS架构异同

CS：客户端服务器架构模式

* 优点：充分利用客户端机器的资源，减轻服务器的负荷

  一部分安全要求不高的计算任务存储在客户端执行，不需要把所有的计算和存储都在服务器端执行，从而减轻服务器的压力，也能够减轻网络负荷

* 缺点：需要安装；升级维护成本较高

BS：浏览器服务器架构模式

* 优点：客户端不需要安装，维护成本较低
* 缺点：所有的计算和存储任务都是放在服务器端的，服务器的负荷较重；在服务端计算完成之后再把结果在传输到客户端，因此客户端和服务器端会进行非常繁重的数据通信，从而网络负荷叫较重

## Tomcat

### Tomcat简单配置

首先打开 [Tomcat官网](https://tomcat.apache.org/) 下载对应的版本，将下载的文件解压缩到保存的路径下，记住这个路径`tomcat-path`

然后配置环境变量：

* 新建一个系统变量`CATALINA_HOME`，变量值为之前解压后`Tomcat`文件的路径
* 找到`Path`添加一个`%CATALINA_HOME%\bin`

然后打开`cmd`命令行窗口，输入`startup.bat`，然后打开网页 [localhost:8080](http://127.0.0.1:8080/)

### Tomcat简单介绍

Tomcat叫做 `WebContainer`，相当于是一个容器

目录结构：

* `bin` 可执行文件目录
* `conf `配置文件目录
* `lib `存放lib的目录
* `logs` 日志文件目录
* `webapps` 项目部署的目录
* `work` 工作目录
* `temp` 临时目录

> 配置环境变量，让`tomcat`能够运行，因为`tomcat`也是`java`和`c`来写的，因此需要配置`JAVA_HOME`

**启动`Tomcat`**，打开cmd命令行窗口，输入

```shell
startup.bat
```

### Tomcat部署

手动新建，在`webapps`中新建一个文件夹，将项目相关文件放在该新建文件夹中

### IDEA部署

IDEA部署总是有中文乱码，参考这篇文章解决了 

[【已解决】关于IDEA中 Tomcat 控制台打印日志中文乱码的解决 - 胖琛 - 博客园 (cnblogs.com)](https://www.cnblogs.com/yanglichen/p/11435628.html)

> IDEA部署的位置在当前工程的`out`文件夹下

## AddServlet

* 获取用户(客户端)发给我的数据
* 调用DAO中的方法完成添加功能
* 在控制台打印添加成功

> `DAO`是数据访问层
>
> `Service`是业务层

21
