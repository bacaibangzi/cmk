package com.sc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil���ڹ�����
 * 
 * @author cuibin
 * 
 */
public class DateUtil {
	// sDateFormat
	private static SimpleDateFormat sDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static SimpleDateFormat sDateTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		String mDateTime = sDateTimeFormat.format(cal.getTime());
		return mDateTime;
	}

	/**
	 * ��ʽ��ʱ��
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		String mDateTime = sDateTimeFormat.format(date.getTime());
		return mDateTime;
	}
	
	/**
	 * �õ���ǰ����
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		String mDateTime = sDateFormat.format(cal.getTime());
		return mDateTime;
	}

	/**
	 * ���ַ���ת��Date����
	 * 
	 * @param _date
	 * @return
	 */
	public static Date stringToDate(String _date) {
		Date date = null;
		try {
			date = sDateFormat.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * ��ָ�����ڼ���ָ�������������
	 * 
	 * @return
	 */
	public static String getDateByResetDay(String datestr, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDate(datestr));
		cal.add(Calendar.DATE, days);
		String redate = sDateFormat.format(cal.getTime());
		return redate;
	}

	/**
	 * ��ָ�����ڼ���ָ�������������
	 * 
	 * @return
	 */
	public static String getDateTimeByResetDay(String datestr, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDate(datestr));
		cal.add(Calendar.DATE, days);
		String redate = sDateTimeFormat.format(cal.getTime());
		return redate;
	}

	/**
	 * ����ָ������֮�����������
	 * 
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static int dateDayInteval(String sDate, String eDate) {
		long day = 0;
		try {
			Date StartDate = sDateFormat.parse(sDate);
			Date EndDate = sDateFormat.parse(eDate);
			day = (EndDate.getTime() - StartDate.getTime())
					/ (24 * 60 * 60 * 1000);
		} catch (Exception ex) {
			System.err.print(ex.getMessage());
		}
		return (int) day;
	}

	public int getTodayCarsByOdd() {
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.DAY_OF_WEEK);
		return 0;
	}
}
