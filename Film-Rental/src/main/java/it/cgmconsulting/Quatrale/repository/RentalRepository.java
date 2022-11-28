package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Rental;
import it.cgmconsulting.Quatrale.entity.RentalId;
import it.cgmconsulting.Quatrale.payload.response.CustomerStoreResponse;
import it.cgmconsulting.Quatrale.payload.response.FilmRentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, RentalId> {


    @Query(value = "SELECT new it.cgmconsulting.Quatrale.payload.response.CustomerStoreResponse(" +
            "s.storeName, " +
            "COUNT(DISTINCT(r.rentalId.inventoryId))) " +
            "FROM Inventory i " +
            "LEFT JOIN Store s " +
            "ON i.storeId = s.storeId " +
            "LEFT JOIN Rental r " +
            "ON r.rentalId.inventoryId = i.inventoryId " +
            "WHERE s.storeName = :storeName")
    CustomerStoreResponse countCustomersByStore(String storeName);


    @Query(value = "SELECT DISTINCT r FROM Rental r " +
            "LEFT JOIN Inventory i " +
            "ON r.rentalId.inventoryId.storeId = i.storeId " +
            "WHERE r.rentalId.rentalDate BETWEEN :after AND :before AND i.storeId.storeId = :storeId " +
            "ORDER BY r.rentalId.rentalDate ASC")
    List<Rental> getAllInTimeRange(long storeId, @Param("after") LocalDate after, @Param("before") LocalDate before);

    @Query(value = "SELECT new it.cgmconsulting.Quatrale.payload.response.FilmRentResponse(" +
            "i.filmId.filmId, " +
            "i.filmId.title, " +
            "i.storeId.storeName) " +
            "FROM Rental r " +
            "LEFT JOIN Inventory i " +
            "ON r.rentalId.inventoryId = i.inventoryId " +
            "WHERE r.rentalId.customerId.customerId = :customerId")
    List<FilmRentResponse> getAllRentByOneCustomer(@Param("customerId") long customerId);


    List<Rental> findAllByRentalIdInventoryIdInventoryId(long inventoryId);


/*
    @Query("SELECT NEW it.cgmconsulting.Quatrale.payload.response.FilmMaxRentResponse(" +
            "r.rentalId.inventoryId.filmId.filmId, " +
            "r.rentalId.inventoryId.filmId.title, " +
            "(SELECT COUNT(DISTINCT (customerId)) WHERE")
    List<FilmRentResponse> getFilmByMaxRent();


 */
}