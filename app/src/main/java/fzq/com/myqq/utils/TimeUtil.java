package fzq.com.myqq.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.TextUtils;

public class TimeUtil {

    private final static SimpleDateFormat sp1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sp2 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS");
    private final static SimpleDateFormat sp3 = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    // 下面的两种情况也可以用sp1的结果进行字符串分割得到。
    private final static SimpleDateFormat sp4 = new SimpleDateFormat(
            "yyyy-MM-dd");
    private final static SimpleDateFormat sp5 = new SimpleDateFormat("HH:mm:ss");
    private final static SimpleDateFormat sp6 = new SimpleDateFormat("HH:mm");

    // 记住下面的这个坑
    /*
	 * public TimeUtil(){ currentTimeMillis = System.currentTimeMillis(); }
	 */

    // 也记住下面的这个坑
	/*
	 * static{ currentTimeMillis = System.currentTimeMillis(); }
	 */

    /**
     * 通过查看 new Date().getTime(); 的源码可知，里面调用了 System.currentTimeMillis()
     *
     * @return
     */
    public static long getCurrrentTime() {
        return System.currentTimeMillis();
    }

    // //////////////////////////////////////////////////////////////////////////////
    // 第一种情况：自动获取当前系统时间，并返回指定格式的字符串

    /**
     * 返回 【yyyy-MM-dd HH:mm:ss】 格式
     *
     * @return
     */
    public static String getTimeNormal() {
        return getTimeNormal(getCurrrentTime());
    }

    /**
     * 返回 【yyyy-MM-dd HH:mm:ss.SSS】 格式
     *
     * @return
     */
    public static String getTimeLong() {
        return getTimeLong(getCurrrentTime());
    }

    /**
     * 返回 【yyyyMMddHHmmss】 格式
     *
     * @return
     */
    public static String getTimeNoSplit() {
        return getTimeNoSplit(getCurrrentTime());
    }

    /**
     * 返回 【yyyy-MM-dd】 （年月日）格式
     *
     * @return
     */
    public static String getDates() {
        return getDates(getCurrrentTime());
    }

    /**
     * 返回【HH:mm:ss】 （时分秒）格式
     *
     * @return
     */
    public static String getTime() {
        return getTime(getCurrrentTime());
    }

    /**
     * 返回【HH:mm】 （时分秒）格式
     *
     * @return
     */
    public static String getTimeNoSecond() {
        return getTimeNoSecond(getCurrrentTime());
    }

    // ///////////////////////////////////////////////////////////////////////////////
    // 第二种情况：传入时间的参数，返回指定格式的字符串

    /**
     * 返回 【yyyy-MM-dd HH:mm:ss】 格式
     *
     * @param timeMillis
     * @return
     */
    public static String getTimeNormal(long timeMillis) {
        Date date = new Date(timeMillis);
        return sp1.format(date);
    }

    /**
     * 返回 【yyyy-MM-dd HH:mm:ss.SSS】 格式
     *
     * @param timeMills
     * @return
     */
    public static String getTimeLong(long timeMills) {
        Date date = new Date(timeMills);
        return sp2.format(date);
    }

    /**
     * 返回 【yyyyMMddHHmmss】 格式
     *
     * @param timeMillis
     * @return
     */
    public static String getTimeNoSplit(long timeMillis) {
        Date date = new Date(timeMillis);
        return sp3.format(date);
    }

    /**
     * 返回 【yyyy-MM-dd】 （年月日）格式
     *
     * @param timeMillis
     * @return
     */
    public static String getDates(long timeMillis) {
        Date date = new Date(timeMillis);
        return sp4.format(date);
    }

    /**
     * 返回 【HH:mm:ss】 （时分秒）格式
     *
     * @param timeMillis
     * @return
     */
    public static String getTime(long timeMillis) {
        Date date = new Date(timeMillis);
        return sp5.format(date);
    }

    /**
     * 返回 【HH:mm】 （时分秒）格式
     *
     * @param timeMillis
     * @return
     */
    public static String getTimeNoSecond(long timeMillis) {
        Date date = new Date(timeMillis);
        return sp6.format(date);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 第三种情况：将各种格式的时间字符串转换为long类型。

    /**
     * 将 【yyyy-MM-dd HH:mm:ss】格式转换为long类型
     *
     * @param time
     * @return
     */
    public static long timeNormalToLong(String time) {
        return timeToLong(time, sp1);
    }

    /**
     * 将 【yyyyMMddHHmmss】格式转换为long类型
     *
     * @param time
     * @return
     */
    public static long timeNoSplitToLong(String time) {
        return timeToLong(time, sp3);
    }

    /**
     * 将自定义的时间字符串和自定义的时间格式转换为long类型
     *
     * @param time
     * @param format
     * @return
     */
    public static long timeToLong(String time, SimpleDateFormat format) {

        long result = 0;

        if (TextUtils.isEmpty(time) || null == format) {
            result = -1;
        } else {
            try {
                Date date = format.parse(time);

                result = date.getTime();
            } catch (Exception e) {
            }
        }

        return result;
    }


    ///////////////////////////////////////////////////////////////////////////
    //第四种情况：各种格式的相互转换

    /**
     * yyyy-MM-dd HH:mm:ss与yyyyMMddHHmmss格式互换
     *
     * @param time
     * @return
     */
    public static String timeFormatExchange(String time) {
        if (time.length() == 19) {
            return String.format("%s%s%s%s%s%s", time.substring(0, 4), time.substring(5, 7), time.substring(8, 10),
                    time.substring(11, 13), time.substring(14, 16), time.substring(17, 19));
        } else if (time.length() == 14) {
            return String.format("%s-%s-%s %s:%s:%s", time.substring(0, 4), time.substring(4, 6), time.substring(6, 8),
                    time.substring(8, 10), time.substring(10, 12), time.substring(12, 14));
        } else {
            return "";
        }
    }

}
