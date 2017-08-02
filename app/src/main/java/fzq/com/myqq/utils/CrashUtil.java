package fzq.com.myqq.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class CrashUtil implements UncaughtExceptionHandler {

    private final String TAG = "CrashUtil.java";
    private Context context;

    private String dirPtah;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    /**
     * 初始化一次即可，所以建议在程序启动后的Application中初始化
     *
     * @param context 上下文参数
     * @param dirPath 要保存crash文件的路径，这里是文件夹路径，文件会自动按时间格式命名为txt文件。
     */
    public void createCrashHandler(Context context, String dirPath) {

        this.context = context;

        //后者是对应路径中\\的情况
        if (dirPath.charAt(dirPath.length() - 1) == '/' || dirPath.endsWith(File.separator)) {
            this.dirPtah = dirPath;
        } else {
            this.dirPtah = dirPath + "/";
        }

        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        }

    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉,请重启程序", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        // 收集设备参数信息
        collectDeviceInfo();
        // 保存日志文件
        saveCrashInfo2File(ex);

        // 崩溃后退出系统
        System.exit(1);
        return true;
    }

    /**
     * 收集设备参数信息
     */
    public void collectDeviceInfo() {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);

            if (null != pi) {
                String versionName = pi.packageName == null ? "null"
                        : pi.packageName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }

            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG,
                    "NameNotFoundException occured when collect package info",
                    e);
        } catch (IllegalAccessException e) {
            Log.e(TAG,
                    "IllegalAccessException occured when collect package info",
                    e);
        } catch (IllegalArgumentException e) {
            Log.e(TAG,
                    "IllegalArgumentException occured when collect package info",
                    e);
        }
    }

    /**
     * 保存错误信息到文件中
     */
    private void saveCrashInfo2File(Throwable ex) {
        // 不依赖于外部工具，根据传入的文件夹路径自己创建崩溃文件。
        String time = sdf.format(new Date(System.currentTimeMillis()));
        File file = new File(dirPtah + time + ".txt");

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + " = " + value + "\n");
        }

        // 打印并写入文件中
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        Throwable cause = ex.getCause();
        while (null != cause) {
            cause.printStackTrace(pw);
            cause = cause.getCause();
        }
        pw.close();
        String result = writer.toString();
        sb.append(result);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());

        } catch (FileNotFoundException e) {
            Log.e(TAG,
                    "FileNotFoundException occured when save error-infos to the file",
                    e);
        } catch (IOException e) {
            Log.e(TAG, "IOException occured when save error-infos to the file",
                    e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException occured when close File-io", e);
            }
        }
    }

}
