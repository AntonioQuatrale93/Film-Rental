package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenreName(String genreName);
}
