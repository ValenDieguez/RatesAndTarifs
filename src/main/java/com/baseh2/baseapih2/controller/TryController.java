package com.baseh2.baseapih2.controller;

import com.baseh2.baseapih2.DTO.TryDTO;
import com.baseh2.baseapih2.Service.TryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TryController {

    @Autowired
    TryService tryService;


    @PostMapping
    public ResponseEntity<TryDTO> createTry(@RequestBody TryDTO tryDTO) {
        return new ResponseEntity<>(tryService.createTry(tryDTO), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TryDTO>> getAllTry() {
        return new ResponseEntity<>(tryService.getAllTries(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TryDTO> getOneTry(@PathVariable int id) {
        return new ResponseEntity<>(tryService.getOneTry(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> modifyTry(@RequestBody TryDTO tryDTO) {
        return new ResponseEntity<>(tryService.modifyTry(tryDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTry(@PathVariable int id ) {
        tryService.deleteTry(id);
        return new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT);
    }
}
