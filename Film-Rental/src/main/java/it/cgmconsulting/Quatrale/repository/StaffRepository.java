package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {


}
