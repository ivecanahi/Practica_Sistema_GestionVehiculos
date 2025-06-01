package edu.unl.cc.validador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ValidadorExcepciones {

    //se valida con el formato ABC-2532
    public static String validarFormatoPlaca(String placa) throws IllegalArgumentException {
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }
        if (!placa.matches("^[A-Z]{3}-\\d{4}$")) {
            throw new IllegalArgumentException("La placa no cumple con el formato ABC-1234.");
        }
        return placa;
    }

    public static void validarNumeroPositivo(String entrada) throws IllegalArgumentException {
        try {
            double numero = Double.parseDouble(entrada);
            if (numero <= 0) {
                throw new IllegalArgumentException("El número debe ser positivo.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Entrada inválida: no es un número.");
        }
    }

    public static void validarCapacidad(double capacidad) throws IllegalArgumentException {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        }
    }

    public static void validarConsumo(double consumo) throws IllegalArgumentException {
        if (consumo <= 0) {
            throw new IllegalArgumentException("El consumo debe ser mayor a cero.");
        }
    }

    public static void validarDistancia(double distancia) throws IllegalArgumentException {
        if (distancia <= 0) {
            throw new IllegalArgumentException("La distancia recorrida debe ser mayor a cero.");
        }
    }
    public static void validarFechaNoNula(Date fecha) throws IllegalArgumentException {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
    }

    //se valida con el formato 2006-06-23
    public static void validarFechaFormato(String fechaString) throws IllegalArgumentException {
        try {
            LocalDate.parse(fechaString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha no tiene un formato válido (YYYY-MM-DD).");
        }
    }
}