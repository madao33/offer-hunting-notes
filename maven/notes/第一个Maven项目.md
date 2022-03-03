# 第一个Maven项目

## 创建Mavan项目

可以直接通过Idea创建一个仓库，修改项目中的依赖

> 关于Idea如何创建项目工程就不赘述了，博客有很多类似的教程

项目的文件树如下所示：

```shell
C:.
│  pom.xml
│
├─.idea
│      .gitignore
│      compiler.xml
│      jarRepositories.xml
│      misc.xml
│      vcs.xml
│      workspace.xml
│
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─itheima
    │  │              Demo.java
    │  │
    │  └─resources
    └─test
        ├─java
        │  └─com
        │      └─itheima
        │              DemoTest.java
        │
        └─resources
```

当然，为了更好地理解并且方便后续在服务器上使用（主要是为了装逼），可以尝试使用命令行的方式创建

* 创建工程

  ```shell
  mvn archetype:generate 
      -DgroupId={project-packaging}  
      -DartifactId={project-name} 
      -DarchetypeArtifactId=maven-archetype-quickstart
      -DinteractiveMode=false
  ```

* 创建`java`工程

  ```shell
  mvn archetype:generate -DgroupId=com.itheima -DartifactId=maven-test -DarchetypeArtifactId=maven-archetype-quickstart -Dversion=0.0.1-snapshot -DinteractiveMode=false
  ```

* 创建`web`工程

  ```shell
  mvn archetype:generate -DgroupId=com.itheima -DartifactId=web-project -DarchetypeArtifactId=maven-archetype-webapp -Dversion=0.0.1-snapshot -DinteractiveMode=false
  ```

## Maven命令

* 编译

  ```shell
  mvn compile
  ```

  编译之后会在根目录生成一个`target`文件夹，如果编译的结果不想要，可以执行

* 清除

  ```shell
  mvn clean
  ```

* 测试

  ```shell
  mvn test
  ```

  `test`命令运行之后，在根目录的文件路径`target\surefire-reports\com.xxxx.xxx.txt`文件中有测试结果，同一目录下另一个文件夹的文件`.xml`是详细的测试结果

* 打包

  ```shell
  mvn package
  ```

  首先进行编译，测试，然后是打包

* 安装

  ```shell
  mvn install
  ```

  将打包之后的包放在仓库中

> 在运行编译命令的时候遇到了这个提示信息
>
> ```shell
> [ERROR] COMPILATION ERROR :
> [ERROR] 不再支持源选项 6。请使用 7 或更高版本。
> [ERROR] 不再支持目标选项 6。请使用 7 或更高版本。
> [INFO] 2 errors
> [INFO] -------------------------------------------------------------
> [INFO] ------------------------------------------------------------------------
> [INFO] BUILD FAILURE
> [INFO] ------------------------------------------------------------------------
> [INFO] Total time:  0.665 s
> [INFO] Finished at: 2022-03-03T14:41:49+08:00
> [INFO] ------------------------------------------------------------------------
> [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project java01: Compilation failure: Compilation failure:
> [ERROR] 不再支持源选项 6。请使用 7 或更高版本。
> [ERROR] 不再支持目标选项 6。请使用 7 或更高版本。
> [ERROR] -> [Help 1]
> [ERROR]
> [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
> [ERROR] Re-run Maven using the -X switch to enable full debug logging.
> [ERROR]
> [ERROR] For more information about the errors and possible solutions, please read the following articles:
> [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
> PS C:\Users\Administrator\Documents\codes\notes\java-notes\maven\codes\maven-project\java01> mvn compile
> [INFO] Scanning for projects...
> 
> ```
>
> 可以在`pom.xml`中添加`mavan-compiler-plugin`，以下是`pom.xml`文件的示例，其中`finalName`指定的是项目打包之后的名称，可以修改为自己项目名
>
> ```xml
> <build>
>     <finalName>java01</finalName>
>     
>         <plugins>
>             <plugin>
>                 <groupId>org.apache.maven.plugins</groupId>
>                 <artifactId>maven-compiler-plugin</artifactId>
>                 <configuration>
>                     <source>1.7</source>
>                     <target>1.7</target>
>                 </configuration>
>             </plugin>
>         </plugins>
>         
>     </build>
> ```
>
> 修改之后，再次`mvn compile`，编译成功

## 