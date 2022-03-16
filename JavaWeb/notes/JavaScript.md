# JavaScript笔记

## JavaScript简介

`Javascript `语言诞生主要是完成页面的数据验证。因此它运行在客户端，需要运行浏览器来解析执行 `JavaScript `代码

> `JS `是弱类型，`Java `是强类型
>
> 也就是`JS`类型可变

JS特点：

1. 交互性（它可以做的就是信息的动态交互）
2. 安全性（不允许直接访问本地硬盘）
3. 跨平台性（只要是可以解释 JS 的浏览器都可以执行，和平台无关）

## JS和HTML代码结合方式

### 直接在body标签

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            alert("Hello, javascript");
        </script>
    </head>
    <body>

    </body>
</html>
```

### 单独文件

`html`文件

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript" src="js/1.js">
        </script>
    </head>
    <body>

    </body>
</html>
```

`js`文件

```javascript
alert("Hello, javascript");
```

> 现在需要使用 script 引入外部的 js 文件来执行
>
> src 属性专门用来引入 js 文件路径（可以是相对路径，也可以是绝对路径）
>
> script 标签可以用来定义 js 代码，也可以用来引入 js 文件
>
> 但是，两个功能二选一使用。不能同时使用两个功能

## 变量

* 数值类型：`number`
* 字符串类型：`string`
* 对象类型：`object`
* 布尔类型：`boolean`
* 函数类型：`function`

`JavaScript `里特殊的值：

* `undefined`未定义，所有 js 变量未赋于初始值的时候，默认值都是 `undefined`.
* `null`空值
* `NaN`全称是：Not a Number。非数字。非数值。

JS 中的定义变量格式：

```javascript
var 变量名;
var 变量名 = 值;
```

`typeof()`是`JavaScript`语言提供的一个函数

测试代码

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            var i;
            // alert(i);

            i = 12;
            
            i = "abc";
            alert(typeof(i));
            
            var a = 12;
            var b = "abc";

            alert(a * b);
        </script>
    </head>
</html>
```

> `NaN`是非数字，非数值

## 关系运算

* `==`等于是简单的做字面值的比较
* `===`除了做字面值的比较之外，还会比较两个变量的数据类型

示例

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            var a = "12";
            var b = 12;

            alert( a == b); // true
            alert( a === b); // false
        </script>
    </head>
<body>

</body>
</html>
```

## 逻辑运算

且运算：`&&`
或运算：`||`
取反运算：`!`

在 `JavaScript `语言中，所有的变量，都可以做为一个 `boolean `类型的变量去使用

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            var a = 0;
            if (a) {
                alert("零为真");
            } else {
                alert("零为假");
            }

            var c = undefined; 
            if (c) {
                alert("null为真");
            } else {
                alert("null为假");
            }

            var d = ""; 
            if (c) {
                alert("d为真");
            } else {
                alert("d为假");
            }
        </script>
    </head>
<body>

</body>
</html>
```

> `0` 、`null`、 `undefined`、`“”`(空串) 都认为是 `false`；

`&&` 且运算。
有两种情况：

* 第一种：当表达式全为真的时候。返回最后一个表达式的值。
* 第二种：当表达式中，有一个为假的时候。返回第一个为假的表达式的值

`||`或运算

* 第一种情况：当表达式全为假时，返回最后一个表达式的值
* 第二种情况：只要有一个表达式为真。就会把回第一个为真的表达式的值

并且 `&& `与运算和` ||`或运算有短路。
短路就是说，当这个`&&`或`||`运算有结果了之后 。后面的表达式不再执行

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            
            var a = "abc";
            var b = true;
            var d = false;
            var c = null;

            alert( a && b );
            alert( b && a );
            alert(a && d );
            alert( a && c );
            alert(a && d && c ); 

            alert( d || c);
            alert( c || d);
            alert(a || c);
            alert(b || c);

        </script>
    </head>
<body>

</body>
</html>
```

## 数组

JS 中 数组的定义：

```javascript
var 数组名 = [];// 空数组
var 数组名 = [1 , ’abc’ , true];// 定义数组同时赋值元素
```

`javascript`语言中的数组，只要我们通过数组下标赋值，就会自动给数组做扩容操作

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            var arr = [];
            // alert( arr.length );
            arr[0] = 12;
            alert(arr.length);

            arr[2] = "abc";
            alert(arr.length);

            for (var i = 0; i < arr.length; i++){
                alert(arr[i]);
            }

        </script>
    </head>

    <body>

    </body>
</html>
```

## 函数定义

### function关键字

使用的格式如下:

```javascript
function 函数名(形参列表){
	函数体
}
```

> 在`javascript`中返回值，直接使用`return`返回值即可

示例代码

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            function fun(){
                alert("无参函数fun()被调用了");
            }
            fun();

            function fun2(a, b) {
                alert("有参函数fun2()被调用了a=" + a + "b=" + b);
            }
            fun2(2, 1);

            function sum(num1, num2) {
                var result = num1 + num2;
                return result;
            }

            alert(sum(100, 50))

        </script>
    </head>
    <body>

    </body>
</html>
```

另一种定义方式是

```javascript
var 函数名 = function(形参列表) {
	函数体
	}
```

示例代码

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <script type="text/javascript">
            var fun = function() {
                alert("无参函数");
            }
            fun();

            var fun2 = function(a, b) {
                alert("有参函数a=" + a + ", b=" + b);
            }
            fun2(1, 2);

            var fun3 = function(num1, num2) {
                return num1 + num2;
            }
            alert(fun3(100, 200));

        </script>
    </head>
    <body>

    </body>
</html>
```

> `javascript`不允许函数重载，会导致函数的覆盖

### 函数中的`arguments`隐形参数

在`function`函数中不需要定义，但却可以直接用来获取所有参数的变量，称作是隐形参数

隐形参数类似于`java`基础的可变长参数一样

```java
public void fun(Object ... args) { ... };
```

可变长参数其实是一个数组

`javascript`中的隐形参数也跟`java`中类似，也是一个数组

```javascript
function sum() {
    var result = 0;
    for (var i = 0; i < arguments.length; i++) {
        if (typeof(arguments[i])=="number")
            result += arguments[i];
    }
    return result;
}

alert(sum(1, 2, 3, "abc", 5, 6, 7, 8, 9));
```

## 自定义对象

### Object形式自定义对象

```javascript
var 变量名 = new Object();
变量名.属性名 = 值;
变量名.函数名 = function(){}

对象的访问：
	变量名.属性名 / 函数名
```

例如下面的代码

```javascript
var obj = new Object();
obj.name = "madao33";
obj.age = 24;
obj.fun = function() {
    alert("姓名：" + this.name + "，年龄：" + this.age);
}

obj.fun();
```

### `{}`花括号形式的自定义对象

```javascript
var 变量名 = {
    属性名 : 值,
    属性名 : 值,
    函数名 : function(){}
};
```

示例代码

```javascript
var obj = {
    name : "madao33",
    age : 24,
    fun : function() {
        alert("姓名： " + this.name + " 年龄：" + this.age);
    }
};

obj.fun();
```

## JavaScript事件

电脑输入设备与页面进行交互的响应

### 常用事件

* `onload `加载完成事件：页面加载完成之后，常用于做页面 js 代码初始化操作
* `onclick `单击事件：常用于按钮的点击响应操作。
* `onblur `失去焦点事件：常用用于输入框失去焦点后验证其输入内容是否合法。
* `onchange `内容发生改变事件：常用于下拉列表和输入框内容发生改变后操作
* `onsubmit `表单提交事件：常用于表单提交前，验证所有表单项是否合法。

事件注册：其实就是告诉浏览器，当事件响应后要执行哪些操作代码，叫事件注册或事件绑定，事件的注册又分为静态注册和动态注册两种:

* 静态注册事件：通过 html 标签的事件属性直接赋于事件响应后的代码，这种方式我们叫静态注册
* 动态注册事件：是指先通过 js 代码得到标签的 `dom `对象，然后再通过 dom 对象.事件名 = function(){} 这种形式赋于事件响应后的代码，叫动态注册。动态注册基本步骤：
  * 1、获取标签对象
  * 2、标签对象.事件名 = fucntion(){}

`onload`示例

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript">
        function onloadFun() {
            alert('静态注册onload事件，所有代码');
        }

        window.onload = function() {
            alert("动态注册onload事件");
        }
    </script>
</head>


<body>

</body>
</html>
```

`onclick`事件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript">
        function onclickFun() {
            alert("静态注册onclick事件");
        }

        window.onload = function() {
            /*
            * document是JavaScript语言提供的一个对象
            * getElementById 通过Id获取html中元素标签
            */
            var btnobj = document.getElementById("btn01");
            btnobj.onclick = function() {
                alert("动态注册的onclick事件");
            }
        }
    </script>
</head>

<body>
    <button onclick="onclickFun();">按钮1</button>
    <button id="btn01">按钮2</button>
</body>
</html>
```

