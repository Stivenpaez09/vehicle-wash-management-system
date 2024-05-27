package com.mycompany.lavaderoautos.logica;

import com.mycompany.lavaderoautos.persistencia.ControladoraPersistencia;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controladora {
    private ControladoraPersistencia controlPersis = null;

    public Controladora() {
        this.controlPersis = new ControladoraPersistencia();
    }

    public List<Duenio> buscarDuenios() {
        return this.controlPersis.buscarDuenios();
    }

    public List<Vehiculo> buscarVehiculos(){
        return this.controlPersis.buscarVehiculos();
    }
    
    public void crearDuenio(String nombre, String celular) {
        Duenio duenio = new Duenio();
        duenio.setNombre(nombre);
        duenio.setNumero(celular);
        this.controlPersis.crearDuenio(duenio);
    }

    public void crearVehiculo(String tipo, String placa, String modelo, String color, String duenioRecibido, String servicio) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(tipo);
        vehiculo.setPlaca(placa);
        vehiculo.setModelo(modelo);
        vehiculo.setColor(color);
        vehiculo.setServicio(servicio);
        
        ZonedDateTime ahora = ZonedDateTime.now();
        String horaActual = ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        vehiculo.setHora(horaActual);
        
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
        return this.controlPersis.buscarVehiculo(id);
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
            if (vehi.getDuenio() != null) {
                if (!vehi.getDuenio().equals(dueEncontrado)) {

                    vehi.getDuenio().eliminarVehiculo(vehi);
                    vehi.setDuenio(dueEncontrado);
                    dueEncontrado.agregarVehiculo(vehi);
                }
            } else {
                vehi.setDuenio(dueEncontrado);
                dueEncontrado.agregarVehiculo(vehi);
            }
        }
        this.controlPersis.editarVehiculo(vehi);
    }

    public String calcularTotal() {
        List<Vehiculo> listaVehiculos = this.buscarVehiculos();
        int valores = 0;
        for (Vehiculo lista : listaVehiculos) {
            valores += Integer.parseInt(lista.getValor());
        }
        String suma = String.valueOf(valores);
        return suma;
    }

    public void vaciarVehiculos() {
        List<Vehiculo> listaVehiculos = this.buscarVehiculos();
        
        for (Vehiculo lista : listaVehiculos){
            int id = lista.getId();
            this.controlPersis.eliminarVehiculo(id);
        }
    }

    public void eliminarDuenio(int id) {
        this.controlPersis.eliminarDuenio(id);
    }

    public Duenio buscarDuenio(int id) {
        return this.controlPersis.buscarDuenio(id);
    }

    public void editarDuenio(Duenio due, String nombre, String celular) {
        due.setNombre(nombre);
        due.setNumero(celular);
        
        this.controlPersis.editarDuenio(due);
    }

    public boolean validarDatos(int id, String nombre, String celular) {
        List<Duenio> listaDuenios = this.buscarDuenios();
        for(Duenio lista : listaDuenios){
            if(lista.getId() != id){
                if(lista.getNombre().equalsIgnoreCase(nombre)){
                    return false;
                }
            }
            
        }
        return true;
        
    }

    public String verificarNombreDuenio(Duenio duenio) {
        if(duenio == null){
            return "no existe";
        }
        return duenio.getNombre();
    }

    public String verificarNumeroDuenio(Duenio duenio) {
        if(duenio == null){
            return "no existe";
        }
        return duenio.getNumero();
    }

    
    
    
}
