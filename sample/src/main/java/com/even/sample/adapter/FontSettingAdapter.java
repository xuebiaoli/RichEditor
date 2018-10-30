package com.even.sample.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.even.sample.R;

import java.util.List;

/**
 * Font Setting Adapter
 * Created by even.wu on 9/8/17.
 */

public class FontSettingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FontSettingAdapter(@Nullable List<String> data) {
        super(R.layout.item_font_setting, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content, item);
    }
}
