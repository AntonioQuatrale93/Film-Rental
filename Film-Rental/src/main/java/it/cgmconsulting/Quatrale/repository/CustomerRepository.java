package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
