package fzq.com.myqq.ui.fragment.contact.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by fzq on 2017/7/31.
 */

public class ContactFragAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragList;

    public ContactFragAdapter(FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
