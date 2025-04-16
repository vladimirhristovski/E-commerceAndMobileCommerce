package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing accommodation hosts")
public class HostController {

    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all available hosts.")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully.")
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return this.hostApplicationService.findAll();
    }

    @Operation(summary = "Get host by ID", description = "Finds a host by its ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Host found successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Host not found."
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return this.hostApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new host", description = "Creates a new host.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Host created successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Host not created successfully."
            )
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto) {
        return this.hostApplicationService.save(createHostDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing host", description = "Updates a host by ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Host updated successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Host not found."
            )
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto createHostDto) {
        return this.hostApplicationService.update(id, createHostDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by its ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Host deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Host not found."
            )
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.hostApplicationService.findById(id).isPresent()) {
            this.hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
