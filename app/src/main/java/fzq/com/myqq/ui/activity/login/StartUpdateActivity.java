package fzq.com.myqq.ui.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import fzq.com.myqq.R;
import fzq.com.myqq.base.Constants;
import fzq.com.myqq.utils.SharedPreferensUtil;

/**
 * 程序安装后第一次启动的界面
 */
public class StartUpdateActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private ProgressBar progressBar;
    private TextView textView;
    private LinearLayout layout;
    private ImageView image;


    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_update);

        //这句话很重要，在整个APP中使用的SP都是在这里初始化的
        SharedPreferensUtil.initSharedPreferences(this);


        sp = SharedPreferensUtil.getInstance();
        editor = sp.edit();

        progressBar = (ProgressBar) findViewById(R.id.startAct_progressBar);
        textView = (TextView) findViewById(R.id.startAct_text);
        layout = (LinearLayout) findViewById(R.id.startAct_layout);
        image = (ImageView) findViewById(R.id.startAct_image);

        if (sp.getBoolean(Constants.isFirstStartApp, false)) {
            editor.putBoolean(Constants.isFirstStartApp, true);
            layout.setVisibility(View.VISIBLE);
            startUpdate();
        } else {
            image.setVisibility(View.VISIBLE);
            welcomeLogin();
        }

        progressBar.setProgress(0);
        textView.setText("正在更新数据(" + progress + "%)");


    }

    private void startUpdate() {
        //TODO 创建数据库，相关文件，全局变量等工作。

        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    while (progress <= 99) {
                        sleep(200);
                        progress++;

                        progressBar.setProgress(progress);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("正在更新数据(" + progress + "%)");
                            }
                        });
                    }

                    sleep(1000);
                    goToLoginAct();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private void welcomeLogin() {
        try {
            Thread.sleep(500);

            goToLoginAct();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goToLoginAct() {
        startActivity(new Intent(StartUpdateActivity.this, LoginActivity.class));
        finish();
    }
}
