package ru.javabegin.training.springlibrary.objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.springlibrary.dao.interfaces.BookDAO;
import ru.javabegin.training.springlibrary.entities.Book;

import java.util.List;

@Component
@Scope("singleton")
public class LibraryFacade {


    private final BookDAO bookDAO;

    private final SearchCriteria searchCriteria;

    private List<Book> books;

    @Autowired
    public LibraryFacade(BookDAO bookDAO, SearchCriteria searchCriteria) {
        this.bookDAO = bookDAO;
        this.searchCriteria = searchCriteria;
    }


    public List<Book> getBooks() {
        if (books == null) {
            books = bookDAO.getBooks();
        }
        return books;
    }

    public void searchBooksByLetter() {
        books = bookDAO.getBooks(searchCriteria.getLetter());
    }


}
