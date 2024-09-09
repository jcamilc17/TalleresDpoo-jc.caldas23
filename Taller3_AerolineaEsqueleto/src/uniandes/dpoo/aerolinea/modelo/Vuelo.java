package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;

import java.util.Collection;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	private String fecha;
	private Ruta ruta;
	private Avion avion;
	private Collection<Tiquete> tiquetes;
	

	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
		this.tiquetes = new ArrayList<>();
	}
	
	public Ruta getRuta() {
		return ruta;
	}


	public Avion getAvion() {
		return avion;
	}


	public String getFecha() {
		return fecha;
	}
	
	public Collection<Tiquete> getTiquetes() {
		return tiquetes;
	}
	
	public void agregarTiquete(Tiquete tiquete) {
		this.tiquetes.add(tiquete);
	}

}
