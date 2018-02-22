package com.logiccache.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logiccache.core.BookService;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.logiccache.fixtures.BookFixture.OLIVER_TWIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookResourceTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private static BookService mockBookService = mock(BookService.class);

    @ClassRule
    public static final ResourceTestRule RULE = ResourceTestRule.builder()
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .addResource(new BookResource(EXECUTOR, mockBookService))
            .build();

    @Before
    public void setUp() {
        when(mockBookService.retrieveAllBooks()).thenReturn(Collections.singletonList(OLIVER_TWIST));
    }

    @Test
    public void testSyncResource() throws JsonProcessingException {
        assertThat(RULE.target("/book/sync").request().get(String.class))
                .isEqualTo(MAPPER.writeValueAsString(Collections.singletonList(OLIVER_TWIST)));
    }

    @Test
    public void testAsyncResource() throws JsonProcessingException {
        assertThat(RULE.target("/book/async").request().get(String.class))
                .isEqualTo(MAPPER.writeValueAsString(Collections.singletonList(OLIVER_TWIST)));
    }

    @Test
    public void testAsyncFutureResource() throws JsonProcessingException {
        assertThat(RULE.target("/book/async_future").request().get(String.class))
                .isEqualTo(MAPPER.writeValueAsString(Collections.singletonList(OLIVER_TWIST)));
    }

    @Test
    public void testSyncFutureResource() throws JsonProcessingException {
        assertThat(RULE.target("/book/sync_future").request().get(String.class))
                .isEqualTo(MAPPER.writeValueAsString(Collections.singletonList(OLIVER_TWIST)));
    }
}