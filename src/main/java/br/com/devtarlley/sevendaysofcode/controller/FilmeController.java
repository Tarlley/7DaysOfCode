package br.com.devtarlley.sevendaysofcode.controller;

import br.com.devtarlley.sevendaysofcode.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public ResponseEntity<?> top250Filmes(){
        filmeService.top250Filmes();
        return ResponseEntity.ok().build();
    }
}