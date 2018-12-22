package org.bookity.service.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Component
public class BookityUtil {
    private final static Logger logger = LoggerFactory.getLogger(BookityUtil.class);
    @Value("${bookity.book.pagesize}")
    private String bookPerPageCount;
    @Value("${interceptor.exclude.paths}")
    private String excludePathPatterns;

    public int getBookPageSize() {
        int perPageCount = 10;
        try {
            perPageCount = Integer.parseInt(bookPerPageCount);
        } catch (NumberFormatException e) {
            logger.error("Invalid bookity.book.pagesize provided: " + bookPerPageCount);
        }
        return perPageCount;
    }

    public List<String> getExcludePathPatterns() {
        if (StringUtils.isNotBlank(excludePathPatterns)) {
            String[] excludePathArray = StringUtils.split(excludePathPatterns, ",");
            return new ArrayList<>(Arrays.asList(excludePathArray));
        }
        return new ArrayList<>();
    }
}
