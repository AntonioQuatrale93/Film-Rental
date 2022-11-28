package it.cgmconsulting.Quatrale.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RangeRequest {

    private LocalDate isAfter;
    private LocalDate isBefore;
}
