package com.example.wisdom.partybuilding.mvp.bean.home;

public class SignInBean {


    /**
     * msg : 签到成功
     * meetingname : 支部党员大会
     * meetingaddress : 北京
     * status : success
     * meetingtime : 1545875390000
     */

    private String msg;
    private String meetingname;
    private String meetingaddress;
    private String status;
    private long meetingtime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public String getMeetingaddress() {
        return meetingaddress;
    }

    public void setMeetingaddress(String meetingaddress) {
        this.meetingaddress = meetingaddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getMeetingtime() {
        return meetingtime;
    }

    public void setMeetingtime(long meetingtime) {
        this.meetingtime = meetingtime;
    }
}
