package com.engdados.arrocha.arrochadb.Entities;

public enum Grau {
    BACHARELADO("Bacharelado"), LICENCIATURA_PLENA("Licenciatura Plena");
    
    private final String graus;

    Grau(String graus){
        this.graus = graus;
    }

    String getGraus(){
        return graus;
    }
}
