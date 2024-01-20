package pers.os467.management.config;

import io.netty.util.internal.StringUtil;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.MultipartConfigElement;
import java.io.File;

@Configuration
public class FileConfig {

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @Bean
    public MultipartConfigElement multipartConfigElement(){

        MultipartConfigFactory factory = new MultipartConfigFactory();

        if (!StringUtil.isNullOrEmpty(filePath)) {
            File file = new File(filePath);
            if (!file.exists()) {
                boolean dirCreated = file.mkdir();
                if(! dirCreated){
                    throw new RuntimeException("create file " + file.getAbsolutePath() + " failed!");
                }
            }

            // 需要写和执行的权限
            if(! (file.canWrite() && file.canExecute())){
                throw new RuntimeException(file.getAbsolutePath() + " Permission denied!");
            }
        }

        factory.setLocation(filePath);
        return factory.createMultipartConfig();
    }

}
