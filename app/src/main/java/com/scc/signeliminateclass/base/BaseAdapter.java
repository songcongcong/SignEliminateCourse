package com.scc.signeliminateclass.base;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import io.reactivex.annotations.Nullable;


/**
 *  Description:
 *  Created by RockPhoenixHIAPAD on 2017/12/28 on 17:02.
 * @param <T> T
 * @param <K> K
 */

public abstract class BaseAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    /**
     * BaseAdapter
     * @param layoutResId layoutResId
     * @param data data
     */
    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        init();
    }

    /**
     * BaseAdapter
     * @param data data
     */
    public BaseAdapter(@Nullable List<T> data) {
        super(data);
        init();
    }

    /**
     * BaseAdapter
     * @param layoutResId layoutResId
     */
    public BaseAdapter(int layoutResId) {
        super(layoutResId);
        init();
    }

    /**
     * init
     */
    private void init() {
        setEnableLoadMore(true);
        openLoadAnimation(); //开启默认动画载入（仅开启加载新item时开启动画）
    }
}
