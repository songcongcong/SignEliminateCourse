package com.scc.signeliminateclass.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scc.signeliminateclass.R;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.widget.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @data 2019/11/14
 */
public class SignInAdapter extends RecyclerView.Adapter<SignInAdapter.MyViewHolder> {
    /**
     * 上下文
     */
    private Context context;
    /**
     * mList
     */
    private List<UserOutListInfo.MessageBean> mList;

    /**
     * isCheck
     */
    private boolean isCheck;
    /**
     * isSelector
     */
    private boolean isSelector;
    /**
     * mPosition
     */
    private int mPosition;

    /**
     * list--用来存储当前item
     */
    static List<MyViewHolder> myViewHolderList = new ArrayList<MyViewHolder>();

    /**
     * index
     */
    private int index;

    /**
     * SignInAdapter
     * @param mcontext mcontext
     * @param messageBeans messageBeans
     */
    public SignInAdapter(Context mcontext, List<UserOutListInfo.MessageBean> messageBeans) {
        this.context = mcontext;
        this.mList = messageBeans;
    }

    /**
     * 多参数构造器
     * @param cot cot
     * @param beanList beanList
     * @param check check
     * @param position position
     */
    public SignInAdapter(Context cot, List<UserOutListInfo.MessageBean> beanList, boolean check, int position) {
        this.context = cot;
        this.mList = beanList;
        this.isSelector = check;
        this.mPosition = position;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sign_recycle_item, null);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("song", "消课请求成功：" + mList.get(position).getSign_time());
        holder.mTvName.setText(mList.get(position).getSign_time());
        // 私教图片
        Glide.with(context).load(mList.get(position).getEmployee_sign_image())
                .transform(new GlideCircleTransform(context))
                .into(holder.mPrPic);
        // 会员图片
        Glide.with(context).load(mList.get(position).getMember_sign_image())
                .transform(new GlideCircleTransform(context))
                .into(holder.mUrPic);
        if (isSelector && mPosition != -1 && mPosition == position) {
            mList.get(mPosition).setSelector(isSelector);
            holder.mRadioButton.setChecked(isSelector);
        }
        if (position == 0 && !isCheck && !isSelector) {
            mList.get(position).setSelector(true);
            holder.mRadioButton.setChecked(true);
        }
        myViewHolderList.add(holder);
        holder.mRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheck = true;
                if (isChecked) {
                    index = position;
                }

                for (MyViewHolder mv : myViewHolderList) {
                    mv.mRadioButton.setChecked(false);
                    mList.get(position).setSelector(false);
                }
                if (isChecked) {
                    holder.mRadioButton.setChecked(true);
                    mList.get(position).setSelector(true);
                } else {
                    holder.mRadioButton.setChecked(false);
                    mList.get(position).setSelector(false);
                }

                if (onItemListener != null) {
                    onItemListener.onChilkListener(mList.get(position).isSelector(), mList.get(position).getId(),
                            position);
                }
                if (onItemChilkListener != null) {
                    onItemChilkListener.onChilkListener(mList.get(position).getEmployee_sign_image(),
                            mList.get(position).getMember_sign_image(), mList.get(position).getSign_time(),
                            mList.get(position).isSelector());
                }
            }

        });

        if (mList.get(position).isSelector()) {
            if (onItemListener != null) {
                Log.d("song", "默认选中:" + mList.get(position).isSelector() + "m:"
                        + mList.get(position).getId() + ",:" + position);
                onItemListener.onChilkListener(mList.get(position).isSelector(), mList.get(position).getId(), position);
            }
            if (onItemChilkListener != null) {
                onItemChilkListener.onChilkListener(mList.get(position).getEmployee_sign_image(),
                        mList.get(position).getMember_sign_image(), mList.get(position).getSign_time(),
                        mList.get(position).isSelector());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * myViewHolder
     */
    public class MyViewHolder extends BaseViewHolder {
        /**
         * mRadioButton
         */
        private final CheckBox mRadioButton;
        /**
         * mPrPic
         */
        private final ImageView mPrPic;
        /**
         * mUrPic
         */
        private final ImageView mUrPic;
        /**
         * mTvName
         */
        private final TextView mTvName;

        /**
         * MyViewHolder
         * @param view view
         */
        public MyViewHolder(View view) {
            super(view);
            mRadioButton = view.findViewById(R.id.rb_btn);
            mPrPic = view.findViewById(R.id.iv_private_pic);
            mUrPic = view.findViewById(R.id.iv_user_pic);
            mTvName = view.findViewById(R.id.tv_sign_time);
        }
    }

    /**
     * onItemListener
     */
    private onItemListener onItemListener;

    /**
     * onItemListener
     */
    public interface onItemListener {
        /**
         * onChilkListener
         * @param  isSelector isSelector
         * @param id id
         * @param position position
         */
        void onChilkListener(boolean isSelector, int id, int position);
    }

    /**
     * setOnItemListener
     * @param onItemListener onItemListener
     */
    public void setOnItemListener(SignInAdapter.onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    /**
     * 回显传值回调
     */
    private onItemChilkListener onItemChilkListener;

    /**
     * onItemChilkListener
     */
    public interface onItemChilkListener {
        /**
         * 点击监听回调
         * @param mPrUrl mPrUrl
         * @param mUserUrl mUserUrl
         * @param time time
         * @param isCheck isCheck
         */
        void onChilkListener(String mPrUrl, String mUserUrl, String time, boolean isCheck);
    }

    /**
     * setOnItemChilkListener
     * @param onItemListener onItemListener
     */
    public void setOnItemChilkListener(onItemChilkListener onItemListener) {
        this.onItemChilkListener = onItemListener;
    }

    /**
     * 获取集合
     * @return list
     */
    public List<UserOutListInfo.MessageBean> getmList() {
        return mList;
    }

    /**
     * 获取集合
     * @return List
     */
    public static List<MyViewHolder> getMyViewHolderList() {
        return myViewHolderList;
    }
}
