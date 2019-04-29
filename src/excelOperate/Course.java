package excelOperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.corba.se.spi.orb.StringPair;
import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.launcher.resources.launcher;

public class Course {

	public static Map<String, Map<Integer, String[]>> courseMap;
	// 专业号 周几 开始节/结束节/上课周 全部是字符串
	public static Map<Integer, String[]> timeMap;
	// 周几 开始节/结束节/上课周 全部是字符串
	public static String[] classWeek = new String[5];
	// 课程时间信息 每周五天 周A中每节的上课周
	public static String majorNum; // 专业号

	public static int weekday; // 周几

	public static void init(List<String> aString) { // 初始化函数，未完成
		getMajorNum(aString.get(0)); // 第一格 1-26为课程
		System.out.println(aString.get(0));
		int count = 0;
		for (int i = 1; i < aString.size() - 1; i++) {// size大小为27
			getWeekDay(i);// 周几
			classWeek[i % 5] = addOneClass(aString.get(i));

			// getOneDayClass(weekday, aString.get(i));
			System.out.println("到这里了么");
			count++;
			if (count % 5 == 0) {
				addCourseMap(majorNum, weekday, classWeek);// 给专业号A，星期x的天数，加上其一天的课
			}
		}
		// String []a=aString.split("//n");
		// int ArraySize=a.length;

	}

	public static void getOneDayClass(int weekDay, String classStrings) { // 获得某一格子的课程信息  未完成
		if (classStrings.isEmpty()) { // 这节没课

		} else {
			String[] aStrings = classStrings.split("\\n");
			
			// 5 10 15
			// 4 9 14 [1-15,17-18周,单周][1-2节]
			for (int j = 1; j <= (aStrings.length) / 5; j++) {
				String a = aStrings[j * 5 - 1];
				System.out.println("分割后的内容为："+a);
			}
			 
		}
	}

	// 专业号
	public static void getMajorNum(String numString) {
		majorNum = numString.replaceAll("//D+", "");
	}

	// 周几
	public static void getWeekDay(int x) {
		int y = (int) (x - 1) / 5;
		if (y > 5)
			weekday = 1;
		else
			weekday = y;
	}

	public int returnWeekday() {
		return this.weekday;
	}


	// 不知道对不对
	public static void addCourseMap(String majorNum, int weekDay, String[] aStrings) { // 增加course类及其中内容

		if (courseMap == null) {
			courseMap = new TreeMap<String, Map<Integer, String[]>>();
		}

		if (courseMap.get(majorNum) == null) {
			courseMap.put(majorNum, new TreeMap<Integer, String[]>());
		}

		if (courseMap.get(majorNum).get(weekDay) == null) {
			// System.out.println("運行");
			courseMap.get(majorNum).put(weekDay, aStrings);
		}
	}

	// 不知道对不对
	public static void addTimeMap(int weekDay, String[] aStrings) {// 增加TimeMap
		if (timeMap == null) {
			timeMap = new TreeMap<Integer, String[]>();
		}
		if (timeMap.get(weekDay) == null) {
			timeMap.put(weekDay, aStrings);
		}
	}

	public static String addOneClass(String a) { // 获得"1-15,17-18周,单周"类型字符串，确定其使用周数
		System.out.println("正在添加课程信息……");
		String oneClassString = "";
		List<Integer> lst = new ArrayList<Integer>();
		int flag = 0;
		String[] numString = a.split(",");
		int num = numString.length;

		if (a.contains("单周")) {
			flag = 1;
			num--;
		} else if (a.contains("双周")) {
			flag = 2;
			num--;
		}
		for (int i = 0; i < num; i++) {
			String[] numString2 = numString[i].split("-"); // 去掉 - 符号
			if (numString2[1].contains("周"))
				numString2[1] = numString2[1].substring(0, numString2[1].length() - 1);
			List<Integer> lst1 = new ArrayList<Integer>();
			lst1 = getNum(numString2[0], numString2[1], flag);
			lst.addAll(lst1);
		}
		for (int j = 0; j < lst.size(); j++) {
			oneClassString = oneClassString + lst.get(j) + ',';
		}
		oneClassString = oneClassString.substring(0, oneClassString.length() - 1); // 输出1,3,5,7,9,11,13,15,17
		System.out.println(oneClassString);
		System.out.println("课程信息添加完毕");
		return oneClassString;
	}

	public static List<Integer> getNum(String a, String b, int flag) { // 返回a和b之間的數字list
		String numString = "";
		List<Integer> lst = new ArrayList<Integer>();
		int numA = changeToInt(a);
		int numB = changeToInt(b);
		for (int i = numA; i <= numB; i++) {
			if (flag == 1)// 单周
			{
				if (i % 2 != 0)
					lst.add(i);
			} else if (flag == 2) {
				if (i % 2 == 0)
					lst.add(i);
			} else {
				lst.add(i);
			}
		}
		return lst;
	}

	public static int changeToInt(String a) { // 將字符串改為數字 例如： 字符串“12”變為數字12
		char[] aChars = a.toCharArray();
		int num = 0;
		int count = 0;
		while (count < aChars.length) {
			num = num * 10 + (int) aChars[count] - 48;
			count++;
		}
		return num;
	}

	public String returnMajorNum() {
		return this.majorNum;
	}

	public String[] returnClassWeek() {
		return this.classWeek;
	}

	public Map<Integer, String[]> returnTimeMap() {
		return this.timeMap;
	}

	public Map<String, Map<Integer, String[]>> returnCourseMap() {
		return this.courseMap;
	}

	public static void main(String[] args) {
		String[] aStrings = new String[] { "3D综合强化训练(Ⅰ)[试]", "韩兆云", "LY513", "环境设计1640903[37人]",
				"[1-8,10-15,17-18周][1-2节]", "3D综合强化训练(Ⅰ)[试]", "韩兆云", "LY513", "环境设计1640903[37人]",
				"[1-8,10-15,17-18周][1-2节]", "3D综合强化训练(Ⅰ)[试]", "韩兆云", "LY513", "环境设计1640903[37人]",
				"[1-8,10-15,17-18周][1-2节]" };
		List<String> list = new ArrayList<String>();
		list.add("旅游管理1610103");
		list.add("旅游规划学[试]\r\n" + "周丽君\r\n" + "LY208\r\n" + "1610103-04[57人]\r\n" + "[1-18周][1-2节]");
		list.add("旅游市场营销[试]\r\n" + "梅爱松\r\n" + "LY215\r\n" + "1610103-04[57人]\r\n" + "[1-15周][3-4节]");
		// addCourseMap("环境设计1640903", 1, aStrings);
		// init(list);
		
		//  for(int i=0;i<aStrings3.length;i++) {
		 //System.out.println(i+"位置上的内容为"+aStrings3[i]); }
		 
		 // System.out.println(m.matches());
		 
		 getOneDayClass(1, "现代饭店管理[试]\r\n" + 
		 		"孙凤武\r\n" + 
		 		"LY209\r\n" + 
		 		"酒店1710701-02[67人]\r\n" + 
		 		"[1-18周,双周][1-2节]\r\n" + 
		 		"大学英语视听说\r\n" + 
		 		"王璨\r\n" + 
		 		"105语音室(十)\r\n" + 
		 		"酒店管理1710701[34人]\r\n" + 
		 		"[1-12周,单周][1-2节]");
		

		addOneClass("1-15,17-18周,单周");

	}

}
