package fzq.com.myqq.ui.activity.setting;

import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;

import fzq.com.myqq.R;

/**
 * Created by fzq on 2017/7/31.
 */

public class UserManagerActivity extends AppCompatActivity {

    @Override
    public void setContentTransitionManager(TransitionManager tm) {
        super.setContentTransitionManager(tm);
        setContentView(R.layout.activity_usermanager);
    }
}
