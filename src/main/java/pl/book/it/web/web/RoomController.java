package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Dto.BookingDto;
import model.Dto.PlaceDto;
import model.Rooms;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.book.it.web.client.PlaceClient;
import pl.book.it.web.client.RoomClient;
import pl.book.it.web.client.UserClient;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(WebConst.API_PLACES_PATH)
public class RoomController {

    private final RoomClient roomClient;
    private final PlaceClient placeClient;
    private final UserClient userClient;

    @GetMapping("/{placeId}/rooms")
    public String getAllRoomsInPlace(ModelMap modelMap, @PathVariable Long placeId) {
        final Rooms allRoomsInPlace = roomClient.findAllRoomsInPlace(placeId);
        return returnViewOfPlaceAndRoomsWithIt(modelMap, placeId, allRoomsInPlace);
    }

    @GetMapping("/{placeId}/rooms/search")
    public String getAllRoomsInPlaceAvailableInDates(ModelMap modelMap, @PathVariable Long placeId,
                                                     @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                     @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                                     @AuthenticationPrincipal Principal principal) {
        final Rooms allRoomsInPlaceAvailableInDates = roomClient.findAllRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId);
        final PlaceDto place = placeClient.findPlaceById(placeId);
        final Map<String, Object> attributes = new HashMap<>();
        final String email = principal.getName();
//        final UserDto user = userClient.getUser(email);
        attributes.put("rooms", allRoomsInPlaceAvailableInDates);
        attributes.put("place", place);
        attributes.put("from", dateFrom);
        attributes.put("to", dateTo);
        attributes.put("email", email);
        attributes.put("booking", new BookingDto());
        modelMap.addAllAttributes(attributes);
        return "place";
//        return returnViewOfPlaceAndRoomsWithIt(modelMap, placeId, allRoomsInPlaceAvailableInDates);
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
