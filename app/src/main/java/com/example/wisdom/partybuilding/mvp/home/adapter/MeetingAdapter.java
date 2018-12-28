package com.example.wisdom.partybuilding.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.bean.home.MeetingBean;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {
    private Context context;
    private List<MeetingBean.MeetingsBean> list;

    //接口监听     待办 FolderAdapter
    MeetingAdapter.onClickLinstener onClickLinstener;


    public void setOnClickLinstener(MeetingAdapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public MeetingAdapter(Context context, List<MeetingBean.MeetingsBean> datalist) {
        this.context = context;
        this.list = datalist;
    }

    @Override
    public MeetingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meeting_adapter_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        MeetingAdapter.ViewHolder viewHolder = new MeetingAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MeetingAdapter.ViewHolder holder, final int position) {

        holder.meetingadaptertiem.setText(list.get(position).getMeetingtitle());


        holder.meeting_adapter_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickLinstener != null) {
                    onClickLinstener.setOnClickaNotes(view, position);
                }
            }
        });
        holder.meeting_adapter_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickLinstener != null) {
                    onClickLinstener.setOnClickaSign(view, position);
                }

            }
        });


        holder.meeting_adapter_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickLinstener != null) {
                    onClickLinstener.setOnClickaLinear(view, position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout meeting_adapter_linear;
        TextView meetingadaptertiem;
        LinearLayout meeting_adapter_notes;
        LinearLayout meeting_adapter_sign;

        public ViewHolder(View itemView) {
            super(itemView);
            meeting_adapter_linear = itemView.findViewById(R.id.meeting_adapter_linear);
            meetingadaptertiem = itemView.findViewById(R.id.meeting_adapter_tiem);
            meeting_adapter_notes = itemView.findViewById(R.id.meeting_adapter_notes);
            meeting_adapter_sign = itemView.findViewById(R.id.meeting_adapter_sign);


        }
    }

    //定义点击接口
    public interface onClickLinstener {

        void setOnClickaNotes(View view, int position);

        void setOnClickaSign(View view, int position);

        void setOnClickaLinear(View view, int position);
    }

}
