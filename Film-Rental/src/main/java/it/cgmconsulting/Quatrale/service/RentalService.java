package it.cgmconsulting.Quatrale.service;

import it.cgmconsulting.Quatrale.entity.Customer;
import it.cgmconsulting.Quatrale.entity.Inventory;
import it.cgmconsulting.Quatrale.entity.Rental;
import it.cgmconsulting.Quatrale.entity.RentalId;
import it.cgmconsulting.Quatrale.payload.response.CustomerStoreResponse;
import it.cgmconsulting.Quatrale.payload.response.FilmRentResponse;
import it.cgmconsulting.Quatrale.repository.CustomerRepository;
import it.cgmconsulting.Quatrale.repository.InventoryRepository;
import it.cgmconsulting.Quatrale.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    StoreService storeService;

    @Autowired
    CustomerRepository customerRepository;

    public CustomerStoreResponse countCustomersByStore(String storeName) {
        return rentalRepository.countCustomersByStore(storeName);
    }


    public RentalId createRentalId(long customerId, long inventoryId, LocalDate date) {
        Optional<Inventory> i = inventoryRepository.findById(inventoryId);
        Optional<Customer> c = customerRepository.findById(customerId);
        if (i.isPresent() && c.isPresent()) {
            RentalId rentalId = new RentalId(c.get(), i.get(), date);
            return rentalId;
        }
        return null;
    }

    public List<Rental> findAllByRentalIdInventoryIdInventoryId(long inventoryId){
        return rentalRepository.findAllByRentalIdInventoryIdInventoryId(inventoryId);
    }



    public Rental addRental(long customerId, long inventoryId) {
        Customer c = customerRepository.findById(customerId).get();
        Inventory i = inventoryRepository.findById(inventoryId).get();

        List<Rental> rentalList = rentalRepository.findAllByRentalIdInventoryIdInventoryId(inventoryId);
        rentalList.forEach(rental -> {
            if (rental.getRentalReturn() == null) {
                rentalList.remove(rental);
            }
        });
        Rental rental = new Rental();
        if (!rentalList.isEmpty() && inventoryRepository.findById(inventoryId).isPresent()) {
            RentalId rentalId = new RentalId(c, i, LocalDate.now());
            rental.setRentalId(rentalId);
            return rentalRepository.save(rental);
        }
        return rental;
    }



    @Transactional
    public Rental updateRental(long customerId, long inventoryId, LocalDate date) {
        RentalId rentalId = createRentalId(customerId, inventoryId, date);
        Optional<Rental> rental = rentalRepository.findById(rentalId);
        if (rental.isPresent())
            if (rental.get().getRentalReturn() == null)
                rental.get().setRentalReturn(LocalDate.now());
        return rental.get();
    }

    public List<Rental> getAllWithinRange(long storeId, LocalDate beforeDate, LocalDate afterDate) {
        return rentalRepository.getAllInTimeRange(storeId, beforeDate, afterDate);
    }

    public List<FilmRentResponse> getAllRentByOneCustomer(long customerId) {
        return rentalRepository.getAllRentByOneCustomer(customerId);
    }


}



