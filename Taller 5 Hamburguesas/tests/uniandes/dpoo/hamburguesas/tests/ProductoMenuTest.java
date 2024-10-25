package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoMenuTest {
	
	private ProductoMenu producto;

	@BeforeEach
	void setUp() throws Exception {
		producto = new ProductoMenu("Hamburguesa", 15000);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    void testGetNombre() {
        assertEquals("Hamburguesa", producto.getNombre(), "El nombre del producto no es correcto.");
    }

	@Test
    void testGetPrecio() {
        assertEquals(15000, producto.getPrecio(), "El precio del producto no es correcto.");
    }
    
	@Test
    void testGenerarTextoFactura() {
        String expectedFactura = "Hamburguesa\n            15000\n";
        assertEquals(expectedFactura, producto.generarTextoFactura(), "El texto de la factura no es correcto.");
    }

	@Test
	void testPrecioCeroONegativo() {
	    producto = new ProductoMenu("Producto Gratis", 0);
	    assertEquals(0, producto.getPrecio(), "El precio debería ser cero.");
	    
	    producto = new ProductoMenu("Producto Negativo", -100);
	    assertEquals(-100, producto.getPrecio(), "El precio debería ser negativo si se permite.");
	}

	


}
