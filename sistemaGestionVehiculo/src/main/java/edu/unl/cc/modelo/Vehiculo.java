package edu.unl.cc.modelo;

import edu.unl.cc.validador.ValidadorExcepciones;

import java.util.Date;
/**
 * AUTORES:
 * Emilio Galvez, Katheryn Contento, Alejandro Padilla, Ivett Zaragocin
 */
public abstract class Vehiculo {
    private String placaUnica;
    private double capacidadCarga;
    private double consumoCombustibleKm;
    private Date fechaUltimoMantenimiento;

    public Vehiculo(String placaUnica, double capacidadCarga,
                    double consumoCombustibleKm, Date fechaUltimoMantenimiento) {
        setPlacaUnica(placaUnica);
        setCapacidadCarga(capacidadCarga);
        setConsumoCombustibleKm(consumoCombustibleKm);
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
    }

    public abstract double generarCostoMantenimiento();
    public abstract double calcularCostoViaje(double distanciaKm, double precioCombustible);
    public abstract boolean solicitarMantenimiento();
    public abstract boolean actualizarMantenimiento();

    public String getPlacaUnica() {
        return placaUnica;
    }

    /**
     *
     * @param placaUnica
     * @throws IllegalArgumentException
     */
    public void setPlacaUnica(String placaUnica) throws IllegalArgumentException {
        ValidadorExcepciones.validarFormatoPlaca(placaUnica);
        this.placaUnica = placaUnica;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    /**
     *
     * @param capacidadCarga
     * @throws IllegalArgumentException
     */
    public void setCapacidadCarga(double capacidadCarga) throws IllegalArgumentException{
        if (capacidadCarga <= 0)
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        this.capacidadCarga = capacidadCarga;
    }

    public double getConsumoCombustibleKm() {
        return consumoCombustibleKm;
    }

    /**
     * @param consumoCombustibleKm
     * @throws IllegalArgumentException
     */
    public void setConsumoCombustibleKm(double consumoCombustibleKm) throws IllegalArgumentException{
        if (consumoCombustibleKm <= 0)
            throw new IllegalArgumentException("El consumo de combustible debe ser mayor a cero.");
        this.consumoCombustibleKm = consumoCombustibleKm;
    }

    public Date getFechaUltimoMantenimiento() {
        return fechaUltimoMantenimiento;
    }

    public void setFechaUltimoMantenimiento(Date fechaUltimoMantenimiento) {
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
    }

    @Override
    public String toString() {
        return  "\nPlaca: " + placaUnica +
                ",\nCapacidad Carga: " + capacidadCarga +
                ",\nConsumo de Combustible por Km: " + consumoCombustibleKm +
                ",\nFecha de Ultimo Mantenimiento: " + fechaUltimoMantenimiento
                ;
    }
}