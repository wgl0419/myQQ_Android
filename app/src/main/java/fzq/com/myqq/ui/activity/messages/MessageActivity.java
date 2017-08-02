package fzq.com.myqq.ui.activity.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import fzq.com.myqq.R;

/**
 * Created by fzq on 2017/8/1.
 * RecyclerView实现聊天界面：http://blog.csdn.net/omrapollo/article/details/52691320
 * http://blog.csdn.net/easkshark/article/details/51131637
 */

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_chat_dital);
    }
}
