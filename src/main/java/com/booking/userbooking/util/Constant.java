package com.booking.userbooking.util;

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



}
