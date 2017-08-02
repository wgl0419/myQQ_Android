package fzq.com.myqq.ui.fragment.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fzq.com.myqq.R;

/**
 * Created by fzq on 2017/7/31.
 */

public class FriendsFrag extends Fragment {

    @BindView(R.id.friendFrag_expandableList)
    ExpandableListView expandableListView;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
