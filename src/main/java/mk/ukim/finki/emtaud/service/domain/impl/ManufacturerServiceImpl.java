package mk.ukim.finki.emtaud.service.domain.impl;

import mk.ukim.finki.emtaud.model.domain.Manufacturer;
import mk.ukim.finki.emtaud.repository.ManufacturerRepository;
import mk.ukim.finki.emtaud.service.domain.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(Manufacturer manufacturer) {
        return Optional.of(this.manufacturerRepository.save(manufacturer));
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> update(Long id, Manufacturer manufacturer) {
        return this.manufacturerRepository.findById(id).map(existingManufacturer -> {
            if (manufacturer.getName() != null) {
                existingManufacturer.setName(manufacturer.getName());
            }
            if (manufacturer.getAddress() != null) {
                existingManufacturer.setAddress(manufacturer.getAddress());
            }
            return this.manufacturerRepository.save(existingManufacturer);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);
    }
}
