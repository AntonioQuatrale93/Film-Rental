package it.cgmconsulting.Quatrale.service;

import it.cgmconsulting.Quatrale.entity.Film;
import it.cgmconsulting.Quatrale.entity.FilmStaff;
import it.cgmconsulting.Quatrale.entity.Inventory;
import it.cgmconsulting.Quatrale.entity.Staff;
import it.cgmconsulting.Quatrale.payload.request.UpdateFilmRequest;
import it.cgmconsulting.Quatrale.payload.response.FilmResponse;
import it.cgmconsulting.Quatrale.payload.response.FilmStoreResponse;
import it.cgmconsulting.Quatrale.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    FilmStaffRepository filmStaffRepository;

    @Autowired
    StaffRepository staffRepository;


    public boolean existsById(long id) {
        return filmRepository.existsById(id);
    }

    @Transactional
    public Film updateFilm(long filmId, UpdateFilmRequest updateFilmRequest) {
        Optional<Film> film = filmRepository.findById(filmId);
        if (updateFilmRequest.getDescription() != null)
            film.get().setDescription(updateFilmRequest.getDescription());
        if (updateFilmRequest.getTitle() != null)
            film.get().setTitle(updateFilmRequest.getTitle());
        if (updateFilmRequest.getReleaseYear() != 0)
            film.get().setReleaseYear(updateFilmRequest.getReleaseYear());
        if (updateFilmRequest.getLanguage() != null)
            film.get().setLanguageId(languageRepository.findByLanguageName(updateFilmRequest.getLanguage()));
        if (updateFilmRequest.getGenre() != null)
            film.get().setGenre(genreRepository.findByGenreName(updateFilmRequest.getGenre()));

        return film.get();
    }

    public List<FilmResponse> findFilmsByLanguageId(long languageId) {
        return filmRepository.findByLanguage(languageId);
    }

    public List<FilmStoreResponse> findFilmInStore(long filmId) {
        return inventoryRepository.findByStore(filmId);
    }


    public void save(Film film) {
        filmRepository.save(film);
    }


    public Inventory addFilm(long storeId, long filmId) {
        return inventoryRepository.save(new Inventory(filmRepository.findById(filmId).get(), storeRepository.findById(storeId).get()));
    }




}



