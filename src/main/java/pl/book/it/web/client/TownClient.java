package pl.book.it.web.client;

import lombok.RequiredArgsConstructor;
import model.Towns;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.book.it.web.config.BookingApiClientConfiguration;

import static pl.book.it.web.web.WebConst.API_TOWNS_PATH;

@Service
@RequiredArgsConstructor
public class TownClient {
    private final RestTemplate restTemplate;
    private final BookingApiClientConfiguration baconfig;

    public Towns findAllTowns() {
        return restTemplate.getForObject(baconfig.getBaseUrl() + API_TOWNS_PATH, Towns.class);
    }
}
