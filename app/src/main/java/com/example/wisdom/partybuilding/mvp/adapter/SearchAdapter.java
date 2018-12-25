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
import com.example.wisdom.partybuilding.mvp.bean.home.PartyknowledgeBean;
import com.example.wisdom.partybuilding.utils.DateUtils;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<DynamicBean.NewsBean> list;

    //接口监听     待办 FolderAdapter
    SearchAdapter.onClickLinstener onClickLinstener;


    public void setOnClickLinstener(SearchAdapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public SearchAdapter(Context context, List<DynamicBean.NewsBean> datalist) {
        this.context = context;
        this.list = datalist;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_adapter_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        SearchAdapter.ViewHolder viewHolder = new SearchAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final SearchAdapter.ViewHolder holder, final int position) {
        holder.notice_adapter_name.setText(list.get(position).getTitile());
        holder.notice_adapter_type.setVisibility(View.VISIBLE);
        holder.notice_adapter_type.setText(list.get(position).getTypename()+"");
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
private TextView notice_adapter_type;
        public ViewHolder(View itemView) {
            super(itemView);
            notice_adapter_name = itemView.findViewById(R.id.notice_adapter_name);
            notice_adapter_time = itemView.findViewById(R.id.notice_adapter_time);
            notice_adapter_type= itemView.findViewById(R.id.notice_adapter_type);
            notice_adapter_layout = itemView.findViewById(R.id.notice_adapter_layout);
        }
    }

    //定义点击接口
    public interface onClickLinstener {
        void setOnClick(View view, int position);
    }

}
