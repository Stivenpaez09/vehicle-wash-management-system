package com.mycompany.lavaderoautos.logica;

import com.mycompany.lavaderoautos.logica.Vehiculo;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-27T19:39:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Duenio.class)
public class Duenio_ { 

    public static volatile SingularAttribute<Duenio, String> numero;
    public static volatile ListAttribute<Duenio, Vehiculo> listaVehiculos;
    public static volatile SingularAttribute<Duenio, Integer> id;
    public static volatile SingularAttribute<Duenio, String> nombre;

}