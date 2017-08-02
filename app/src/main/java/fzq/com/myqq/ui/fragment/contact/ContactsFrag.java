package fzq.com.myqq.ui.fragment.contact;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fzq.com.myqq.R;
import fzq.com.myqq.ui.activity.MainActivity;
import fzq.com.myqq.ui.fragment.contact.adapter.ContactFragAdapter;
import fzq.com.myqq.ui.myview.MySearchView;
import fzq.com.myqq.ui.myview.MyViewPager;
import fzq.com.myqq.ui.myview.MyViewPagerIndicator;

/**
 * Created by fzq on 2017/7/25.
 * <p>
 * TextView tv = (TextView) findViewById(R.id.text);
 * tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
 * tv.setText("添加下划线");
 * <p>
 * 关于实现TextView的指示器下划线：http://blog.csdn.net/qq_16674697/article/details/51954228
 */

public class ContactsFrag extends Fragment {

    private Unbinder unBinder;
    @BindView(R.id.contactFrag_searchView)
    MySearchView mySearchView;
    @BindView(R.id.contactFrag_myViewPagerIndicator)
    MyViewPagerIndicator myViewPagerIndicator;
    @BindView(R.id.contactFrag_myViewPager)
    MyViewPager myViewPager;


    private Context context;
    private MainActivity activity;
    private List<Fragment> fragList;
    private ContactFragAdapter adapter;

    private String[] indicatorTexts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getContext();
        activity = (MainActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        unBinder = ButterKnife.bind(this, rootView);

        indicatorTexts = context.getResources().
                getStringArray(R.array.contactFrag_viewPager_indicator);
        //添加相应的Fragment
        initFragments();

        adapter = new ContactFragAdapter(getFragmentManager(), fragList);
        myViewPager.setAdapter(adapter);

        myViewPagerIndicator.setViewPager(myViewPager, 0);
        //设置ViewPager指示器的相关参数
        setMyViewPagerIndicator();
        return rootView;

    }

    /**
     * 添加相应的Fragment
     */
    private void initFragments() {
        fragList = new ArrayList<>();
        fragList.add(new FriendsFrag());
        fragList.add(new GroupFrag());
        fragList.add(new MulitiPeoplesChatFrag());
        fragList.add(new DeviceFrag());
        fragList.add(new ContactsListFrag());
        fragList.add(new OfficeAccountFrag());
    }

    /**
     * 设置ViewPager指示器的相关参数
     */
    private void setMyViewPagerIndicator() {
        myViewPagerIndicator.setTitles(indicatorTexts);
        myViewPagerIndicator.setDefaultHeight(dp2px(30));//设置默认高度为30dp
        myViewPagerIndicator.setTabPadding(dp2px(10), 0, dp2px(10), dp2px(5));//设置tabPadding左右10dp
        myViewPagerIndicator.setBackgroundRadius(25);//设置外框半径25dp
        myViewPagerIndicator.setShowTabSizeChange(true);//显示字体大小切换效果
        myViewPagerIndicator.setShowBackground(false);//不显示背景
        myViewPagerIndicator.setShowIndicator(true);//显示指示器
        myViewPagerIndicator.setDeuceTabWidth(false);//不平分tab宽度，默认为平分
        myViewPagerIndicator.setTabTextSize(14);//设置tab默认字体大小
        myViewPagerIndicator.setTabMaxTextSize(20);//设置tab变换字体大小，如果setShowTabSizeChange设置false，则按默认字体大小
        myViewPagerIndicator.setTabPressColor(Color.RED);//设置tab选中后的字体颜色
        myViewPagerIndicator.setTabTextColor(Color.parseColor("#666666")); //设置未选中时字体颜色
        myViewPagerIndicator.setIndicatorColor(Color.RED);//设置指示器颜色为红色
        myViewPagerIndicator.setmBackgroundColor(Color.RED);//设置背景颜色为红色，如果setShowBackground为false则无背景
        myViewPagerIndicator.setBackgroundLineColor(Color.RED);//设置背景框颜色，如果setShowBackground为false则无背景框颜色
        myViewPagerIndicator.setBackgroundStrokeWidth(dp2px(1));//设置背景框宽度
        myViewPagerIndicator.setDrawIndicatorCreator(new MyViewPagerIndicator.DrawIndicatorCreator() {
            @Override
            public void drawIndicator(Canvas canvas, int left, int top, int right, int bottom, Paint paint, int raduis) {
                //设置下滑线条
                RectF oval = new RectF(left, bottom - dp2px(2), right, bottom);
                canvas.drawRect(oval, paint);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }


    /**
     * dpתpx
     *
     * @param dpVal
     * @return
     */
    public int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
