package com.logiccache.core;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.common.collect.Iterables;
import com.logiccache.api.Book;
import com.logiccache.factory.MappingManagerFactory;
import com.logiccache.fixtures.BookFixture;
import org.junit.Before;
import org.junit.Test;

import static com.logiccache.fixtures.BookFixture.BOOKS;
import static com.logiccache.fixtures.BookFixture.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    private BookService service;
    private final Result mockResult = mock(Result.class);

    @Before
    public void setUp() {
        final Session mockSession = mock(Session.class);
        final ResultSet mockResultSet = mock(ResultSet.class);
        final Mapper mockMapper = mock(Mapper.class);
        final MappingManager mockMappingManager = mock(MappingManager.class);
        final MappingManagerFactory mockMappingManagerFactory = mock(MappingManagerFactory.class);
        when(mockMappingManagerFactory.createMappingManager()).thenReturn(mockMappingManager);
        when(mockSession.execute(anyString())).thenReturn(mockResultSet);
        when(mockMappingManager.mapper(eq(Book.class))).thenReturn(mockMapper);
        when(mockMapper.map(eq(mockResultSet))).thenReturn(mockResult);
        service = new BookServiceImpl(mockSession, mockMappingManagerFactory);
    }

    @Test
    public void testRetrieveBook() {
        assertThat(service.retrieveBook(ID.toString())).isEqualTo(BookFixture.ANIMAL_FARM);
    }

    @Test
    public void testRetrieveAllBooks() {
        when(mockResult.all()).thenReturn(BOOKS);
        assertThat(service.retrieveAllBooks()).containsSequence(BOOKS);
    }

}