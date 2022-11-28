package it.cgmconsulting.Quatrale.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmStoreResponse {

    private long filmId;
    private String title;
    private String storeName;
}
