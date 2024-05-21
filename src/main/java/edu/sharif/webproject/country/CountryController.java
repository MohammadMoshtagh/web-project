package edu.sharif.webproject.country;

import edu.sharif.webproject.country.dto.CountryDto;
import edu.sharif.webproject.country.dto.CountryNameDto;
import edu.sharif.webproject.country.dto.CountryNamesResponse;
import edu.sharif.webproject.country.dto.CountryWeatherDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    public CountryNamesResponse getCountries(@RequestParam int pageNum, @RequestParam int pageSize) {
        return countryService.getAllCountriesNames(pageNum, pageSize);
    }

    @GetMapping("/{name}")
    public CountryDto getCountry(@PathVariable String name) {
        return countryService.getCountryByName(name);
    }

    @GetMapping("/{name}/weather")
    public CountryWeatherDto getCountryWeather(@PathVariable String name) {
        return countryService.getCountryWeatherByCountryName(name);
    }
}
