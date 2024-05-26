package com.mycompany.lavaderoautos.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vehiculo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String tipo;
    private String placa;
    private String modelo;
    private String color;
    private String servicio;
    private String Valor;
    @ManyToOne
    @JoinColumn(name = "fk_duenio")
    private Duenio duenio;

    public Vehiculo() {
    }

    public Vehiculo(int id, String tipo, String placa, String modelo, String color, String servicio, String Valor, Duenio duenio) {
        this.id = id;
        this.tipo = tipo;
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.servicio = servicio;
        this.Valor = Valor;
        this.duenio = duenio;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }
    
    
}
