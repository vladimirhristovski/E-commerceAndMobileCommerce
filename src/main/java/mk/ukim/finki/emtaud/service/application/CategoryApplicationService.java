package mk.ukim.finki.emtaud.service.application;

import mk.ukim.finki.emtaud.dto.CreateCategoryDto;
import mk.ukim.finki.emtaud.dto.DisplayCategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryApplicationService {

    List<DisplayCategoryDto> findAll();

    Optional<DisplayCategoryDto> save(CreateCategoryDto createCategoryDto);

    Optional<DisplayCategoryDto> findById(Long id);

    Optional<DisplayCategoryDto> update(Long id, CreateCategoryDto category);

    void deleteById(Long id);


}
