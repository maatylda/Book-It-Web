package pl.book.it.web.client;

import lombok.RequiredArgsConstructor;
import model.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.book.it.web.config.BookingApiClientConfiguration;

import static pl.book.it.web.web.WebConst.API_USERS_PATH;


@Service
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;
    private final BookingApiClientConfiguration baConfig;

    public ResponseEntity<UserDto> createUser(UserDto user) {
        //User createdUser = restTemplate.getForObject("http://localhost:8081/signin", User.class, HttpStatus.CREATED);
        return restTemplate.postForEntity(baConfig.getBaseUrl() + API_USERS_PATH + "/create", user, UserDto.class);
    }

    ///
    public UserDto getUser(String email) {
        return restTemplate.getForObject(baConfig.getBaseUrl() + "/api/bia/users/" + email, UserDto.class);
    }
}
