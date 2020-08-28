package com.sda;

import com.sda.Service.AuthorValidatorService;
import com.sda.Service.BookValidatorService;
import com.sda.Service.IOService;
import com.sda.dao.AuthorDao;
import com.sda.dao.BookDao;
import com.sda.model.Author;
import com.sda.model.Book;
;

public class LibraryApp {

    private IOService ioService;
    private BookDao bookDao;
    private BookValidatorService bookValidatorService;
    private AuthorDao authorDao;
    private AuthorValidatorService authorValidatorService;

    public LibraryApp() {
        this.ioService = new IOService();
        this.bookDao = new BookDao();
        this.authorDao= new AuthorDao();
        this.bookValidatorService= new BookValidatorService(bookDao,ioService);
        this.authorValidatorService= new AuthorValidatorService(authorDao,ioService);

    }

    public void start() {
        boolean shouldContinue= true;
        while (shouldContinue) {
            ioService.displayMenu();

            String userInput = ioService.getUserInput();

            processUserInput(userInput);

            shouldContinue=ioService.getUserInputToContinue();
        }

    }


    private void processUserInput(String userInput) {
        switch (userInput) {
            case "1":
                addBook();
                break;
            case "2":
                addAuthor();
                break;
            case "3":
                deleteABook();
                break;
            case "4":
                deleteAnAuthor();
                break;
            case "5":
                updateABook();
                break;
            case "6":
                updateAnAuthor();
                break;
            case "7":
                ioService.displayInfo("Have a great day!");
                break;
             default:
                 ioService.displayError("Error");
        }
    }

    private void updateAnAuthor() {
        String last_name = ioService.getField("Last name");
        String first_name = ioService.getField("First name");
        Author searchedAuthor = authorDao.findAuthorByLastName(last_name);
        String updatedAuthorLastName = ioService.getField("Updated last name");
        String updateAuthorFirstName = ioService.getField("Update first nam:");
        if(searchedAuthor.getLastName().equals(last_name) && searchedAuthor.getFirstName().equals(first_name)){
            boolean updateSuccesful = updateAuthorDetails(updatedAuthorLastName,updateAuthorFirstName,searchedAuthor);
            if(!updateSuccesful){
                return;
            }
            authorDao.updateEntity(searchedAuthor);
            ioService.displayInfo("Author has been updated!");
            return;
        }
        ioService.displayError("Oops, error!");

    }

    private boolean updateAuthorDetails(String updatedAuthorLastName, String updateAuthorFirstName, Author searchedAuthor) {
        searchedAuthor.setLastName(updatedAuthorLastName);
        searchedAuthor.setFirstName(updateAuthorFirstName);
        return true;
    }

    private void updateABook() {
        String titleUserInput = ioService.getField("Title:");
        Book currentBook = bookDao.findBookByTitle(titleUserInput);
        String updateDescriptionUserInput = ioService.getField("New description:");
        String updateTitleUserInput =ioService.getField("New title:");
            if(currentBook.getTitle().equals(titleUserInput)){
                boolean updateSuccesful =updateBookDetails(updateDescriptionUserInput,updateTitleUserInput,currentBook);
                if(!updateSuccesful){
                    return;
                }
                bookDao.updateEntity(currentBook);
                ioService.displayInfo("update done");
                return;
            }
        ioService.displayError("Eroare! Titlul nu exista");


    }

    private boolean updateBookDetails(String updateDescriptionUserInput,String updateTitleUserInput,Book currentBook) {

        currentBook.setTitle(updateTitleUserInput);
        currentBook.setDescription(updateDescriptionUserInput);
        return true;
    }

    private void deleteAnAuthor() {
        String lastNameUserInput= ioService.getField("Last Name");
        Author author= authorDao.findAuthorByLastName(lastNameUserInput);
        authorDao.deleteEntity(author);
        ioService.displayInfo("Author "+lastNameUserInput+ " has been deleted");
    }

    private void deleteABook() {
        String titleUserInput= ioService.getField("Title:");
        Book book= bookDao.findBookByTitle(titleUserInput);
        bookDao.deleteEntity(book);
        ioService.displayInfo("Book "+titleUserInput+" has been deleted");
    }

    private void addAuthor() {
        String userInputFirstName=ioService.getField("First name of the author:");
        String userInputLastName=ioService.getField("Last name of the author:");

        Author author = new Author(userInputFirstName,userInputLastName);

        if (authorValidatorService.validateAuthorLastName(author.getLastName())){
            authorDao.saveEntity(author);
        }
    }

    private void addBook() {
        String titleUserInput= ioService.getField("Title:");
        String descriptionUserInput= ioService.getField("Description:");
        String authorUserInput= ioService.getField("Author:");

        Author author = authorDao.findAuthorByLastName(authorUserInput);
        Book book = new Book(titleUserInput,descriptionUserInput);
        if (bookValidatorService.validateBook(book)){
            book.setAuthor(author);
            bookDao.saveEntity(book);
            ioService.displayInfo("Book has been added!");
        } else {
            ioService.displayError("Error, author does not exist! Please add an author first and then retry");
            addAuthor();
        }

    }
}
