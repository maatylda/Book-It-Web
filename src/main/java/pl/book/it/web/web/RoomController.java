package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Dto.BookingDto;
import model.Dto.PlaceDto;
import model.Rooms;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.book.it.web.client.PlaceClient;
import pl.book.it.web.client.RoomClient;
import pl.book.it.web.client.UserClient;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(WebConst.PLACES_PATH)
public class RoomController {

    private final RoomClient roomClient;
    private final PlaceClient placeClient;
    private final UserClient userClient;

    @GetMapping("/{placeId}/rooms")
    public String getAllRoomsInPlace(ModelMap modelMap, @PathVariable Long placeId) {
        final Rooms allRoomsInPlace = roomClient.findAllRoomsInPlace(placeId);
        return returnViewOfPlaceAndRoomsWithIt(modelMap, placeId, allRoomsInPlace);
    }

    @GetMapping(value = "/{placeId}/rooms/search")
    public String getAllRoomsInPlaceAvailableInDates(ModelMap modelMap, @PathVariable Long placeId,
                                                     @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                     @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        final Rooms allRoomsInPlaceAvailableInDates = roomClient.findAllRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId);
        final PlaceDto place = placeClient.findPlaceById(placeId);
        final Map<String, Object> attributes = new HashMap<>();
        final BookingDto myBooking = BookingDto.builder().dateFrom(dateFrom).dateTo(dateTo).placeId(placeId).build();
        attributes.put("rooms", allRoomsInPlaceAvailableInDates);
        attributes.put("place", place);
        attributes.put("booking", myBooking);
        modelMap.addAllAttributes(attributes);
        return "place";
    }

    private String returnViewOfPlaceAndRoomsWithIt(ModelMap modelMap, @PathVariable Long placeId, Rooms allRoomsInPlace) {
        final PlaceDto place = placeClient.findPlaceById(placeId);
        final Map<String, Object> attributes = new HashMap<>();
        attributes.put("rooms", allRoomsInPlace);
        attributes.put("place", place);
        modelMap.addAllAttributes(attributes);
        return "place2";
    }
}
