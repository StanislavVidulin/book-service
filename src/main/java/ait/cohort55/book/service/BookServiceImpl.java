package ait.cohort55.book.service;

import ait.cohort55.book.dao.AuthorRepository;
import ait.cohort55.book.dao.BookRepository;
import ait.cohort55.book.dao.PublisherRepository;
import ait.cohort55.book.dto.AuthorDto;
import ait.cohort55.book.dto.BookDto;
import ait.cohort55.book.dto.exceptions.ConflictException;
import ait.cohort55.book.dto.exceptions.NotFoundException;
import ait.cohort55.book.model.Author;
import ait.cohort55.book.model.Book;
import ait.cohort55.book.model.Publisher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void addBook(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getIsbn())) {
            throw new ConflictException("Book with ISBN" + bookDto.getIsbn() + " already exists");
        }
        // publisher. Сначала нужно его создать, чтобы добавить книгу
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
                .orElseGet(() -> publisherRepository.save(new Publisher(bookDto.getPublisher())));
        // authors
        Set<Author> authors = bookDto.getAuthors().stream()
                .map(a -> authorRepository.findById(a.getName())
                        .orElseGet(() -> authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
                .collect(Collectors.toSet());

        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
        bookRepository.save(book);
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(NotFoundException::new);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    @Override
    public BookDto deleteBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(NotFoundException::new);
        bookRepository.deleteById(isbn);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    @Override
    public BookDto updateBookTitle(String isbn, String title) {
        Book book = bookRepository.findById(isbn).orElseThrow(NotFoundException::new);
        book.setTitle(title);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<BookDto> findBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthorsNameIgnoreCase(authorName)
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<BookDto> findBooksByPublisher(String publisherName) {
        return bookRepository.findBooksByPublisherPublisherName(publisherName)
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }

    @Override
    public Iterable<AuthorDto> findBookAuthors(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(NotFoundException::new);
        return book.getAuthors().stream()
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .toList();
    }

    @Override
    public Iterable<String> findPublishersByAuthor(String authorName) {
        return publisherRepository.findPublishersByAuthor(authorName);
    }

    @Transactional
    @Override
    public AuthorDto deleteAuthor(String authorName) {
        Author author = authorRepository.findById(authorName).orElseThrow(NotFoundException::new);
//        bookRepository.findBooksByAuthorsNameIgnoreCase(authorName)
//                .forEach(b -> bookRepository.delete(b));
        bookRepository.deleteBooksByAuthorsNameIgnoreCase(authorName);
        authorRepository.delete(author);
        return modelMapper.map(author, AuthorDto.class);
    }
}
