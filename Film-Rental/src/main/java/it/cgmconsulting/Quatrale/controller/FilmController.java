package it.cgmconsulting.Quatrale.controller;

import it.cgmconsulting.Quatrale.entity.Inventory;
import it.cgmconsulting.Quatrale.payload.request.UpdateFilmRequest;
import it.cgmconsulting.Quatrale.payload.response.FilmResponse;
import it.cgmconsulting.Quatrale.payload.response.FilmStoreResponse;
import it.cgmconsulting.Quatrale.service.FilmService;
import it.cgmconsulting.Quatrale.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("film")
public class FilmController {

    @Autowired
    FilmService filmService;

    @Autowired
    StoreService storeService;

    @PutMapping("/update-film/{filmId}")
    public ResponseEntity<?> updateFilm(@PathVariable long filmId, @RequestBody UpdateFilmRequest updateFilmRequest) {
        if (!filmService.existsById(filmId))
            return new ResponseEntity<>("Film not found", HttpStatus.NOT_FOUND);
        filmService.updateFilm(filmId, updateFilmRequest);
        return new ResponseEntity<>("Film updated", HttpStatus.OK);
    }

    @GetMapping("find-films-by-language/{languageId}")
    public ResponseEntity<?> findByLanguageId(@PathVariable long languageId){
        List<FilmResponse> list = filmService.findFilmsByLanguageId(languageId);
        if(list.isEmpty())
            return new ResponseEntity<>("No films founds", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("find-films-in-store/{filmId}")
    public ResponseEntity<?> findFilmInStore(@PathVariable long filmId){
        List<FilmStoreResponse> list = filmService.findFilmInStore(filmId);
        if(list.isEmpty())
            return new ResponseEntity<>("No films founds", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("add-film-to-store/{storeId}/{filmId}")
    public ResponseEntity<?> addToStore(@PathVariable long storeId, @PathVariable long filmId){
        if(!filmService.existsById(filmId))
            return new ResponseEntity<>("Film not found", HttpStatus.NOT_FOUND);
        if(!storeService.existsById(storeId))
            return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
        Inventory inventory = filmService.addFilm(storeId, filmId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);

    }





}
