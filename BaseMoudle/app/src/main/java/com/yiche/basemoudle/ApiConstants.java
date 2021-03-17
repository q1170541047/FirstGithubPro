package com.yiche.basemoudle;


import com.yiche.baselibrary.util.pro.UrlManager;

/**
 * 接口url
 */
public interface ApiConstants {
    /*-------------------------------- host开始 --------------------------------*/
    String BASE_URL = UrlManager.getInstance().getUrl();
//    String BASE_URL = "http://zxchen-api-ycsk.beta.saasyc.com/";

    //    String BASE_H5_URL = "http://niudy-h5-docker-niudy.beta.saasyc.com";
    String BASE_H5_URL = UrlManager.getInstance().geth5Url();
    /**
     * 平台注册协议
     */
    String H5_REGISTER_URL = "/#/ycsk/register";
    /**
     * 隐私协议
     */
    String H5_PRIVACY_URL = "/#/ycsk/privacy";
    /**
     * 关于我们
     */
    String H5_ABOUT_URL = "/#/ycsk/about";
    /**
     * 常见问题与反馈
     */
    String H5_HELP_URL = "https://support.qq.com/products/134338";
    /**
     * 车商金融合作协议
     */
    String H5_COOPERARION_URL = "/#/ycsk/cooperation";
    /**
     * 发送验证码
     */
    String SEND_COED = "/app/v1/sms/sendSms";
    /**
     * 注册
     */
    String USER_REG = "/app/v1/user/regLogin";
    /**
     * 登录
     */
    String USER_LOGIN = "/app/v1/user/login";
    /**
     * 获取用户信息
     */
    String USER_INFO = "/app/v1/user/info";

    /**
     * 首页数据
     */
    String HOME = "/app/v2_1/service/list";
    /**
     * 首页banner
     */
    String HOME_BANNER = "/app/v1/banner/list";
    /**
     * 首页订单数
     */
    String HOME_ORDER = "/app/v1/trade/statistics";
    /**
     * 订单列表
     */
    String HOME_ORDER_LIST = "/app/v1/trade/list";
    /**
     * 消息列表
     */
    String MESSAGE_LIST = "/app/v2_3/message/index";
    /**
     * 更新消息已读
     */
    String NOTIFY_MESSAGE_READ = "/app/v2_3/message/read";
    /**
     * app通用选项配置
     */
    String COMMON_DICT = "/common/dict";
    /**
     * 人保--详情获取
     */
    String TRADE_PICC_DETAIL = "/app/v1/trade/piccDetail";

    /**
     * 获取数据服务列表--目前只有二手车估值次数
     */
    String HOME_DATA_SERVICE = "/app/v1/data/serviceSurplus";
    /**
     * 数据服务-账单记录
     */
    String HOME_DATA_PRODYCT_LIST = "/app/v1/data/Productlist";
    /**
     * 数据服务-消费记录
     */
    String HOME_DATA_CONSUME_LIST = "/app/v1/data/consumeList";
    /**
     * 影像资料绑定
     */
    String MATERIAL_ADD = "/app/v1/material/add";
    /**
     * 影像资料解绑
     */
    String MATERIAL_DELETE = "/app/v1/material/delete";
    /**
     * OCR身份证识别
     */
    String OCR_IDCRAD = "/yiche/ocr/idcard";
    /**
     * OCR营业执照识别
     */
    String OCR_BUSINESSLICENSE = "/yiche/ocr/businessLicense";
    /**
     * OCR银行卡识别
     */
    String OCR_BANKCARD = "/yiche/ocr/bankcard";
    /**
     * OCR行驶证识别
     */
    String OCR_VEHICLELICENSE = "/yiche/ocr/vehicleLicense";
    /**
     * 获取查询配置(大数据查询)
     */
    String GET_BUSINESS_OPTION = "/app/v1/appCredit/appConfig";
    /**
     * 提交查询(大数据查询)
     */
    String SUBMIT_QUERY = "/app/v1/appCredit/submit";
    /**
     * 获取大数据查询列表
     */
    String GET_BIGDATA_LIST = "/app/v1/appCredit/list";
    /**
     * 大数据详情配置
     */
    String BIGDATA_DETAIL_OPTION = "/app/appCredit/detail";
    /**
     * 获取指定用户第三方征信报告详细信息
     */
//    String CREDIT_ALL_DETAIL = "/api/auxiliary/credit/all/detail";
    String CREDIT_ALL_DETAIL = "/app/v1/appCredit/allDetail";
    /**
     * 获取指定用户第三方征信报告详细信息（示例)
     */
    String EXAMPLE_DETAIL = "/app/v1/appCredit/creditDetailExample";
    /**
     * 获取app审核状态
     */
    String GET_APPSTATUS = "/app/v1/version/appStatus";
    /**
     * 获取wx订单信息
     */
    String WX_PARAMETER = "/app/v1/pay/toPay";
    /**
     * 获取wx订单信息
     */
    String GET_AREA = "/common/region";
    /**
     * 贷后对接、GPS商城列表
     */
    String ENTRY_LIST = "/app/v2/entry/list";
    /**
     * 贷后对接、GPS商城列表
     */
    String CARSHOP_DETAIL = "/app/v1/carShop/detail";
    /**
     * 亿车商项目公共配置
     */
    String BUSINESS_TYPE = "/system/dict";
    /**
     * 发布需求--贷后对接、GPS商城、抵押解押代办
     */
    String DEMAND_SEND2 = "/app/v2_1/demand/send";
    /**
     * 发布需求--贷后对接、GPS商城、抵押解押代办
     */
    String DEMAND_SEND = "/app/v2/demand/send";
    /**
     * 委托代办列表
     */
    String DEMAND_LIST = "/app/v2/demand/list";
    /**
     * 代办机构列表
     */
    String AGENCY_LIST = "/app/v2/entry/agencyList";
    /**
     * 获取公司详情--贷后对接、GPS商城
     */
    String COMPANY_INFO = "/app/v2/entry/info";
    /**
     * 申请入驻--贷后对接、GPS商城、抵押解押代办
     */
    String ENTRY_APPLY = "/app/v2_1/entry/apply";
    /**
     * VIN码查询
     */
    String VIN_SUBMIT = "/app/v2/vin/submit";
    /**
     * 获取查询详情
     */
    String VIN_DETAIL = "/app/v2/vin/detail";
    /**
     * 违章查询详情
     */
    String CARILLEGAL_DETAIL = "/app/v2/carIllegal/detail";
    /**
     * 车型库品牌列表
     */
    String CAR_BRAND = "/app/v2/carLib/brand";
    /**
     * 获取车系列表
     */
    String CAR_SERIES = "/app/v2/carLib/series";
    /**
     * 获取车型列表
     */
    String CAR_MODEL = "/app/v2/carLib/model";
    /**
     * 获取车型详细参数
     */
    String CAR_PARAMETER = "/app/v2/carLib/detail";
    /**
     * 获取查询价格配置--vin、违章查询
     */
    String SERVICE_PRICE = "/app/v2/service/getServicePrice";
    /**
     * vin查询记录
     */
    String VIN_LIST = "/app/v2/vin/list";
    /**
     * 违章查询记录
     */
    String ILLEGAL_LIST = "/app/v2/carIllegal/list";
    /**
     * 违章查询提交
     */
    String ILLEGAL_QUERY = "/app/v2/carIllegal/submit";
    /**
     * 获取首页活动公告列表
     */
    String GONGGAO_LIST = "/app/v1/notice/list";
    /**
     * 首页获取公告详情
     */
    String GONGGAO_DETAIL = "/app/v1/notice/detail";
    /**
     * 个人认证详情获取
     */
    String GEREN_RENZHENG_DETAIL = "/app/v2/user/authenticate/info";
    /**
     * 个人认证提交、重新提交
     */
    String GEREN_RENZHENG_SUBMIT = "/app/v2/user/authenticate";
    /**
     * 获取优惠券列表
     */
    String COUPONS_LIST = "/app/v2/coupon/list";
    /**
     * 获取充值id
     */
    String GETCHONGZHI_ID = "/app/v1/recharge/create";
    /**
     * 查询支付结果
     */
    String GETPAY_RESULT = "/app/v1/pay/checkPayResult";
    /**
     * 二手车评估提交
     */
    String CAR_EVALUATE = "/app/v1/jzg/submit";
    /**
     * 获取界面权限
     */
    String PERMISSION_LIST = "/app/v2/user/permission/list";
    /**
     * 生成授权书
     */
    String DSJCX_SCSQS = "/app/v1/appCredit/share";
    /**
     * 获得会员信息
     */
    String MEMBER_INFO = "/app/v2/vip/info";
    /**
     * 获取会员支付id
     */
    String MEMBER_PAYID = "/app/v2/order/createVipOrder";
    /**
     * 会员发起支付
     */
    String MERBER_PAY = "/app/v2/pay/toPay";
    /**
     * 检测手机号是否注册
     */
    String CHECKREG = "/app/v2/user/checkReg";
    /**
     * 企业入驻查询
     */
    String QIYE_APPLYINFO = "/app/v2_1/entry/applyInfo";
    /**
     * 版本控制
     */
    String VERSION_UPDATE = "/app/v1/version/update";
    /**
     * 获得企业信息
     */
    String MY_QIYE_INFO = "/app/v2_2/saas/getCompanyInfo";
    /**
     * 员工管理信息
     */
    String YUANGONG_INFO = "/app/v2_2/saas/staffList";
    /**
     * 客户管理列表
     */
    String CUSTOMER_MANAGE_LIST = "/app/v2_2/customer/list";
    /**
     * 汽车分期列表
     */
    String CARDFQ_LIST = "/app/v2_3/carstageCustomer/list";
    /**
     * 企业菜单配置
     */
    String COMPANY_CONFIG = "/app/v2_2/saas/companyConfig";
    /**
     * 企业设置
     */
    String COMPANY_SET = "/app/v2_2/saas/save";
    /**
     * 解散企业
     */
    String COMPANY_DISSOLVE = "/app/v2_2/saas/companyDissolution";
    /**
     * 获取软件服务信息
     */
    String SOFTWARE_LIST = "/app/v2_2/saas/softwareList";
    /**
     * 开启试用服务
     */
    String START_SOFTWARE = "/app/v2_2/saas/startService";
    /**
     * 客户管理新增、编辑
     */
    String CUSTOMER_SAVE = "/app/v2_2/customer/save";
    /**
     * 提交/编辑汽车分期客户详情
     */
    String CAR_CUSTOMER_SAVE = "/app/v2_3/carstageCustomer/save";
    /**
     * 客户详情获取
     */
    String CUSTOMER_DETAIL = "/app/v2_2/customer/detail";
    /**
     * 汽车分期客户详情获取
     */
    String CAR_CUSTOMER_DETAIL = "/app/v2_3/carstageCustomer/detail";
    /**
     * 设置密码
     */
    String SET_PASSWORD = "/app/v1/user/setPwd";
    /**
     * 我的邀请列表
     */
    String MY_INVITE_LIST = "/app/v2_2/account/inviteUser";
    /**
     * 获取 亿播看点 分类列表
     */
    String GET_NEWS_TITLE = "/common/tag";
    /**
     * 获取 亿播看点 列表
     */
    String GET_NEWS_LIST = "/app/v2_2/article/list";
    /**
     * 获取 亿播看点 的打开详情
     */
    String news_detail = "/app/v2_2/article/appDetail";
    /**
     * 朋友圈列表
     */
    String CIRCLE_LIST = "/app/v2_2/circle/list";
    /**
     * 发布评论
     */
    String COMMENT_SEND = "/app/v2_2/circle/comment/send";
    /**
     * 关注，取关好友
     */
    String CIRCLE_FOLLOW = "/app/v2_2/circle/follow";
    /**
     * 朋友圈详情
     */
    String CIRCLE_INFO = "/app/v2_2/circle/info";
    /**
     * 朋友圈个人中心
     */
    String CIRCLE_CENTER = "/app/v2_2/circle/centre";
    /**
     * 发布朋友圈
     */
    String CIRCLE_PUBLISH = "/app/v2_2/circle/publish";
    /**
     * 朋友圈个人中心用户信息
     */
    String CIRCLE_CENTER_USERINFO = "/app/v2_2/circle/centre/userInfo";
    /**
     * 我的评论通知数量
     */
    String CIRCLE_COMMENT_NUM = "/app/v2_2/circle/comment/num";
    /**
     * 我的评论通知列表
     */
    String CIRCLE_COMMENT_LIST = "/app/v2_2/circle/comment/list";
    /**
     * 读取所有评论数量
     */
    String CIRCLE_COMMENT_READALL = "/app/v2_2/circle/comment/readAll";
    /**
     * 检测首页弹窗活动是否开启
     */
    String CHECK_REGACTIVE = "/app/v2/user/checkRegActive";
    /**
     * 获取服务分享信息
     */
    String SERVER_SHARECONFIG = "/app/v2_3/server/shareConfig";
    /**
     * 贷后对接-贷后需求列表
     */
    String DAIHOU_XUQIU_LIST = "/app/v2_3/loanDemand/list";
    /**
     * 车商圈举报
     */
    String CIRCLE_ACCUSATION = "/app/v2_2/circle/accusation";
    /**
     * 第三方服务-入驻企业列表
     */
    String THIRD_RZQY_LIST = "/app/v2_3/thirdService/entries";
    /**
     * 第三方服务-发布需求列表
     */
    String THIRD_XQFB_LIST = "/app/v2_3/thirdService/demands";
    /**
     * 第三方服务-发布需求
     */
    String THIRD_XUQIU_SEND = "/app/v2_3/thirdService/addDemand";
    /**
     * 第三方服务-发布需求
     */
    String THIRD_RUZHU_SEND = "/app/v2_3/thirdService/applyEntry";
    /**
     * 渠道资金列表
     */
    String CHANNELFUND_LIST = "/app/v2_3/channelFund/list";
    /**
     * 资金渠道入驻
     */
    String CHANNELFUND_APPLY = "/app/v2_3/channelFund/applyEntry";
    /**
     * 资金渠道公司详情
     */
    String CHANNELFUND_DETAIL = "/app/v2_3/channelFund/detail";
    /**
     * 寻车列表
     */
    String LOOKCAR_LIST = "/app/v2_3/lookCar/list";
    /**
     * 寻车
     */
    String LOOKCAR_SEND = "/app/v2_3/lookCar/save";
    /**
     * 寻车详情
     */
    String LOOKCAR_DETAIL = "/app/v2_3/lookCar/info";
    /**
     * 汽车分期影像件配置
     */
    String CARFQ_MEDIA_INFO = "/app/v2_3/saasFileConfig/index";
    /**
     * 新消息数量
     */
    String NEW_MSG_NUM = "/app/v2_3/message/newMsgNum";
    /**
     * 公司详情中拨打电话功能统计
     */
    String COMPANY_CONTACT_CALLPHONE = "/app/v2_3/companyContact/record";
    /**
     * 删除朋友圈自己发布内容
     */
    String CIRCLE_DELETE = "/app/v2_2/circle/del";
    /**
     * 企业认证详情获取-2.4
     */
    String COMPANY_AUTH_INFO = "/app/v2_4/carShop/detail";
    /**
     * 企业认证提交、重新提交-2.4
     */
    String COMPANY_AUTH_SUBMIT = "/app/v2_4/carShop/submit";
    /**
     * APP首页寻车滚动列表
     */
    String LOOKCAR_APPHOMELIST = "/app/v2_4/lookCar/appHomeList";
    /**
     * 我的钱包-提现记录
     */
    String TIXIAN_RECORD_LIST = "/app/v2_4/withdraw/record";
    /**
     * 获取用户IM信息
     */
    String GETIM_INFO = "/app/v2_4/User/getImInfo";
    /**
     * 提交个人信息
     */
    String SAVE_USERINFO = "/app/v2_4/User/saveInfo";
    /**
     * 获取个人信息
     */
    String GET_USERINFO = "/app/v2_4/User/getUserInfo";
    /**
     * 影像资料
     */
    String MATERIAL_INFO = "/app/v1/material/index";
    /**
     * 获取app常用配置项
     */
    String APP_CONFIG = "/common/appConfig/config";
    /**
     * 车商车源添加
     */
    String DEALER_CAR_SEND = "/app/v2_5/carshopCarSource/submit";
    /**
     * 车商车源列表
     */
    String DEALER_CAR_LIST = "/app/v2_5/carshopCarSource/list";
    /**
     * 车商车源举报
     */
    String DEALER_CAR_ACCUSATION = "/app/v2_5/carshopCarSource/accusation";
    /**
     * 寻车标记对接成功
     */
    String XUNCHE_DOCKING = "/app/v2_4/lookCar/appDockingSuccess";
    /**
     * 车商车源标记对接成功
     */
    String DEALERCAR_DOCKING = "/app/v2_5/carshopCarSource/dockingSuccess";
    /**
     * 车商车源详情
     */
    String DEALER_CAR_DETAIL = "/app/v2_5/carshopCarSource/detail";
}