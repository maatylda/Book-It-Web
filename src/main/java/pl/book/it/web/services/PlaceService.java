package pl.book.it.web.services;

import lombok.RequiredArgsConstructor;
import model.Dto.PlaceDto;
import model.Places;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.book.it.web.web.WebConst;


@Service
@RequiredArgsConstructor
public class PlaceService {
    private final RestTemplate restTemplate;

    public Places findAllPlaces() {
        return restTemplate.getForObject(WebConst.API_PLACES_PATH, Places.class);
    }

    public Places findAllPlacesByTown(String townName) {
        return restTemplate.getForObject(WebConst.API_PLACES_PATH+"/towns/"+townName,Places.class);
    }

    public PlaceDto findPlaceById (Long placeId) {
        return restTemplate.getForObject(WebConst.API_PLACES_PATH+"/"+placeId,PlaceDto.class);
    }
}
