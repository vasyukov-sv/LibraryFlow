package ru.javabegin.training.springlibrary.dao.interfaces;

import ru.javabegin.training.springlibrary.entities.Author;
import ru.javabegin.training.springlibrary.entities.Book;
import ru.javabegin.training.springlibrary.entities.Genre;

import java.util.List;

/**
 * Created by sbt-vasyukov-sv on 25.04.2017 12:49.
 */
public interface BookDAO {

    List getBooks();

    List<Book> getBooks(Author author);

    List<Book> getBooks(String bookName);

    List<Book> getBooks(Genre genre);

    List<Book> getBooks(Character letter);

}
