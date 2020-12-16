package pl.book.it.web.client;

import lombok.RequiredArgsConstructor;
import model.Dto.PlaceDto;
import model.Places;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.book.it.web.config.BookingApiClientConfiguration;

import java.time.LocalDate;

import static pl.book.it.web.web.WebConst.API_PLACES_PATH;


@Service
@RequiredArgsConstructor
public class PlaceClient {
    private final RestTemplate restTemplate;
    private final BookingApiClientConfiguration baConfig;

    public Places findAllPlaces() {
        return restTemplate.getForObject(baConfig.getBaseUrl() + API_PLACES_PATH, Places.class);
    }

    public Places findAllPlacesByTown(String townName) {
        return restTemplate.getForObject(baConfig.getBaseUrl() + API_PLACES_PATH + "/towns/" + townName, Places.class);
    }

    public PlaceDto findPlaceById(Long placeId) {
        return restTemplate.getForObject(baConfig.getBaseUrl() + API_PLACES_PATH + "/" + placeId, PlaceDto.class);
    }

    public Places findAllPlaceInTownAvailableInDates(LocalDate from, LocalDate to, String townName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baConfig.getBaseUrl() + API_PLACES_PATH + "/search")
                .queryParam("from", from.toString())
                .queryParam("to", to.toString())
                .queryParam("town", townName);
        return restTemplate.getForObject(uriBuilder.build().toString(), Places.class);
    }
}
