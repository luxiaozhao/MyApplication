package com.example.wisdom.partybuilding.net;

public class URLS {
    /**
     * 服务器地址
     */
//    public static final String BASE_URL = "http://119.80.161.8:9999/FHBE/";
//    public static final String BASE_URL = "http://192.168.1.199:9999/FHBE/";
    public static final String BASE_URL = "http://192.168.1.199:8080/FHBE/";
//  http://192.168.1.199:8080/FHBE/login.ht?username=djys&password=1
    /**
     *  首页——轮播图
     */
    public static final String HOME_CAROUSELMAP = BASE_URL + "mobile/carousel/carousel/carousel.ht";

    /**
     *  首页——动态
     */
    public static final String HOME_DYNAMIC_LIST = BASE_URL + "mobile/mobillefunctions/mobilleFunctions/getFunctions.ht";

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
//  http://127.0.0.1:8080/FHBE/login.ht?username=djys&password=1
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
    public static final String ONLINEEXAM = BASE_URL + "exam/exam/appplan.ht?sid=";

    /*
     * 中央精神
     * */
    public static final String DYNAMICMODULE = "mobile/mobileNews/mobileNews/listnews.ht?pageSize=5&pageIndex=2&itemName=";

    /*
     * 首页——动态(党委新闻,基层交流,学习园地，通知公告)
     * */
    public static final String HOME_DYNAMIC = BASE_URL + "mobile/mobileNews/mobileNews/listnews.ht";

    /*
     * 首页——动态(党务知识)
     * */
    public static final String HOME_PARTYKNOWLEDGE = BASE_URL + "djy/partyWork/partyWork/listapp.ht ";

    /*
     *  首页—— 搜索
     * */
    public static final String HOME_SEARCH = BASE_URL + "mobile/mobileNews/mobileNews/search.ht";
//  //  http://192.168.1.199:9999/FHBE/mobile/mobileNews/mobileNews/search.ht?param=学习&pageSize=20&pageIndex=1


    /*
     * 党委新闻详情
     *   http://192.168.1.199:8080/FHBE/djy/partyNews/partyNews/news.ht?infoId=10000011990139
     * */
    public static final String HOME_NEWS_DETAIL = BASE_URL + "djy/partyNews/partyNews/news.ht?infoId=";
//

    /*
     * 基层交流详情
     *     FHBE/djy/study/study/news.ht？infoId=
     * */
    public static final String HOME_BASICLEVEL_DETAIL = BASE_URL + "djy/study/study/news.ht?infoId=";


    /*
     * 学习园地详情
     *    FHBE/djy/basicSpirit/basicSpirit/news.ht？infoId=
     * */
    public static final String HOME_LEARNING_DETAIL = BASE_URL + "djy/basicSpirit/basicSpirit/news.ht?infoId=";

    /*
     * 党务知识详情页
     *   FHBE/djy/partyWork/partyWork/news.ht?infoId=
     * */
    public static final String HOME_PARTYWORK_DETAIL = BASE_URL + "djy/partyWork/partyWork/news.ht?infoId=";

    /*
     * 通知公告详情页
     *  FHBE/djy/promulgate/promulgate/news.ht?infoId=
     * */
    public static final String HOME_NOTICE_DETAIL = BASE_URL + "djy/promulgate/promulgate/news.ht?infoId=";






    /*
     * 代办
     * //    http://192.168.1.199:8080/FHBE/mobile/bpm/bpmMobileTask/pendingMatters.ht?sid=AC9363F950D03AF78E5798A25D5D6F8E
     * */

    public static final String UPCOMING_LIST = BASE_URL + "mobile/bpm/bpmMobileTask/pendingMatters.ht";


}
