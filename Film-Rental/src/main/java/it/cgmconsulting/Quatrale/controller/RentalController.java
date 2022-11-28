package it.cgmconsulting.Quatrale.controller;

import it.cgmconsulting.Quatrale.entity.Rental;
import it.cgmconsulting.Quatrale.payload.response.CustomerStoreResponse;
import it.cgmconsulting.Quatrale.payload.response.FilmRentResponse;
import it.cgmconsulting.Quatrale.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("rental")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity countCustomersByStore(@PathVariable String storeName) {
        CustomerStoreResponse response = rentalService.countCustomersByStore(storeName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("add-rental/{customerId}/{inventoryId}")
    public ResponseEntity<?> addRental(long customerId, long inventoryId) {
        Rental r = rentalService.addRental(customerId, inventoryId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PostMapping("update-rental/{customerId}/{inventoryId}")
    public ResponseEntity updateRental(@PathVariable long customerId, @PathVariable long inventoryId, @RequestBody LocalDate date) {
        Rental r = rentalService.updateRental(customerId, inventoryId, date);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping("/count-rentals-in-date-range-by-store/{storeId}")
    public ResponseEntity<?> getAllWithinRange(@PathVariable long storeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate afterDate) {
        List<Rental> list = rentalService.getAllWithinRange(storeId, beforeDate, afterDate);
        if (list.isEmpty())
            return new ResponseEntity<>("no rental found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
    public ResponseEntity<?> getAllRentByOneCustomer(@PathVariable("customerId") long customerId) {
        List<FilmRentResponse> list = rentalService.getAllRentByOneCustomer(customerId);
        if(list.isEmpty())
            return new ResponseEntity<>("no rent found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
