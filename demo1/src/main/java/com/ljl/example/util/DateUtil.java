package com.ljl.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 * 日期工具类
 *
 * @author wenlong.chen
 */
public class DateUtil {

    // 默认显示日期的格式
    private static final String DATEFORMAT_STR = "yyyy-MM-dd";

    // 默认显示日期时间的格式
    private static final String DATETIMEF_STR = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取当前日期yyyy-MM-dd的形式
     *
     * @return
     */
    public static String getCurDate() {
        return dateToStr(Calendar.getInstance().getTime(), DATEFORMAT_STR);
    }
    
    /**
     * 获取当前日期的毫秒值
     *
     * @return
     */
	public static Long getMilliseconds() {
		return getMilliseconds(new Date());
	}
    
    /**
     * 获取指定日期的毫秒值
     *
     * @return
     */
	public static Long getMilliseconds(Date date) {
		if (date != null) {
			return date.getTime();
		}
		return null;
	}
    
	/**
     * 获取当前日期的秒值
     *
     * @return
     */
	public static Long getSeconds() {
		return getSeconds(new Date());
	}
    
    /**
     * 获取当前日期的秒值
     *
     * @return
     */
	public static Long getSeconds(Date date) {
		if (date != null) {
			Long mt = getMilliseconds(new Date());
			return mt / 1000L;
		}
		return null;
	}

    /**
     * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
     *
     * @return
     */
    public static String getCurDateTime() {
        return dateToStr(new Date(), DATETIMEF_STR);
    }

	/**
	 * 不知道传递过来的str值为格式化之后的数据还是时间戳，此处做个冗余处理
	 * 
	 * @param value
	 * @return
	 */
	public static Date parseStr(String value) {
		return parseStr(value, "yyyy-MM-dd hh:mm:ss", "yyyy-MM-dd");
	}
	
	/**
	 * 不知道传递过来的str值为格式化之后的数据还是时间戳，此处做个冗余处理
	 * 
	 * @param value
	 * @param formatStr
	 * @return
	 */
	public static Date parseStr(String value, String... formatStrs) {
		Date date = null;
		if (value != null && !"".equals(value)) {
			try {
				Long temp = Long.parseLong(value);
				if (value.length() == 10) {
					temp *= 1000;
				}
				date = new Date(temp);
			} catch (Exception e) {
			}
			for (String formatStr : formatStrs) {
				if (date == null) {
					date = strToDate(value, formatStr);
				} else {
					break;
				}
			}
		}
		return date;
	}
    
    /**
     * 将字符串转换成指定格式的日期
     *
     * @param str
     * @param formatStr
     * @return
     */
    public static Date strToDate(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 获取日期的long格式
     *
     * @param date
     * @return
     */
    public static Long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 将long格式的日期转化成Date类型
     *
     * @param timestamp
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long timestamp, String formatType)
            throws ParseException {
        Date dateOld = new Date(timestamp);
        String sDateTime = dateToStr(dateOld, formatType);
        Date date = strToDate(sDateTime, formatType);
        return date;
    }

    /**
     * 日期转换成字符串
     * <pre>
     * DateUtil.dateToStr(new Date())   =2016-04-12
     * </pre>
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, DATEFORMAT_STR);
    }


    /**
     * 日期转换成指定格式的字符串
     * <pre>
     * DateUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss")   =2016-04-12 11:51:24
     * </pre>
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(date);
        return str;
    }

    /**
     * 获取指定日期是星期几
	 * @author arvin
     * 
     * @param date
     * @return
     */
	public static Integer getWeekByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		Integer[] weeks = new Integer[] { 7, 1, 2, 3, 4, 5, 6 };
		if (null != date) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0 || w > 7) {
			w = 0;
		}
		return weeks[w];
	}
	
	/**
	 * 获取参考日期后的最近的星期几数据
	 * @author arvin
	 * 
	 * @param weekList 需要获取的数据{1,2,3,4,5,6,7}分别代表了星期一至星期天
	 * @param date 参考的日期
	 * @return
	 */
	public static List<Date> getLatestDateByWeeks(List<Integer> weekList, Date date) {
		List<Date> dateList = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		// 设置星期一为每个星期的第一天（只针对weekofmonth和weekofyear有作用）
		calendar.setFirstDayOfWeek(2);
		calendar.setTime(date);
		Integer cw = getWeekByDate(date);
		int year = calendar.get(Calendar.YEAR);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		for (Integer iw : weekList) {
			if (iw >= cw) {
				calendar.setWeekDate(year, weekOfYear, (iw + 1) > 7 ? 1 : (iw + 1));
			} else {
				calendar.setWeekDate(year, weekOfYear + 1, (iw + 1) > 7 ? 1 : (iw + 1));
			}
			dateList.add(calendar.getTime());
		}
		return dateList;
	}
	
	 /**
     * 获取指定日期的年，月，日
     * 	返回的结果均为正常思维下的数据，比如9月返回9，而不是8，星期天返回的是7，而不是1
	 * @author arvin
     * 
     * @param date
     * @return {'year':2017,'month':9,'day':2,'week':6} 
     */
	public static Map<String, Integer> spiltDate(Date date) {
		Map<String, Integer> out = new HashMap<String, Integer> ();
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		out.put("year", calendar.get(Calendar.YEAR));
		out.put("month", calendar.get(Calendar.MONTH) + 1);
		out.put("day", calendar.get(Calendar.DAY_OF_MONTH));
		out.put("week", getWeekByDate(date));
		return out;
	}
	
	 /**
     * 获取指定时间段内的每月的X号数据
     * 	如果入参为 dayIndex = 31， startDate = 2017-06-01 endDate = 2017-12-30
     * 	则返回的出参为 List<Date> {"2017-07-31","2017-08-31","2017-10-31"}
	 * @author arvin
	 * 
     * @param dayIndex
     * @param startDate
     * @param endDate
     * @return 
     */
	public static List<Date> getDayForPeriodDate(Integer dayIndex, Date startDate, Date endDate) {
		List<Date> out = new ArrayList<Date>();
		Map<String, Integer> startSpiltDates = spiltDate(startDate);
		Map<String, Integer> endSpiltDates = spiltDate(endDate);
		Integer year = startSpiltDates.get("year");
		Integer month = startSpiltDates.get("month");
		Integer day = startSpiltDates.get("day");
		// 如果要获取的具体几号小于开始时间的具体几号，则表示该月已经无法获取该日期，则直接进入下一个月作为开始日期
		if (dayIndex.compareTo(day) < 0) {
			// 如果月份大于12，则表示这一年已经无法获取日期，则直接进入下一年的一月作为开始日期
			if (++month > 12) {
				month = 1;
				year++;
			}
		}
		// 如果实际开始日期的年份大于结束年份，则直接结束
		while (year.compareTo(endSpiltDates.get("year")) <= 0) {
			Integer referMonth = endSpiltDates.get("month");
			if (year.compareTo(endSpiltDates.get("year")) < 0) {
				referMonth = 12;
			}
			while (month.compareTo(referMonth) <= 0) {
				Date td = null;
				// 只有年份和月份都相等时才需要判断dayIndex和endSpiltDates.get("day")的关系
				if (month.compareTo(referMonth) == 0 && year.compareTo(endSpiltDates.get("year")) == 0) {
					// 如果需要的几号信息小于等于结束的日期的几号才需要设置
					if (dayIndex.compareTo(endSpiltDates.get("day")) <= 0) {
						td = createDateByYearAndMonthAndDay(year, month, dayIndex);
					}
				} else {
					td = createDateByYearAndMonthAndDay(year, month, dayIndex);
				}
				if (td != null) {
					out.add(td);
				}
				month++;
			}
			// 遍历结束，则代表当前的一年已经结束
			month = 1;
			year++;
		}
		return out;
	}
	
	/**
	 * 获取默认时间的那个月的天数
	 * 
	 * @return
	 */
	public static Integer getMaxDayNumFromDate() {
		return getMaxDayNumFromDate(new Date());
	}
	
	/**
	 * 获取指定时间的那个月的天数
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getMaxDayNumFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 根据年月日的具体参数设置时间并且返回
	 * @author arvin
	 * 
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return
	 */
	private static Date createDateByYearAndMonthAndDay(Integer year, Integer month, Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		Integer maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (day <= maxDays) {
			calendar.set(Calendar.DAY_OF_MONTH, day);
			return calendar.getTime();
		}
		return null;
	}
	
	/**
	 * 获取一天的00:00:00.000数据
	 * 
	 * @author arvin
	 * 
	 * @return
	 */
	public static Date getDateWithoutEndTime() {
		return getDateWithoutEndTime(new Date());
	}
	
	/**
	 * 获取一天的00:00:00.000数据
	 * 
	 * @author arvin
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateWithoutEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取一天的23:59:59.999数据
	 * 
	 * @author arvin
	 * 
	 * @return
	 */
	public static Date getDateWithEndTime() {
		return getDateWithEndTime(new Date());
	}

	/**
	 * 获取一天的23:59:59.999数据
	 * 
	 * @author arvin
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateWithEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * date之后的 days的时间
	 * 
	 * @param date 时间
	 * @param days 需要变更的天数
	 * @return
	 */
	public static Date dayAffter (Date date, int days) {
		return operation(getDateWithEndTime(date), Calendar.DAY_OF_YEAR, days * 1);
	}
	
	/**
	 * date之前的 days的时间
	 * 
	 * @param date 时间
	 * @param days 需要变更的天数
	 * @return
	 */
	public static Date dayBefore (Date date, int days) {
		return operation(getDateWithoutEndTime(date), Calendar.DAY_OF_YEAR, days * 1);
	}
	
	/**
	 * 时间运算
	 * 
	 * @param date 时间
	 * @param field 需要加减的属性
	 * @param 变量
	 * @return
	 */
	public static Date operation(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
	/**
	 * 获取默认时间的那个月的第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayForMonth() {
		return getFirstDayForMonth(new Date());
	}
	
	/**
	 * 获取指定时间的那个月的第一天
	 * 
	 * @param date 时间
	 * @return
	 */
	public static Date getFirstDayForMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateWithoutEndTime(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取默认时间的那个月的最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayForMonth() {
		return getLastDayForMonth(new Date());
	}
	
	/**
	 * 获取指定时间的那个月的最后一天
	 * 
	 * @param date 时间
	 * @return
	 */
	public static Date getLastDayForMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateWithEndTime(date));
		calendar.set(Calendar.DAY_OF_MONTH, getMaxDayNumFromDate(date));
		return calendar.getTime();
	}
	
	public static void main(String[] args) {

		System.out.println("week:" + getLastDayForMonth(parseStr("2027-12-02", "yyyy-MM-dd")));
		System.out.println("week:" + getFirstDayForMonth(parseStr("2027-12-02", "yyyy-MM-dd")));
	}
}
