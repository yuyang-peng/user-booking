package com.booking.userbooking.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * author : jiaze.chen
 * Date : 2023/2/23 14:57
 **/
@Data
public class RecommendCommodityVo {
    /**
     * 商品编号
     */
    private String recommendCommodityNo;

    /**
     * 商品名称
     */
    private String recommendCommodityName;

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
}
