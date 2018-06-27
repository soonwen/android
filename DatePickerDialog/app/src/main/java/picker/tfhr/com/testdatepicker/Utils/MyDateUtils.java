package picker.tfhr.com.testdatepicker.Utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author huangsf
 * 功能描述：日期处理
 */

public class MyDateUtils {

    public static final String date_Format = "yyyy-MM-dd HH:mm:ss";
    public  static final String date_Format2 = "yyyy-MM-dd";
    public  static final String date_Format3 = "yyyy-MM";
    public static final String date_Format4 = "yyyyMMddHHmmss";
    public static final SimpleDateFormat sdf2 = new SimpleDateFormat(date_Format2);
    public static final String now_date_Format = "HH:MM";

    /**
     * 查询当天时间
     * @param dFormat 时间格式
     * @return
     */
    public static String getCurTimeFormat(String dFormat) {
        String dtime = "";
        if (StringUtils.isEmpty(dFormat)) {
            SimpleDateFormat sdf = new SimpleDateFormat(date_Format);
            return sdf.format(new Date());
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(dFormat);
                return sdf.format(new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dtime;
    }

    /**
     * 获取上一个月
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    public static String getTime() {
        Calendar now = Calendar.getInstance();
        String hour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(now.get(Calendar.MINUTE));
        String time = hour + ":" + minute;
        return time;
    }

    /**
     * 根据时间戳查询
     * @param value
     * @return
     */
    public static String getDateFromLong(String value) {
        long d = Long.valueOf(value);
        Date date = new Date(d);
        SimpleDateFormat format = new SimpleDateFormat(date_Format);
        return format.format(date);
    }
    /**
     * 根据时间戳查询
     * @param value
     * @return
     */
    public static String getDateFromLongs(String value) {
        long d = Long.valueOf(value);
        Date date = new Date(d);
        SimpleDateFormat format = new SimpleDateFormat(date_Format3);
        return format.format(date);
    }
    public static String getDateFromLongData(String value) {
        long d = Long.valueOf(value);
        Date date = new Date(d);
        SimpleDateFormat format = new SimpleDateFormat(date_Format2);
        return format.format(date);
    }


    public static String getConcreteTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMddHHmmssSSSS");
        Calendar cal = Calendar.getInstance();
        String id = sdf.format(cal.getTime());

        return id;
    }

    public static String getUUID() {
        UUID uuid  =  UUID.randomUUID();
        String id = uuid.toString().replaceAll("\\-", "");
        return id;
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        //英式，周日是第一天（即周日时：c.get(Calendar.DAY_OF_WEEK)=1），周六是最后一天（即周六时：c.get(Calendar.DAY_OF_WEEK)=7）
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0){
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 1);
        return sdf2.format(c.getTime());
    }
    /**
     * 得到明天的日期
     *
     * @return yyyy-MM-dd
     */
    public static String getLastday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,  1);
        return sdf2.format(c.getTime());
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return sdf2.format(c.getTime());
    }
    /**
     * 得到上周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfLastWeek() {
        Calendar c = Calendar.getInstance();
        //英式，周日是第一天（即周日时：c.get(Calendar.DAY_OF_WEEK)=1），周六是最后一天（即周六时：c.get(Calendar.DAY_OF_WEEK)=7）
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0){
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 1-7);
        return sdf2.format(c.getTime());
    }

    /**
     * 得到上周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfLastWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7-7);
        return sdf2.format(c.getTime());
    }
    /**
     * 获取当前时间
     * @param
     */
    public static String getCurrentDate(int style){
        SimpleDateFormat df = null;
        switch (style){
            case 0:
                df = new SimpleDateFormat("yyyy-MM");
                break;
            case 1:
                df = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 2:
                df = new SimpleDateFormat("dd");
                break;
            case 3:
                df = new SimpleDateFormat(date_Format);
                break;
        }
        return df.format(new Date());

    }

    public static int getZhouQing(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("日")) {
                return 0;
            }
            if (str.equals("月")) {
                return 1;
            }
        }
        return -1;
    }

    // 字符串类型日期转化成date类型
    public static Date strToDate(String style, String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(style);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String dateToStr(String style, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(style);
        return formatter.format(date);
    }

    public static String clanderTodatetime(Calendar cal ,String form){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat(form);
        str = sdf.format(cal.getTime());
        return str;
    }

    public static void main(String[] args) {
        System.out.println(getMondayOfLastWeek());
    }

}
