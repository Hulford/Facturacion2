package com.tuempresa.facturacion.modelo;
import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

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
	 
	 @Stereotype("MENU")
	 String observaciones;
	 
	 @ManyToOne(fetch=FetchType.LAZY, optional=false)
	 @ReferenceView("Simple")
	 Cliente cliente;
	 
	@ElementCollection
	@ListProperties("producto.numero, producto.descripcion, cantidad, precioPorUnidad, " +
			"importe+[" + 
			"documentoComercial.porcentajeIVA," +
			"documentoComercial.iva," +
			"documentoComercial.importeTotal" +
			"]")
	 private Collection<Detalle> detalles;
	
	@Digits(integer=2, fraction=0) 
	BigDecimal porcentajeIVA;
	@ReadOnly
	@Money
	@Calculation("sum(detalles.importe) * porcentajeIVA / 100")
	BigDecimal iva;
	@ReadOnly
	@Money
	@Calculation("sum(detalles.importe) + iva") 
	BigDecimal importeTotal; 
	
	 
	 
	 
	
	
	
	
	
}
