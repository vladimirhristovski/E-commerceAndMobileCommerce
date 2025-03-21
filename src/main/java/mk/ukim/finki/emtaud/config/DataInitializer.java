package mk.ukim.finki.emtaud.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtaud.model.Category;
import mk.ukim.finki.emtaud.model.Manufacturer;
import mk.ukim.finki.emtaud.repository.CategoryRepository;
import mk.ukim.finki.emtaud.repository.ManufacturerRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public DataInitializer(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @PostConstruct
    public void init() {

        this.categoryRepository.save(new Category("Sports", "Sports category"));
        this.categoryRepository.save(new Category("Food", "Food category"));
        this.categoryRepository.save(new Category("Music", "Music category"));

        this.manufacturerRepository.save(new Manufacturer("Nike", "USA"));
        this.manufacturerRepository.save(new Manufacturer("KFC", "USA"));
        this.manufacturerRepository.save(new Manufacturer("A Records", "UK"));

    }
}
