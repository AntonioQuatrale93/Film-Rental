package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreName(String storeName);
}
