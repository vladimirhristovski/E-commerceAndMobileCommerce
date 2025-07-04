package mk.ukim.finki.emtaud.config.init;

import mk.ukim.finki.emtaud.model.domain.Category;
import mk.ukim.finki.emtaud.model.domain.Manufacturer;
import mk.ukim.finki.emtaud.model.domain.User;
import mk.ukim.finki.emtaud.model.enumerations.Role;
import mk.ukim.finki.emtaud.repository.CategoryRepository;
import mk.ukim.finki.emtaud.repository.ManufacturerRepository;
import mk.ukim.finki.emtaud.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init() {

        this.categoryRepository.save(new Category("Sports", "Sports categoryId"));
        this.categoryRepository.save(new Category("Food", "Food categoryId"));
        this.categoryRepository.save(new Category("Music", "Music categoryId"));

        this.manufacturerRepository.save(new Manufacturer("Nike", "USA"));
        this.manufacturerRepository.save(new Manufacturer("KFC", "USA"));
        this.manufacturerRepository.save(new Manufacturer("A Records", "UK"));

        this.userRepository.save(new User(
                "vh",
                passwordEncoder.encode("vh"),
                "Vladimir",
                "Hristovski",
                Role.ROLE_ADMIN
        ));
        this.userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

    }
}
