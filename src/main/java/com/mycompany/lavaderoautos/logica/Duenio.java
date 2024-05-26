package com.mycompany.lavaderoautos.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Duenio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    private String numero;
    
    @OneToMany (mappedBy = "duenio")
    private List<Vehiculo> listaVehiculos;

    public Duenio() {
    }

    public Duenio(int id, String nombre, String numero, List<Vehiculo> listaVehiculos) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.listaVehiculos = listaVehiculos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
        this.listaVehiculos.add(vehiculo);
    }
    
    public void eliminarVehiculo(Vehiculo vehiculo){
        this.listaVehiculos.remove(vehiculo);
    }
}
