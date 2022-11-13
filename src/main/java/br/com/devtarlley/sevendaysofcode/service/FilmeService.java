package br.com.devtarlley.sevendaysofcode.service;

import br.com.devtarlley.sevendaysofcode.model.Filme;
import br.com.devtarlley.sevendaysofcode.model.FilmeList;
import br.com.devtarlley.sevendaysofcode.util.ConstantsUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.apiKey}")
    private String apiKey ;

    private static final String URL = ConstantsUtils.URLBASE + "/Top250Movies/";
    ResponseEntity<FilmeList> forEntity = null;

    public List<Filme> top250Filmes(Optional<String> title) {

        if (forEntity == null){
            forEntity = restTemplate.getForEntity(URL + apiKey, FilmeList.class);
        }

        if (title.isPresent()){
            return Objects.requireNonNull(forEntity.getBody()).getItems()
                    .stream()
                    .filter(filme -> filme.title().toLowerCase().contains(title.orElse("").toLowerCase())).toList();

        }else{
            return forEntity.getBody().getItems();
        }
    }

    public ResponseEntity<?> htmlOfTop250() {
            final ResponseEntity<FilmeList> response = restTemplate.getForEntity(URL + apiKey, FilmeList.class);

            try {
                PrintWriter writer = new PrintWriter("src/main/resources/content.html");
                new HTMLGenerator(writer).generate(response.getBody().getItems());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        return response;
    }
}
