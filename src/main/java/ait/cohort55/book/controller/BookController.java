package ait.cohort55.book.controller;

import ait.cohort55.book.dto.AuthorDto;
import ait.cohort55.book.dto.BookDto;
import ait.cohort55.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    @GetMapping("/book/{isbn}")
    public BookDto findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @DeleteMapping("/book/{isbn}")
    public BookDto deleteBookByIsbn(@PathVariable String isbn) {
        return bookService.deleteBookByIsbn(isbn);
    }

    @PatchMapping("/book/{isbn}/title/{title}")
    public BookDto updateBookTitle(@PathVariable String isbn, @PathVariable String title) {
        return bookService.updateBookTitle(isbn, title);
    }

    @GetMapping("/books/author/{authorName}")
    public Iterable<BookDto> findBooksByAuthor(@PathVariable String authorName) {
        return bookService.findBooksByAuthor(authorName);
    }

    @GetMapping("/books/publisher/{publisherName}")
    public Iterable<BookDto> findBooksByPublisher(@PathVariable String publisherName) {
        return bookService.findBooksByPublisher(publisherName);
    }

    @GetMapping("/authors/book/{isbn}")
    public Iterable<AuthorDto> findBookAuthors(@PathVariable String isbn) {
        return bookService.findBookAuthors(isbn);
    }

    @GetMapping("/publishers/author/{authorName}")
    public Iterable<String> findPublishersByAuthor(@PathVariable String authorName) {
        return bookService.findPublishersByAuthor(authorName);
    }

    @DeleteMapping("/author/{authorName}")
    public AuthorDto deleteAuthor(@PathVariable String authorName) {
        return bookService.deleteAuthor(authorName);
    }
}
