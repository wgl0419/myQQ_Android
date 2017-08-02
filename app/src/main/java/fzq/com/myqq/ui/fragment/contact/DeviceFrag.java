package fzq.com.myqq.ui.fragment.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fzq.com.myqq.R;

/**
 * Created by fzq on 2017/7/31.
 */

public class DeviceFrag extends Fragment {

    @BindView(R.id.deviceFrag_text_myPC)
    TextView textMyPC;
    @BindView(R.id.deviceFrag_text_findNewDevice)
    TextView textFindNewDevice;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_device, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
