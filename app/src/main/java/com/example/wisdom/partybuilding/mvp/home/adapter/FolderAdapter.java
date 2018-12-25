package com.example.wisdom.partybuilding.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.bean.home.HomeDynamicBean;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
    private Context context;
    private List<HomeDynamicBean.CarouselmapBean> list;

    //接口监听     待办 FolderAdapter
   FolderAdapter.onClickLinstener onClickLinstener;

    public void setOnClickLinstener(FolderAdapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public FolderAdapter(Context context, List<HomeDynamicBean.CarouselmapBean> datalist) {
        this.context = context;
        this.list = datalist;
    }

    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_results_list_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
    FolderAdapter.ViewHolder viewHolder = new FolderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FolderAdapter.ViewHolder holder, final int position) {

        holder.adapter_name.setText(list.get(position).getFunctionname());

        if (list.get(position).getFunctionname().equals("中央精神")){
            Glide.with(context)
                    .load(R.mipmap.centralspirit)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        }else  if (list.get(position).getFunctionname().equals("党组声音")){
            Glide.with(context)
                    .load(R.mipmap.r44)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        }else if (list.get(position).getFunctionname().equals("党委新闻")){
            Glide.with(context)
                    .load(R.mipmap.dangweixinwen)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        }else if (list.get(position).getFunctionname().equals("基层交流")){
            Glide.with(context)
                    .load(R.mipmap.grassroots_exchange)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        }else if (list.get(position).getFunctionname().equals("学习园地")){
            Glide.with(context)
                    .load(R.mipmap.g66)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        }else if (list.get(position).getFunctionname().equals("党务知识")){
            Glide.with(context)
                    .load(R.mipmap.r33)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        }else if (list.get(position).getFunctionname().equals("在线考试")){
            Glide.with(context)
                    .load(R.mipmap.examination)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        }else if (list.get(position).getFunctionname().equals("缴纳党费")){
            Glide.with(context)
                    .load(R.mipmap.pay_party_fees)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        } else if (list.get(position).getFunctionname().equals("三会一课")){
            Glide.with(context)
                    .load(R.mipmap.r22)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .crossFade(1000)
                    .into(holder.adapter_img);
        } else {

//            holder.adapter_linear.setVisibility(View.GONE);
        }

            holder.adapter_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onClickLinstener != null) {
                    onClickLinstener.setOnClick(view, position);
                }

            }
        });

//        beans = new ArrayList<>();
//        Bean bean1 = new Bean();
//        bean1.setString("中央精神");
//        bean1.setImg(R.mipmap.centralspirit);
//
//        Bean bean2 = new Bean();
//        bean2.setString("党组声音");
//        bean2.setImg(R.mipmap.r44);
//
//        Bean bean3 = new Bean();
//        bean3.setString("党委新闻");
//        bean3.setImg(R.mipmap.r22);
//
//        Bean bean4 = new Bean();
//        bean4.setString("基层交流");//grassroots_exchange
//        bean4.setImg(R.mipmap.grassroots_exchange);
//
//        Bean bean5 = new Bean();
//        bean5.setString("学习园地");
//        bean5.setImg(R.mipmap.g66);
//
//        Bean bean6 = new Bean();
//        bean6.setString("党务知识");
//        bean6.setImg(R.mipmap.r33);
//
//
//        Bean bean7 = new Bean();
//        bean7.setString("在线考试");
//        bean7.setImg(R.mipmap.examination);//examination
//
//        Bean bean8 = new Bean();
//        bean8.setString("缴纳党费");
//        bean8.setImg(R.mipmap.pay_party_fees);//pay_party_fees

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView adapter_img;
        private TextView adapter_name;
        private LinearLayout adapter_linear;

        public ViewHolder(View itemView) {
            super(itemView);
            adapter_img = itemView.findViewById(R.id.adapter_img);
            adapter_name = itemView.findViewById(R.id.adapter_name);
            adapter_linear = itemView.findViewById(R.id.adapter_linear);
        }
    }

    //定义点击接口
    public interface onClickLinstener {
        void setOnClick(View view, int position);
    }

}
