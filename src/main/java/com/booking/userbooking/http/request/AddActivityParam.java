package com.booking.userbooking.http.request;

import lombok.Data;

/**
 * author : jiaze.chen
 * Date : 2023/2/23 8:54
 **/
@Data
public class AddActivityParam {
    /**
     * 活动编号
     */
    private String activityNo;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDescription;

    /**
     * 活动状态 0：停用 1：启用
     */
    private Integer status;

    /**
     * 活动图片
     */
    private String image;
}
