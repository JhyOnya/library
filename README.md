### 置顶_问题
#### Seat类getSeatTime函数需要完善_姜弘扬
<br> 给定一个vip姓名和一个时间，计算vip还有多久下课
<br> getSeatTime(String name, String askTime)
<br> 输入String时间，"2019年04月25日 10:31:28"
<br> 输出String剩余时间，输出什么我的“剩余时间：”后面就加什么
#### 学生信息表的设定_王秀月
<br> 关于学生信息 -给学生类信息设定为[学号 姓名 ] 其中学号设定为9位数字，前7位为班级号eg.1730704，后2位为学生在班级内的序号eg.01；173070401为金融学1730704班1号。 
<br> 建议学生相关操作，使用学生学号作为参数，查询其班级、课表、是否为vip等信息。
#### vip设定Seat.init_姜弘扬
<br> 只需要把vip信息用下面两个函数添加即可，参数：int 教室，int 座位，String 姓名。无返回
<br> //添加time时没有课的VIP
<br> addUsingVIP(1, 6, "va");
<br> //添加time时有课的VIP
<br> addEmptyVIP(2, 4, "vc");
<br> 参数说明：
<br> (String类型)time时 没有课的VIP : UsingVIP
<br> (String类型)time时  有课的VIP  : EmptyVIP

### 如果有问题就在上面写，这个就置顶了，其他日志进度更新从下面开始↓
### 0507更改——成一铭
<br>1、excelOperate->Course.java ，将"空"更改为null
<br>2、excelOperate->readFile.java ，第18行static String sourceFile = "课程总表.xls"; 前面添加了public便于调用
<br>3、timeOperate->getTime.java，添加了nameToClass字典，表示<vip姓名，vip班级>。计算剩余时间的接口是calRemainTime(String name,String askTime)
<br>4、excelOperate->Course.java可能打开时候会乱码，得用utf-8打开才行，因为其中有中文，如果乱码会导致没法正常读，参考链接：https://blog.csdn.net/llqqxf/article/details/79358723


### 0505更改，姜弘扬
<br>1、由于UI界面不关心班级问题，所以姓名班级的map请写到自己的部分
<br>2、Seat.java中的init()仅做测试，请提供用户列表信息的调用接口，届时再做修改
### 0504更改，成一铭
<br>1、建立了一个新的map，key是vip的名字，value是vip的班级
<br>2、将Seat.init中的usingVip以及EmptyVip合并起来，都是vip，去掉了参数String time。
<br>3、删除了msgChange.java中关于EmptyVip的相关代码，因为系统是随时刷新的，所以不用管添加时vip有没有课，运行之后，马上刷新
<br>4、可以修改Seat.java中的init()添加会员
<br>5、修改mainFrame.java中的当前时间可以进行任意时间的测试
<br>6、修改getTime.java中的baseTime(真实时间必须是周一)，便于测试
### 0426关于进度_姜弘扬
<br> 删除Message类
<br> 为了导出jar包时图片仍能显示，对图片存放进行修改
<br> Seat类我放到package frameOperate中了
<br> Course类感觉用不到了
<br> 交互函数框架给好了，我的界面交互只需要Seat.init
<br> 用户添加的操作我都写完了，我现在只需要完善Seat.init函数，和Seat.getSeatTime函数

### 0426修改Seat_姜弘扬
<br> 修改seat类的部分函数
<br> Seat类的Map<Integer, Map<Integer, String>> seats
<br> 只存储当前时间下的座位信息，选定时间下的座位信息只查询vip的信息，即vipSeats

### 0425关于进度_姜弘扬
UI界面部分功能基本完成，优化也已结束
<br> 等待Seat类getSeatTime函数中 vip座位的剩余可用时间函数的完善
<br> 其他未完善的函数不是我直接使用的，但也都是必不可少的，还有些类可能需要自行补充

### 0425关于时间_姜弘扬
注意到一个问题，由于我们不止要通过当前时间获取上课信息，还需要通过指定时间获取上课信息
<br> 所以我们需要一个函数，提供一个时间后，将所有相关计算函数全部调用，即重新计算所有信息
<br> 可以把我目前测试用的init作为这个函数，然后大家进行补充
<br> 另，我把readme所有信息都倒过来写了，这样最新的信息就在最上面，不用往下滑了

### 0424修改Message_姜弘扬
依旧在改UI，顺带根据需求增加交互类方法需求
<br>新增getroomSeat方法，获取当前房间座位情况

##### 新建getSeatTime方法，请时间部分帮忙进行补充
传入：String 姓名 
<br> 返回：String "小时:分:秒"，剩余可使用时间

### 0424关于修改程序_姜弘扬
现在有四个类，其中三个类是一人负责一部分，请不要更改其他人的部分 
<br> 另一个Message类中，如果有做修改，请务必写在readme，防止出现问题
<br> 另一个Message类中，如果有做修改，请务必写在readme，防止出现问题

### 0424关于学生信息表的设定_王秀月

<br> 关于学生信息 -给学生类信息设定为[学号 姓名 ] 其中学号设定为9位数字，前7位为班级号eg.1730704，后2位为学生在班级内的序号eg.01；173070401为金融学1730704班1号。 
<br> 建议学生相关操作，使用学生学号作为参数，查询其班级、课表、是否为vip等信息。

### 0424关于界面_姜弘扬
1、Message-Seat类
<br> selectSeat 选择座位 传入：教室，座位号 
<br> addVIP 新建VIP 返回：教室，座位号，姓名
<br> 2、少考虑了学生相关的操作，需要认领一下任务
<br> 3、需要Message中添加一个函数isVIP，为继续编写界面，函数默认返回值已写好，需要补充重写
<br> 传入：String 姓名 
<br> 返回：Map<Integer, Integer> 如果是vip，返回教室，座位号；如果不是，返回0,0

### 0423关于时间_成一铭
<br> 1、以第一周第一天为基准，首先计算本周属于第几周
<br> 2、查看班级
<br> 3、计算班级+本周具体时间对应的课表情况

### 0423课程总表内容整理_王秀月：

<br>excel表格纵向内容概括：
<br>专业名 班级代码
<br>课程名 授课教师 授课地点 授课班级代码-授课班级代码（人数） [a-b周上课]  [a-b节上课]

<br>excel表格横向内容概括：
<br>横向34行为 周x，第n节课
<br>其余横向为 某班级在某个时间需要上什么课

### 0423初步分工想法_姜弘扬：
课程相关，都表格+判断学生身份（是否vip）
<br> 时间相关，判断当前时间（第几周周几第几节课）+计算两个时间的差（可用时间）
<br> 界面相关，信息交互类构建，界面交互+教师座位分配
<br> <br> 各位有什么想法？目前一定有很多没考虑到的东西
<br> 另，建议为ppt准备的截图从现在开始，每次更新截一下图，同步更新在GitHub上
