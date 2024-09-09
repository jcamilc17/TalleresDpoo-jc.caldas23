package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;


public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	
	private static final int COSTO_POR_KM_NATURAL = 600;
	private static final int COSTO_POR_KM_CORPORATIVO = 900;
	private static final double DESCUENTO_PEQ = 0.02;
	private static final double DESCUENTO_MEDIANAS = 0.1;
	private static final double DESCUENTO_GRANDES = 0.2;
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase = calcularCostoBase(vuelo, cliente);
		double descuento = calcularPorcentajeDescuento(cliente);
		int costoConDescuento = (int) (costoBase *(1 - descuento));
		return costoConDescuento + calcularValorImpuestos(costoConDescuento);
	}
	
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		if (cliente instanceof ClienteCorporativo) {
			return distancia * COSTO_POR_KM_CORPORATIVO;
		} else {
			return distancia * COSTO_POR_KM_NATURAL;
		}
	}
	
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		if (cliente instanceof ClienteCorporativo) {
			ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
			
			if (corporativo.getTamanoEmpresa()==3) {
				return DESCUENTO_PEQ;
			}else if (corporativo.getTamanoEmpresa()==2) {
				return DESCUENTO_MEDIANAS;
			}else if (corporativo.getTamanoEmpresa()==1){
				return DESCUENTO_GRANDES;
			}
		}
		return 0;
	}

}
