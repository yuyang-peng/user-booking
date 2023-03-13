package com.booking.userbooking.pojo;

import lombok.Data;

import java.util.Date;

/**
 * author : jiaze.chen
 * Date : 2023/3/6 14:01
 **/
@Data
public class WxInfo {
    private String session_key;
    private String openid;
    private Date UpdateTime;
    private String ToUserName;
    private int Status;
}
