package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Film;
import it.cgmconsulting.Quatrale.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query(value = "SELECT NEW it.cgmconsulting.Quatrale.payload.response.FilmResponse " +
            "(f.filmId, " +
            "f.title, " +
            "f.description, " +
            "f.releaseYear, " +
            "(SELECT languageName FROM Language l where l.languageId = :languageId) AS languageName)" +
            "FROM Film f " +
            "WHERE f.languageId IN:languageId"
    )
    List<FilmResponse> findByLanguage(@Param("languageId") long languageId);


}
