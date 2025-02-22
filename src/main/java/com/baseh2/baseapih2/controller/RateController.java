package com.baseh2.baseapih2.controller;

import com.baseh2.baseapih2.DTO.RateDTO;
import com.baseh2.baseapih2.Service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/rates")
@CrossOrigin(origins = "http://localhost:4200")
public class RateController {

    @Autowired
    RateService rateService;



    @GetMapping("/getOne/{time}/{productId}/{brandId}")
    public ResponseEntity<RateDTO> getOneTry(@PathVariable LocalDateTime time, @PathVariable Long productId, @PathVariable Long brandId) {
        return new ResponseEntity<>( rateService.getOne(time,productId,brandId), HttpStatus.OK);
    }

}
