package ait.cohort55.book.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AuthorDto {
    private String name;
//    @JsonAlias("birthday") - если ошиблись с названием поля, можно своё задать
    private LocalDate birthDate;
}
