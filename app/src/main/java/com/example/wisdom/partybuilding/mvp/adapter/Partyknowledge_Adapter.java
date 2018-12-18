package com.example.wisdom.partybuilding.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.utils.DateUtils;

import java.util.List;

public class Partyknowledge_Adapter extends RecyclerView.Adapter<Partyknowledge_Adapter.ViewHolder> {
    private Context context;
    private List<DynamicBean.NewsBean> list;

    //接口监听     通知 FolderAdapter
    Partyknowledge_Adapter.onClickLinstener onClickLinstener;


    public void setOnClickLinstener(Partyknowledge_Adapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public Partyknowledge_Adapter(Context context, List<DynamicBean.NewsBean> datalist) {
        this.context = context;
        this.list = datalist;
    }

    @Override
    public Partyknowledge_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_adapter_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        Partyknowledge_Adapter.ViewHolder viewHolder = new Partyknowledge_Adapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final Partyknowledge_Adapter.ViewHolder holder, final int position) {
        holder.notice_adapter_name.setText(list.get(position).getTitile());
        try {
            long releasetime = list.get(position).getReleasetime();
            long    releasetimess =   releasetime/1000;
            String releasetimes = DateUtils.timesTwo(releasetimess + "");
            holder.notice_adapter_time.setText(releasetimes);

        } catch (Exception e) {
        }

        holder.notice_adapter_layout.setOnClickListener(new View.OnClickListener() {
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

        private TextView notice_adapter_name;
        private TextView notice_adapter_time;
        private LinearLayout notice_adapter_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            notice_adapter_name = itemView.findViewById(R.id.notice_adapter_name);
            notice_adapter_time = itemView.findViewById(R.id.notice_adapter_time);
            notice_adapter_layout = itemView.findViewById(R.id.notice_adapter_layout);
        }
    }

    //定义点击接口
    public interface onClickLinstener {
        void setOnClick(View view, int position);
    }

}
