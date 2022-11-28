package it.cgmconsulting.Quatrale.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long filmId;

    @Column(length = 100)
    private String title;

    private String description;

    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    Language languageId;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    Genre genre;


    public Film(String title, String description, int releaseYear, Language languageId, Genre genre) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmId == film.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }
}
