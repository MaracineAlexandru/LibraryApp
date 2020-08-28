package com.sda.Service;

import com.sda.dao.BookDao;
import com.sda.model.Book;

public class BookValidatorService {
    private BookDao bookDao;
    private IOService ioService;

    public BookValidatorService(BookDao bookDao, IOService ioService) {
        this.bookDao = bookDao;
        this.ioService = ioService;
    }

    public boolean validateBook(Book book) {
        return validateName(book.getTitle());
    }

    private boolean validateName(String title) {
        boolean isTitleUnique = bookDao.isTitleUnique(title);
        if (!isTitleUnique) {
        ioService.displayError("There is another book with this name");
        }
        return isTitleUnique;
    }
}
