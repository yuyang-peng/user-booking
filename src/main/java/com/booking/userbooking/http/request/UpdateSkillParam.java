package com.booking.userbooking.http.request;

import lombok.Data;

/**
 * author : jiaze.chen
 * Date : 2023/2/23 10:45
 **/
@Data
public class UpdateSkillParam {
    /**
     * 技能编号
     */
    private String skillNo;

    /**
     * 技能名称
     */
    private String skillName;

    /**
     * 金额
     */
    private Integer skillAmount;
}
