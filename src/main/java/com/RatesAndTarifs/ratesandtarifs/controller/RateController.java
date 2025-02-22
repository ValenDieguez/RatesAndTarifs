package com.RatesAndTarifs.ratesandtarifs.controller;

import com.RatesAndTarifs.ratesandtarifs.DTO.RateDTO;
import com.RatesAndTarifs.ratesandtarifs.Service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/rates")
@CrossOrigin(origins = "http://localhost:4200")
public class RateController {

    @Autowired
    RateService rateService;

    @GetMapping("/{id}")
    public ResponseEntity<RateDTO> getRateById(@PathVariable Long id) {
        return ResponseEntity.ok(rateService.getRateById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<RateDTO> getApplicableRate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {
        return ResponseEntity.ok(rateService.getOne(date, productId, brandId));
    }

    @PostMapping
    public ResponseEntity<RateDTO> createRate(@RequestBody RateDTO rate) {
        return new ResponseEntity<>(rateService.createRate(rate), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<RateDTO> updatePrice(
            @PathVariable Long id,
            @RequestBody Integer newPrice) {
        return ResponseEntity.ok(rateService.updateRatePrice(id, newPrice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
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
