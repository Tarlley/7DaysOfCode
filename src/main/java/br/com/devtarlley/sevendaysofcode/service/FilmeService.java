package br.com.devtarlley.sevendaysofcode.service;

import br.com.devtarlley.sevendaysofcode.model.Top250Data;
import br.com.devtarlley.sevendaysofcode.util.ConstantsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.apiKey}")
    private String apiKey ;

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmeService.class);

    public void top250Filmes() {

        final String url = ConstantsUtils.URLBASE + "/Top250Movies/" + apiKey;

        final Top250Data forObject = restTemplate.getForObject(url, Top250Data.class);

        if(forObject != null){
        LOGGER.info("Lista de filmes: {}",forObject);
        }

    }
}
