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
}
