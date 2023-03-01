package com.booking.userbooking.controller;

import com.booking.userbooking.util.FileNameUtil;
import com.booking.userbooking.util.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * author : jiaze.chen
 * Date : 2023/2/21 9:36
 **/
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadFileController {
    private final String[] arrsuffix = {"PNG","JPG","JPEG","BMP","GIF","SVG"};

    @RequestMapping(value = "/photoUpload")
    public ResultObject<Object> upload(@RequestParam("multipartFile") MultipartFile file, HttpServletRequest request) {
        ResultObject<Object> res = new ResultObject<>();
        try {
            if (file.isEmpty()) {
                res.setMsg("上传图片服务器内容为空");
                return res;
            } else {
                String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                if (!Arrays.asList(arrsuffix).contains(suffix.toUpperCase())) {
                    res.setMsg("请选择正确格式的文件");
                    return res;
                }
                //获取文件名称
                String filenameUp = file.getOriginalFilename();

                //定义要上传文件 的存放路径
                String property = System.getProperty("user.dir");
                property = property + "/src/main/resources/static/images";
                String localPath = property;
                filenameUp = FileNameUtil.getFileName(filenameUp);
                File dest = new File(localPath);
                if (!dest.exists()) {
                    dest.mkdirs();
                }
                dest = new File(localPath,filenameUp);
                //拷贝文件到指定路径储存
                FileUtils.copyInputStreamToFile(file.getInputStream(),dest);
                res.setData(dest);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回
        res.setMsg("未知错误导致上传失败");
        return res;
    }
}
