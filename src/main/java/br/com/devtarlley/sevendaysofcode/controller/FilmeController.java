package br.com.devtarlley.sevendaysofcode.controller;

import br.com.devtarlley.sevendaysofcode.model.Filme;
import br.com.devtarlley.sevendaysofcode.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/top250")
    public List<Filme> top250Filmes(@RequestParam(required = false) Optional<String> title){
        return filmeService.top250Filmes(title);
    }
    @GetMapping("/html/top250")
    public ResponseEntity<?> htmlOfTop250(){
        return filmeService.htmlOfTop250();
    }
}
