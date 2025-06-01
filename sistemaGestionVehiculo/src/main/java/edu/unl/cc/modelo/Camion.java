package edu.unl.cc.modelo;

import java.util.Date;
/**
 * AUTORES:
 * Emilio Galvez, Katheryn Contento, Alejandro Padilla, Ivett Zaragocin
 */
public class Camion extends Vehiculo {

    private int numeroEjes;
    private int costoMantenimiento= 50;

    public Camion(String placaUnica, double capacidadCarga, double consumoCombustibleKm,
                  Date fechaUltimoMantenimiento, int numeroEjes) {
        super(placaUnica, capacidadCarga, consumoCombustibleKm, fechaUltimoMantenimiento);
        this.numeroEjes = numeroEjes;
    }

    @Override
    public double generarCostoMantenimiento() {
        return costoMantenimiento;
    }

    @Override
    public double calcularCostoViaje(double distanciaKm, double precioCombustible) {
        return (distanciaKm * getConsumoCombustibleKm() * precioCombustible) + costoMantenimiento;
    }

    @Override
    public boolean solicitarMantenimiento() {
        long dias = (new Date().getTime() - getFechaUltimoMantenimiento().getTime()) / (1000L * 60 * 60 * 24);
        return dias > 90;
    }

    @Override
    public boolean actualizarMantenimiento() {
        if (solicitarMantenimiento()) {
            setFechaUltimoMantenimiento(new Date());
            return true;
        }
        return false;
    }

    public int getNumeroEjes() {
        return numeroEjes;
    }

    public void setNumeroEjes(int numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    @Override
    public String toString() {
        return  "-> Camion" +
                "\nNumero de Ejes: " + numeroEjes+
                super.toString()
                ;
    }
}