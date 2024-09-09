package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	
	protected List<Tiquete> listaTiquetes;
	
	public Cliente() {
		this.listaTiquetes = new ArrayList<>();
	}
	
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	public void agregarTiquete (Tiquete tiquete) {
		listaTiquetes.add(tiquete);
	}
	
	public int calcularValorTotalTiquetes() {
		int total = 0;
		for (Tiquete tiquete : listaTiquetes) {
			total += tiquete.getTarifa();
		}
		return total;
	}
	
	public void usarTiquetes(Vuelo vuelo) {
		for (Tiquete tiquete : listaTiquetes) {
			if (!tiquete.esUsado() && tiquete.getVuelo().equals(vuelo)) {
				tiquete.marcarComoUsado();
			}
		}
		
	}

}
