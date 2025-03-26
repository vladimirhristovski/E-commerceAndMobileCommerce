package mk.ukim.finki.emtaud.service.domain.impl;

import mk.ukim.finki.emtaud.model.domain.Category;
import mk.ukim.finki.emtaud.repository.CategoryRepository;
import mk.ukim.finki.emtaud.service.domain.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> save(Category category) {
        return Optional.of(this.categoryRepository.save(category));
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> update(Long id, Category category) {
        return this.categoryRepository.findById(id).map(existingCategory -> {
            if (category.getName() != null) {
                existingCategory.setName(category.getName());
            }
            if (category.getDescription() != null) {
                existingCategory.setDescription(category.getDescription());
            }
            return categoryRepository.save(existingCategory);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
