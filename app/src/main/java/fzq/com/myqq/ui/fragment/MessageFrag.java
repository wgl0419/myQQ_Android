package fzq.com.myqq.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fzq.com.myqq.R;
import fzq.com.myqq.ui.myview.MySearchView;

/**
 * Created by fzq on 2017/7/25.
 * 主界面中的消息Fragment
 */

public class MessageFrag extends Fragment {

    private MySearchView mySearchView;
    private RecyclerView recyclerView;
    /**用来插广告或其他作用的Text*/
    private TextView adviertisementView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_msg, container, false);
        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView){
        mySearchView = rootView.findViewById(R.id.msgFrag_searchView);
        recyclerView = rootView.findViewById(R.id.msgFrag_recyclerView);
        adviertisementView = rootView.findViewById(R.id.msgFrag_adviertisement);
    }
}
