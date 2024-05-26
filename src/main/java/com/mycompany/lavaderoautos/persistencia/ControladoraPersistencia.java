package com.mycompany.lavaderoautos.persistencia;

import com.mycompany.lavaderoautos.logica.Duenio;
import com.mycompany.lavaderoautos.logica.Vehiculo;
import com.mycompany.lavaderoautos.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    VehiculoJpaController vehiJpa = new VehiculoJpaController();
    DuenioJpaController dueJpa = new DuenioJpaController();

    public List<Duenio> buscarDuenios() {
        return dueJpa.findDuenioEntities();
    }

    public List<Vehiculo> buscarVehiculos() {
        return vehiJpa.findVehiculoEntities();
    }

    public void crearDuenio(Duenio duenio) {
        dueJpa.create(duenio);
    }

    public void crearVehiculo(Vehiculo vehiculo) {
        vehiJpa.create(vehiculo);
    }

    public void eliminarVehiculo(int id) {
        try {
            vehiJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vehiculo buscarVehiculo(int id) {
        return vehiJpa.findVehiculo(id);
    }

    public void editarVehiculo(Vehiculo vehi) {
        try {
            vehiJpa.edit(vehi);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
