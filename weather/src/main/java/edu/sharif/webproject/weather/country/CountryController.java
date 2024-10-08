package edu.sharif.webproject.weather.country;

import edu.sharif.webproject.weather.config.RabbitMQConfig;
import edu.sharif.webproject.weather.country.entity.dto.CountryDto;
import edu.sharif.webproject.weather.country.entity.dto.CountryNamesResponse;
import edu.sharif.webproject.weather.country.entity.dto.CountryWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("")
    public CountryNamesResponse getCountries(@RequestParam int pageNum, @RequestParam int pageSize) {
        return countryService.getAllCountriesNames(pageNum, pageSize);
    }

    @GetMapping("/{name}")
    public CountryDto getCountry(@PathVariable String name) {
        CountryDto countryDto = (CountryDto) rabbitTemplate.convertSendAndReceive(RabbitMQConfig.QUEUE_NAME, name);
        if (countryDto != null) {
            return countryDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found!");
        }
    }

    @GetMapping("/{name}/weather")
    public CountryWeatherDto getCountryWeather(@PathVariable String name) {
        return countryService.getCountryWeatherByCountryName(name);
    }
}
