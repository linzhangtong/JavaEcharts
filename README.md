***
最近公司需求要我实现制图的要求，如图所示

![制图要求.png](http://upload-images.jianshu.io/upload_images/3435345-5632205b8ae78130.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***
问了度娘，发现了度娘自己家的echarts好像好评不断，然后找了一下基本Java的demo能跑起来的基本没有，要么太复杂了！所以我写下了这篇文章，这是最详细的了，感觉没有之一！
***
开始本教程:
我们就实现一个小功能吧，查询学生的成绩并且制成图（饼状图和柱形图）。
***
1.创建表（newstudentinfo）
```javascript
CREATE TABLE `newstudentinfo` (
  `id` int(11) NOT NULL COMMENT '学号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `score` decimal(4,2) DEFAULT NULL COMMENT '分数',
  `subject` int(11) DEFAULT NULL COMMENT '科目编号',
  `teacherid` int(11) DEFAULT NULL COMMENT '任课教师编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
插入几条数据，这里我就不插入了，因为数据太杂了，截个图下来。

![newstudentInfo.png](http://upload-images.jianshu.io/upload_images/3435345-4f902d93851858e1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
准备好了我们就开始搭建SSM框架，具体怎么搭建这里不做多解释。可以看下面的demo，也可以去看我之前的文章。
***
现在说下柱形图怎么做！
首先引入官方给的echarts.js。地址：http://echarts.baidu.com/download.html，然后按自己需要下载（我下载的是常用版的）。


![下载JS.png](http://upload-images.jianshu.io/upload_images/3435345-02018095f453aa68.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后引入
```javascript
 <!-- 引入 echarts.js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.common.min.js"></script>
    <!-- 引入jquery.js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<!---最好用<script></script>,不要<script/>-->
```
然后在body里面写个<div>
```javascript
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
```
然后异步请求
```javascript
var names = [];    //类别数组（实际用来盛放X轴坐标值）
    var nums = [];    //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type: "post",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "${pageContext.request.contextPath}/hello",    //请求发送到TestServlet处
        data: {},
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    names.push(result[i].name);    //挨个取出类别并填入类别数组
                }
                for (var i = 0; i < result.length; i++) {
                    nums.push(result[i].score);    //挨个取出销量并填入销量数组
                }
                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {
                        data: names
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '成绩',
                        data: nums
                    }]
                });
            }
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })
```
这里要说一下，因为柱状图他接受的data类型是这种格式的
```javascript
["1","2","3","4","5"];
```
所以我们要构造这种数据，后台返回的就是一个简单的json数据

![后台返回的json.png](http://upload-images.jianshu.io/upload_images/3435345-eb97aa37ebf3a254.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

接下来饼状图跟柱形图差不多，就是改一个属性为pie，还有饼状图接受的数据一定要是这种格式
```javascript
{[value:"1",name:"one"],[value:"2",name:"two"],[value:"3",name:"three"],[value:"4",name:"four"],[value:"5",name:"five"]}
```
接下来看看效果图：
柱形图：

![柱形图.png](http://upload-images.jianshu.io/upload_images/3435345-fda9eabce3fa21cf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***
饼状图：

![饼状图.png](http://upload-images.jianshu.io/upload_images/3435345-5fd448fcdada34a1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

****
最后，假如还是看不懂，没关系，demo在这里，用了maven和ssm的，IDE是用了IDEA，一般导入就能使用了！
喜欢给个star，看桑思密达！
