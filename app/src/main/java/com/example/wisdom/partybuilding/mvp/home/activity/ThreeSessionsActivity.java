package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreeSessionsActivity extends BaseActivity {
    @BindView(R.id.three_sessions_branch_party_assembly)
    LinearLayout threeSessionsBranchPartyAssembly;
    @BindView(R.id.three_sessions_branch_committee)
    LinearLayout threeSessionsBranchCommittee;
    @BindView(R.id.three_sessions_party_group_meeting)
    LinearLayout threeSessionsPartyGroupMeeting;
    @BindView(R.id.three_sessions_party_class)
    LinearLayout threeSessionsPartyClass;

    public static void start(Context context) {
        Intent intent = new Intent(context, ThreeSessionsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_three_sessions;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @OnClick({R.id.three_sessions_branch_party_assembly, R.id.three_sessions_branch_committee, R.id.three_sessions_party_group_meeting, R.id.three_sessions_party_class})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.three_sessions_branch_party_assembly:
                SessionsItemActivity.start(activity,"支部党员大会");
                finish();
                break;
            case R.id.three_sessions_branch_committee:
                SessionsItemActivity.start(activity,"支部委员会");
                finish();
                break;
            case R.id.three_sessions_party_group_meeting:
                SessionsItemActivity.start(activity,"党小组会");
                finish();
                break;
            case R.id.three_sessions_party_class:
                SessionsItemActivity.start(activity,"党课");
                finish();
                break;
        }
    }
}
