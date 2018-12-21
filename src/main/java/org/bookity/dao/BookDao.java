package org.bookity.dao;

import org.bookity.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
public interface BookDao extends CrudRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    Page<Book> getBooksByAuthorLikeOrTitleLikeOrCategoryLikeOrIsbnLike(String author, String title, String category, String isbn, Pageable pageable);
}