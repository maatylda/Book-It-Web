package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Places;
import model.Towns;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.book.it.web.client.PlaceClient;
import pl.book.it.web.client.TownClient;

import java.time.LocalDate;
import java.util.Map;

import static pl.book.it.web.web.WebConst.PLACES_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class PlaceController {

    private final PlaceClient placeClient;
    private final TownClient townClient;

    @GetMapping(PLACES_PATH)
    public String getAllPlaces(ModelMap modelMap) {
        final Places allPlaces = placeClient.findAllPlaces();
        modelMap.addAttribute("places", allPlaces);
        return "places";
    }

    @GetMapping("/places/towns/{townName}")
    public String getAllPlacesInTown(ModelMap modelMap, @PathVariable("townName") String townName) {
        final Places places = placeClient.findAllPlacesByTown(townName);
        modelMap.addAttribute("places", places);
        return "places";
    }

    @GetMapping(path = "/places/search")
    public String getAllPlacesInTownAvailableInDates(ModelMap modelMap, @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                     @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                                     @RequestParam("town") String townName) {

        Places places = placeClient.findAllPlaceInTownAvailableInDates(dateFrom, dateTo, townName);
        modelMap.addAllAttributes(Map.of(
                "places", places,
                "from", dateFrom,
                "to", dateTo));
        return "search";
    }

    @GetMapping("/index")
    public String searchForm(ModelMap modelMap) {
        final Towns allTowns = townClient.findAllTowns();
        modelMap.addAttribute("towns", allTowns);
        return "index";
    }
}
