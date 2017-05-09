package ru.javabegin.training.springlibrary.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.springlibrary.dao.interfaces.GenreDAO;
import ru.javabegin.training.springlibrary.entities.Genre;

import java.util.List;

import static ru.javabegin.training.springlibrary.objects.Utils.castList;

@Component
public class GenreDAOImpl implements GenreDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public GenreDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Genre> getGenres() {
        return castList(Genre.class, sessionFactory.getCurrentSession().createCriteria(Genre.class).list());
    }
}