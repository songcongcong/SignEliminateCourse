package com.scc.signeliminateclass.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    private Context context;

    private List<UserOutListInfo.MessageBean> mList;
    private CompoundButton lastCheckedRB = null;
    private int index;
    private boolean isCheck;
    private boolean isSelector;
    private int mPosition;
    public SignInAdapter(Context context, List<UserOutListInfo.MessageBean> mList) {
        this.context = context;
        this.mList = mList;
    }
    public SignInAdapter(Context context, List<UserOutListInfo.MessageBean> mList, boolean check, int position) {
        this.context = context;
        this.mList = mList;
        this.isSelector = check;
        this.mPosition = position;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sign_recycle_item, null);
        return new MyViewHolder(view);
    }

    static List<MyViewHolder> list = new ArrayList<MyViewHolder>();
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("song", "消课请求成功：" + mList.get(position).getSign_time());
        holder.mTvName.setText(mList.get(position).getSign_time());
        // 私教图片
        Glide.with(context).load(mList.get(position).getEmployee_sign_image()).transform(new GlideCircleTransform(context))
                .into(holder.mPrPic);
        // 会员图片
        Glide.with(context).load(mList.get(position).getMember_sign_image()).transform(new GlideCircleTransform(context))
                .into(holder.mUrPic);
        if (isSelector && mPosition != -1 && mPosition == position) {
            Log.d("song","为true："+mPosition);
            mList.get(mPosition).setSelector(isSelector);
            holder.mRadioButton.setChecked(isSelector);
        }
        if (position == 0 && !isCheck && !isSelector) {
            mList.get(position).setSelector(true);
            holder.mRadioButton.setChecked(true);
            Log.d("song","第一个：");
        }
        list.add(holder);
        holder.mRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheck = true;
                if(isChecked) {
                    index = position;
                }

                for(MyViewHolder mv : list) {
                    mv.mRadioButton.setChecked(false);
                    mList.get(position).setSelector(false);
                }
                if(isChecked) {
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
                            mList.get(position).getMember_sign_image(), mList.get(position).getSign_time(), mList.get(position).isSelector());
                }
            }

        });

        if (mList.get(position).isSelector()) {
            if (onItemListener != null) {
                Log.d("song", "默认选中:" + mList.get(position).isSelector() + "m:" + mList.get(position).getId()+",:"+position);
                onItemListener.onChilkListener(mList.get(position).isSelector(), mList.get(position).getId(), position);
            }
            if (onItemChilkListener != null) {
                onItemChilkListener.onChilkListener(mList.get(position).getEmployee_sign_image(),
                        mList.get(position).getMember_sign_image(), mList.get(position).getSign_time(), mList.get(position).isSelector());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends BaseViewHolder {

        private final CheckBox mRadioButton;
        private final ImageView mPrPic;
        private final ImageView mUrPic;
        private final TextView mTvName;

        public MyViewHolder(View view) {
            super(view);
            mRadioButton = view.findViewById(R.id.rb_btn);
            mPrPic = view.findViewById(R.id.iv_private_pic);
            mUrPic = view.findViewById(R.id.iv_user_pic);
            mTvName = view.findViewById(R.id.tv_sign_time);
        }
    }

    private onItemListener onItemListener;

    public interface onItemListener {
        void onChilkListener(boolean isSelector, int id, int position);
    }

    public void setOnItemListener(SignInAdapter.onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    private onItemChilkListener onItemChilkListener;

    public interface onItemChilkListener {
        void onChilkListener(String mPrUrl, String mUserUrl, String time, boolean isCheck);
    }

    public void setOnItemChilkListener(onItemChilkListener onItemListener) {
        this.onItemChilkListener = onItemListener;
    }

    public List<UserOutListInfo.MessageBean> getmList() {
        return mList;
    }
}
