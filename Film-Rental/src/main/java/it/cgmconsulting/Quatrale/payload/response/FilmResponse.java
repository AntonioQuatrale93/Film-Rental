package it.cgmconsulting.Quatrale.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilmResponse {

    private long filmId;
    private String title;
    private String description;
    private int releaseYear;
    private String languageName;


}
