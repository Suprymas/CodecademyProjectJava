package com.mano.codecademy.dinerApi.restaurant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Setter
    @Getter
    private String zipCode;
    private Float peanutScore;
    private Float eggScore;
    private Float dairyScore;
    private Float overallScore;

    public Restaurant() {}

    public Restaurant(Long id,
                      String name,
                      Float peanutScore,
                      Float eggScore,
                      Float dairyScore,
                      Float overallScore) {
        this.id = id;
        this.name = name;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
        this.overallScore = overallScore;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Float getPeanutScore() {
        return this.peanutScore;
    }

    public void setPeanutScore(Float peanutScore){
        this.peanutScore = peanutScore;
    }

    public Float getEggScore() {
        return this.eggScore;
    }

    public void setEggScore(Float eggScore){
        this.eggScore = eggScore;
    }

    public Float getDairyScore() {
        return this.dairyScore;
    }

    public void setDairyScore(Float dairyScore){
        this.dairyScore = dairyScore;
    }

    public Float getOverallScore(){
        return this.overallScore;
    }

    public void setOverallScore(Float overallScore){
        this.overallScore = overallScore;
    }


}
