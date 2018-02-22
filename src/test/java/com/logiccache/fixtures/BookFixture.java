package com.logiccache.fixtures;

import com.logiccache.api.Book;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

public class BookFixture {
    public static final Book ANIMAL_FARM = new Book(
            UUID.fromString("e08d2dbf-8969-4d91-b1de-241140ae3aef"),
            "Animal Farm",
            "George Orwell");

    public static final Book THE_RAGGED_TROUSERED_PHILANTHROPISTS = new Book(
            UUID.fromString("e08d2dbf-8969-4d91-b1de-241140ae3aef"),
            "The Ragged-Trousered Philanthropists",
            "Robert Tressell");

    public static final Book FOR_WHOM_THE_BELL_TOLLS = new Book(
            UUID.fromString("e08d2dbf-8969-4d91-b1de-241140ae3aef"),
            "For Whom the Bell Tolls",
            "Ernest Hemmingway");

    public static final Book OLIVER_TWIST = new Book(
            UUID.fromString("e08d2dbf-8969-4d91-b1de-241140ae3aef"),
            "Oliver Twist",
            "Charles Dickens");


    public static final UUID ID = UUID.fromString("e08d2dbf-8969-4d91-b1de-241140ae3aef");

    public static final List<Book> BOOKS = Lists.newArrayList(ANIMAL_FARM,
            THE_RAGGED_TROUSERED_PHILANTHROPISTS,
            FOR_WHOM_THE_BELL_TOLLS,
            OLIVER_TWIST);
}
