package fzq.com.myqq.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import fzq.com.myqq.R;
import fzq.com.myqq.base.Constants;
import fzq.com.myqq.ui.activity.leftmenu.SettingsActivity;
import fzq.com.myqq.ui.fragment.contact.ContactsFrag;
import fzq.com.myqq.ui.fragment.message.MessageFrag;
import fzq.com.myqq.ui.fragment.state.StatesFrag;
import fzq.com.myqq.utils.SharedPreferensUtil;


/**
 * Created by fzq on 2017/7/25.
 * 登录成功后的主界面
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    //ScrollView -- 左边侧滑菜单栏部分
    private LinearLayout layoutSetting, layoutNeight, layoutLimixiu;

    //ScrollView -- 右边主界面部分
    private LinearLayout layoutMsg, layoutContact, layoutState;
    private MessageFrag msgFrag;
    private ContactsFrag contactsFrag;
    private StatesFrag statesFrag;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        msgFrag = new MessageFrag();
        contactsFrag = new ContactsFrag();
        statesFrag = new StatesFrag();
        Fragment[] frags = {msgFrag, contactsFrag, statesFrag};

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.layoutMain_layout_FragContainer,
                frags[sp.getInt(Constants.mainFragIndex, 0)]).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initViews() {
        sp = SharedPreferensUtil.getInstance();
        editor = sp.edit();

        //ScrollView -- 左边侧滑菜单栏部分
        layoutSetting = (LinearLayout) findViewById(R.id.leftMenu_layout_setting);
        layoutNeight = (LinearLayout) findViewById(R.id.leftMenu_layout_neight);
        layoutLimixiu = (LinearLayout) findViewById(R.id.leftMenu_layout_limixiu);
        layoutSetting.setOnClickListener(this);
        layoutNeight.setOnClickListener(this);
        layoutLimixiu.setOnClickListener(this);

        //ScrollView -- 右边主界面部分
        layoutMsg = (LinearLayout) findViewById(R.id.layoutMain_layout_msg);
        layoutContact = (LinearLayout) findViewById(R.id.layoutMain_layout_contact);
        layoutState = (LinearLayout) findViewById(R.id.layoutMain_layout_state);
        layoutMsg.setOnClickListener(this);
        layoutContact.setOnClickListener(this);
        layoutState.setOnClickListener(this);
    }

    Intent newActIntent = null;
    @Override
    public void onClick(View view) {
        newActIntent = new Intent();
        switch (view.getId()) {
            case R.id.leftMenu_layout_setting:
                newActIntent.setClass(this, SettingsActivity.class);
                startActivity(newActIntent);
                break;
            case R.id.leftMenu_layout_neight:
                break;
            case R.id.leftMenu_layout_limixiu:
                break;

            case R.id.layoutMain_layout_msg:
                if (sp.getInt(Constants.mainFragIndex, 0) == 0) {
                    return;
                }
                replaceFrag(0);
                break;
            case R.id.layoutMain_layout_contact:
                if (sp.getInt(Constants.mainFragIndex, 0) == 1) {
                    return;
                }
                replaceFrag(1);
                break;
            case R.id.layoutMain_layout_state:
                if (sp.getInt(Constants.mainFragIndex, 0) == 2) {
                    return;
                }
                replaceFrag(2);
                break;
            default:
                break;
        }
    }

    private void replaceFrag(int flag) {
        //一个translation只能commit一次，fuck。。。。
        FragmentTransaction transaction0 = getSupportFragmentManager().beginTransaction();
        if (flag == 0) {
            transaction0.replace(R.id.layoutMain_layout_FragContainer, msgFrag).commit();
            editor.putInt(Constants.mainFragIndex, 0);
        } else if (flag == 1) {
            transaction0.replace(R.id.layoutMain_layout_FragContainer, contactsFrag).commit();
            editor.putInt(Constants.mainFragIndex, 1);
        } else if (flag == 2) {
            transaction0.replace(R.id.layoutMain_layout_FragContainer, statesFrag).commit();
            editor.putInt(Constants.mainFragIndex, 2);
        }

        editor.commit();
    }

}
