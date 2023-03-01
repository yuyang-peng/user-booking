package com.booking.userbooking.service;

import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.BookInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.booking.userbooking.vo.BookInfoVo;
import com.github.pagehelper.PageInfo;

/**
* @author jiaze.chen
* @description 针对表【book_info(预约表)】的数据库操作Service
* @createDate 2023-02-21 16:12:59
*/
public interface BookInfoService extends IService<BookInfo> {

    PageInfo<BookInfoVo> getBookingByBarberNo(BarberInfo barberInfo,String param, int page, int limit);
}
