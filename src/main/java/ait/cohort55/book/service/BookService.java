package ait.cohort55.book.service;

import ait.cohort55.book.dto.AuthorDto;
import ait.cohort55.book.dto.BookDto;

public interface BookService {
    void addBook(BookDto bookDto);

    BookDto findBookByIsbn(String isbn);

    BookDto deleteBookByIsbn(String isbn);

    BookDto updateBookTitle(String isbn, String title);

    Iterable<BookDto> findBooksByAuthor(String authorName);

    Iterable<BookDto> findBooksByPublisher(String publisherName);

    Iterable<AuthorDto> findBookAuthors(String isbn);

    Iterable<String> findPublishersByAuthor(String authorName);

    AuthorDto deleteAuthor(String authorName);
}
