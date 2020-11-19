package pl.book.it.web.services;

import lombok.RequiredArgsConstructor;
import model.Places;
import model.Rooms;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RestTemplate restTemplate;

    public Rooms findAllRoomsInPlace(Long placeId) {
        return restTemplate.getForObject("http://localhost:8081/api/bia/places/"+placeId+"/rooms", Rooms.class);
    }

}

