package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Places;
import model.Towns;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.book.it.web.services.PlaceService;
import pl.book.it.web.services.TownService;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bia/places")
public class PlaceController {

    private final PlaceService placeService;
    private final TownService townService;

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

    @GetMapping("/search")
    public String getAllPlacesInTownAvailableInDates(ModelMap modelMap, @RequestParam("from")LocalDate dateFrom,
                                                     @RequestParam("to")LocalDate dateTo,
                                                     @RequestParam("town") String townName, Errors errors){
        if (errors.hasErrors()) {
            return "index";
        }
        final Places places = placeService.findAllPlaceInTownAvailableInDates(dateFrom, dateTo, townName);
        modelMap.addAttribute("places",places);
        return "places";
    }

    @GetMapping("/index")
    public String searchForm (ModelMap modelMap) {
        final Towns allTowns = townService.findAllTowns();
        modelMap.addAttribute("towns",allTowns);
        return "index";
    }


}
