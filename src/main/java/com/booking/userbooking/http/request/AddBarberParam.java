package com.booking.userbooking.http.request;

import lombok.Data;

/**
 * author : jiaze.chen
 * Date : 2023/2/22 15:57
 **/
@Data
public class AddBarberParam {
    /**
     * 理发师编号
     */
    private String barberNo;

    /**
     * 理发师姓名
     */
    private String barberName;

    /**
     * 理发师登录密码
     */
    private String barberPassword;

    /**
     * 理发师年龄
     */
    private Integer barberAge;

    /**
     * 理发师电话
     */
    private String barberPhone;

    /**
     * 理发师技能
     */
    private String barberSkillNo;


    /**
     * 剩余上午可预约数量
     */
    private Integer morningBookingNum;

    /**
     * 剩余下午可预约数量
     */
    private Integer afternoonBookingNum;

    /**
     * 评分
     */
    private Double score;

    /**
     * 图片
     */
    private String image;
}
