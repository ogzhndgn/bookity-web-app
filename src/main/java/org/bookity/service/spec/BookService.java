package org.bookity.service.spec;

import org.bookity.exception.ServiceException;
import org.bookity.model.Book;
import org.springframework.data.domain.Page;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
public interface BookService {
    Page<Book> getBooksByPage(int page) throws ServiceException;

    Page<Book> searchBooks(String keyword, int page) throws ServiceException;
}
