package br.com.devtarlley.sevendaysofcode.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Top250DataDetail {

    private String id ;
    private String rank ;
    private String title ;
    private String fullTitle ;
    private String year ;
    private String image ;
    private String crew ;
    private String imDbRating ;
    private String imDbRatingCount ;


    @Override
    public String toString() {
        return "{id='" + id + '\'' +
                ", rank='" + rank + '\'' +
                ", title='" + title + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", year='" + year + '\'' +
                ", image='" + image + '\'' +
                ", crew='" + crew + '\'' +
                ", imDbRating='" + imDbRating + '\'' +
                ", imDbRatingCount='" + imDbRatingCount + '\'' +
                '}';
    }
}
