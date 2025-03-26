package mk.ukim.finki.emtaud.service.application.impl;

import mk.ukim.finki.emtaud.dto.CreateManufacturerDto;
import mk.ukim.finki.emtaud.dto.DisplayManufacturerDto;
import mk.ukim.finki.emtaud.service.application.ManufacturerApplicationService;
import mk.ukim.finki.emtaud.service.domain.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerApplicationServiceImpl implements ManufacturerApplicationService {

    private final ManufacturerService manufacturerService;

    public ManufacturerApplicationServiceImpl(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<DisplayManufacturerDto> findAll() {
        return DisplayManufacturerDto.from(this.manufacturerService.findAll());
    }

    @Override
    public Optional<DisplayManufacturerDto> findById(Long id) {
        return this.manufacturerService.findById(id).map(DisplayManufacturerDto::from);
    }

    @Override
    public Optional<DisplayManufacturerDto> save(CreateManufacturerDto manufacturer) {
        return this.manufacturerService.save(manufacturer.toManufacturer()).map(DisplayManufacturerDto::from);
    }

    @Override
    public Optional<DisplayManufacturerDto> update(Long id, CreateManufacturerDto manufacturer) {
        return this.manufacturerService.update(id, manufacturer.toManufacturer()).map(DisplayManufacturerDto::from);
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerService.deleteById(id);
    }
}
