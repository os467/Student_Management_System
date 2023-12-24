package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.service.FileService;
import pers.os467.management.service.UserService;
import pers.os467.management.utils.JwtUser;

import javax.servlet.http.HttpSession;
import java.io.File;


@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Value("${img-base-url}")
    private String imgBaseUrl;

    @PostMapping("/uploadIMG")
    public ResponseEntity uploadIMGFile(@RequestParam("file") MultipartFile multipartFile){
        //将图片写到存储地址
        String fileName = fileService.uploadIMGFile(multipartFile);
        if (fileName != null){
            JwtUser jwtUser = (JwtUser) httpSession.getAttribute(Constant.JWT_USER);
            //更新用户头像
            String avatarUrl = imgBaseUrl + File.separator + fileName;
            int i = userService.updateUserAvatar(avatarUrl, jwtUser);

            if (i > 0){
                return ResponseUtils.getSuccessResult(fileName);
            }
            return ResponseUtils.getFailResult("更新头像失败");
        }
        return ResponseUtils.getFailResult("图片上传失败");
    }

}
