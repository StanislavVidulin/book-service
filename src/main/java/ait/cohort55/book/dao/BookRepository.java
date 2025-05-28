package ait.cohort55.book.dao;

import ait.cohort55.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, String> {
//    Stream<Book> findBooksByAuthorsNameIgnoreCase(String authorName); // name - поле в Author

//    Stream<Book> findBooksByPublisherPublisherName(String publisherName);

    void deleteBooksByAuthorsNameIgnoreCase(String authorName);

    // publisherName - поле в Publisher
}
