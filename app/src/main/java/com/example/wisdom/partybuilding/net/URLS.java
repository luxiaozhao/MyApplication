package com.example.wisdom.partybuilding.net;

public class URLS {
    /**
     * 服务器地址
     */
//    public static final String BASE_URL = "http://119.80.161.8:9999/FHBE/";
    public static final String BASE_URL = "http://192.168.1.199:9999/FHBE/";

    /**
     * 轮播图
     */
    public static final String HOME_CAROUSELMAP = BASE_URL + "mobile/carousel/carousel/carousel.ht";
    /*
     * 修改密码
     * */
    public static final String CHANGEPASSWORD = BASE_URL + "platform/system/sysUser/uppwd.ht";

    /*
     *  需要用到sid和pid的时候都需要验证一遍，防止过期（git请求）
     * */
    public static final String REVERIFICATION = BASE_URL + "valsession.ht";

    /**
     * 登陆
     */

    public static final String SEND_MESSAGE = BASE_URL + "login.ht";
    /*
     * 个人资料
     *   ?sid=FBFBEF8C665116A47DAE032AC6567D86&pid=14272319760723301X
     *
     * */
    public static final String PERSONALINFORMATION = BASE_URL + "personinfo.jsp";


    /**
     * 首页通知
     */

    public static final String HOMENOTIFICATION = BASE_URL + "mobile/mobileNews/mobileNews/promulgate.ht";
//  http://119.80.161.8:9999/FHBE/mobile/mobileNews/mobileNews/promulgate.ht?sid=9014816C7A3BFDBACE793C975142EFDC


    /*
     * 在线考试
     * */
    public static final String ONLINEEXAM = BASE_URL+"exam/exam/appplan.ht?sid=";
//      http://192.168.1.199:9999/FHBE/exam/exam/appplan.ht?  sid=31B6D8E295BD72ED1878D989C40FABA6&pid=14272319760723301X

//    public static final String ONLINEEXAM = "exam/course/appcourse.ht";
//    http://192.168.1.199:9999/FHBE/mobile/mobileNews/mobileNews/listnews.ht?pageSize=5&pageIndex=2&itemName=中央精神

    /*
     * 中央精神
     * */
    public static final String DYNAMICMODULE = "mobile/mobileNews/mobileNews/listnews.ht?pageSize=5&pageIndex=2&itemName=";

    /*
     * 首页——动态(党委新闻,基层交流,学习园地)
     * */
    public static final String HOME_DYNAMIC = BASE_URL+"mobile/mobileNews/mobileNews/listnews.ht";

    /*
     * 首页——动态(党务知识)
     * */
    public static final String HOME_PARTYKNOWLEDGE = BASE_URL+"djy/partyWork/partyWork/listapp.ht ";


}
