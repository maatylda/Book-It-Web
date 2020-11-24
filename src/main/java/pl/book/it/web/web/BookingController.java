package pl.book.it.web.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import model.Dto.BookingDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.book.it.web.client.BookingClient;

import java.time.LocalDate;

import static pl.book.it.web.web.WebConst.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = BOOKINGS_PATH)

public class BookingController {

    private final BookingClient bookingClient;

    @GetMapping(path = "/new2", consumes = MediaType.ALL_VALUE)
    public String createNewBooking2(Model model) {
        model.addAttribute("bookingDto", new BookingDto());

        return "booking_form";
    }

    @PostMapping(path = "/new2", consumes = MediaType.ALL_VALUE)
    public String createNewBooking(BookingDto bookingDto, @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                   @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo, BindingResult bindingResult) {
        bookingDto.setDateFrom(dateFrom);
        bookingDto.setDateTo(dateTo);
        final BookingDto booking = bookingClient.createBooking(bookingDto);
        return "redirect:users/bookings/myBooking";
    }

    @GetMapping("/myBooking")
    public String showBooking() {
        return "booking";
    }
}
