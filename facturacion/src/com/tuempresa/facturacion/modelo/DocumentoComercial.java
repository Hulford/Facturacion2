package com.tuempresa.facturacion.modelo;
import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import lombok.*;
@Entity @Getter @Setter
@View(members =
"anyo, numero, fecha;"+
"datos{"+
"cliente;"+
		"detalles;"+
"observaciones"+
		"}")
abstract public class DocumentoComercial extends Identificable{
	
	 @Column(length=4)
	 @DefaultValueCalculator(CurrentYearCalculator.class)
	 int anyo;
	 
	 @Column(length=6)
	 int numero;
	 
	 @Required
	@DefaultValueCalculator(CurrentLocalDateCalculator.class)
	 LocalDate fecha;
	 
	 @ManyToOne(fetch=FetchType.LAZY, optional=false)
	 @ReferenceView("Simple")
	 Cliente cliente;
	 
	@ElementCollection
	@ListProperties("producto.numero, producto.descripcion, cantidad, precioPorUnidad, importe")
	 Collection<Detalle> detalles;
	 
	 @TextArea
	 String observaciones;
	 
	 
	
	
	
	
	
}
