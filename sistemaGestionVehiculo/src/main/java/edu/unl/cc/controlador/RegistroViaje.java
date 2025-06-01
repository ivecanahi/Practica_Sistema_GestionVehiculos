package edu.unl.cc.controlador;

import edu.unl.cc.modelo.Vehiculo;
import edu.unl.cc.modelo.Viaje;
import edu.unl.cc.validador.ValidadorExcepciones;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

/**
 * AUTORES:
 * Emilio Galvez, Katheryn Contento, Alejandro Padilla, Ivett Zaragocin
 */
public class RegistroViaje {

        public static void registrarViaje(GestionFlota gestion, Scanner sc) {
            String placa = solicitarPlaca(sc);
            double distancia = solicitarDistancia(sc);
            Date fechaViaje = solicitarFechaViaje(sc);
            double precio = gestion.getPrecioCombustible();
            Viaje viaje = gestion.registrarViaje(placa, distancia, fechaViaje, precio);
            mostrarResumenViaje(viaje, distancia, precio);
        }


        private static String solicitarPlaca(Scanner sc) {
            while (true) {
                try {
                    System.out.print("Placa del vehículo: ");
                   return ValidadorExcepciones.validarFormatoPlaca(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Error de validación: " + e.getMessage());
                }
            }
        }

        private static double solicitarDistancia(Scanner sc) {
            while (true) {
                try {
                    System.out.print("Distancia recorrida (km): ");
                    String distanciaStr = sc.nextLine();
                    ValidadorExcepciones.validarNumeroPositivo(distanciaStr);
                    return Double.parseDouble(distanciaStr);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        private static Date solicitarFechaViaje(Scanner sc) {
            while (true) {
                try {
                    System.out.print("Fecha del viaje (YYYY-MM-DD): ");
                    String fechaStr = sc.nextLine();
                    ValidadorExcepciones.validarFechaFormato(fechaStr);
                    LocalDate fechaLocal = LocalDate.parse(fechaStr);
                    return Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage() + ". Intente nuevamente.");
                }
            }
        }

        private static void mostrarResumenViaje(Viaje viaje, double distancia, double precioCombustible) {
            double costo = viaje.calcularCostoTotal(precioCombustible);
            double combustibleUsado = distancia * viaje.getVehiculo().getConsumoCombustibleKm();
            double mantenimiento = Math.round(viaje.getVehiculo().generarCostoMantenimiento());

            System.out.println("Costo total del viaje: $" + costo);
            System.out.println("Combustible usado: " + combustibleUsado + " litros");
            System.out.println("Costo de mantenimiento: $" + mantenimiento);

            verificarMantenimiento(viaje);
        }

        private static void verificarMantenimiento(Viaje viaje) {
            Vehiculo vehiculo = viaje.getVehiculo();
            if (vehiculo.solicitarMantenimiento()) {
                System.out.println("Mantenimiento pendiente desde " + vehiculo.getFechaUltimoMantenimiento());
                System.out.println("Se recomienda realizar el mantenimiento antes del viaje.");
            }
        }
    }


