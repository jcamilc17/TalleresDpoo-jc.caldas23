package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class PedidoTest {
	
	private Pedido pedido;
    private ProductoMenu producto1;
    private ProductoMenu producto2;


	@BeforeEach
	void setUp() throws Exception {
		producto1 = new ProductoMenu("Hamburguesa", 10000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);
        
        pedido = new Pedido("Juan Moreno", "Calle 153");
        
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    void testGetNombreCliente() {
        assertEquals("Juan Moreno", pedido.getNombreCliente(), "El nombre del cliente no es correcto.");
    }
	
	@Test
	void testIdentificadorUnico() {
	    Pedido pedido1 = new Pedido("Cliente 1", "Direccion 1");
	    Pedido pedido2 = new Pedido("Cliente 2", "Direccion 2");
	    
	    assertNotEquals(pedido1.getIdPedido(), pedido2.getIdPedido(), "El identificador de los pedidos debe ser único.");
	}

	
	@Test
    void testGetPrecioTotalPedido() {
        int precioNeto = 10000 + 5000; 
        int precioIVA = (int) (precioNeto * 0.19); 
        int precioTotalEsperado = precioNeto + precioIVA;
        
        assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido(), "El precio total del pedido no es correcto.");
    }
	
	@Test
	void testGenerarTextoFactura() {
        String expectedFactura = "Cliente: Juan Moreno\n" + 
                                 "Dirección: Calle 153\n" + 
                                 "----------------\n" + 
                                 producto1.generarTextoFactura() + 
                                 producto2.generarTextoFactura() + 
                                 "----------------\n" + 
                                 "Precio Neto:  15000\n" + 
                                 "IVA:          2850\n" + 
                                 "Precio Total: 17850\n";

        assertEquals(expectedFactura, pedido.generarTextoFactura(), "El texto de la factura no es correcto.");
    }
	
	@Test
	void testPedidoSinProductos() {
	    Pedido pedidoVacio = new Pedido("Carlos Perez", "Calle 200");
	    
	    String expectedFactura = "Cliente: Carlos Perez\n" + 
	                             "Dirección: Calle 200\n" + 
	                             "----------------\n" + 
	                             "----------------\n" + 
	                             "Precio Neto:  0\n" + 
	                             "IVA:          0\n" + 
	                             "Precio Total: 0\n";
	                             
	    assertEquals(expectedFactura, pedidoVacio.generarTextoFactura(), "La factura del pedido sin productos no es correcta.");
	}
	
	@Test
	void testCalculoIVAConPrecioAlto() {
	    ProductoMenu productoCaro = new ProductoMenu("Combo Deluxe", 1_000_000);
	    pedido.agregarProducto(productoCaro);
	    
	    int precioNeto = 10000 + 5000 + 1_000_000;
	    int precioIVA = (int) (precioNeto * 0.19);
	    int precioTotalEsperado = precioNeto + precioIVA;
	    
	    assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido(), "El cálculo de precio total para un precio alto no es correcto.");
	}


	
	@Test
    void testGuardarFactura() throws FileNotFoundException {
        File archivo = new File("facturaTest.txt");
        pedido.guardarFactura(archivo);
        
        assertTrue(archivo.exists(), "El archivo de factura no fue creado correctamente.");
        
        archivo.delete();
    }
	
}
