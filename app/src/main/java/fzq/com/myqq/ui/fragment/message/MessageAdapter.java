package fzq.com.myqq.ui.fragment.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fzq.com.myqq.R;
import fzq.com.myqq.ui.myview.MySlideItem;

/**
 * Created by fzq on 2017/7/28.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<MessageBean> msgList;
    private Context context;
    private LayoutInflater inflater;

    public MessageAdapter(Context context, List<MessageBean> msgList) {
        this.context = context;
        this.msgList = msgList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_myslideitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MessageBean bean = msgList.get(position);
        holder.msgUser.setText(bean.getMsgUser());
        holder.mySlideItem.setOnSlideDeleteListener(new MySlideItem.OnSlideDeleteListener() {
            @Override
            public void onOpen(MySlideItem slideDelete) {

            }

            @Override
            public void onClose(MySlideItem slideDelete) {

            }
        });

    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageUser;
        TextView msgUser;
        TextView msgScan;
        MySlideItem mySlideItem;

        public MyViewHolder(View view) {
            super(view);
            msgUser = view.findViewById(R.id.mySlideItem_text_msgUser);
            mySlideItem = view.findViewById(R.id.mySlideItem_slideItem);
        }
    }

}
