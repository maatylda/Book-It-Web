package pl.book.it.web.services;

import lombok.RequiredArgsConstructor;
import model.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;

    public ResponseEntity<UserDto> createUser(UserDto user) {
        //User createdUser = restTemplate.getForObject("http://localhost:8081/signin", User.class, HttpStatus.CREATED);
        return restTemplate.postForEntity("http://localhost:8081/api/bia/users/create", user, UserDto.class);
    }

}
