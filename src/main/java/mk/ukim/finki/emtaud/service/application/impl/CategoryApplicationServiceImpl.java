package mk.ukim.finki.emtaud.service.application.impl;

import mk.ukim.finki.emtaud.dto.CreateCategoryDto;
import mk.ukim.finki.emtaud.dto.DisplayCategoryDto;
import mk.ukim.finki.emtaud.service.application.CategoryApplicationService;
import mk.ukim.finki.emtaud.service.domain.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryApplicationServiceImpl implements CategoryApplicationService {

    private final CategoryService categoryService;

    public CategoryApplicationServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<DisplayCategoryDto> findAll() {
        return DisplayCategoryDto.from(this.categoryService.findAll());
    }

    @Override
    public Optional<DisplayCategoryDto> save(CreateCategoryDto createCategoryDto) {
        return this.categoryService.save(createCategoryDto.toCategory()).map(DisplayCategoryDto::from);
    }

    @Override
    public Optional<DisplayCategoryDto> findById(Long id) {
        return this.categoryService.findById(id).map(DisplayCategoryDto::from);
    }

    @Override
    public Optional<DisplayCategoryDto> update(Long id, CreateCategoryDto category) {
        return this.categoryService.update(id, category.toCategory()).map(DisplayCategoryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        this.categoryService.deleteById(id);
    }
}
