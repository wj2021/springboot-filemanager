package org.shaofan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义配置
 */
@Component
public class CustomWebConfiguration implements WebMvcConfigurer {
    @Value("${filemanager.rootList}")
    private String rootList;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] roots = rootList.split(",");
        for (int i = 0; i < roots.length; ++i) {
            roots[i] = "file:" + roots[i].trim();
        }

        ResourceHandlerRegistration reg = registry.addResourceHandler("/**");

        reg.addResourceLocations("classpath:/META-INF/resources/",
        "classpath:/resources/", "classpath:/static/", "classpath:/public/", "classpath:/webapp/");
        
        // 将根目录下的资源全部设为可直接访问，注意如果filePath是写死在这里，一定不要忘记尾部的/或者\\，这样才能读取其目录下的文件
        for (String r : roots) {
            reg.addResourceLocations(r);
        }
    }
}