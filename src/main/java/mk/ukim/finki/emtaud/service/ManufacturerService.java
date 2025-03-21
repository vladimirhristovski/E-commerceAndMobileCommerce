package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> save(Manufacturer manufacturer);

    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> update(Long id, Manufacturer manufacturer);

    void deleteById(Long id);

}
