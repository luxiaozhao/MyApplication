package com.example.wisdom.partybuilding.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        String meetingtitle = list.get(position).getMeetingtitle();
        Log.e("TTT","meetingtitle:"+meetingtitle);
        holder.meetingadaptertiem.setText(list.get(position).getMeetingtitle());

//        try {
//            list.get(position).getReleasetime();
//            holder.notice_adapter_time.setText();
//
//        }catch (Exception e){}


//        try {
//            long releasetime = list.get(position).getReleasetime();
//            long releasetimess = releasetime / 1000;
//            String releasetimes = DateUtils.timesTwo(releasetimess + "");
//            holder.notice_adapter_time.setText(releasetimes);
//
//        } catch (Exception e) {
//        }

//        holder.notice_adapter_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onClickLinstener != null) {
//                    onClickLinstener.setOnClick(view, position);
//                }
//            }
//        });
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
        void setOnClick(View view, int position);
    }

}

//        RecyclerView.Adapter<MeetingAdapter.ViewHolder> {
//    private LayoutInflater mInflater;
//    private List<MeetingBean.MeetingsBean> meetingsBeans;
//
//    //接口监听
//    MeetingAdapter.onClickLinstener onClickLinstener;
//
//    public void setOnClickLinstener(MeetingAdapter.onClickLinstener onClickLinstener) {
//        this.onClickLinstener = onClickLinstener;
//    }
//
//    public MeetingAdapter(Context context, List<MeetingBean.MeetingsBean> datats) {
//        mInflater = LayoutInflater.from(context);
//        meetingsBeans = datats;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ViewHolder(View arg0) {
//            super(arg0);
//        }
//        TextView meetingTiem,meetingSignName;
//        LinearLayout meetingNotes,meetingSign,meetingLinear;
//        ImageView meetingSignImg;
//    }
//
//    @Override
//    public int getItemCount() {
//        return meetingsBeans.size();
//    }
//
//    /**
//     * 创建ViewHolder
//     */
//    @Override
//    public MeetingAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = mInflater.inflate(R.layout.meeting_adapter_layout,//MeetingAdapter
//                viewGroup, false);
//        MeetingAdapter.ViewHolder viewHolder = new MeetingAdapter.ViewHolder(view);
//
//        viewHolder.meetingTiem = (TextView) view
//                .findViewById(R.id.meeting_adapter_tiem);
//
//        viewHolder.meetingNotes = (LinearLayout) view
//                .findViewById(R.id.meeting_adapter_notes);
//        viewHolder.meetingSign = (LinearLayout) view
//                .findViewById(R.id.meeting_adapter_sign);
//
//
//        viewHolder.meetingSignName = (TextView) view
//                .findViewById(R.id.meeting_adapter_sign_name);
//
//
//        viewHolder.meetingSignImg = (ImageView) view
//                .findViewById(R.id.meeting_adapter_sign_img);
//        viewHolder.meetingLinear = (LinearLayout) view
//                .findViewById(R.id.meeting_adapter_linear);
//        return viewHolder;
//    }
//
//    /**
//     * 设置值
//     */
//    @Override
//    public void onBindViewHolder(final MeetingAdapter.ViewHolder viewHolder, final int position) {
//
//        viewHolder.meetingTiem.setText(meetingsBeans.get(position).getMeetingtitle());
////        if (mDatas.get(position).getFunctionname().equals("中央精神")) {
////            viewHolder.mImg.setImageResource(R.mipmap.centralspirit);
////        } else if (mDatas.get(position).getFunctionname().equals("党组声音")) {
////            viewHolder.mImg.setImageResource(R.mipmap.r44);
////        } else if (mDatas.get(position).getFunctionname().equals("党委新闻")) {
////            viewHolder.mImg.setImageResource(R.mipmap.dangweixinwen);
////        } else if (mDatas.get(position).getFunctionname().equals("基层交流")) {
////            viewHolder.mImg.setImageResource(R.mipmap.grassroots_exchange);
////        } else if (mDatas.get(position).getFunctionname().equals("学习园地")) {
////            viewHolder.mImg.setImageResource(R.mipmap.g66);
////        } else if (mDatas.get(position).getFunctionname().equals("党务知识")) {
////            viewHolder.mImg.setImageResource(R.mipmap.r33);
////        } else if (mDatas.get(position).getFunctionname().equals("在线考试")) {
////            viewHolder.mImg.setImageResource(R.mipmap.examination);
////        } else if (mDatas.get(position).getFunctionname().equals("缴纳党费")) {
////            viewHolder.mImg.setImageResource(R.mipmap.pay_party_fees);
////        } else if (mDatas.get(position).getFunctionname().equals("三会一课")) {
////
////            viewHolder.mImg.setImageResource(R.mipmap.r22);
////        } else {
////
//////            holder.adapter_linear.setVisibility(View.GONE);
////        }
//
//        viewHolder.meetingNotes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onClickLinstener != null) {
//                    onClickLinstener.setOnClickaNotes(view, position);
//                }
//            }
//        });
//
//        viewHolder.meetingSign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onClickLinstener != null) {
//                    onClickLinstener.setOnClickaSign(view, position);
//                }
//            }
//        });
//          viewHolder.meetingLinear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onClickLinstener != null) {
//                    onClickLinstener.setOnClickaLinear(view, position);
//                }
//            }
//        });
//    }
//    //定义点击接口
//    public interface onClickLinstener {
//        void setOnClickaNotes(View view, int position);
//        void setOnClickaSign(View view, int position);
//        void setOnClickaLinear(View view, int position);
//    }
//}
