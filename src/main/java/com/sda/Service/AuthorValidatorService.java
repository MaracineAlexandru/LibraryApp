package com.sda.Service;

import com.sda.dao.AuthorDao;
import com.sda.model.Author;

public class AuthorValidatorService {
    private AuthorDao authorDao;
    private IOService ioService;

    public AuthorValidatorService(AuthorDao authorDao, IOService ioService) {
        this.authorDao = authorDao;
        this.ioService = ioService;
    }

    public boolean validateAuthorLastName(String lastName){
        boolean isAuthorUnique= authorDao.isAuthorUnique(lastName);
        if (!isAuthorUnique){
            ioService.displayError("Exista deja un autor cu numele acesta de familie!");
            return false;
        }
        return true;

    }
}
