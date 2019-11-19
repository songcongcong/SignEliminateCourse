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

    private List<PrivateErrorListInfo.MessageBean> mList;

    private Context context;

    public ErrorRecycleAdapter(List<PrivateErrorListInfo.MessageBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
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

    public class MyViewHolder extends BaseViewHolder {

        private final TextView mUserName;
        private final RelativeLayout mRelayout;
        private final ImageView mImg;

        public MyViewHolder(View view) {
            super(view);
            mUserName = view.findViewById(R.id.tv_user_name);
            mRelayout = view.findViewById(R.id.relat_item);
            mImg = view.findViewById(R.id.img_user);
        }
    }

    // 定义接口
    private onItemChilkListenre onItemChilkListenre;

    public void setOnItemChilkListenre(ErrorRecycleAdapter.onItemChilkListenre onItemChilkListenre) {
        this.onItemChilkListenre = onItemChilkListenre;
    }

    public interface onItemChilkListenre {
        void OnItemChilkListener(String nickName, String imgUrl, int id);
    }
}
