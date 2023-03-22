package com.booking.userbooking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booking.userbooking.pojo.BarberInfo;
import com.booking.userbooking.pojo.BookInfo;
import com.booking.userbooking.service.BookInfoService;
import com.booking.userbooking.mapper.BookInfoMapper;
import com.booking.userbooking.util.Constant;
import com.booking.userbooking.vo.BarberVo;
import com.booking.userbooking.vo.BookInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author jiaze.chen
* @description 针对表【book_info(预约表)】的数据库操作Service实现
* @createDate 2023-02-21 16:12:59
*/
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService{

    @Override
    public PageInfo<BookInfoVo> getBookingByBarberNo(BarberInfo barberInfo,String param, int page, int limit) {
        PageHelper.startPage(page,limit);
        String barberNo = barberInfo.getBarberNo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        if (Constant.ADMIN_NAME.equals(barberNo)){
            barberNo = "";
        }
        List<BookInfoVo>  bookInfoVos = getBaseMapper().getAllBookingByBarberNo(param,barberNo);
        //拼参数预约时间
        for (BookInfoVo bookInfoVo : bookInfoVos){
            Date tmpTime = bookInfoVo.getCreateTime();
            calendar.setTime(tmpTime);
            // 把日期往后增加一天,整数  往后推,负数往前移动
            calendar.add(Calendar.DATE, 1);
            // 这个时间就是日期往后推一天的结果
            Date onlyTime = calendar.getTime();
            if (bookInfoVo.getBookingType() < 5){
                bookInfoVo.setBookingTime(sdf.format(onlyTime) + "-" + "上午" + "-" + Constant.BOOKING_TYPE_TO_TIME.get(bookInfoVo.getBookingType()));
            }else {
                bookInfoVo.setBookingTime(sdf.format(onlyTime) + "-" + "下午" + "-" + Constant.BOOKING_TYPE_TO_TIME.get(bookInfoVo.getBookingType()));
            }
        }
        return new PageInfo<>(bookInfoVos);
    }
}




