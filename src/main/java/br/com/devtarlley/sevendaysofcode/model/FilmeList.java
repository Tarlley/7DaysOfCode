package br.com.devtarlley.sevendaysofcode.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FilmeList {

    private List<Filme> items ;

    private String errorMessage ;

    @Override
    public String toString() {
        return "Items=" + items.toString();
    }
}
