package it.cgmconsulting.Quatrale.repository;

import it.cgmconsulting.Quatrale.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {


    Language findByLanguageName(String genreName);
}
