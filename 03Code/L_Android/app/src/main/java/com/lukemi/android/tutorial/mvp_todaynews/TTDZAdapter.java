package com.lukemi.android.tutorial.mvp_todaynews;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lukemi.android.tutorial.R;

import java.util.List;

/**
 * Created by android on 2017/7/11.
 */

public class TTDZAdapter extends BaseQuickAdapter<TTDZBean.DataBean, BaseViewHolder> {

    private int maxItemCount;

    public int getMaxItemCount() {
        return maxItemCount;
    }

    public void setMaxItemCount(int maxItemCount) {
        this.maxItemCount = maxItemCount;
    }

    public TTDZAdapter(@LayoutRes int layoutResId, @Nullable List<TTDZBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    public int getItemCount() {
        if (maxItemCount != 0) {
            return maxItemCount > super.getItemCount() ? super.getItemCount() : maxItemCount;
        }
        return super.getItemCount();
    }


    public TTDZAdapter(@Nullable List< TTDZBean.DataBean> data) {
        super(data==null?0:data.size());

    }

    public TTDZAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,  TTDZBean.DataBean item) {
        helper.setText(R.id.tv_content, item.toString());
    }
}
