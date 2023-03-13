package com.booking.userbooking.mapper;

import com.booking.userbooking.pojo.BookInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.booking.userbooking.vo.BookInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author jiaze.chen
* @description 针对表【book_info(预约表)】的数据库操作Mapper
* @createDate 2023-02-21 16:12:59
* @Entity com.booking.userbooking.pojo.BookInfo
*/
@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    List<BookInfoVo> getAllBookingByBarberNo(@Param("param") String param,@Param("barberNo") String barberNo);

    Integer insertBook(BookInfo bookInfo);
}




