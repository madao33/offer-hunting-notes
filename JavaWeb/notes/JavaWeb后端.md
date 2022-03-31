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

## Servlet

* 获取用户(客户端)发给我的数据
* 调用DAO中的方法完成添加功能
* 在控制台打印添加成功

> `DAO`是数据访问层
>
> `Service`是业务层

在IDEA中的操作步骤：

* 新建项目->新建模块
* 在模块中添加web
* 创建artifact-部署包
* 可以通过`<welcome-file-list>`标签进行设置欢迎页，在`tomcat`的`web.xml`中设置，或者在自己项目中的`web.xml`中设置

> 如果遇到`405`问题，表示当前请求的方法不支持

在`WEB-INF/web.xml`中添加路由

```xml
<servlet>
    <servlet-name>Demo01Servlet</servlet-name>
    <servlet-class>com.madao.servlets.Demo01Servlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>Demo01Servlet</servlet-name>
    <url-pattern>/demo01</url-pattern>
</servlet-mapping>
```

### 编码设置

#### POST方式

在表单输入中文之后，设置断点查看获得的数据为乱码

![image-20220331105934510](imgs\image-20220331105934510.png)

在获取`request`参数之前设置其编码

```java
request.setCharacterEncoding("UTF-8");
```

这样解决了乱码问题

![image-20220331110344145](imgs/image-20220331110344145.png)

#### GET

如果是`get`请求发送的中文数据，在`tomcat8`之前转码稍微有点麻烦：

* 需要首先将字符串打散成字节数组
* 然后将字节数组按照设定的编码重新组装成字符串

```java
String fname = request.getParameter("fname");
byte[] bytes = fname.getBytes("ISO-8859-1");
fname = ne wString(bytes, "UTF-8");
```

> 目前大多数使用的都是`tomcat8`，不用进行以上的操作

### Servlet的继承关系

* `javax.servlet.Servlet`接口
* `javax.servlet.GenericServlet`抽象类
* `javax.servlet.http.HttpServlet`抽象子类

相关方法

`javax.servlet.Servlet`接口：

* `void init(config)` 初始化方法
* `void service(request, response)` 服务方法
* `void destory()` 销毁方法

`javax.servlet.GenericServlet`抽象类

* `void service(request, response)` 仍然是抽象的

`javax.servlet.http.HttpServlet` 抽象子类

```java
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        long lastModified;
        if (method.equals("GET")) {
            lastModified = this.getLastModified(req);
            if (lastModified == -1L) {
                this.doGet(req, resp);
            } else {
                long ifModifiedSince;
                try {
                    ifModifiedSince = req.getDateHeader("If-Modified-Since");
                } catch (IllegalArgumentException var9) {
                    ifModifiedSince = -1L;
                }

                if (ifModifiedSince < lastModified / 1000L * 1000L) {
                    this.maybeSetLastModified(resp, lastModified);
                    this.doGet(req, resp);
                } else {
                    resp.setStatus(304);
                }
            }
        } else if (method.equals("HEAD")) {
            lastModified = this.getLastModified(req);
            this.maybeSetLastModified(resp, lastModified);
            this.doHead(req, resp);
        } else if (method.equals("POST")) {
            this.doPost(req, resp);
        } else if (method.equals("PUT")) {
            this.doPut(req, resp);
        } else if (method.equals("DELETE")) {
            this.doDelete(req, resp);
        } else if (method.equals("OPTIONS")) {
            this.doOptions(req, resp);
        } else if (method.equals("TRACE")) {
            this.doTrace(req, resp);
        } else {
            String errMsg = lStrings.getString("http.method_not_implemented");
            Object[] errArgs = new Object[]{method};
            errMsg = MessageFormat.format(errMsg, errArgs);
            resp.sendError(501, errMsg);
        }

    }
```

* `void service(request, response)` 不是抽象的
  * `String method = req.getMethod();` 获取请求的方式
  * 各种`if`判断，根据请求方式不同，决定去调用不同的`do`方法
  * 在`HttpServlet`这个抽象类中，`do`方法都差不多

> * 继承关系：`HttpServlet`->`GenericServlet`->`Servlet`
>
> * `Servlet`中的核心方法：`init()`，`service()`，`destroy()`
>
> * 服务方法：当有请求过来时，`service`方法会自动响应，其实是`tomcat`容器调用的
>
>   * 在`HttpServlet`中我们会分析请求的方式：到底是`get`、`post`、`head`还是`delete`等等
>
>   * 然后再决定调用时哪个`do`开头的方法
>   * 那么在`HttpSevlet`中这些`do`方法默认都是`405`的实现风格，需要我们子类去实现对应的方法
>
> * 因此，我们在新建`Servlet`时，我们才会考虑请求方法，从而决定重写哪个`do`方法

![image-20220331154139976](imgs/image-20220331154139976.png)

### Servlet的生命周期

生命周期：从出生到死亡的过程，对应`Servlet`三个方法:`init()`，`service()`，`destroy()`

默认情况下：

* 第一次接受请求时，`Servlet`会进行实例化（调用构造方法，使用反射进行实例化）、初始化（调用`init()`）、然后服务（调用`service()`）
* 第二次请求开始，每一次都是服务
* 当容器关闭时，其中的所有`servlet`实例会被销毁，调用销毁方法

通过案例发现

* `Servlet`实例只会创建一个，其他的请求都是这个实例去响应
* 默认情况下，第一次请求时，`tomcat`才会去实例化，初始化，然后再服务

> * 这样的好处主要是，提高系统的启动速度
> * 缺点是：第一次请求时，启动速度较慢

`Servlet`的初始化时机：

* 默认是第一次初始化、实例化
* 我们可以通过`<load-on-startup>`来设置`Servlet`的启动顺序，数字越小，启动顺序越靠前，最小值0

```xml
<servlet>
    <servlet-name>Demo02Servlet</servlet-name>
    <servlet-class>com.madao.servlets.Demo02Servlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
```

`Servlet`在容器中是：单例的、线程不安全的

* 单例：所有的请求都是同一个实例去响应
* 线程不安全：一个线程需要根据这个实例中的某个成员变量值去做逻辑判断，但是在中间某个时机，另一个线程改变了这个成员变量的值，从而导致第一个线程的执行路径发生了变化
* 所以尽量不要在`servlet`中定义成员变量，或者如果定义了成员变量，不要通过成员变量的值去做一些逻辑运算

26-servlet-http协议



































