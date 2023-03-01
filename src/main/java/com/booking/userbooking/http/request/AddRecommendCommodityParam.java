package com.booking.userbooking.http.request;

import lombok.Data;

/**
 * author : jiaze.chen
 * Date : 2023/2/23 14:56
 **/
@Data
public class AddRecommendCommodityParam {
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
}
