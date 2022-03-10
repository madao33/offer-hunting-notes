# CSS学习笔记

## 为什么需要css

控制网页外观显示

## css基本分类

### 标签样式表

```css
p{
	color:red;
}
```

### 类样式表

```css
.f20{
    font-size:20px;
}
```

### ID样式表

```css
#p4{
    background-color: pink;
    font-size: 24px;
    font-weight: bolder;
    font-style: italic;
    font-family: "华文彩云";
}
```

### 组合样式

```css
div p{
    color: blue;
}
div .f32{
    font-size: 32px;
    font-family: "黑体";
}
```

## css位置分类

### 内部样式表

被`style`标签包围的范围是CSS环境, 可以写`CSS`代码

```html
<style type="text/css">
    /* 标签样式表*/
    p{
        color:red;
    }
</style>
```

### 嵌入式样式表

写在标签内部，以一个属性存在，优先级高

```html
<p><span style="font-size: 60px; font-weight: bolder;color:yellow">Hello</span></p>
```

### 外部样式表

一般会将`HTML`文件和`CSS`文件分离开来，将`css`代码单独成一个文件

`css\demo01.cs`

```css
#p4{
    background-color: pink;
    font-size: 24px;
    font-weight: bolder;
    font-style: italic;
    font-family: "华文彩云";
}
```

然后在`html`文件中使用

```html
<head>
    <!--  other head settings -->
    <link rel="stylesheet" href="css/demo01.css">
</head>
```

## 盒子模型

```html
<html>
    <head>
        <meta charset="utf-8">
        <style type="text/css">
            #div1{
                width:400px;
                height:400px;
                background-color: greenyellow;
                
                border-width: 1px;
                border-style: solid;
                border-color:blue;
            }

            #div2{
                width:150px;
                height: 150px;
                background-color: darkorange;

                margin-top: 100px;
                margin-left: 100px;

                /* margin: 100px 100px 100px 100px; */
                padding-top:50px;
                padding-left: 50px;
                
            }

            #div3{
                width:100px;
                height: 100px;
                background-color: aquamarine;

                /* margin-top: 50px;
                margin-left: 50px; */

                /* margin: 100px 100px 100px 100px; */
                
            }

            #div4{
                width: 400px;
                height: 400px;
                background-color: greenyellow;
            }

        </style>
    </head>

    <body>
        <div id="div1">
            <div id="div2">
                <div id="div3">&nbsp;</div>
            </div>
        </div>
        
        <div id="div4">&nbsp;</div>

       
    </body>
</html>
```

> 不同的浏览器的显示效果都不同，会存在一些兼容性问题，上述代码在chrome浏览器显示的处于居中位置

## css布局

`position`:

* `absolute `-- 绝对定位 , 需要配合使用 `left `, `top`
* `relative `-- 相对定位 , 一般会和 `float `, `margin `, `padding `.... 一起使用



