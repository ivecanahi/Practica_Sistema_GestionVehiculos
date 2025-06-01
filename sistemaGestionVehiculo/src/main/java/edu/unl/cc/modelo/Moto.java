package edu.unl.cc.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Moto extends Vehiculo {
    private int cilindrada;


    public Moto() {
        super("XXX-0000", 0, 0, new Date());
        this.cilindrada = 0;
    }

    public Moto(String placaUnica, double capacidadCarga, double consumoCombustibleKm, Date fechaUltimoMantenimiento, int cilindrada) {
        super(placaUnica, capacidadCarga, consumoCombustibleKm, fechaUltimoMantenimiento);
        this.cilindrada = cilindrada;
    }

    @Override
    public double generarCostoMantenimiento() {
        return cilindrada * 0.18;
    }

    // Método con parámetros para calcular costo real
    @Override
    public double calcularCostoViaje(double distanciaKm, double precioCombustible) {
        return distanciaKm * getConsumoCombustibleKm() * precioCombustible;
    }

    @Override
    public boolean solicitarMantenimiento() {
        long diasDesdeUltimo = (new Date().getTime() - getFechaUltimoMantenimiento().getTime()) / (1000L * 60 * 60 * 24);
        return diasDesdeUltimo > 180;
    }

    @Override
    public boolean actualizarMantenimiento() {
        if (solicitarMantenimiento()) {
            setFechaUltimoMantenimiento(new Date());
            return true;
        }
        return false;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return "-> Moto: " +
                "\nCilindrada=" + cilindrada +
                super.toString()
                ;
    }
}


