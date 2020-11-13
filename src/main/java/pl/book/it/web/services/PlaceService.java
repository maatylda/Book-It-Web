package pl.book.it.web.services;

import lombok.RequiredArgsConstructor;
import model.Places;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class PlaceService {
    private final RestTemplate restTemplate;

    public Places findAllPlaces() {
        return restTemplate.getForObject("http://localhost:8081/api/bia/places", Places.class);
    }

    public Places findAllPlacesByTown(String townName) {
        return restTemplate.getForObject("http://localhost:8081/api/bia/places/towns/"+townName,Places.class);
    }
}
