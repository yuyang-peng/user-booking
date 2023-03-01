package com.booking.userbooking.util;

import cn.hutool.core.util.IdUtil;

/**
 * author : jiaze.chen
 * Date : 2023/2/21 9:35
 **/
public class FileNameUtil {
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return IdUtil.simpleUUID() + FileNameUtil.getSuffix(fileOriginName);
    }
}
