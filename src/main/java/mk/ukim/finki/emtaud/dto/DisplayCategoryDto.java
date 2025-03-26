package mk.ukim.finki.emtaud.dto;

import mk.ukim.finki.emtaud.model.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCategoryDto(Long id, String name, String description) {

    public static DisplayCategoryDto from(Category category) {
        return new DisplayCategoryDto(category.getId(), category.getName(), category.getDescription());
    }

    public static List<DisplayCategoryDto> from(List<Category> categories) {
        return categories.stream().map(DisplayCategoryDto::from).collect(Collectors.toList());
    }

    public Category toCategory() {
        return new Category(name, description);
    }

}
