package com.booking.userbooking.http.request;

import lombok.Data;

/**
 * author : jiaze.chen
 * Date : 2023/2/21 16:54
 **/
@Data
public class UserLoginParam {
    private String userName;
    private String password;
    private String type;
}
