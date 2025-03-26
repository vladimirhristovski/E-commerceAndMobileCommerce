package mk.ukim.finki.emtaud.service.application;

import mk.ukim.finki.emtaud.dto.CreateProductDto;
import mk.ukim.finki.emtaud.dto.DisplayProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductApplicationService {

    Optional<DisplayProductDto> update(Long id, CreateProductDto productDto);

    Optional<DisplayProductDto> save(CreateProductDto productDto);

    Optional<DisplayProductDto> findById(Long id);

    List<DisplayProductDto> findAll();

    void deleteById(Long id);


}
