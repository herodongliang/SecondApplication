package com.example.administrator.secondapplication.utils;

/**
 * Created by guadong on 12/7/2016.
 */
public class PathUrlUtils {
    public static String UrlHead="http://tw.chinacloudapp.cn:8000";
    public static String REGISTERPATH="/one_auth/api/user";
    public static String VALIDATION ="/one_auth/api/validation_code";
    public static String ACCESSTOKEN="/one_auth/api/access_tokens";
    public static String USERPHOTO="/one_auth/api/images";
    public static String PROFILE="/one_auth/api/user/profile";
    public static String MAINHEADER="http://tw-feedback-ci.chinacloudapp.cn:8001";//tw-feedback-ci.chinacloudapp.cn    tw.chinacloudapp.cn
    public static String MAINHEADERSECOND="http://tw.chinacloudapp.cn:8001";
    public static String USERLIST="/feedback_star/api/users";
    public static String GETEVALUATIONLIST="/feedback_star/api/evaluative_dimensions";
    public static String CREATEEVALUATION="/feedback_star/api/evaluative_dimensions";
    public static String MOMENTS="/feedback_star/api/moments?page=";
    public static String MOMENTSDETAILS="/feedback_star/api/moments/";
    public static String MOMENTSBEHIND="&page_size=";
    public static String CREATEFEEDBACKREQUEST="/feedback_star/api/feedback_requests";
    public static String GETFEEDBACKLIST="/feedback_star/api/feedback_request:s?page=1&page_size=10";
    public static String MESSAGES="/feedback_star/api/messages";
    public static String DETAILSMESSAGES="/feedback_star/api/messages/";
    public static String GIVEFEEDBACK="/feedback_star/api/feedback";

}
