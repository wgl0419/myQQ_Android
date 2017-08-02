package fzq.com.myqq.ui.fragment.contact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * Created by fzq on 2017/8/1.
 */

public class FriendFragAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> fatherList;
    private LayoutInflater inflater;

    public FriendFragAdapter(Context context, List<String> groupList){
        this.context = context;
        this.fatherList = groupList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return fatherList.size();
    }
    @Override
    public Object getGroup(int i) {
        return null;
    }
    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }


}
