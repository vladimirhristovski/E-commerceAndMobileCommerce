package mk.ukim.finki.emtaud.dto;

import mk.ukim.finki.emtaud.model.domain.Manufacturer;

import java.util.List;
import java.util.stream.Collectors;

public record CreateManufacturerDto(String name, String address) {

    public static CreateManufacturerDto from(Manufacturer manufacturer) {
        return new CreateManufacturerDto(manufacturer.getName(), manufacturer.getAddress());
    }

    public static List<CreateManufacturerDto> from(List<Manufacturer> manufacturers) {
        return manufacturers.stream().map(CreateManufacturerDto::from).collect(Collectors.toList());
    }

    public Manufacturer toManufacturer() {
        return new Manufacturer(name, address);
    }

}
