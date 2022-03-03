# Maven依赖

## 依赖配置

```xml
<dependencies>
    
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.12</version>
    </dependency>
   <!--...-->
</dependencies>
```

* 依赖传递
  * 直接依赖：在当前项目中通过依赖配置建立的依赖关系
  * 间接依赖：被依赖的资源如果依赖其他资源，当前项目间接依赖其他资源

> 对于`Maven`项目，可能会出现不同版本的依赖，这种情况叫做**依赖传递冲突问题**

## 依赖传递冲突问题

* **路径优先**：当依赖中出现相同的资源时，层级越深，优先级越低，**层级越浅，优先级越高**
* **声明优先**：当资源在相同层级被依赖时，配置顺序靠前的覆盖配置顺序靠后的
* **特殊优先**：当同级配置了相同资源的不同版本，后配置的覆盖先配置的

## 可选依赖

对外隐藏当前所依赖的资源

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <optional>true</optional>
</dependency>
```

## 排除依赖

主动断开依赖的资源，被排除的资源无需指定版本

```xml
 <dependency>
     <groupId>com.itheima</groupId>
     <artifactId>project03</artifactId>
     <version>1.0-SNAPSHOT</version>
     
     <exclusions>
         <exclusion>
             <groupId>log4j</groupId>
             <artifactId>log4j</artifactId>
         </exclusion>
     </exclusions>
</dependency>
```

## 依赖范围

依赖的`jar`默认情况可以在任何地方使用，可以通过`scope`标签设定作用范围

作用范围

* 主程序范围有限`main`文件夹范围内
* 测试程序范围有效 `test`文件夹范围内
* 是否参与打包 `package`指令范围内

![](img\scope.png)

## 依赖范围传递性

带有传递范围的资源在进行传递时，作用范围将受到影响

![](img/scope2.png)



