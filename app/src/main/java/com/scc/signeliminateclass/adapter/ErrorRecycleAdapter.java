package com.scc.signeliminateclass.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scc.signeliminateclass.R;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;

import java.util.List;

/**
 * @author
 * @data 2019/11/11
 */
public class ErrorRecycleAdapter extends RecyclerView.Adapter<ErrorRecycleAdapter.MyViewHolder> {
    /**
     * mList
     */
    private List<PrivateErrorListInfo.MessageBean> mList;
    /**
     * 上下文
     */
    private Context context;

    /**
     * 构造器
     * @param list list
     * @param mcontext mcontext
     */
    public ErrorRecycleAdapter(List<PrivateErrorListInfo.MessageBean> list, Context mcontext) {
        this.mList = list;
        this.context = mcontext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.error_item_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mUserName.setText(mList.get(position).getNickname());
        Glide.with(context).load(mList.get(position).getImage()).into(holder.mImg);
        holder.mRelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemChilkListenre != null) {
                    onItemChilkListenre.OnItemChilkListener(mList.get(position).getNickname(),
                            mList.get(position).getImage(), mList.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * ViewHolder
     */
    public class MyViewHolder extends BaseViewHolder {
        /**
         * mUserName
         */
        private final TextView mUserName;
        /**
         * mRelayout
         */
        private final RelativeLayout mRelayout;
        /**
         * mImg
         */
        private final ImageView mImg;
        /**
         * MyViewHolder
         * @param view view
         */
        public MyViewHolder(View view) {
            super(view);
            mUserName = view.findViewById(R.id.tv_user_name);
            mRelayout = view.findViewById(R.id.relat_item);
            mImg = view.findViewById(R.id.img_user);
        }
    }

    /**
     *  定义接口
     */
    private onItemChilkListenre onItemChilkListenre;

    /**
     * setOnItemChilkListenre
     * @param onItemChilkListenre onItemChilkListenre
     */
    public void setOnItemChilkListenre(ErrorRecycleAdapter.onItemChilkListenre onItemChilkListenre) {
        this.onItemChilkListenre = onItemChilkListenre;
    }

    /**
     * onItemChilkListenre
     */
    public interface onItemChilkListenre {
        /**
         * OnItemChilkListener
         * @param nickName nickName
         * @param imgUrl imgUrl
         * @param id id
         */
        void OnItemChilkListener(String nickName, String imgUrl, int id);
    }
}
