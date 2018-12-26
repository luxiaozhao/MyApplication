package com.example.wisdom.partybuilding.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.bean.home.HomeDynamicBean;

import java.util.List;

//  landscape  滑动
public class LandscapeAdapter extends RecyclerView.Adapter<LandscapeAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<HomeDynamicBean.CarouselmapBean> mDatas;

    //接口监听     待办 FolderAdapter
    FolderAdapter.onClickLinstener onClickLinstener;

    public void setOnClickLinstener(FolderAdapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public LandscapeAdapter(Context context, List<HomeDynamicBean.CarouselmapBean> datats) {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.landscape_adapter_layout,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.id_index_gallery_item_image);
        viewHolder.mTxt = (TextView) view
                .findViewById(R.id.id_index_gallery_item_text);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTxt.setText(mDatas.get(position).getFunctionname());
        if (mDatas.get(position).getFunctionname().equals("中央精神")) {
            viewHolder.mImg.setImageResource(R.mipmap.centralspirit);
        } else if (mDatas.get(position).getFunctionname().equals("党组声音")) {
            viewHolder.mImg.setImageResource(R.mipmap.r44);
        } else if (mDatas.get(position).getFunctionname().equals("党委新闻")) {
            viewHolder.mImg.setImageResource(R.mipmap.dangweixinwen);
        } else if (mDatas.get(position).getFunctionname().equals("基层交流")) {
            viewHolder.mImg.setImageResource(R.mipmap.grassroots_exchange);
        } else if (mDatas.get(position).getFunctionname().equals("学习园地")) {
            viewHolder.mImg.setImageResource(R.mipmap.g66);
        } else if (mDatas.get(position).getFunctionname().equals("党务知识")) {
            viewHolder.mImg.setImageResource(R.mipmap.r33);
        } else if (mDatas.get(position).getFunctionname().equals("在线考试")) {
            viewHolder.mImg.setImageResource(R.mipmap.examination);
        } else if (mDatas.get(position).getFunctionname().equals("缴纳党费")) {
            viewHolder.mImg.setImageResource(R.mipmap.pay_party_fees);
        } else if (mDatas.get(position).getFunctionname().equals("三会一课")) {

            viewHolder.mImg.setImageResource(R.mipmap.r22);
        } else {

//            holder.adapter_linear.setVisibility(View.GONE);
        }

        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickLinstener != null) {
                    onClickLinstener.setOnClick(view, position);
                }
            }
        });

    }
    //定义点击接口
    public interface onClickLinstener {
        void setOnClick(View view, int position);
    }
}
