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
        return this.dueJpa.findDuenioEntities();
    }

    public List<Vehiculo> buscarVehiculos() {
        return this.vehiJpa.findVehiculoEntities();
    }

    public void crearDuenio(Duenio duenio) {
        this.dueJpa.create(duenio);
    }

    public void crearVehiculo(Vehiculo vehiculo) {
        this.vehiJpa.create(vehiculo);
    }

    public void eliminarVehiculo(int id) {
        try {
            this.vehiJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vehiculo buscarVehiculo(int id) {
        return this.vehiJpa.findVehiculo(id);
    }

    public void editarVehiculo(Vehiculo vehi) {
        try {
            this.vehiJpa.edit(vehi);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarDuenio(int id) {
        try {
            this.dueJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Duenio buscarDuenio(int id) {
        return this.dueJpa.findDuenio(id);
    }

    public void editarDuenio(Duenio due) {
        try {
            this.dueJpa.edit(due);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
