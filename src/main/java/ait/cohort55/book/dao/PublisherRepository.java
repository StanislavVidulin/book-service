package ait.cohort55.book.dao;

import ait.cohort55.book.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

    @Query("select distinct p.publisherName from Book b join b.publisher p join b.authors a where a.name=?1")
    List<String> findPublishersByAuthor(String authorName);

    // убрать дубликаты: можно использовать вместо листа сет, а можно distinct
    // distinct лучше, он уберёт сразу на стороне базы данных, Set уберёт на серверной стороне
    // так зачем чтобы дубликаты шли из базы на сервер, если их можно сразу убрать в базе
    // a.name=?1 в sql нумерация параметров начинается с 1, а в mongodb с 0
}
