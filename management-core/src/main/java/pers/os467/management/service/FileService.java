package pers.os467.management.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 将文件写到服务器磁盘指定文件路径
     * @param multipartFile 需要被转储的文件
     * @return 文件名称
     */
    String uploadIMGFile(MultipartFile multipartFile);

}
