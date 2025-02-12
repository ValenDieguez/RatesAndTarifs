package com.baseh2.baseapih2.controller;

import com.baseh2.baseapih2.DTO.PricesDTO;
import com.baseh2.baseapih2.Service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class PricesController {

    @Autowired
    PricesService pricesService;



    @GetMapping("/getOne/{time}/{productId}/{brandId}")
    public ResponseEntity<PricesDTO> getOneTry(@PathVariable LocalDateTime time, @PathVariable Long productId, @PathVariable Long brandId) {
        return new ResponseEntity<>( pricesService.getOne(time,productId,brandId), HttpStatus.OK);
    }

}
