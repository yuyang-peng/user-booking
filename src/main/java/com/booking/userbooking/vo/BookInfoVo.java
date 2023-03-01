package com.booking.userbooking.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * author : jiaze.chen
 * Date : 2023/2/23 13:35
 **/
@Data
public class BookInfoVo {
    /**
     * 理发师编号
     */
    private String barberNo;

    /**
     * 理发师编号
     */
    private String barberName;

    private Double score;

    /**
     * 预约类型 1：上午，2：下午
     */
    private Integer bookingType;

    /**
     * 预约时间
     */
    private String bookingTime;

    /**
     * 技能编号
     */
    private String skillNo;

    /**
     * 技能编号
     */
    private String skillName;

    /**
     * 金额
     */
    private String skillAmount;

    /**
     * 用户ID
     */
    private String openId;

    /**
     * 用户ID
     */
    private String phone;

    /**
     * 预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
