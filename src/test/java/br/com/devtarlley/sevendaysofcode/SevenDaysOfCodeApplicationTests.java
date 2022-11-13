package br.com.devtarlley.sevendaysofcode;

import br.com.devtarlley.sevendaysofcode.model.FilmeList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SevenDaysOfCodeApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnTop250Filmes(){

        final ResponseEntity<FilmeList> forEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/filmes/top250", FilmeList.class);
        assertEquals(HttpStatus.OK,forEntity.getStatusCode());
        assertNotNull(forEntity.getBody());
    }

}
