package org.bookity.controller;

import org.apache.commons.lang3.StringUtils;
import org.bookity.exception.ServiceException;
import org.bookity.model.Book;
import org.bookity.service.spec.BookService;
import org.bookity.service.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Controller
public class BookController {

    private final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;
    @Autowired
    MessageUtil messageUtil;

    @GetMapping(value = "/book-list/{page}")
    public ModelAndView listBooks(@PathVariable("page") int page, @RequestParam(value = "searchtext", required = false) String searchText) {
        ModelAndView modelAndView = new ModelAndView("book");
        try {
            Page<Book> bookPage;
            if (StringUtils.isNotBlank(searchText)) {
                bookPage = bookService.searchBooks(searchText.trim(), page);
                modelAndView.addObject("searchtext", searchText.trim());
            } else {
                bookPage = bookService.getBooksByPage(page);
            }
            int totalPages = bookPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                modelAndView.addObject("pageNumbers", pageNumbers);
            }
            modelAndView.addObject("bookList", bookPage.getContent());
        } catch (ServiceException e) {
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(e.getServiceError().name()));
        }
        return modelAndView;
    }
}
