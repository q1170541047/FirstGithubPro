package com.yiche.baselibrary.util.pro;

/**
 * Created by PVer on 2017/9/30.
 */

public class IntentKeys {
    //URL
    public static String KEY_USER = "user";// 用户信息
    public static final String TOKEN = "mytoken";
    public static final String CONFIG = "config";
    public static boolean IS_PRINT_LOG = true;//设置是否打印日志
    public static String COMPRESS_PHOTO = "compress_photo";//压缩图片缓存地址
    //URL
    public static String MYJS = "myjs";// 用户信息
    public static final String JSURL = "JSURL";
    public static final String URL = "url";
    public static final String BANK = "bank";
    public static final String CARTYPE = "car_type";
    public static final String USERREALITION = "userrealition";
    public static final String REALITIONSHIP = "realitionship";
    public static final int pageSize = 10;
    public static String KEY_GPS = "key_gps_int";//GPS
    public static String KEY_MESSAGE = "key_message";//透传消息数量统计
    public static final String MESSAGE_DATA = "message_data"; //token
    public static final String TITLE = "title"; //token
    public static final String FINANCIAL = "financial";
    public static final String NOFINANCIAL = "nofinancial";

    public static final String MERCHANTID = "merchantId";
    public static final String MERCHANTNAME = "merchantname";
    public static final String RONGZIPRODUCT = "RONGZIPRODUCT";

    //picc
    //car_type_picc,edulvl_picc,mrtlstat_picc,job_picc,modelcode_picc,no_agency_occptn_picc,
    // agency_occptn_picc,living_status_picc,estate_type_picc,estate_property_picc,job_type_picc,
    // is_together_picc,relationship_picc
    public static final String CAR_TYPE_PICC = "car_type_picc";
    public static final String EDULVL_PICC = "edulvl_picc";
    public static final String MRTLSTAL_PICC = "mrtlstat_picc";
    public static final String JOB_PICC = "job_picc";
    public static final String MODELCODE_PICC = "modelcode_picc";
    public static final String NO_AGENCY_OCCPTN_PICC = "no_agency_occptn_picc";
    public static final String AGENCY_OCCPTN_PICC = "agency_occptn_picc";
    public static final String LIVING_STATUS_PICC = "living_status_picc";
    public static final String ESTATE_TYPE_PICC = "estate_type_picc";
    public static final String ESTATE_PROPERTY_PICC = "estate_property_picc";
    public static final String JOB_TYPE_PICC = "job_type_picc";
    public static final String IS_TOGETHER_PICC = "is_together_picc";
    public static final String RELATIONSHIP_PICC = "relationship_picc";
    public static final String GPS_VENDOR_PICC = "gps_vendor";//gps配置
    public static final String SEX = "sex";//性别
    public static final String POLITICAL = "political";//政治面貌
    public static final String MRTLSTAT = "mrtlstat";//婚姻
    public static final String EDULVL = "edulvl";//学历
    public static final String RELTSHIP = "reltship";//社会关系
    public static final String BANK_ACCOUNT_TYPE = "bank_account_type";//银行账户类型
    public static final String BANK_ACCOUNT_USE = "bank_account_use";//银行账户用途
    public static final String THIRD_SERVICE_TYPE = "third_service_type";//银行账户用途
    public static final String PERIOD = "period";//贷款期数
    public static final String CAR_TYPE = "car_type";//车辆类型

    public static final String CONTACT_TYPE = "contact_type";

    public static final String CARDEALER = "cardealer";
    public static final String OPTION_CONFIG = "optionconfig";

    public static class UserSp {
        public static final String NAME = "user"; //sp文件名
        public static final String USER_NAME = "user_name"; //sp文件名
        public static final String USER = "user"; //用户信息
        public static final String TOKEN = "token"; //token
        public static final String GTALIAS = "gtAlias"; //token
        public static final String APPCONFIG = "appConfig"; //token


    }
//
//    //车辆估价结果
//    public static final String EVALUATION_RESULT = "evaluationResult";
//
//    //已收购车列表
//    public static final String ACQUIRED_CARS = "acquiredCars";
//
//    //选择的已收购车辆
//    public static final String SELECTED_ACQUIRED_CAR = "selectedAcquiredCar";
//
//    //车
//    public static final String CAR = "car";
//
//    //车品牌
//    public static final String CAR_BRAND = "carBrand";
//
//    //车系
//    public static final String CAR_SERIES = "carSeries";
//
//    //车Model
//    public static final String CAR_MODEL = "carModel";
//
//    //城市
//    public static final String CITY = "city";
//
//    //不限
//    public static final String UNLIMIT = "unlimit";
//
//    //从哪个Activity过来
//    public static final String FROM_ACTIVIY = "fromActivity";
//
//    //前往哪个页面
//    public static final String TO_ACTIVITY = "toActivity";
//
//    //哪种方式获取User信息：刷新，自动登录，主动登录 ...
//    public static final String WAY_GET_USER = "wayGetUser";
//
//    //短信验证码
//    public static final String SMS_CODE = "smsCode";
//
//    //是否重新认证
//    public static final String RE_AUTH = "reAuth";
//
//    //电话号码
//    public static final String PHONE_NUMBER = "phoneNumber";
//
//    //图形验证码
//    public static final String IMAGE_CODE = "imageCode";
//
//    //图形验证码Auth
//    public static final String IMAGE_AUTH = "imageAuth";
//
//    //短信验证码Auth
//    public static final String MOBILE_AUTH = "mobileAuth";
//
//    //BorrowerID
//    public static final String BORROWER_ID = "borrowerId";
//
//    //主页面Tab 索引
//    public static final String TAB_INDEX = "tabIndex";
//
//    //打款申请和审核区分标记
//    public static final String FLAG = "role_id";
//    public static final String LOCATING = "locating";
//    public static final String FAILED = "failed";
//    public static final String SUCCESS = "success";

//    public static final String ACTIVITY_TYPE="activity_type";//跳转type
//    public static final int ACTIVITY_NUMBER_ONE=11;//设置fragment界面--点击自己
//    public static final int ACTIVITY_NUMBER_TWO=22;//设置fragment界面--点击下属
//
//    public static String KEY_MESSAGE="key_message";//透传消息数量统计
//    public static final int RESULT_VERSION = 100;
//
//
//    /**
//     * 使用servic的hashMap对象
//     */
//    public static final String RESULT_HOME = "8";//家访视频
//    public static final String RESULT_FACE = "7";//面签视频


//
//    public static final int CREDIT= 3; //征信查询授权书
//    public static final int RLATTORNEY= 4; //授权书签字照
//    public static final int FACE = 5; //客户正脸照
//    public static final int LICENSE = 6; //本人驾驶证


//
//    public static final int INSURANCEAPPLICATIONDATA= 70; //投保单
//    public static final int IDPOSITIVEDATA = 80; //银行流水
//    public static final int INCOMEDATA = 90; //收入相关资料
//    public static final int Bankcard= 100; //银行卡卡号面
//    public static final int Approval = 110; //合格证
//    public static final int Vehiclereport = 120; //车辆评估报告
//    public static final int RELATIONSHIP = 130; //结婚证等关系证明
//    public static final int INSURANCEAPPLICATION = 140; //车辆抵押合同
//    public static final int IDPOSITIVE = 150; //租赁合同
//    public static final int INCOME = 160; //客户车辆买卖合同
//    public static final int IDOPPOSITE= 170; //商业车险保单
//    public static final int BANKCARD = 180; //首付款凭证
//    public static final int APPROVAL = 190; //购车协议
//    public static final int VEHICLEREPORT = 200; //车辆登记证书


}
