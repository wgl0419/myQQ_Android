package fzq.com.myqq.ui.activity.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import fzq.com.myqq.R;

/**
 * Created by fzq on 2017/7/29.
 * 登录成功 -- 在主界面向右滑动 -- 在滑出的菜单中点击设置 -- 进入设置界面 --
 * 在设置界面，点击“关于QQ与帮助” -- 进入该界面
 */

public class AboutQQAndHelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutqq_and_help);
    }
}
