package com.udea.modelos;
import java.time.LocalDate;

public class Propiedad {
    private int numeroDeMatricula;
    private int estrato;
    private String barrio;
    private int valorDePropiedad;
    private String tipoPropiedad;
    private LocalDate fechaRegistroPropiedad;

    public Propiedad(int numeroDeMatricula, int estrato, String barrio, int valorDePropiedad, String tipoPropiedad, LocalDate fechaRegistroPropiedad) {
        this.numeroDeMatricula = numeroDeMatricula;
        this.estrato = estrato;
        this.barrio = barrio;
        this.valorDePropiedad = valorDePropiedad;
        this.tipoPropiedad = tipoPropiedad;
        this.fechaRegistroPropiedad = fechaRegistroPropiedad;
    }

    public int getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public void setNumeroDeMatricula(int numeroDeMatricula) {
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getValorDePropiedad() {
        return valorDePropiedad;
    }

    public void setValorDePropiedad(int valorDePropiedad) {
        this.valorDePropiedad = valorDePropiedad;
    }

    public String getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(String tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public LocalDate getFechaRegistroPropiedad() {
        return fechaRegistroPropiedad;
    }

    public void setFechaRegistroPropiedad(LocalDate fechaRegistroPropiedad) {
        this.fechaRegistroPropiedad = fechaRegistroPropiedad;
    }

    @Override
    public String toString() {
        return String.format("Propiedad [Matrícula: %d, Estrato: %d, Barrio: %s, Valor: %d, Tipo: %s, Fecha de Registro: %s]",
                numeroDeMatricula, estrato, barrio, valorDePropiedad, tipoPropiedad, fechaRegistroPropiedad);
    }

}

