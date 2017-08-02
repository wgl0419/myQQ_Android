package fzq.com.myqq.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by fzq on 2017/7/28.
 * 这里主要是为了使用 ButterKnife框架而新建的基础Activity。
 * Butter Knife，专门为Android View设计的绑定注解，专业解决各种findViewById。
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //教程里是8.2以前的版本，可以解绑，但是现在下载最新的8.7没有unbind方法了
//        ButterKnife.unbind(this);//解除绑定，官方文档只对fragment做了解绑
    }
}
