package com.sanxin.cloud.common.times;


import com.sanxin.cloud.exception.ThrowJsonException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 ***********************************************
 * Copyright (c)  by goldensoft
 * All right reserved.
 * Create Date: 2012-2-15
 * Create Author: huangxin
 * File Name:  日期操作工具类
 * Last version:  1.0
 * Last Update Date:
 * Change Log:
 *************************************************
 */
public class DateUtil {
	/**
	 * 默认日期格式：yyyy-MM-dd
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 默认时间格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 默认时间戳格式，到毫秒 yyyy-MM-dd HH:mm:ss SSS
	 */
	public static final String DEFAULT_DATEDETAIL_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

	public static final String DEFAULT_DATEDETAIL = "yyyyMMddHHmmss";
	/**
	 * 1天折算成毫秒数
	 */
	public static final long MILLIS_A_DAY = 24 * 3600 * 1000;
	
	private static Map<String, Object> parsers = new HashMap<String, Object>();

	private static SimpleDateFormat getDateParser(String pattern) {
		Object parser = parsers.get(pattern);
		if (parser == null) {
			parser = new SimpleDateFormat(pattern);
			parsers.put(pattern, parser);
		}
		return (SimpleDateFormat) parser;
	}

	/**
	 * 取得当前系统日期
	 * 
	 * @return
	 */
	public static Date currentDate() {
		return new Date();
	}
	
	/**
	 * 取得当前系统时间
	 * 
	 * @return
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 取得系统当前日期，返回默认日期格式的字符串。
	 * 
	 * @param strFormat
	 * @return
	 */
	public static String nowDate(String strFormat) {
		Date date = new Date();
		return getDateParser(strFormat).format(date);
	}

	/**
	 * 取得当前系统时间戳
	 * 
	 * @return
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 将日期字符串转换为java.util.Date对象
	 * 
	 * @param dateString
	 * @param pattern
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static Date toDate(String dateString, String pattern){
		Date time=null;
		try {
			time = getDateParser(pattern).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 将日期字符串转换为java.util.Date对象，使用默认日期格式
	 * 
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static Date toDate(String dateString){
		try {
			return getDateParser(DEFAULT_DATE_PATTERN).parse(dateString);
		} catch (Exception e) {
			throw new ThrowJsonException("获取时间失败");
		}
	}

	/**
	 * 将时间字符串转换为java.util.Date对象
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static Date toDateTime(String dateString) throws Exception {
		return getDateParser(DEFAULT_DATETIME_PATTERN).parse(dateString);
	}

	/**
	 * 将java.util.Date对象转换为字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toDateString(Date date, String pattern) {
		return getDateParser(pattern).format(date);
	}

	/**
	 * 将java.util.Date对象转换为字符串，使用默认日期格式
	 * @param date
	 * @return
	 */
	public static String toDateString(Date date) {
		return getDateParser(DEFAULT_DATE_PATTERN).format(date);
	}

	/**
	 * 将java.util.Date对象转换为时间字符串，使用默认日期格式
	 * @param date
	 * @return
	 */
	public static String toDateTimeString(Date date) {
		return getDateParser(DEFAULT_DATETIME_PATTERN).format(date);
	}

	/**
	 * 日期相减
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date diffDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * MILLIS_A_DAY );
		return c.getTime();
	}
	
	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 * @author doumingjun create 2007-04-07
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	
	
	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 * @author doumingjun create 2007-04-07
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		
		c.setTimeInMillis(getMillis(date) + ((long) day) * MILLIS_A_DAY);
		return c.getTime();
	}
	
	public static Date getStringDate(String strDate) throws Exception{
		return getDateParser(DEFAULT_DATEDETAIL).parse(strDate);
	}
	
	public static Date getNowDate(){
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar date = Calendar.getInstance();
		Date newCreatDate = new Date();
		 try {
			 newCreatDate = bartDateFormat.parse(bartDateFormat.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return newCreatDate;
	}
	
	 /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     * 日期相减，获取对应的天数
     * 
     * @param bginDate
     * @param endDate
     * @return
     */
    public static long getDaySub(Date bginDate,Date endDate)
    {
        long day=0;
        try{
            day=(endDate.getTime()-bginDate.getTime())/(24*60*60*1000);    
            System.out.println("相隔的天数="+day);   
        }catch (Exception e){
        	 e.printStackTrace();
            return -1;
        }   
        return day;
    }
    /**
     * 
    * @Title: getDateTimeAddMinutes 
    * @Description: TODO当前时间向后推n分钟
    * @param @param n
    * @param @return    设定文件 
    * @return long    返回类型 
    * @throws
     */
    public static long getDateTimeAddMinutes(int n){
     return Calendar.getInstance().getTimeInMillis()+n*1000*60;
    }
    
    
    /**
     * 获取下周当前星期日期
     * @param count
     * @param pattern
     * @return
     */
	 public static String getNextMonday(int count,String pattern) {  
         
	        Calendar strDate = Calendar.getInstance();         
	        strDate.add(strDate.DATE,count);  
	          
	        //System.out.println(strDate.getTime());  
	        GregorianCalendar currentDate = new GregorianCalendar();  
	        currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));  
	        Date monday = currentDate.getTime();  
	        SimpleDateFormat df = new SimpleDateFormat(pattern);  
	        String preMonday = df.format(monday);  
	        return preMonday;  
	 } 
	 
	/**
	 * 获取当前星期的日期 
	 * @param weekDay
	 * @return
	 */
 	public static Date getWeekDay(int weekDay) {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Date dates = new Date();
        for (int i = 0; i < 7; i++) {
        	if(i==weekDay-1){
               dates = calendar.getTime();
        	}
        	calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
 	
 	/**
	 * 将int型时间(1970年至今的秒数)转换成Date型时间
	 * 
	 * @param unixTime
	 *            1970年至今的秒数
	 * @return
	 */
	public static Date getDateByUnixTime(long unixTime) {
		return new Date(unixTime * 1000L);
	}
	
	/**
	 * 计算距离到当前时间多少天 小时 分钟
	 * @param endDate
	 * @return
	 */
	public static String getDatePoor(Date endDate) {
		Date nowDate=new Date();
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = nowDate.getTime()-endDate.getTime() ;
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    String msg="";
	    if(min>0) {
    			msg= min + "分钟以前";
	    }else {
	    		msg="刚刚";
	    }
	    if(hour>0) {
	    		msg=hour + "小时以前";
	    }
	    if(day>31) {
	    		msg="很久以前";
	    }else if(day>0 && day<=31){
	    		msg=day + "天以前";
	    }
	 	return msg;
	}
	/**
	 * 根据时间段获取日期
	 * @param s1
	 * @param s2
	 * @return
	 * @throws ParseException
	 */
	public static List<String> dateeginEnd(String s1,String s2) throws ParseException {

 		List<String> strings=new ArrayList<>();
 		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Date   begin=sdf.parse(s1);      
		Date   end=sdf.parse(s2);   
   
		double  between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒      
		double  day=between/(24*3600);
		for(int i = 0;i<=day;i++){

			Calendar cd = Calendar.getInstance();   
			cd.setTime(begin);   
			cd.add(Calendar.DATE, i);//增加一天  
			strings.add(sdf.format(cd.getTime()));
		}
		return strings;
	}
	
	/**
	 * 当月最后一天
	 * @param date
	 * @return
	 */
	public static String getLastDayOfMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = calendar.getTime();
		
		lastDate.setDate(lastDay);
		
		return DateUtil.toDateString(lastDate);
	}
	
	/**
	 * 当月最后一天
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonthDate(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = calendar.getTime();
		
		lastDate.setDate(lastDay);
		
		return lastDate;
	}
	
	/**
	 * 
	 * 获取当月日期组
	 * 如： 1月 {2018.1.1,2018.1.2 ... 2018.1.31}
	 * @return
	 */
	public static List<Date> getAllTheDateOftheMonth() {
		Date date=new Date();
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	public static int getLocalweek(Date date) {
		int weekOfMonth=1;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		} catch (Exception e) {
		}
		return weekOfMonth;
	}
	
	public static int getLocalWeeknum(Date date) {
		int num=1;
		try {
			Calendar c = Calendar.getInstance(); 
 			c.setTime(date);
 			num=c.getActualMaximum(Calendar.WEEK_OF_MONTH);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return num;
	}
	
	public static int getMouthnum(Date date) {
		int num=30;
		try {
			Calendar c = Calendar.getInstance(); 
 			c.setTime(date);
 			num=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return num;
	}
	
	
	public static String dateDiff(long startTime, long endTime) {
		//按照传入的格式生成一个simpledateformate对象
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		//获得两个时间的毫秒时间差异
		String msg="0天0小时0分钟";
		if(endTime>=startTime) {
			long diff =endTime -startTime;
			long day = diff/nd;//计算差多少天
			long hour = diff%nd/nh;//计算差多少小时
			long min = diff%nd%nh/nm;//计算差多少分钟
			msg=day+"天"+hour+"小时"+min+"分钟";
		}
		return msg;
	}
	
	
	public static long  dateMinus(long startTime, long endTime) {
		//按照传入的格式生成一个simpledateformate对象
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long min=0;
		//获得两个时间的毫秒时间差异
		if(endTime>=startTime) {
			long diff =endTime -startTime;
			 min = diff/nm;//计算差多少分钟
		}
		return min;
	}
	
	/**
	 * 加上月份
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date,Integer month) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  c.add(Calendar.MONTH, month);
	  return c.getTime();
	}
	
	/**
	 * 获取当月最后一天
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 返回指定日历字段可能捅有的最大值
		int value = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, value);
		return calendar.getTime();
	}
	
	
	/**
	 * 
	 * 分割时间段获取日期
	 * @param dateStr 时间段字符串 如：2018-03-27 ~ 2018-03-28
	 * @param regex 分隔符 如：~
	 * @param pattern 时间格式
	 * @return
	 * @author WahYee
	 * @date 2018年3月27日 上午9:45:28
	 */
	public static Date[] splitDateSection(String dateStr,String regex,String pattern){
		String[] arr = dateStr.split(regex);
		Date[] result = new Date[2];
		result[0] = toDate(arr[0].trim(), pattern);
		result[1] = toDate(arr[1].trim(), pattern);
		return result;
	}
	
	/**
	 * 
	 * 获取当日的起始时间
	 * @return
	 * @author WahYee
	 * @date 2018年3月31日 下午2:06:40
	 */
	public static Date getStartTime(Date date) { 
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();  
    }  
	
	/**
	 * 
	 * 获取当日结束时间
	 * @return
	 * @author WahYee
	 * @date 2018年3月31日 下午2:05:55
	 */
	public static Date getEndTime(Date date) {  
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime();  
    }
	
	/**
	 * 
	 * 获取当月开始时间
	 * @param date
	 * @return
	 * @author WahYee
	 * @date 2018年3月31日 下午2:46:56
	 */
	public static Date getStartTimeOfMonth(Date date) { 
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.add(Calendar.MONTH, 0);
        todayStart.set(Calendar.DAY_OF_MONTH,1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();  
    }  
	
	/**
	 * 
	 * 获取当月的结束时间
	 * @param date
	 * @return
	 * @author WahYee
	 * @date 2018年3月31日 下午2:47:20
	 */
	public static Date getEndTimeOfMonth(Date date){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.DAY_OF_MONTH,todayEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime();  
	}
	
	/**
     * 功能描述：返回小时
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
	/**
	 * 
	 * 获取当年的开始时间
	 * @param date
	 * @return
	 * @author WahYee
	 * @date 2018年3月31日 下午4:29:24
	 */
	public static Date getStartTimeOfYear(Date date) { 
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        int year =  todayStart.get(Calendar.YEAR);
        todayStart.clear(); 
        todayStart.set(Calendar.YEAR, year); 
        return todayStart.getTime();  
    }
	
	/**
	 * 
	 * 获取当年的结束时间
	 * @param date
	 * @return
	 * @author WahYee
	 * @date 2018年3月31日 下午4:29:46
	 */
	public static Date getEndTimeOfYear(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        int year =  todayEnd.get(Calendar.YEAR);
        todayEnd.clear(); 
        todayEnd.set(Calendar.YEAR, year); 
        todayEnd.roll(Calendar.DAY_OF_YEAR, -1);  
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime();  
    }
	
	 /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    public static boolean isFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.currentDate());
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
	}
    
    /**
	 * 判断该日期是否是该月的最后一天
	 * 
	 * @param date
	 *            需要判断的日期
	 * @return
	 */
	public static boolean isLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.currentDate());
		return calendar.get(Calendar.DAY_OF_MONTH) == calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static Date addMinute(int minute) {
		 Calendar nowTime = Calendar.getInstance();
		 nowTime.add(Calendar.MINUTE, minute);
		 return nowTime.getTime();
	}
	

	public static void main(String[] args) {
		if (isLastDayOfMonth()) {
			System.out.println(11);
		}else {
			System.out.println(22);
		}
	}
}
