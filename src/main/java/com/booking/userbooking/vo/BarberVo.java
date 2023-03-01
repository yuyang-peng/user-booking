package com.booking.userbooking.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 理发师信息表
 * @TableName barber_info
 */
@TableName(value ="barber_info")
@Data
public class BarberVo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
     * 理发师技能名称
     */
    private String barberSkillNames = "";

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

    /**
     * 后端图片
     */
    private String imageTmp;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    private String updateId;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}