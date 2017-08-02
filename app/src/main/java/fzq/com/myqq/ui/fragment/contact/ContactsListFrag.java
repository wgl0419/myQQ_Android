package fzq.com.myqq.ui.fragment.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import fzq.com.myqq.R;

/**
 * Created by fzq on 2017/7/31.
 */

public class ContactsListFrag extends Fragment {

    @BindView(R.id.ContactsFrag_contactList_text)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contacts_contact,   container, false);

        return rootView;
    }
}
