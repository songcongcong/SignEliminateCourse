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
import com.scc.signeliminateclass.bean.UserPhoneListInfo;

import java.util.List;

/**
 * @author
 * @data 2019/11/12
 */
public class UserErrorAdapter extends RecyclerView.Adapter<UserErrorAdapter.MyViewHolder> {
    /**
     * context
     */
    private Context context;

    /**
     * mList
     */
    private List<UserPhoneListInfo.MessageBean> mList;

    /**
     * 构造器
     * @param context context
     * @param mList mList
     */
    public UserErrorAdapter(Context context, List<UserPhoneListInfo.MessageBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.error_item_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mUserName.setText(mList.get(position).getName());
        Glide.with(context).load(mList.get(position).getAvatar()).into(holder.mImg);
        holder.mRelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemChilkListenre != null) {
                    onItemChilkListenre.OnItemChilkListener(mList.get(position).getName(),
                            mList.get(position).getAvatar(),
                            mList.get(position).getMembership_id());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * MyViewHolder
     */
    public class MyViewHolder extends BaseViewHolder {
        /**
         * mRelayout
         */
        private final RelativeLayout mRelayout;
        /**
         * mImg
         */
        private final ImageView mImg;
        /**
         * mUserName
         */
        private final TextView mUserName;

        /**
         * MyViewHolder
         * @param view view
         */
        public MyViewHolder(View view) {
            super(view);
            mRelayout = itemView.findViewById(R.id.relat_item);
            mImg = itemView.findViewById(R.id.img_user);
            mUserName = itemView.findViewById(R.id.tv_user_name);
        }
    }

    /**
     *  定义接口
     */
    public onItemChilkListenre onItemChilkListenre;

    /**
     * setOnItemChilkListenre
     * @param onItemChilkListenre onItemChilkListenre
     */
    public void setOnItemChilkListenre(onItemChilkListenre onItemChilkListenre) {
        this.onItemChilkListenre = onItemChilkListenre;
    }

    /**
     * onItemChilkListenre
     */
    public interface onItemChilkListenre {
        /**
         * OnItemChilkListener
         * @param name name
         * @param avatar avatar
         * @param id id
         */
        void OnItemChilkListener(String name, String avatar, int id);
    }
}
