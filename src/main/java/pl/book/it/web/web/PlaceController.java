package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Places;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.book.it.web.services.PlaceService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bia/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String getAllPlaces(ModelMap modelMap) {
        final Places allPlaces = placeService.findAllPlaces();
        modelMap.addAttribute("places", allPlaces);
        return "places";
    }

    @GetMapping("/towns/{townName}")
    public String getAllPlacesInTown(ModelMap modelMap, @PathVariable("townName") String townName){
        final Places placesInTown = placeService.findAllPlacesByTown(townName);
        modelMap.addAttribute("places",placesInTown);
        return "places";
    }
}
