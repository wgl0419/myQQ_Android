package fzq.com.myqq.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferensUtil {


    private static SharedPreferences sp = null;
    private static final String spName = "myQQSP";
    /**
     * 这个方法用于在Application或BaseActivity中初始化SP。
     *
     * @param context
     * @return
     */
    public static void initSharedPreferences(Context context) {
        if (null == sp) {
            synchronized (SharedPreferensUtil.class){
                if(null == sp){
                    sp = context.getSharedPreferences(spName, context.MODE_PRIVATE);
                }
            }
        }
    }

    /**
     * 如果在Application或BaseActivity中初始化SP了，那么任何一个类如果要用的话就在这里直接返回好了。
     *
     * @return
     */
    public static SharedPreferences getInstance() {
        return sp;
    }


}
