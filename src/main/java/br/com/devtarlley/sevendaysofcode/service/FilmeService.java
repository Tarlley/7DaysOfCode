package br.com.devtarlley.sevendaysofcode.service;

import br.com.devtarlley.sevendaysofcode.model.Filme;
import br.com.devtarlley.sevendaysofcode.model.FilmeList;
import br.com.devtarlley.sevendaysofcode.util.ConstantsUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.apiKey}")
    private String apiKey ;

    private List<Filme> favoritos = new ArrayList<>();

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

    public ResponseEntity<?> saveFavoritos(String filmeId) {
        if (forEntity == null){
            forEntity = restTemplate.getForEntity(URL + apiKey, FilmeList.class);
        }
        if (favoritos.stream().anyMatch(filme -> filme.id().equalsIgnoreCase(filmeId))){
            return new ResponseEntity<>("Favorito j?? cadastrado",HttpStatus.BAD_REQUEST);
        }
        try {
            favoritos.add(forEntity.getBody().getItems().stream().filter(filme -> filme.id().equalsIgnoreCase(filmeId)).findFirst().orElse(null));
            return new ResponseEntity<>("Favorito adicionado com sucesso",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao adicionar favorido",HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> favoritos() {
        return new ResponseEntity<>(favoritos,HttpStatus.OK);
    }
}
