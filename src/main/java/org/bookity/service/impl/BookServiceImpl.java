package org.bookity.service.impl;

import org.bookity.dao.BookDao;
import org.bookity.enums.ServiceError;
import org.bookity.exception.ServiceException;
import org.bookity.model.Book;
import org.bookity.service.spec.BookService;
import org.bookity.service.util.BookityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Service
public class BookServiceImpl implements BookService {

    private final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookDao bookDao;
    @Autowired
    BookityUtil bookityUtil;

    private int bookCountPerPage;

    @PostConstruct
    public void setUp() {
        //TODO this may be removed because it's not needed.
        bookCountPerPage = bookityUtil.getBookPageSize();
    }

    @Override
    public Page<Book> getBooksByPage(int page) throws ServiceException {
        //TODO handle invalid page number. Negative and upper ones.
        try {
            PageRequest pageRequest = PageRequest.of(page - 1, bookCountPerPage);
            return bookDao.findAll(pageRequest);
        } catch (Exception e) {
            logger.error("Error@getBooksByPage ", e);
            throw new ServiceException(ServiceError.ERR_GETTING_BOOKS_PAGE);
        }
    }
}
