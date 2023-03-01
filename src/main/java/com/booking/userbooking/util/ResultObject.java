package com.booking.userbooking.util;

import lombok.Data;

/**
 * @author jiaze.chen
 */
@Data
public class ResultObject<T> {
    private String code;
    private String msg;
    private T data;
    private Long count;
}
