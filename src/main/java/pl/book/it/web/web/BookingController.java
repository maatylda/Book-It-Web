package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Dto.BookingDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.book.it.web.client.BookingClient;

import static pl.book.it.web.web.WebConst.API_BOOKINGS_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = API_BOOKINGS_PATH)

public class BookingController {

    private final BookingClient bookingClient;

    @PostMapping(path = "/new", consumes = MediaType.ALL_VALUE)
    public String createNewBooking(BookingDto bookingDto) {
        final BookingDto booking = bookingClient.createBooking(bookingDto);
        return "booking";
    }

    @GetMapping("/myBooking")
    public String showBooking() {
        return "booking";
    }
}
