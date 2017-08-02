package fzq.com.myqq.ui.fragment.message;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fzq.com.myqq.R;
import fzq.com.myqq.ui.activity.MainActivity;
import fzq.com.myqq.ui.myview.MySearchView;

/**
 * Created by fzq on 2017/7/25.
 * 主界面中的消息Fragment
 * RecyclerView实现聊天界面：http://blog.csdn.net/omrapollo/article/details/52691320
 * http://blog.csdn.net/easkshark/article/details/51131637
 * http://www.jianshu.com/p/34f060f72834 -- 仿朋友圈
 */

public class MessageFrag extends Fragment {

    private Context context;
    private MainActivity mainActivity;
    private MySearchView mySearchView;
    private RecyclerView recyclerView;
    /**用来插广告或其他作用的Text*/
    private TextView adviertisementView;

    private List<MessageBean> msgList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();
        mainActivity = (MainActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_msg, container, false);

        initViews(rootView);
        initDatas();

        MessageAdapter adapter = new MessageAdapter(context, msgList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new MyItemDecoration(context, MyItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void initViews(View rootView){
        mySearchView = rootView.findViewById(R.id.msgFrag_searchView);
        recyclerView = rootView.findViewById(R.id.msgFrag_recyclerView);
        adviertisementView = rootView.findViewById(R.id.msgFrag_adviertisement);
    }

    private void initDatas(){
        msgList = new ArrayList<>();
        for(int i='A'; i<='z'; i++){
            String str = "User - " + i;
            MessageBean bean = new MessageBean();
            bean.setMsgUser(str);
            msgList.add(bean);
        }
    }
}
