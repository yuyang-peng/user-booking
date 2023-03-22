package com.booking.userbooking.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jiaze.chen
 */
public class Constant {
    public static String loginUrl="/login.html";
    public static String TEACHER_LOGIN_URI="/api/teacherLogin";
    public static String NULL_URI="/404.html";
    public static String STUDENT_INDEX="/student-index.html";
    //     /test.html
    public static String MY_SCORE="my-course.html";
    //  /my-grades.html
    public static String SCORE="/my-grades";
    public static String ERROR_URI="/error";
    public static String AESSET="/assets/";
    //学生登录请求地址
    public static String STUDENT_LOGIN_URL="/api/studentLogin";
    //学生退出请求地址
    public static String STUDENT_LOGINOUT_URL="/api/studentLoginOut";
    //查询我的总成绩
    public static String GET_MY_COURSE="/course/getCourseInfoByStudentNo";
    //查询我的成绩
    public static String GET_MY_SCORE="/grades/getCourseInfoByStudentNo";
    //统一返回码--修改密码
    public static String PASSWORD_RETUEN_CODE="-1";
    //统一返回码--成功
    public static String SUCCESS_RETUEN_CODE="0";
    //统一返回码--失败
    public static String FAILURE_RETUEN_CODE="-9999";
    //统一返回码--已存在
    public static String HASE_RETUEN_CODE="1";

    public static String ADMIN_TYPE="0";

    public static String ADMIN_NAME="admin";

    public static String APPID= "wxec15cb622338dfea";

    public static String APPSECRET= "552652553c6fe1b53204f3228c5ff7f9";

    public static final Map<Integer, String> BOOKING_TYPE_TO_TIME = new LinkedHashMap<Integer, String>(){
        {
            put(1, "8:00:00-9:00:00");
            put(2, "9:00:00-10:00:00");
            put(3, "10:00:00-11:00:00");
            put(4, "11:00:00-12:00:00");
            put(5, "14:00:00-15:00:00");
            put(6, "15:00:00-16:00:00");
            put(7, "16:00:00-17:00:00");
            put(8, "17:00:00-18:00:00");
        }
    };



}
