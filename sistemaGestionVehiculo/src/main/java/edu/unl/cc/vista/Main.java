package edu.unl.cc.vista;

import edu.unl.cc.modelo.Camioneta;
import edu.unl.cc.modelo.Dao;
import edu.unl.cc.controlador.GestionFlota;
import edu.unl.cc.modelo.Vehiculo;
import java.util.List;
import java.util.Scanner;
import static edu.unl.cc.controlador.RegistroVehiculo.registrarVehiculo;
import static edu.unl.cc.controlador.RegistroViaje.registrarViaje;
/**
 * AUTORES:
 * Emilio Galvez, Katheryn Contento, Alejandro Padilla, Ivett Zaragocin
 */
public class Main {

    public static void main(String[] args) {
        Dao dao = new Dao();
        GestionFlota gestion = new GestionFlota(dao);
        Scanner sc = new Scanner(System.in);
        while (true) {
            mostrarMenu();
            int opcion = Integer.parseInt(sc.nextLine());
            try {
                switch (opcion) {
                    case 1:
                        registrarVehiculo(gestion, sc);
                        break;
                    case 2:
                        registrarViaje(gestion, sc);
                        break;
                    case 3:
                        System.out.println("Ingrese placa para actualizar mantenimiento: ");
                        String placaM = sc.nextLine();
                        boolean actualizado = gestion.actualizarMantenimiento(placaM);
                        if (actualizado) {
                            System.out.println("Mantenimiento actualizado.");
                        } else {
                            System.out.println("No se pudo actualizar mantenimiento o no requiere mantenimiento.");
                        }
                        break;
                    case 4:
                        List<Vehiculo> lista = gestion.listarVehiculos();
                        for (Vehiculo vehiculo : lista) {
                            System.out.println(vehiculo);
                            if (vehiculo instanceof Camioneta camioneta) {
                                System.out.println("4x4: " + (camioneta.isTraccion4x4() ? "Sí" : "No"));
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opción inválida, intente nuevamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error de validación: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error! " + e.getMessage());
            }
        }
    }

    public static void mostrarMenu (){
        System.out.println("* Menú Sistema Gestión de Vehiculos *");
        System.out.println("Autores: ");
        System.out.println("Ivett Zaragocin\nEmilio Galvez\nAlejandro Padilla\nKatheryn Contento");
        System.out.println(" ------------------------------------");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Registrar viaje");
        System.out.println("3. Actualizar mantenimiento");
        System.out.println("4. Listar vehículos");
        System.out.println("5. Salir");
        System.out.println("Seleccione una opción: ");
    }

}