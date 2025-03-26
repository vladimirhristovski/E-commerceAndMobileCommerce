package mk.ukim.finki.emtaud.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtaud.dto.CreateProductDto;
import mk.ukim.finki.emtaud.dto.DisplayProductDto;
import mk.ukim.finki.emtaud.service.application.ProductApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Endpoints for managing products")
public class ProductController {

    private final ProductApplicationService productApplicationServiceService;

    public ProductController(ProductApplicationService productApplicationServiceService) {
        this.productApplicationServiceService = productApplicationServiceService;
    }

    @Operation(summary = "Get all products", description = "Retrieves a list of all available products")
    @GetMapping
    public List<DisplayProductDto> findAll() {
        return this.productApplicationServiceService.findAll();
    }

    @Operation(summary = "Get product by ID", description = "Finds a product by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayProductDto> findById(@PathVariable Long id) {
        return this.productApplicationServiceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new product", description = "Creates a new product based on the given ProductDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayProductDto> save(@RequestBody CreateProductDto createProductDto) {
        return this.productApplicationServiceService.save(createProductDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO: refactor product update functionality
    @Operation(summary = "Update an existing product", description = "Updates a product by ID using ProductDto.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayProductDto> update(@PathVariable Long id, @RequestBody CreateProductDto createProductDto) {
        return this.productApplicationServiceService.update(id, createProductDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.productApplicationServiceService.findById(id).isPresent()) {
            this.productApplicationServiceService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
