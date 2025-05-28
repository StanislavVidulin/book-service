package ait.cohort55.book.dao;

import ait.cohort55.book.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
