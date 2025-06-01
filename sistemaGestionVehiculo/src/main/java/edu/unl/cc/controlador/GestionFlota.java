package edu.unl.cc.controlador;

import edu.unl.cc.modelo.*;
import java.util.Date;
import java.util.List;


public class GestionFlota {

    // Precio del combustible como variable global
    private double precioCombustible = 1.5;
    protected Dao dao;

    public GestionFlota(Dao dao) {
        this.dao = dao;
    }

    public boolean registrarVehiculo(int tipo, String placa, double capacidad, double consumoCombustible,
                                     Date fechaMantenimiento, Integer cilindrada, Boolean es4x4, Integer ejes) {
        Vehiculo vehiculo = null;

        switch (tipo) {
            case 1:
                vehiculo = new Moto(placa, capacidad, consumoCombustible, fechaMantenimiento, cilindrada);
                break;
            case 2:
                vehiculo = new Camioneta(placa, capacidad, consumoCombustible, fechaMantenimiento, es4x4);
                break;
            case 3:
                vehiculo = new Camion(placa, capacidad, consumoCombustible, fechaMantenimiento, ejes);
                break;
            default:
                return false;
        }

        dao.agregarVehiculo(vehiculo);
        return true;
    }

    public Viaje registrarViaje(String placa, double distancia, Date fechaViaje, double precioCombustible) {
        Vehiculo vehiculo = dao.buscarVehiculoPorPlaca(placa);
        if (vehiculo == null) {
            return null;
        }
        Viaje viaje = new Viaje(distancia, fechaViaje, vehiculo);
        dao.agregarViaje(viaje);
        return viaje;
    }

    public boolean actualizarMantenimiento(String placa) {
        Vehiculo vehiculo = dao.buscarVehiculoPorPlaca(placa);
        if (vehiculo == null) {
            return false;
        }
        if (vehiculo.solicitarMantenimiento()) {
            vehiculo.actualizarMantenimiento();
            return true;
        }
        return false;
    }

    public List<Vehiculo> listarVehiculos() {
        return dao.getVehiculos();
    }

    public double getPrecioCombustible() {
        return precioCombustible;
    }

    public void setPrecioCombustible(double precioCombustible) {
        this.precioCombustible = precioCombustible;
    }
}