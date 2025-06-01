package edu.unl.cc.modelo;

import edu.unl.cc.validador.ValidadorExcepciones;

import java.util.Date;


public class Viaje {
    private double distanciaRecorridaKm;
    private Date fechaViaje;
    private Vehiculo vehiculo;

    public Viaje(double distanciaRecorridaKm, Date fechaViaje, Vehiculo vehiculo) {
        setDistanciaRecorridaKm(distanciaRecorridaKm);
        setFechaViaje(fechaViaje);
        setVehiculo(vehiculo);
    }

    /**
     * Calcula el costo operativo únicamente por combustible (sin mantenimiento).
     */
    public double calcularCostoOperativo(double precioCombustible) {
        return distanciaRecorridaKm * vehiculo.getConsumoCombustibleKm() * precioCombustible;
    }

    /**
     * Calcula el costo total: combustible + mantenimiento.
     */
    public double calcularCostoTotal(double precioCombustible) {
        double costoCombustible = calcularCostoOperativo(precioCombustible);
        double costoMantenimiento = vehiculo.generarCostoMantenimiento();
        return costoCombustible + costoMantenimiento;
    }

    public double getDistanciaRecorridaKm() {
        return distanciaRecorridaKm;
    }

    public void setDistanciaRecorridaKm(double distanciaRecorridaKm) {
        ValidadorExcepciones.validarDistancia(distanciaRecorridaKm);
        this.distanciaRecorridaKm = distanciaRecorridaKm;
    }

    public Date getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(Date fechaViaje) {
        ValidadorExcepciones.validarFechaNoNula(fechaViaje);
        this.fechaViaje = fechaViaje;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "-> Viaje: " +
                "\n * Distancia Recorrida en Km: " + distanciaRecorridaKm +
                "\n * Fecha de Viaje:" + fechaViaje +
                "\n * Vehiculo: " + vehiculo
                ;
    }
}


