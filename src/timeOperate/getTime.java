package timeOperate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import excelOperate.Course;
import excelOperate.readFile;
import excelOperate.readFile;

public class getTime {
	private static String baseDate = "2019-03-04";
	private static int weekNum = 11;
	private static int dayOfWeek = 6;
	private static String remainTime = null;
	private static String[] timeList = new String[10];
	private static String yMd, Hms;// 年月日，时分秒
	public static Map<String, String> nameToClass = new TreeMap<String, String>();
	public static Map<String, int[]> nameToSeat = new TreeMap<String, int[]>();
	// 创建姓名到班级的词典

	public static void initNameToClass() {
		nameToClass.put("王俊凯", "1610107");
		nameToSeat.put("王俊凯", new int[] { 0, 2 });
		nameToClass.put("王源", "1610108");
		nameToSeat.put("王源", new int[] { 0, 5 });
		nameToClass.put("易烊千玺", "1610109");
		nameToSeat.put("易烊千玺", new int[] { 0, 9 });
		nameToClass.put("白敬亭", "151105");
		nameToSeat.put("白敬亭", new int[] { 1, 2 });
		nameToClass.put("何炅", "151107");
		nameToSeat.put("何炅", new int[] { 2, 1 });
		nameToClass.put("撒贝宁", "151106");
		nameToSeat.put("撒贝宁", new int[] { 3, 4 });
		nameToClass.put("刘昊然", "1640905");
		nameToSeat.put("刘昊然", new int[] { 4, 13 });
		nameToClass.put("李晨", "1640501");
		nameToSeat.put("李晨", new int[] { 5, 18 });
		nameToClass.put("魏大勋", "1820102");
		nameToSeat.put("魏大勋", new int[] { 6, 23 });
		nameToClass.put("杨幂", "1730301");
		nameToSeat.put("杨幂", new int[] { 7, 9 });
		// 对6个vip进行赋值
	}

	public static void calWeekAndDay(String askTime) throws ParseException {
		System.out.println("askTime:"+askTime);
		yMd = askTime.split(" ")[0];
		Hms = askTime.split(" ")[1];
		System.out.println("yMd"+yMd);
		System.out.println("Hms"+Hms);
//		System.out.println(askTime);
//		System.out.println(yMd);
//		System.out.println(Hms);
		yMd = yMd.replace("年", "-");
		yMd = yMd.replace("月", "-");
		yMd = yMd.replace("日", "-");
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = ft.parse(yMd);

		Date date2 = ft.parse(baseDate);

		long days = (date1.getTime() - date2.getTime()) / (24 * 3600 * 1000);

		long intervalDays = days + 1;

		weekNum = (int) Math.ceil(intervalDays / 7.0);
		int countDayOfWeek = (int) (intervalDays % 7);
		dayOfWeek = countDayOfWeek == 0 ? 7 : countDayOfWeek;// 表示周几
	}

	/**
	 * 计算每天真正的上课时间列表
	 * 
	 * @param className
	 * @throws ParseException
	 */
	public static void calTimeList(String name, String askTime) throws ParseException {
		String className = null;
		calWeekAndDay(askTime);
		System.out.println("第几周：" + weekNum);
		System.out.println("周几：" + dayOfWeek);

		if (dayOfWeek == 6 || dayOfWeek == 7)
			return;
		String[] weekList = new String[5];
		Course aCourse = new Course();
		if (nameToClass.containsKey(name)) {
			className = nameToClass.get(name);
		} else {
			System.out.println("没有这个人");
		}
		System.out.println("className:" + className);
		if (readFile.courseMap.containsKey(className)) {
			weekList = readFile.courseMap.get(className);

		}
//		为timeList赋值
		for (int i = 0; i < timeList.length; i++) {
			timeList[i] = null;
		} // 初始化
		if (dayOfWeek > 5 || dayOfWeek < 1)
			return;
		if (contained(weekList[Course.getIndex(dayOfWeek, 1)])) {
			timeList[0] = "08:00:00";
			timeList[1] = "09:40:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 2)])) {
			timeList[2] = "10:00:00";
			timeList[3] = "11:40:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 3)])) {
			timeList[4] = "13:30:00";
			timeList[5] = "15:10:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 4)])) {
			timeList[6] = "15:30:00";
			;
			timeList[7] = "17:10:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 5)])) {
			timeList[8] = "18:00:00";
			;
			timeList[9] = "21:00:00";
		}

	}

	/**
	 * 判断第n周,周m的上课情况，周数——weekNum
	 * 
	 * @param weekList
	 * @return
	 */
	public static boolean contained(String weekList) {
		if (weekList == null)
			return false;
		String[] strarray = weekList.split(",");
		for (int i = 0; i < strarray.length; i++) {
			if (weekNum == Integer.parseInt(strarray[i]))
				return true;
		}
		return false;
	}

	/**
	 * 计算剩余的时间
	 * 
	 * @param askTime
	 * @return
	 * @throws ParseException
	 */
	public static String calRemainTime(String name, String askTime) {
		// UI传入的askTime格式：2019年05月07日 12:01:40
		try {
			calTimeList(name, askTime);

			// 输出验证当天课表
			System.out.println("当天上课时间列表：");
			for (int i = 0; i < 10; i++)
				System.out.println(timeList[i]);

			if (dayOfWeek == 6 || dayOfWeek == 7)
				return "0";
			SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
			Date askT = ft.parse(Hms);
			if (timeList[0] != null && askT.before(ft.parse(timeList[0]))) {
				return "0";
			} else if (timeList[9] != null && ft.parse(timeList[9]).before(askT)) {
				return "0";
			} else {

				for (int i = 1; i < timeList.length; i++) {
					if (timeList[i] != null) {
						Date time2 = ft.parse(timeList[i]);
						if (askT.before(time2)) {// 找到了
							if (i % 2 == 1) {// 正在上课
//								System.out.println(i);
								long diff = time2.getTime() - askT.getTime();
								long hour = (diff / (60 * 60 * 1000));
								long min = ((diff - hour * 60 * 60 * 1000) / (60 * 1000));
								long sec = ((diff - hour * 60 * 60 * 1000 - min * 60 * 1000) / 1000);
								remainTime = hour + ":" + min + ":" + sec;
								return remainTime;
							} else {
								return "0";
							}

						}
					}

				}
				return "0";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return "未知";
		}
	}

	/**
	 * 正常的上课时间： 一(0,1)："08:00:00"-"09:40:00"; 二(2,3)："10:00:00"-"11:40:00";
	 * 三(4,5)："13:30:00"-"15:10:00"; 四(6,7)："15:30:00"-"17:10:00";
	 * 五(8,9)："18:00:00"-"21:00:00";
	 * 
	 */

	public static Date conv(String dateString) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		Date curTime = ft.parse(dateString);
		return curTime;
	}
	public static int[] time() {
		int[] weekandDay=new int[] {weekNum,dayOfWeek};
		return weekandDay;
	}
	// 测试
	public static void main(String[] args) throws ParseException {
		initNameToClass();// 初始化<姓名，班级>
		readFile.readFile(readFile.sourceFile);// 初始化表格数据
		String askTime = "2019年05月17日 15:06:19";
		String name = "王源";
		System.out.println(calRemainTime(name, askTime));

	}

}
