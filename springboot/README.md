# SprintBoot学习笔记 

## 基础知识

###  创建SpringBoot项目

可以直接通过IDEA软件创建，也可以通过vs code创建，IDEA方式创建的过程就不赘述了，通过vs code创建需要安装插件，在vs code插件搜索`Extension Pack for Java`，安装之后，按下快捷键`ctrl+shift+p`，然后输入`Sprng Initializer: Create a Maven Project`，一步步的输入项目配置，就可以了

如果之后网络被屏蔽的话，可以调整`Spring boot`的下载链接为

```http
http://start.aliyun.com
```

### 隐藏文件

可以在`setting->Editior->File Types`中的`Ignored Files and Folders`中添加对应文件匹配项，隐藏掉不想看到的文件

###  pom文件

每一次 Spring Boot 发行都提供了一个它所支持的依赖清单。实际上，您不需要为构建配置提供任何依赖的版本，因为 Spring Boot 已经帮您管理这些了。当您升级 Spring Boot 时，这些依赖也将以一致的方式进行升级。

继承`parent`模块可以避免多个依赖使用相同技术出现的依赖版本冲突，也可以使用引入依赖

`Maven `用户可以继承 `spring-boot-starter-parent` 项目以获取合适的默认值

#### 继承Starter Parent

将项目中配置继承`spring-boot-starter-parent`，只需要按以下设置`parent`

```xml
<!-- 从 Spring Boot 继承默认配置 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.4</version>
</parent>
```

> 您只需要在此依赖上指定 Spring Boot 的版本号。如果您要导入其它 `starter`，则可以放心地省略版本号。您只需要在此依赖上指定 Spring Boot 的版本号。如果您要导入其它 `starter`，则可以放心地省略版本号。

#### 使用Sprint Boot Maven插件

Spring Boot 包括了一个 [Maven 插件](http://felord.cn/_doc/_springboot/2.1.5.RELEASE/_book/pages/using-spring-boot.html#build-tool-plugins-maven-plugin)，它可以将项目打包成一个可执行 jar。如果要使用它，请将插件添加到您的 `<plugins>` 中：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build
```

### REST风格

#### Rest简介

REST(Representational State Transfer) 表现形式状态转换

```http
http://localhost/users
http://localhost/users/1
http://localhost/user/1
http://localhost/user
```

> * 隐藏资源的访问行为，无法通过地址得到行为
> * 书写更加简介

### 配置文件

配置文件在`src/main/resources/`文件夹下，分别有

* `application.properties`
* `application.yaml`
* `application.yml`

> 目前主流是`yml`，如果三者都配置了，`properties`起主导作用，然后是`yml`，最后才是`ymal`

#### ymal格式

YAML（YMAL Ain’t Markup Language），一种数据序列化格式，以数据为核心，重数据轻格式

语法规则：

* 大小写敏感
* 属性层级关系使用多行描述，每行结尾使用冒号结束
* 使用缩进表示层级关系，同层级左侧对齐，只允许使用空格，不允许使用Tab键
* **属性值前面添加空格**（属性名与属性值之间使用冒号+空格作为分隔）
* `#`表示注释

```yaml
server:
  port: 81


country: china
province: beijing
city: beijing
area: haidian

port: 8080
party: true
birthdate: 1949-10-01

user:
  name: madao33
  age: 24

user2:
  name: theXun
  age: 25

a:
  b:
    c:
      d:
        e: 123

likes:
  - game
  - music
  - sleep

likes2: [game,music,sleep]

users:
  - name: zhangsan
    age: 18
  - name: lisi
    age: 17

users2:
  _
    name: zhangsan
    age: 18

  -
    name: lisi
    age: 17

users3: [{name:zhangsan,age:18},{name:lisi,age:17}]
```

读取`yaml`中的单一数据

```java
import org.springframework.beans.factory.annotation.Value;

@Value("${country}")
private String country1;
```

process 20
