package ait.cohort55.book.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@Entity
@Table(name = "author")
public class Author implements Serializable {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE)
    private Set<Book> books;

    // Сет, которого нет в конструкторе, hibernate сам создаст
    public Author(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
