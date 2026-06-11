package com.engdados.arrocha.arrochadb.Entities;

public enum Turno {
    MATUTINO("Matutino"), VESPERTINO("Vespertino"), NOTURNO("Noturno"), TURNO_INDEFINIDO("Turno Indefinido");
    private final String turnos;

    Turno(String turnos){
        this.turnos = turnos;
    }

    String getTurnos(){
        return turnos;
    }
}
