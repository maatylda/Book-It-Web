package pl.book.it.web.client;

import lombok.RequiredArgsConstructor;
import model.Rooms;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.book.it.web.config.BookingApiClientConfiguration;

import java.time.LocalDate;

import static pl.book.it.web.web.WebConst.API_PLACES_PATH;

@Service
@RequiredArgsConstructor
public class RoomClient {
    private final RestTemplate restTemplate;
    private final BookingApiClientConfiguration baConfig;

    public Rooms findAllRoomsInPlace(Long placeId) {
        return restTemplate.getForObject(baConfig.getBaseUrl() + API_PLACES_PATH + "/" + placeId + "/rooms", Rooms.class);
    }

    public Rooms findAllRoomsInPlaceAvailableInDates(LocalDate from, LocalDate to, Long placeId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baConfig.getBaseUrl() + API_PLACES_PATH + "/" + placeId + "/rooms/search")
                .queryParam("from", from.toString())
                .queryParam("to", to.toString());
        return restTemplate.getForObject(uriBuilder.toUriString(), Rooms.class);
    }

}

