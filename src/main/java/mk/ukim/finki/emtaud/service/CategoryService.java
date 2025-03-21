package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> save(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> update(Long id, Category category);

    void deleteById(Long id);

}
