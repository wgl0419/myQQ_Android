package fzq.com.myqq.ui.activity.leftmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fzq.com.myqq.R;
import fzq.com.myqq.ui.activity.setting.*;


/**
 * Created by fzq on 2017/7/29.
 * 登录成功 -- 在主界面向右滑动 -- 在滑出的菜单中点击设置 -- 进入该界面
 * <p>
 * 这里开始使用 ButterKnife框架。
 * Butter Knife，专门为Android View设计的绑定注解，专业解决各种findViewById。
 * <p>
 * 学习链接：http://www.jianshu.com/p/9ad21e548b69
 */
public class SettingsActivity extends AppCompatActivity {

//    @BindViews({R.id.SettingAct_layout_userManager, R.id.SettingAct_layout_phoneNumber
//            , R.id.SettingAct_layout_QQdaren, R.id.SettingAct_layout_msgNotify
//            , R.id.SettingAct_layout_chatRecode, R.id.SettingAct_layout_spaceClean
//            , R.id.SettingAct_layout_accountAndDeviceSafety, R.id.SettingAct_layout_ContactsAndSecret
//            , R.id.SettingAct_layout_assistant, R.id.SettingAct_layout_playWhitFreeFlow
//            , R.id.SettingAct_layout_aboutQQAndHelp})
//    List<RelativeLayout> layoutViews;

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        intent = new Intent();
//        ButterKnife.apply(layoutViews, LayoutOnclick, true);  //不会用，fuck。。。
    }

    //初始化DISABLE
    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            //将view设为不可输入
            view.setEnabled(false);
        }
    };
    //以下是传入布尔值
    final ButterKnife.Setter<View, Boolean> LayoutOnclick = new ButterKnife.Setter<View, Boolean>() {

        @Override
        public void set(@NonNull View view, Boolean value, int index) {
            if (index == 0) {
                Toast.makeText(SettingsActivity.this, "click 0 ", Toast.LENGTH_LONG).show();
            } else if (index == 10) {
                Toast.makeText(SettingsActivity.this, "click the last ", Toast.LENGTH_LONG).show();
            }
        }
    };

    @OnClick(R.id.SettingAct_layout_userManager)
    public void goToUserManagerAct() {
        showLog("进入UserManagerActivity");
        intent.setClass(this, UserManagerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_phoneNumber)
    public void goToPhoneNumberAct() {
        showLog("进入PhoneNumberActivity");
        intent.setClass(this, PhoneNumberActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_QQdaren)
    public void goToQQDarenAct() {
        showLog("进入QQDarenActivity");
        intent.setClass(this, QQDarenActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_msgNotify)
    public void goToMsgNotifyAct() {
        showLog("进入MsgNotifyActivity");
        intent.setClass(this, MsgNotifyActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_chatRecode)
    public void goToChatRecodeAct() {
        showLog("进入ChatRecodeActivity");
        intent.setClass(this, ChatRecodeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_spaceClean)
    public void goToSpaceCleanAct() {
        showLog("进入SpaceCleanActivity");
        intent.setClass(this, SpaceCleanActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_accountAndDeviceSafety)
    public void goToAccountAndDeviceSafetyAct() {
        showLog("进入AccountAndDeviceSafetyActivity");
        intent.setClass(this, AccountAndDeviceSafetyActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_ContactsAndSecret)
    public void goToContactsAndSecretAct() {
        showLog("进入ContactsAndSecretActivity");
        intent.setClass(this, ContactsAndSecretActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_assistant)
    public void goToAssistantAct() {
        showLog("进入AssistantActivity");
        intent.setClass(this, AssistantActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_playWhitFreeFlow)
    public void goToPlayWithFreeFlowAct() {
        showLog("进入PlayWithFreeFlowActivity");
        intent.setClass(this, PlayWithFreeFlowActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.SettingAct_layout_aboutQQAndHelp)
    public void goToAboutQQAndHelperAct() {
        showLog("进入AboutQQAndHelperActivity");
        intent.setClass(this, AboutQQAndHelperActivity.class);
        startActivity(intent);
    }


    private void showLog(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        System.out.println("in the SettingsActivity.java, the msg is: " + msg);
    }
}
