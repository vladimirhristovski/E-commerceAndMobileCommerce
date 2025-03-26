package mk.ukim.finki.emtaud.dto;

import mk.ukim.finki.emtaud.model.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCategoryDto(String name, String description) {

    public static CreateCategoryDto from(Category category) {
        return new CreateCategoryDto(category.getName(), category.getDescription());
    }

    public static List<CreateCategoryDto> from(List<Category> categories) {
        return categories.stream().map(CreateCategoryDto::from).collect(Collectors.toList());
    }

    public Category toCategory() {
        return new Category(name, description);
    }

}
