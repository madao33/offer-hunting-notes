# Maven提示jdk版本不正确

在使用`Maven`管理`Java`项目的时候，执行`validate`或者之后的命令时，出现这个错误提示

```shell
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] 不再支持源选项 5。请使用 7 或更高版本。
[ERROR] 不再支持目标选项 5。请使用 7 或更高版本。
```

这种情况就是配置的`jdk`版本太低，一般是`Maven`设置文件中修改`jdk`版本号，或者在项目中的`pom.xml`文件中修改，只需要修改其中一个就可以了

## 修改`Maven`设置文件

笔者刚开始使用`Maven`尝试管理`Java`项目，而且之后的项目也都是自己使用，这里直接尝试修改`Maven`的`jdk`配置，一劳永逸，找到之前解压的`maven`文件夹，然后找到`apache-maven-3.8.4\conf\settings.xml`文件，通过搜索，找到`<profiles>`

```xml
<profiles>
    
<!-- 其他的设置... -->
    
<!-- 指定jdk版本为1.8 -->
    <profile>
      <id>jdk-1.8</id>
 
      <activation>
		<activeByDefault>true</activeByDefault>
        <jdk>1.8</jdk>
      </activation>
 
      <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
		<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
      </properties>
    </profile>
    
  </profiles>
```

## 修改项目设置

如果是想对于不同的项目设置不同的`jdk`版本，可以就在项目中进行设置，这里也记录一下这种方法

打开项目文件根目录的`pom.xml`文件，然后在其中添加

```xml
<project>
    <!-- 其他设置等 -->
    
	<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
	</properties>
</project>
```

