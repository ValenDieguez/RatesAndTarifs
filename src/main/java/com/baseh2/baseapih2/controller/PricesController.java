package com.baseh2.baseapih2.controller;

import com.baseh2.baseapih2.DTO.PricesDTO;
import com.baseh2.baseapih2.Service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class PricesController {

    @Autowired
    PricesService pricesService;



    @GetMapping("/getOne/{id}")
    public ResponseEntity<PricesDTO> getOneTry(@PathVariable int id) {
        return new ResponseEntity<>( pricesService.getOne(id), HttpStatus.OK);
    }

}
