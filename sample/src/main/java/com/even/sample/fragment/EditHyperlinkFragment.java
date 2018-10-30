package com.even.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.even.sample.R;

/**
 * Edit Hyperlink Activity
 * Created by even.wu on 10/8/17.
 */

public class EditHyperlinkFragment extends Fragment {
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_display_text)
    EditText etDisplayText;

    private OnHyperlinkListener mOnHyperlinkListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_hyperlink, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.iv_back)
    void onClickBack() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }

    @OnClick(R.id.btn_ok)
    void onClickOK() {
        if (mOnHyperlinkListener != null) {
            mOnHyperlinkListener.onHyperlinkOK(etAddress.getText().toString(),
                    etDisplayText.getText().toString());
            onClickBack();
        }
    }

    public void setOnHyperlinkListener(OnHyperlinkListener mOnHyperlinkListener) {
        this.mOnHyperlinkListener = mOnHyperlinkListener;
    }

    public interface OnHyperlinkListener {
        void onHyperlinkOK(String address, String text);
    }
}
