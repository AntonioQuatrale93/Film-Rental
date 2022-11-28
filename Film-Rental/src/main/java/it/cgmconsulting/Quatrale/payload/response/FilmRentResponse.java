package it.cgmconsulting.Quatrale.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmRentResponse {

    private long filmId;
    private String tile;
    private String storeName;
}
