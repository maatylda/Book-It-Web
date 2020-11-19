package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Dto.PlaceDto;
import model.Rooms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.book.it.web.services.PlaceService;
import pl.book.it.web.services.RoomService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(WebConst.API_PLACES_PATH)
public class RoomController {

    private final RoomService roomService;
    private final PlaceService placeService;

    @GetMapping("/{placeId}")
    public String getAllRoomsInPlace(ModelMap modelMap, @PathVariable Long placeId) {
        final Rooms allRoomsInPlace = roomService.findAllRoomsInPlace(placeId);
        final PlaceDto place = placeService.findPlaceById(placeId);
        final Map<String, Object> attributes = new HashMap<>();
        attributes.put("rooms", allRoomsInPlace);
        attributes.put("place", place);
        modelMap.addAllAttributes(attributes);
        //dopisać żeby metoda zwracała też szczegóły miejsca
        return "place";
    }
}
