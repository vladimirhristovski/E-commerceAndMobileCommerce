package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDto(String name, String continent) {

    public static CreateCountryDto from(Country country) {
        return new CreateCountryDto(country.getName(), country.getContinent());
    }

    public static List<CreateCountryDto> from(List<Country> countries) {
        return countries.stream().map(CreateCountryDto::from).collect(Collectors.toList());
    }

    public Country toCountry() {
        return new Country(name, continent);
    }

}
