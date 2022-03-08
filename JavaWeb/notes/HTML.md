# HTML笔记

## 基本标签

>`html`语言是**解释型语言**，不是编译型
>
>不区分大小写

浏览器是容错的

`html`页面中由一对标签组成：`<html></html>`

* `<html>` 称之为 开始标签
* `</html>`称之为 结束标签

### `title `

表示网页的标题

```html
<title>这是我的第一个网页</title>
```

### `<br/>`

表示换行 。`br`标签是一个单标签。单标签：开始标签和结束标签是同一个，斜杠放在单词后面

```html
<p>
    HELLO WORLD!<br/>你好，HTML！
</p>
```

### `img `

图片标签

* `src`属性表示图片文件的路径
* `width`和`height`表示图片的大小
* `alt`表示图片的提示

```html
<img src="imgs\girl.jpg" width="57" height="73" alt="这里是一张图片"/>
```

### `h1`~`h6 `

标题标签

```html
<h1>标题一</h1>
<h2>标题一</h2>
<h3>标题一</h3>
<h4>标题一</h4>
<h5>标题一</h5>
<h6>标题一</h6>
```

### `ol` 

有序列表

```html
<ol type="a" start="3">
    <li>北京</li>
    <li>上海</li>
    <li>天津</li>
    <li>重庆</li>
</ol>
```

`start `表示从*开始，`type` 显示的类型：`A a I i 1`(默认为数字)

### `ul `

无序列表

```html
<ul type="circle">
    <li>章北海</li>
    <li>史强</li>
    <li>罗辑</li>
</ul>
```

type disc(default) , circle , square

### 字体属性

`u `下划线 `b `粗体 ` i `斜体

```html
你是<b><i><u>喜欢</u></i></b>是<b>甜</b>月饼还是<i>咸</i><u>月饼</u>？
```

上标 `sup  `下标 `sub`

```html
水分子的化学式： H<sub>2</sub>O <br/>
氧气的化学式： O<sup>2</sup><br/>
```

HTML中的实体

小于号 `&lt; `大于等于号 `&ge;` 版权 `&copy;`

### `span `

不换行的块标记

```html
<span>赵又廷</span>，夺妻之仇。
```

### 超链接

`a` 表示超链接，`href `链接的地址

`target`:

* ` _self `在本窗口打开
* `_blank`在一个新窗口打开
* `_parent `在父窗口打开
* `_top  `在顶层窗口打开

### `div`分层



## 表格标签table

* 行`tr`
* 列`td`
* 表头列`th`

`table`中有如下属性（虽然已经淘汰，但是最好了解一下）

* `border`：表格边框的粗细
* `width`:表格的宽度
* `cellspacing`：单元格间距
* `cellpadding`：单元格填充

```html
<table border="1" width="600" cellspacing="0" cellpadding="4">
    <tr align="center">
        <th>姓名</th>
        <th>门派</th>
        <th>成名绝技</th>
        <th>内功值</th>
    </tr>
    <tr align="center">
        <td>乔峰</td>
        <td>丐帮</td>
        <td>少林长拳</td>
        <td>5000</td>
    </tr>
    <tr align="center">
        <td>虚竹</td>
        <td>灵鹫宫</td>
        <td>北冥神功</td>
        <td>15000</td>
    </tr>
    <tr align="center">
        <td>扫地僧</td>
        <td>少林寺</td>
        <td>七十二绝技</td>
        <td>未知</td>
    </tr>
</table>
```

表格显示结果

| 姓名   | 门派   | 成名绝技   | 内功值 |
| ------ | ------ | ---------- | ------ |
| 乔峰   | 丐帮   | 少林长拳   | 5000   |
| 虚竹   | 灵鹫宫 | 北冥神功   | 15000  |
| 扫地僧 | 少林寺 | 七十二绝技 | 未知   |

`tr`中有一个属性： `align `-> `center `, `left `, `right` 

`rowspan` : 行合并

`colspan `:  列合并

> 目前`html`表格样式显示一般通过`css`来替代

```html
<table border="1" cellspacing="0" cellpadding="4" width="600">
    <tr>
        <th>名称</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
        <th>操作</th>
    </tr>
    <tr align="center">
        <td>苹果</td>
        <td rowspan="2">5</td>
        <td>20</td>
        <td>100</td>
        <td><img src="imgs/del.jpg" width="24" height="24"/></td>
    </tr>
    <tr align="center">
        <td>菠萝</td>
        <td>15</td>
        <td>45</td>
        <td><img src="imgs/del.jpg" width="24" height="24"/></td>
    </tr>
    <tr align="center">
        <td>西瓜</td>
        <td>6</td>
        <td>6</td>
        <td>36</td>
        <td><img src="imgs/del.jpg" width="24" height="24"/></td>
    </tr>
    <tr align="center">
        <td>总计</td>
        <td colspan="3">181</td>
        <td>/</td>
    </tr>
</table>
```

## 表单标签`form`

* `input type="text" `表示文本框 ， 其中 `name`属性必须要指定，否则这个文本框的数据将来是不会发送给服务器的
* `input type="password" `表示密码框
* `input type="radio"` 表示单选按钮。需要注意的是，`name`属性值保持一致，这样才会有互斥的效果;可以通过`checked`属性设置默认选中的项
* `input type="checkbox" `表示复选框。`name`属性值建议保持一致，这样将来我们服务器端获取值的时候获取的是一个数组
* `select `表示下拉列表。每一个选项是`option`，其中`value`属性是发送给服务器的值 , `selected`表示默认选中的项
* `textarea `表示多行文本框（或者称之为文本域）,它的value值就是开始结束标签之间的内容
* `input type="submit" `表示提交按钮
* `input type="reset" `表示重置按钮
* `input type="button" `表示普通按钮

```html
<form action="demo04.html" method="post">
    昵称：<input type="text" value="请输入你的昵称"/><br/>
    密码：<input type="password" name="pwd"/><br/>
    性别：<input type="radio" name="gender" value="male"/>男
         <input type="radio" name="gender" value="female" checked/>女<br/>
    爱好：<input type="checkbox" name="hobby" value="basketball"/>篮球
         <input type="checkbox" name="hobby" value="football" checked/>足球
         <input type="checkbox" name="hobby" value="earth" checked/>地球<br/>
    星座：<select name="star">
             <option value="1">白羊座</option>
             <option value="2" selected>金牛座</option>
             <option value="3">双子座</option>
             <option value="4">天蝎座</option>
             <option value="5">天秤座</option>
         </select><br/>
    备注：<textarea name="remark" rows="4" cols="50"></textarea><br/>
    <input type="submit" value=" 注 册 "/>
    <input type="reset" value="重置"/>
    <input type="button" value="这是一个普通按钮"/>
</form>
```

## frameset

`frameset`表示页面框架，这个标签已经淘汰，了解一下即可

```html
<html>
	<head></head>
	<frameset rows="20%,*" > <!-- frameborder="no" -->
		<frame src="frames/top.html"/>
		<frameset cols="15%,*">
			<frame src="frames/left.html"/>
			<frameset rows="80%,*">
				<frame src="frames/main.html"/>
				<frame src="frames/bottom.html"/>
			</frameset>
		</frameset>
	</frameset>
</html>
```

`iframe `在一个页面嵌入一个子页面

```html
<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		主页面的内容，下面是嵌入内容
		<iframe src="frames/top.html"/>
	</body>
</html>
```

