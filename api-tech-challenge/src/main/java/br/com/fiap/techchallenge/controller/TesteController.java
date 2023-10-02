package br.com.fiap.techchallenge.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping
    public ResponseEntity<String> ola(){

        return ResponseEntity.ok("OLaa");
    }
    @PostMapping
    public ResponseEntity<String> olaUser(){

        return ResponseEntity.ok("OLaa User");
    }
    @PutMapping
    public ResponseEntity<String> olaAdmin(){

        return ResponseEntity.ok("OLaa Admin");
    }



}
