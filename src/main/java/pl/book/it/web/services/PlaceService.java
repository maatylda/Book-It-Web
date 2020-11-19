package pl.book.it.web.services;

import lombok.RequiredArgsConstructor;
import model.Dto.PlaceDto;
import model.Places;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Map;

import static pl.book.it.web.web.WebConst.*;


@Service
@RequiredArgsConstructor
public class PlaceService {
    private final RestTemplate restTemplate;

    public Places findAllPlaces() {
        return restTemplate.getForObject(START_PATH +API_PLACES_PATH, Places.class);
    }

    public Places findAllPlacesByTown(String townName) {
        return restTemplate.getForObject(START_PATH +API_PLACES_PATH+"/towns/"+townName,Places.class);
    }

    public PlaceDto findPlaceById (Long placeId) {
        return restTemplate.getForObject(START_PATH +API_PLACES_PATH+"/"+placeId,PlaceDto.class);
    }

    public Places findAllPlaceInTownAvailableInDates (LocalDate from, LocalDate to, String townName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(START_PATH+API_PLACES_PATH+"/search")
                .queryParam("from",from.toString())
                .queryParam("to",to.toString())
                .queryParam("town",townName);
        return restTemplate.getForObject(uriBuilder.toUriString(),Places.class);
    }
}
