package com.mycompany.lavaderoautos.logica;

import com.mycompany.lavaderoautos.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    private ControladoraPersistencia controlPersis = null;

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }

    public List<Duenio> buscarDuenios() {
        return controlPersis.buscarDuenios();
    }

    public List<Vehiculo> buscarVehiculos(){
        return controlPersis.buscarVehiculos();
    }
    
    public void crearDuenio(String nombre, String celular) {
        Duenio duenio = new Duenio();
        duenio.setNombre(nombre);
        duenio.setNumero(celular);
        controlPersis.crearDuenio(duenio);
    }

    public void crearVehiculo(String tipo, String placa, String modelo, String color, String duenioRecibido, String servicio) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(tipo);
        vehiculo.setPlaca(placa);
        vehiculo.setModelo(modelo);
        vehiculo.setColor(color);
        vehiculo.setServicio(servicio);
        
        String valor = this.establecerValor(tipo, servicio);
        vehiculo.setValor(valor);
        
        Duenio duenioEncontrado = this.encontrarDuenio(duenioRecibido);
        
        if (duenioEncontrado != null){
            vehiculo.setDuenio(duenioEncontrado);
            duenioEncontrado.agregarVehiculo(vehiculo);
        }
        
        this.controlPersis.crearVehiculo(vehiculo);
    }    
    
    public Duenio encontrarDuenio(String duenioRecibido){
        List<Duenio> listaDuenio = this.buscarDuenios();
        
        for(Duenio i : listaDuenio){
            if(i.getNombre().equals(duenioRecibido)){
                return i;
            }
        }
        
        return null;
    }

    private String establecerValor(String tipo, String servicio) {
        if (tipo.equals("Motocicleta")){
            if(servicio.equals("Lavado exterior")){
            return "6000";
            }
            if(servicio.equals("Lavado general")){
            return "12000";
            }
            if(servicio.equals("Encerado")){
            return "12000";
            }
        }
        
        if (tipo.equals("Automovil")){
            if(servicio.equals("Lavado exterior")){
            return "10000";
            }
            if(servicio.equals("Lavado general")){
            return "18000";
            }
            if(servicio.equals("Encerado")){
            return "24000";
            }
        }
        
        if (tipo.equals("Camioneta")){
            if(servicio.equals("Lavado exterior")){
            return "20000";
            }
            if(servicio.equals("Lavado general")){
            return "34000";
            }
            if(servicio.equals("Encerado")){
            return "40000";
            }
        }
        
        return "0";
    }

    public boolean validarDuenio(String nombre) {
        List<Duenio> listaDuenios = this.buscarDuenios();
        
        for(Duenio i : listaDuenios){
            if(i.getNombre().equalsIgnoreCase(nombre)){
                return false;
            }
        }
        return true;
    }

    public boolean validarLogin(String usuario, String contra) {
        if(usuario.equals("Rodrigo Paez")){
            if (contra.equals("CuentasLavaderoLa35.")){
                return true;
            }
        }
        return false;
    }

    public void eliminarVehiculo(int id) {
        this.controlPersis.eliminarVehiculo(id);
    }

    public Vehiculo buscarVehiculo(int id) {
        return controlPersis.buscarVehiculo(id);
    }

    public void editarVehiculo(Vehiculo vehi, String tipo, String placa, String modelo, String color, String duenio, String servicio) {
        
        vehi.setTipo(tipo);
        vehi.setPlaca(placa);
        vehi.setModelo(modelo);
        vehi.setColor(color);
        vehi.setServicio(servicio);
        String valor = this.establecerValor(tipo, servicio);
        vehi.setValor(valor);
        Duenio dueEncontrado = this.encontrarDuenio(duenio);
        if (dueEncontrado != null) {
            if (!vehi.getDuenio().equals(dueEncontrado)) {
                vehi.getDuenio().eliminarVehiculo(vehi);
                vehi.setDuenio(dueEncontrado);
                dueEncontrado.agregarVehiculo(vehi);
            }
        }
        
        this.controlPersis.editarVehiculo(vehi);
    }
    
    
}
