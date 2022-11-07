package br.com.devtarlley.sevendaysofcode.service;

import br.com.devtarlley.sevendaysofcode.model.Top250Data;
import br.com.devtarlley.sevendaysofcode.util.ConstantsUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.apiKey}")
    private String apiKey ;

    private static final String URL = ConstantsUtils.URLBASE + "/Top250Movies/";

    public ResponseEntity<Top250Data> top250Filmes() {

       return restTemplate.getForEntity(URL + apiKey, Top250Data.class);
    }
}
