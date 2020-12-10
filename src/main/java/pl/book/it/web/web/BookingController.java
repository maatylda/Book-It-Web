package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Dto.BookingDto;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.book.it.web.client.BookingClient;

import java.security.Principal;

import static pl.book.it.web.web.WebConst.BOOKINGS_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = BOOKINGS_PATH)

public class BookingController {

    private final BookingClient bookingClient;

    @GetMapping(path = "/new2")
    public String createNewBooking2(Model model,BookingDto bookingDto) {
        model.addAttribute("bookingDto", bookingDto);
        return "booking_form";
    }

    @PostMapping(path = "/new2",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String createNewBooking(@ModelAttribute("booking") BookingDto bookingDto, @AuthenticationPrincipal Principal principal) {
        bookingDto.setUserEmail(principal.getName());
        final BookingDto booking = bookingClient.createBooking(bookingDto);
        return "redirect:/users/bookings/my";
    }

    @GetMapping("/my")
    public String showBooking() {
        return "booking";
    }
}
