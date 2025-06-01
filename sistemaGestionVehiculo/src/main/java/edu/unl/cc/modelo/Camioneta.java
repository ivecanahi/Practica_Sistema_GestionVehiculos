package edu.unl.cc.modelo;

import java.util.Date;
/**
 * AUTORES:
 * Emilio Galvez, Katheryn Contento, Alejandro Padilla, Ivett Zaragocin
 */
public class Camioneta extends Vehiculo {
    private boolean traccion4x4;

    public Camioneta(String placaUnica, double capacidadCarga, double consumoCombustibleKm,
                     Date fechaUltimoMantenimiento, boolean traccion4x4) {
        super(placaUnica, capacidadCarga, consumoCombustibleKm, fechaUltimoMantenimiento);
        this.traccion4x4 = traccion4x4;
    }

    @Override
    public double generarCostoMantenimiento() {
        return getCapacidadCarga() * (traccion4x4 ? 0.05 : 0.03);
    }

    @Override
    public double calcularCostoViaje(double distanciaKm, double precioCombustible) {
        double costo = distanciaKm * getConsumoCombustibleKm() * precioCombustible;
        if (traccion4x4) costo *= 1.2;
        return costo;
    }

    @Override
    public boolean solicitarMantenimiento() {
        long dias = (new Date().getTime() - getFechaUltimoMantenimiento().getTime()) / (1000L * 60 * 60 * 24);
        return dias > 120;
    }

    @Override
    public boolean actualizarMantenimiento() {
        if (solicitarMantenimiento()) {
            setFechaUltimoMantenimiento(new Date());
            return true;
        }
        return false;
    }

    public boolean isTraccion4x4() {
        return traccion4x4;
    }

    public void setTraccion4x4(boolean traccion4x4) {
        this.traccion4x4 = traccion4x4;
    }

    @Override
    public String toString() {
        return "-> Camioneta" +
                super.toString()
                ;
    }
}