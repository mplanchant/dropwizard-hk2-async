package com.logiccache.core;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.logiccache.api.Book;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton
public class BookServiceImpl implements BookService {

    private Session session;
    private Mapper<Book> mapper;

    @Inject
    public BookServiceImpl(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Book.class);
    }

    @Override
    public Book retrieveBook(String id) {
        return new Book(UUID.randomUUID(), "Animal Farm", "George Orwell");
    }

    @Override
    public List<Book> retrieveAllBooks() {
        final ResultSet resultSet = session.execute("SELECT * FROM books");
        return mapper.map(resultSet).all();
    }
}
