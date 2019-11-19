package com.mchaw.tauruspay.common;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/26 0026 13:54
 */
public class Constant {
    /**
     * http缓存最大Size
     */
    public static final int HTTP_RESPONSE_CACHE_MAX_SIZE = 10 * 1024 * 1024;

    /**
     * 登录session
     */
    public static final String PRE_SESSION_KEY = "session_key";

    /**
     * API环境
     */
    public static final String ENVIRONMENT = "environment";
    public static final String ENVIRONMENT_CUSTOM = "environment_custom";

    /**
     * Intent常量
     */
    public static final String INTENT_ID = "id";
    public static final String INTENT_NAME = "name";
    public static final String INTENT_URL = "url";
    public static final String INTENT_STATUE = "statue";
    public static final String INTENT_TYPE = "type";
    public static final String INTENT_IS_HOT = "is_hot";
    public static final String INTENT_CONTENT = "content";
    public static final String INTENT_PROVINCE = "province";
    public static final String INTENT_CITY = "city";
    public static final String INTENT_MSG = "msg";
    public static final String INTENT_TEXT = "text";
    public static final String INTENT_EDIT = "edit";
    public static final String INTENT_BEAN = "bean";
    public static final String INTENT_DATE = "date";
    public static final String INTENT_VISIBLE = "visible";
    public static final String INTENT_URI = "uri";
    public static final String INTENT_POSITION = "position";
    public static final String INTENT_LIST = "list";
    public static final String INTENT_COMPANY = "company";
    public static final String TYPE = "type";
    public static final String LOTTERCODE = "LOTTERCODE";
    public static final String INTENT_YEAR = "year";
    public static final String INTENT_MONTH = "month";
    public static final String INTENT_DAY = "day";
    public static final String INTENT_BOOLEAN = "boolean";
    public static final String INTENT_EVENTID = "eventId";
    public static final String INTENT_SEASONID = "seasonId";
    public static final String INTENT_ROUND = "round";
    public static final String INTENT_STAGE = "stage";
    public static final String INTENT_ISEMOJI = "isEmoji";
    public static final String INTENT_SEND = "send";
    public static final String INTENT_TAG = "tag";

    /**
     * 比分比赛状态-进行中
     */
    public static final int SCORE_MATCH_STATUE_DOING = 0;
    /**
     * 比分比赛状态-未开始
     */
    public static final int SCORE_MATCH_STATUE_DNS = 1;
    /**
     * 比分比赛状态-已完成
     */
    public static final int SCORE_MATCH_STATUE_DONE = 2;
    /**
     * ActivityForResult 返回值
     */
    public static final int RECHARGE_NEXT_FRAGMENT_BACK = 100;

    /**
     * 二维码库界面状态值
     */
    public static final int PAGE_NORMAL_STATE = 101;
    public static final int PAGE_DELETE_STATE = 102;

}
