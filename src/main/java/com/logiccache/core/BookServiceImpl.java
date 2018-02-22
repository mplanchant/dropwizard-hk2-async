package com.logiccache.core;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.logiccache.api.Book;
import com.logiccache.factory.MappingManagerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton
public class BookServiceImpl implements BookService {

    private Session session;
    private Mapper<Book> mapper;

    @Inject
    public BookServiceImpl(final Session session, final MappingManagerFactory mappingManagerFactory) {
        this.session = session;
        this.mapper = mappingManagerFactory.createMappingManager().mapper(Book.class);
    }

    @Override
    public Book retrieveBook(final String id) {
        return new Book(UUID.fromString("e08d2dbf-8969-4d91-b1de-241140ae3aef"), "Animal Farm", "George Orwell");
    }

    @Override
    public List<Book> retrieveAllBooks() {
        final ResultSet resultSet = session.execute("SELECT * FROM books");
        return mapper.map(resultSet).all();
    }
}
