package fzq.com.myqq.ui.activity.login;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fzq.com.myqq.R;
import fzq.com.myqq.ui.activity.MainActivity;
import fzq.com.myqq.ui.myview.MyVideoView;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private MyVideoView myVideoView;
    private int screenWidth, screenHeight;

    private EditText editName, editPwd;
    private Button btnLogin;
    private TextView textForgetPwd, textNewUser, textReadMe;

    private Intent intent;

    private Handler mainHandler;
    private static final int LOGIN_OK = 0x10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intent = new Intent();

        WindowManager wm = getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        System.out.println("啊啊啊 screenWidth: " + screenWidth + "screenHeight: " + screenHeight);

        System.out.println("哈哈哈哈哈");


        initViews();
        startPlay();

        mainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case LOGIN_OK:
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                        break;

                }
            }
        };
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startPlay();
    }

    @Override
    protected void onStop() {

        myVideoView.stopPlayback();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        editName = (EditText) findViewById(R.id.loginAct_edit_userName);
        editPwd = (EditText) findViewById(R.id.loginAct_edit_pwd);
        btnLogin = (Button) findViewById(R.id.loginAct_btn_login);
        textForgetPwd = (TextView) findViewById(R.id.loginAct_text_forgetPwd);
        textNewUser = (TextView) findViewById(R.id.loginAct_text_newUser);
        textReadMe = (TextView) findViewById(R.id.loginAct_text_readme);


        textForgetPwd.setOnClickListener(this);
        textNewUser.setOnClickListener(this);
        textReadMe.setOnClickListener(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.loginAct_text_forgetPwd:
                intent.setClass(this, ForgetPwdActivity.class);
                break;
            case R.id.loginAct_text_newUser:
                intent.setClass(this, NewUserActivity.class);
                break;
            case R.id.loginAct_text_readme:
                intent.setClass(this, ReadMeActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }


    private void login() {
        mainHandler.sendEmptyMessage(LOGIN_OK);
    }


    private void startPlay() {
        try {
            myVideoView = (MyVideoView) findViewById(R.id.loginAct_myVideoView);
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.start_video2));
            myVideoView.start();

            myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    myVideoView.start();
                }
            });
        } catch (Exception e) {
        }
    }

    private class SurfaceViewListener implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            startPlay();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }
}
