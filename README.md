### 0423初步分工想法_姜弘扬：
课程相关，都表格+判断学生身份（是否vip）
<br> 时间相关，判断当前时间（第几周周几第几节课）+计算两个时间的差（可用时间）
<br> 界面相关，信息交互类构建，界面交互+教师座位分配
<br> <br> 各位有什么想法？目前一定有很多没考虑到的东西
<br> 另，建议为ppt准备的截图从现在开始，每次更新截一下图，同步更新在GitHub上

### 0423课程总表内容整理_王秀月：

<br>excel表格纵向内容概括：
<br>专业名 班级代码
<br>课程名 授课教师 授课地点 授课班级代码-授课班级代码（人数） [a-b周上课]  [a-b节上课]

<br>excel表格横向内容概括：
<br>横向34行为 周x，第n节课
<br>其余横向为 某班级在某个时间需要上什么课


### 0423关于时间_成一铭
<br> 1、以第一周第一天为基准，首先计算本周属于第几周
<br> 2、查看班级
<br> 3、计算班级+本周具体时间对应的课表情况

### 0424关于界面_姜弘扬
1、Message-Seat类
<br> selectSeat 选择座位 传入：教室，座位号 
<br> addVIP 新建VIP 返回：教室，座位号，姓名
<br> 2、少考虑了学生相关的操作，需要认领一下任务
<br> 3、需要Message中添加一个函数isVIP，为继续编写界面，函数默认返回值已写好，需要补充重写
<br> 传入：String 姓名 
<br> 返回：Map<Integer, Integer> 如果是vip，返回教室，座位号；如果不是，返回0,0

### 0424关于学生信息表的设定_王秀月

<br> 关于学生信息 -给学生类信息设定为[学号 姓名 ] 其中学号设定为9位数字，前7位为班级号eg.1730704，后2位为学生在班级内的序号eg.01；173070401为金融学1730704班1号。 
<br> 建议学生相关操作，使用学生学号作为参数，查询其班级、课表、是否为vip等信息。

### 0424关于修改程序_姜弘扬
现在有四个类，其中三个类是一人负责一部分，请不要更改其他人的部分 
<br> 另一个Message类中，如果有做修改，请务必写在readme，防止出现问题
<br> 另一个Message类中，如果有做修改，请务必写在readme，防止出现问题

### 0424修改Message_姜弘扬
依旧在改UI，顺带根据需求增加交互类方法需求
<br>新增getroomSeat方法，获取当前房间座位情况
##### 新建getSeatTime方法，请时间部分帮忙进行补充
传入：String 姓名 
<br> 返回：String "小时:分:秒"，剩余可使用时间

### 0425关于时间_姜弘扬
注意到一个问题，由于我们不止要通过当前时间获取上课信息，还需要通过指定时间获取上课信息
<br> 所以我们需要一个函数，提供一个时间后，将所有相关计算函数全部调用，即重新计算所有信息
