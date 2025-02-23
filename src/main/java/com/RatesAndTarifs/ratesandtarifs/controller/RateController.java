package com.RatesAndTarifs.ratesandtarifs.controller;

import com.RatesAndTarifs.ratesandtarifs.DTO.RateDTO;
import com.RatesAndTarifs.ratesandtarifs.Service.RateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/rates")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Rates", description = "Rate management APIs")
public class RateController {

    @Autowired
    RateService rateService;

    @Operation(summary = "Get a rate by its ID", description = "Retrieves a specific rate using its unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rate found",
            content = @Content(schema = @Schema(implementation = RateDTO.class))),
        @ApiResponse(responseCode = "404", description = "Rate not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RateDTO> getRateById(
        @Parameter(description = "ID of the rate to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(rateService.getRateById(id));
    }

    @Operation(summary = "Search for applicable rate", description = "Find the applicable rate for a given product, brand and date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Applicable rate found",
            content = @Content(schema = @Schema(implementation = RateDTO.class))),
        @ApiResponse(responseCode = "404", description = "No applicable rate found")
    })
    @GetMapping("/search")
    public ResponseEntity<RateDTO> getApplicableRate(
            @Parameter(description = "Date and time for rate application") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @Parameter(description = "Product ID") @RequestParam Long productId,
            @Parameter(description = "Brand ID") @RequestParam Long brandId) {
        return ResponseEntity.ok(rateService.getOne(date, productId, brandId));
    }

    @Operation(summary = "Create a new rate", description = "Creates a new rate entry")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Rate created successfully",
            content = @Content(schema = @Schema(implementation = RateDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<RateDTO> createRate(
        @Parameter(description = "Rate details") @RequestBody RateDTO rate) {
        return new ResponseEntity<>(rateService.createRate(rate), HttpStatus.CREATED);
    }

    @Operation(summary = "Update rate price", description = "Updates the price of an existing rate")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Price updated successfully",
            content = @Content(schema = @Schema(implementation = RateDTO.class))),
        @ApiResponse(responseCode = "404", description = "Rate not found"),
        @ApiResponse(responseCode = "400", description = "Invalid price value")
    })
    @PatchMapping("/{id}/price")
    public ResponseEntity<RateDTO> updatePrice(
            @Parameter(description = "ID of the rate to update") @PathVariable Long id,
            @Parameter(description = "New price value") @RequestBody Integer newPrice) {
        return ResponseEntity.ok(rateService.updateRatePrice(id, newPrice));
    }

    @Operation(summary = "Delete a rate", description = "Deletes an existing rate")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Rate deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Rate not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(
        @Parameter(description = "ID of the rate to delete") @PathVariable Long id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }
}
