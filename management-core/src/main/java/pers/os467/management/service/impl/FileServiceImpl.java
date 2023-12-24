package pers.os467.management.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.os467.management.service.FileService;
import pers.os467.management.utils.IdWorker;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadIMGFile(MultipartFile multipartFile) {
        IdWorker idWorker = new IdWorker(1, 1);

        long fileId = idWorker.nextId();

        String contentType = multipartFile.getContentType();
        String extraName = contentType.substring(contentType.indexOf("/")+1);

        String fileName = fileId+"."+extraName;

        try {
            multipartFile.transferTo(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
