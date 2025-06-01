package edu.unl.cc.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * AUTORES:
 * Emilio Galvez, Katheryn Contento, Alejandro Padilla, Ivett Zaragocin
 */
public class Dao {
    private final List<Vehiculo> vehiculos;
    private final List<Viaje> viajes;

    public Dao() {
        this.vehiculos = new ArrayList<>();
        this.viajes = new ArrayList<>();
    }

    public void actualizarFechaMantenimiento(Vehiculo vehiculo, Date nuevaFecha) {
        if (vehiculo != null && nuevaFecha != null) {
            vehiculo.setFechaUltimoMantenimiento(nuevaFecha);
        }
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            vehiculos.add(vehiculo);
        }
    }

    public void agregarViaje(Viaje viaje) {
        if (viaje != null) {
            viajes.add(viaje);
        }
    }

    public void mostrarVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo v : vehiculos) {
                System.out.println(v);
            }
        }
    }

    public boolean necesitaMantenimiento(Date fechaUltimoMantenimiento) {
        if (fechaUltimoMantenimiento == null) return false;
        // Calcula la diferencia entre la ultima fecha de mantenimiento y la act
        long diferenciaDias = (new Date().getTime() - fechaUltimoMantenimiento.getTime())
                / (1000 * 60 * 60 * 24);// Se transforma a dias
        return diferenciaDias > 180; // Si es mayor a 6 meses
    }

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlacaUnica().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos); // Para proteger la lista original
    }

    public List<Viaje> getViajes() {
        return new ArrayList<>(viajes);
    }
}
