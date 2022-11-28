package it.cgmconsulting.Quatrale.service;

import it.cgmconsulting.Quatrale.entity.Store;
import it.cgmconsulting.Quatrale.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public boolean existsById(long storeId) {
        return storeRepository.existsById(storeId);
    }

    public Store findByStoreName(String storeName) {
        return storeRepository.findByStoreName(storeName);
    }


}
