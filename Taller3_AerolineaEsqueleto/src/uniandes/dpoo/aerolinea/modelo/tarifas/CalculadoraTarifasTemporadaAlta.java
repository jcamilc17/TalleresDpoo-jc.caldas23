package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas{
	
	private static final int COSTO_POR_KM = 1000;
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase = calcularCostoBase(vuelo, cliente);
		double descuento = calcularPorcentajeDescuento(cliente);
		int costoConDescuento = (int) (costoBase *(1 - descuento));
		return costoConDescuento + calcularValorImpuestos(costoConDescuento);
	}
	
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		return distancia * COSTO_POR_KM;
	}
	
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		return 0;
	}

}
