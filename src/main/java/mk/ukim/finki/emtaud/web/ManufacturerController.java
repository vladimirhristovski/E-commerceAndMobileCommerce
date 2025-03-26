package mk.ukim.finki.emtaud.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtaud.dto.CreateManufacturerDto;
import mk.ukim.finki.emtaud.dto.DisplayManufacturerDto;
import mk.ukim.finki.emtaud.service.application.ManufacturerApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@Tag(name = "Manufacturer API", description = "Endpoints for managing manufacturers")
public class ManufacturerController {

    private final ManufacturerApplicationService manufacturerApplicationService;

    public ManufacturerController(ManufacturerApplicationService manufacturerApplicationService) {
        this.manufacturerApplicationService = manufacturerApplicationService;
    }


    @Operation(summary = "Get all manufacturers", description = "Retrieves a list of all available manufacturers.")
    @GetMapping
    public List<DisplayManufacturerDto> findAll() {
        return this.manufacturerApplicationService.findAll();
    }

    @Operation(summary = "Get manufacturer by ID", description = "Finds a manufacturer by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayManufacturerDto> findById(@PathVariable Long id) {
        return this.manufacturerApplicationService.findById(id)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new manufacturer", description = "Creates a new manufacturer.")
    @PostMapping("/add")
    public ResponseEntity<DisplayManufacturerDto> save(@RequestBody CreateManufacturerDto createManufacturerDto) {
        return this.manufacturerApplicationService.save(createManufacturerDto)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing manufacturer", description = "Updates a manufacturer by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayManufacturerDto> update(@PathVariable Long id, @RequestBody CreateManufacturerDto createManufacturerDto) {
        return this.manufacturerApplicationService.update(id, createManufacturerDto)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a manufacturer", description = "Deletes a manufacturer by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.manufacturerApplicationService.findById(id).isPresent()) {
            this.manufacturerApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
