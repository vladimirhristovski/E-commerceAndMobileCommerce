package mk.ukim.finki.emtaud.service.application;

import mk.ukim.finki.emtaud.dto.CreateManufacturerDto;
import mk.ukim.finki.emtaud.dto.DisplayManufacturerDto;

import java.util.List;
import java.util.Optional;

public interface ManufacturerApplicationService {

    List<DisplayManufacturerDto> findAll();

    Optional<DisplayManufacturerDto> findById(Long id);

    Optional<DisplayManufacturerDto> save(CreateManufacturerDto manufacturer);

    Optional<DisplayManufacturerDto> update(Long id, CreateManufacturerDto manufacturer);

    void deleteById(Long id);


}
