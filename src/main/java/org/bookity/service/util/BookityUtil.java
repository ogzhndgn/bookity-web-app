package org.bookity.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Component
public class BookityUtil {
    private final static Logger logger = LoggerFactory.getLogger(BookityUtil.class);
    @Value("${bookity.book.pagesize}")
    private String bookPerPageCount;

    public int getBookPageSize() {
        int perPageCount = 10;
        try {
            perPageCount = Integer.parseInt(bookPerPageCount);
        } catch (NumberFormatException e) {
            logger.error("Invalid bookity.book.pagesize provided: " + bookPerPageCount);
        }
        return perPageCount;
    }
}
