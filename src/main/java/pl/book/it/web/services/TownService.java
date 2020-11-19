package pl.book.it.web.services;

import lombok.RequiredArgsConstructor;
import model.Towns;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static pl.book.it.web.web.WebConst.*;

@Service
@RequiredArgsConstructor
public class TownService {
    private final RestTemplate restTemplate;

    public Towns findAllTowns() {
        return restTemplate.getForObject(START_PATH +API_TOWNS_PATH, Towns.class);
    }
}
