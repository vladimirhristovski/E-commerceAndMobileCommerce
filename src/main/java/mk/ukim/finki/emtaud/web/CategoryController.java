package mk.ukim.finki.emtaud.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtaud.dto.CreateCategoryDto;
import mk.ukim.finki.emtaud.dto.DisplayCategoryDto;
import mk.ukim.finki.emtaud.service.application.CategoryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category API", description = "Endpoints for managing product categories")
public class CategoryController {

    private final CategoryApplicationService categoryApplicationService;

    public CategoryController(CategoryApplicationService categoryApplicationService) {
        this.categoryApplicationService = categoryApplicationService;
    }

    @Operation(summary = "Get all categories", description = "Retrieves a list of all available categories.")
    @GetMapping
    public List<DisplayCategoryDto> findAll() {
        return this.categoryApplicationService.findAll();
    }

    @Operation(summary = "Get category by ID", description = "Finds a category by its ID.")
    @GetMapping("/{id}") // /2
    public ResponseEntity<DisplayCategoryDto> findById(@PathVariable Long id) {
        return this.categoryApplicationService.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new category", description = "Creates a new category.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCategoryDto> save(@RequestBody CreateCategoryDto createCategoryDto) {
        return this.categoryApplicationService.save(createCategoryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing category", description = "Updates a category by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCategoryDto> update(@PathVariable Long id, @RequestBody CreateCategoryDto createCategoryDto) {
        return this.categoryApplicationService.update(id, createCategoryDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a category", description = "Deletes a category by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.categoryApplicationService.findById(id).isPresent()) {
            this.categoryApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
