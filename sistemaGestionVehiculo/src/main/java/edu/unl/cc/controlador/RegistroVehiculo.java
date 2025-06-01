package edu.unl.cc.controlador;

import edu.unl.cc.modelo.Vehiculo;
import edu.unl.cc.validador.ValidadorExcepciones;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class RegistroVehiculo {

    public static void registrarVehiculo(GestionFlota gestion, Scanner sc) {
        int tipo = solicitarTipoVehiculo(sc);
        String placa = solicitarPlaca(sc);
        double capacidad = solicitarCapacidad(sc);
        double consumo = solicitarConsumo(sc);
        Date fechaMantenimiento = solicitarFechaMantenimiento(sc);

        Integer cilindrada = null;
        Boolean es4x4 = null;
        Integer numEjes = null;

        if (!procesarDatosEspecificos(sc, tipo)) return;

        if (tipo == 1) cilindrada = solicitarEntero(sc, "Cilindrada (cc): ");
        if (tipo == 2) es4x4 = solicitarBooleano(sc, "¿La camioneta tiene tracción 4x4? (si/no): ");
        if (tipo == 3) numEjes = solicitarEntero(sc, "Número de ejes: ");

        boolean registrado = gestion.registrarVehiculo(
                tipo, placa, capacidad, consumo, fechaMantenimiento, cilindrada, es4x4, numEjes);
        Vehiculo vehiculo = gestion.dao.buscarVehiculoPorPlaca(placa);
        System.out.println(registrado ? ("Vehículo con placa: " +vehiculo.getPlacaUnica() + " registrado correctamente.") : "Error al registrar vehículo.");
    }

    private static int solicitarTipoVehiculo(Scanner sc) {
        System.out.println("Ingrese el tipo de vehículo (1: Moto, 2: Camioneta, 3: Camión): ");
        return Integer.parseInt(sc.nextLine());
    }

    private static String solicitarPlaca(Scanner sc) {
        while (true) {
            try {
                System.out.print("Ingrese la placa (formato ABC-1234): ");
                String placa = sc.nextLine();
                ValidadorExcepciones.validarFormatoPlaca(placa);
                return placa;
            } catch (Exception e) {
                System.out.println("Error de validación: " + e.getMessage());
            }
        }
    }

    private static double solicitarCapacidad(Scanner sc) {
        return solicitarNumeroPositivo(sc, "Ingrese la capacidad de carga (kg): ");
    }

    private static double solicitarConsumo(Scanner sc) {
        return solicitarNumeroPositivo(sc, "Ingrese el consumo de combustible (litro/km): ");
    }

    private static double solicitarNumeroPositivo(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String valor = sc.nextLine();
                ValidadorExcepciones.validarNumeroPositivo(valor);
                return Double.parseDouble(valor);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static Date solicitarFechaMantenimiento(Scanner sc) {
        while (true) {
            try {
                String fechaStr = leerFecha(sc);
                return convertirFecha(fechaStr);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static String leerFecha(Scanner sc) {
        System.out.print("Ingrese la fecha de último mantenimiento (YYYY-MM-DD): ");
        String fechaStr = sc.nextLine();
        ValidadorExcepciones.validarFechaFormato(fechaStr);
        return fechaStr;
    }

    private static Date convertirFecha(String fechaStr) {
        LocalDate fechaLocal = LocalDate.parse(fechaStr);
        return Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static int solicitarEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(sc.nextLine());
    }

    private static boolean solicitarBooleano(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().equalsIgnoreCase("si");
    }

    private static boolean procesarDatosEspecificos(Scanner sc, int tipo) {
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo de vehículo inválido.");
            return false;
        }
        return true;
    }
}
