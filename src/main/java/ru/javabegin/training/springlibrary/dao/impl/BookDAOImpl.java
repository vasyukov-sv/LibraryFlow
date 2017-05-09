package ru.javabegin.training.springlibrary.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.springlibrary.dao.interfaces.BookDAO;
import ru.javabegin.training.springlibrary.entities.Author;
import ru.javabegin.training.springlibrary.entities.Book;
import ru.javabegin.training.springlibrary.entities.Genre;

import java.util.List;

/**
 * LibraryFlow
 * Created by sbt-vasyukov-sv on 25.04.2017 12:50.
 */
@Component
public class BookDAOImpl implements BookDAO {

    private final SessionFactory sessionFactory;
    private ProjectionList bookProjection;

    @SuppressWarnings("FieldCanBeLocal")
    private List<Book> books;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        bookProjection = Projections.projectionList();
        bookProjection.add(Projections.property("id"), "id");
        bookProjection.add(Projections.property("name"), "name");
        bookProjection.add(Projections.property("image"), "image");
        bookProjection.add(Projections.property("genre"), "genre");
        bookProjection.add(Projections.property("pageCount"), "pageCount");
        bookProjection.add(Projections.property("isbn"), "isbn");
        bookProjection.add(Projections.property("publisher"), "publisher");
        bookProjection.add(Projections.property("author"), "author");
        bookProjection.add(Projections.property("publishYear"), "publishYear");
        bookProjection.add(Projections.property("descr"), "descr");
        bookProjection.add(Projections.property("rating"), "rating");
        bookProjection.add(Projections.property("voteCount"), "voteCount");
    }

    @Transactional
    @Override
    public List<Book> getBooks() {
        return createBookList(createBookCriteria());
    }

    @Transactional
    @Override
    public List<Book> getBooks(Author author) {
        return createBookList(createBookCriteria().add(Restrictions.ilike("author.fio", author.getFio(), MatchMode.ANYWHERE)));
    }

    @Transactional
    @Override
    public List<Book> getBooks(String bookName) {
        return createBookList(createBookCriteria().add(Restrictions.ilike("b.name", bookName, MatchMode.ANYWHERE)));
    }

    @Transactional
    @Override
    public List<Book> getBooks(Genre genre) {
        return createBookList(createBookCriteria().add(Restrictions.ilike("genre.name", genre.getName(), MatchMode.ANYWHERE)));
    }

    @Transactional
    @Override
    public List<Book> getBooks(Character letter) {
        return createBookList(createBookCriteria().add(Restrictions.ilike("b.name", letter.toString(), MatchMode.START)));
    }

    @SuppressWarnings("unchecked")
    private List<Book> createBookList(DetachedCriteria bookListCriteria) {
        Criteria criteria = bookListCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        return criteria.addOrder(Order.asc("b.name"))
                .setProjection(bookProjection)
                .setResultTransformer(Transformers.aliasToBean(Book.class))
                .list();
    }

    private void createAliases(DetachedCriteria criteria) {
        criteria.createAlias("b.author", "author");
        criteria.createAlias("b.genre", "genre");
        criteria.createAlias("b.publisher", "publisher");
    }

    private DetachedCriteria createBookCriteria() {
        DetachedCriteria bookListCriteria = DetachedCriteria.forClass(Book.class, "b");
        createAliases(bookListCriteria);
        return bookListCriteria;
    }
}
