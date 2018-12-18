package com.example.wisdom.partybuilding.mvp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.bean.Bean;
import com.example.wisdom.partybuilding.mvp.bean.Bean1;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {
    private Context context;
    private List<Bean1> list;

    //接口监听     通知 FolderAdapter
    UpcomingAdapter.onClickLinstener onClickLinstener;

    public void setOnClickLinstener(UpcomingAdapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public UpcomingAdapter(Context context, List<Bean1> datalist) {
        this.context = context;
        this.list = datalist;
    }

    @Override
    public UpcomingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.upcoming_adapter_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        UpcomingAdapter.ViewHolder viewHolder = new UpcomingAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final UpcomingAdapter.ViewHolder holder, final int position) {


        holder.upcoming_adapter_name.setText(list.get(position).getName1());

        holder.upcoming_adapter_type.setText(list.get(position).getLeixing());

        holder.upcoming_adapter_name1.setText(list.get(position).getName2());
        holder.upcoming_adapter_while.setText(list.get(position).getShijian());

        int positions = position;
        try {
            if (positions > 6) {
                positions = position - 7;
            }
            switch (positions) {
                case 0:
                    holder.upcoming_adapter_name.setBackgroundResource(R.drawable.text_circle1);
                    break;
                case 1:
                    holder.upcoming_adapter_name.setBackgroundResource(R.drawable.text_circle2);
                    break;
                case 2://"论文",
                    holder.upcoming_adapter_name.setBackgroundResource(R.drawable.text_circle3);
                    break;
                case 3://"著作",
                    holder.upcoming_adapter_name.setBackgroundResource(R.drawable.text_circle4);
                    break;
                case 4:// "专利",
                    holder.upcoming_adapter_name.setBackgroundResource(R.drawable.text_circle5);
                    break;
                case 5://"获奖",
                    holder.upcoming_adapter_name.setBackgroundResource(R.drawable.text_circle6);
                    break;
            }



        } catch (Exception e) {
        }

        holder.upcoming_adapter_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onClickLinstener != null) {
                    onClickLinstener.setOnClick(view, position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout upcoming_adapter_linear;


        private TextView upcoming_adapter_name, upcoming_adapter_type, upcoming_adapter_name1, upcoming_adapter_while;

        public ViewHolder(View itemView) {
            super(itemView);

            upcoming_adapter_linear = itemView.findViewById(R.id.upcoming_adapter_linear);
            upcoming_adapter_name = itemView.findViewById(R.id.upcoming_adapter_name);
            upcoming_adapter_type = itemView.findViewById(R.id.upcoming_adapter_type);
            upcoming_adapter_name1 = itemView.findViewById(R.id.upcoming_adapter_name1);
            upcoming_adapter_while = itemView.findViewById(R.id.upcoming_adapter_while);

        }
    }

    //定义点击接口
    public interface onClickLinstener {
        void setOnClick(View view, int position);
    }

}
