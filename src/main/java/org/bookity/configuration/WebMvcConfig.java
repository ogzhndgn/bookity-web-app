package org.bookity.configuration;

import org.bookity.interceptor.SessionInterceptor;
import org.bookity.service.util.BookityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Autowired
    SessionInterceptor sessionInterceptor;
    @Autowired
    BookityUtil bookityUtil;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = bookityUtil.getExcludePathPatterns();
        for (String excludePathPattern : excludePathPatterns) {
            logger.info(excludePathPattern);
        }
        registry.addInterceptor(sessionInterceptor).excludePathPatterns(excludePathPatterns);
    }
}