package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

class ProductoAjustadoTest {
	
	private ProductoAjustado productoAjustado;
    private ProductoMenu productoBase;
    private Ingrediente ingrediente1;
    private Ingrediente ingrediente2;

	@BeforeEach
	void setUp() throws Exception {
		productoBase = new ProductoMenu("Hamburguesa", 15000);
		productoAjustado = new ProductoAjustado(productoBase);
		
		ingrediente1 = new Ingrediente("Queso", 2000);
        ingrediente2 = new Ingrediente("Tocineta", 2500);
        
        productoAjustado.agregarIngrediente(ingrediente1);
        productoAjustado.agregarIngrediente(ingrediente2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    void testGetNombre() {
        assertEquals("Hamburguesa", productoAjustado.getNombre(), "El nombre del producto ajustado no es correcto.");
    }
	
	@Test
    void testGetPrecio() {
        int precioEsperado = 15000 + 2000 + 2500;
        assertEquals(precioEsperado, productoAjustado.getPrecio(), "El precio del producto ajustado no es correcto.");
    }
	
	@Test
    void testGenerarTextoFactura() {
        String expectedFactura = "Hamburguesa 15000\n" + 
                                 "    +Queso                2000\n" + 
                                 "    +Tocineta                2500\n" + 
                                 "            " + (15000 + 2000 + 2500) + "\n";
        assertEquals(expectedFactura, productoAjustado.generarTextoFactura(), "El texto de la factura no es correcto.");
    }
	
	@Test
	void testEliminarIngrediente() {
	    productoAjustado.eliminarIngrediente(ingrediente1); 
	    
	    String expectedFactura = "Hamburguesa 15000\n" +
	                             "    +Tocineta                2500\n" +
	                             "    -Queso\n" +
	                             "            " + (15000 + 2500) + "\n";
	    
	    assertEquals(expectedFactura, productoAjustado.generarTextoFactura(), "El texto de la factura con ingrediente eliminado no es correcto.");
	}


}
