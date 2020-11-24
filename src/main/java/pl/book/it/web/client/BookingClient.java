package pl.book.it.web.client;

import lombok.RequiredArgsConstructor;
import model.Dto.BookingDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.book.it.web.config.BookingApiClientConfiguration;

import static pl.book.it.web.web.WebConst.API_BOOKINGS_PATH;

@Service
@RequiredArgsConstructor
public class BookingClient {

    private final RestTemplate restTemplate;
    private final BookingApiClientConfiguration baConfig;

    public BookingDto createBooking(BookingDto bookingDto) {
        return restTemplate.postForObject(baConfig.getBaseUrl() + API_BOOKINGS_PATH, bookingDto, BookingDto.class);
    }
}
