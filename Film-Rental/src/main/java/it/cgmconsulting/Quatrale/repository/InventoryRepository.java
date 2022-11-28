package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Inventory;
import it.cgmconsulting.Quatrale.payload.response.FilmStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //todo sistemare subquery storeName
    @Query(value = "SELECT NEW it.cgmconsulting.Quatrale.payload.response.FilmStoreResponse( " +
            "(SELECT f.filmId FROM Film f WHERE f.filmId = :filmId ) AS filmId, " +
            "(SELECT f.title FROM Film f WHERE f.id =:filmId ) AS title, " +
            "i.storeId.storeName AS storeName " +
            ") FROM Inventory i " +
            "WHERE i.filmId = :filmId " +
            "ORDER BY storeName")
    List<FilmStoreResponse> findByStore(@Param("filmId") long filmId);
}
