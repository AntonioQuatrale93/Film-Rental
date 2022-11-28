package it.cgmconsulting.Quatrale.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateFilmRequest {

    private String title;
    private String description;
    private int releaseYear;
    private String genre;
    private String language;


}
