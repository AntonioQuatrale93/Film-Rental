package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.FilmStaff;
import it.cgmconsulting.Quatrale.entity.FilmStaffId;
import it.cgmconsulting.Quatrale.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FilmStaffRepository extends JpaRepository<FilmStaff, FilmStaffId> {

    @Query(value = "SELECT new it.cgmconsulting.Quatrale.payload.response.FilmResponse(" +
            "fs.filmStaffId.film.filmId, " +
            "fs.filmStaffId.film.title, " +
            "fs.filmStaffId.film.description, " +
            "fs.filmStaffId.film.releaseYear, " +
            "fs.filmStaffId.film.languageId.languageName) " +
            "FROM FilmStaff fs " +
            "LEFT JOIN Staff s " +
            "ON fs.filmStaffId.staff = s.staffId " +
            "LEFT JOIN Film f " +
            "ON fs.filmStaffId.film.filmId = f.filmId  " +
            "WHERE s.lastName IN (:lastNames) AND fs.filmStaffId.role.roleName = 'ACTOR' " +
            "ORDER BY f.title")
    List<FilmResponse> getByActors(@Param("lastNames") List<String> lastNames);




}
