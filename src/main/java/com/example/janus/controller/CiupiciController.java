package com.example.janus.controller;


import com.example.janus.model.Ciupici;
import com.example.janus.repository.CiupiciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/ciupici")
public class CiupiciController {

    @Autowired
    private CiupiciRepository ciupiciRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> save(@Valid @RequestBody Ciupici ciupici) {
        return ResponseEntity.ok(ciupiciRepository.save(ciupici));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Ciupici> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(ciupiciRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)));
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Long userId) {
            super("Sorry, we could not find user '" + userId + "'.");
        }
    }

}
