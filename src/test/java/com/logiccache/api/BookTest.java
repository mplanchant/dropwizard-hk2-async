package com.logiccache.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static com.logiccache.fixtures.BookFixture.ANIMAL_FARM;
import static com.logiccache.fixtures.BookFixture.OLIVER_TWIST;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/book.json"), Book.class));
        assertThat(MAPPER.writeValueAsString(ANIMAL_FARM)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture("fixtures/book.json"), Book.class)).isEqualTo(ANIMAL_FARM);
    }
}