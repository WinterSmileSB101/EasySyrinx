package winter.zxb.smilesb101.easysyrinx.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称：EasySyrinx
 * 类描述：工具类
 * 创建人：SmileSB101
 * 创建时间：2017/2/25 0025 12:27
 * 修改人：Administrator
 * 修改时间：2017/2/25 0025 12:27
 * 修改备注：
 */

public class utils{
	/**
	 * 获取当前时间
	 * @param type 时间格式 如 "yyyy年MM月dd日   HH:mm:ss"
	 * @return
	 */
	public static String getCurrentTime(String type)
	{
		SimpleDateFormat formatter   =   new SimpleDateFormat(type);
		Date curDate =  new Date(System.currentTimeMillis());
		String   str   =   formatter.format(curDate);
		return str;
	}

	/**
	 * 获取周几
	 * @param pTime//2016-1-2
	 * @return
	 */
	public static String getWeek(String pTime)
	{
		String week = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		try {
			calendar.setTime(format.parse(pTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SUNDAY) {
			week += "星期天";
		}
		else if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.MONDAY) {
			week += "星期一";
		}
		else if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.TUESDAY) {
			week += "星期二";
		}
		else if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.WEDNESDAY) {
			week += "星期三";
		}
		else if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.THURSDAY) {
			week += "星期四";
		}
		else if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.FRIDAY) {
			week += "星期五";
		}
		else if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SATURDAY) {
			week += "星期六";
		}
		return week;
	}
}
