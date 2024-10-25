package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ComboTest {
	
	private Combo combo;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    private ArrayList<ProductoMenu> itemsCombo;

	@BeforeEach
	void setUp() throws Exception {
		producto1 = new ProductoMenu("Hamburguesa", 10000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);
        
        itemsCombo = new ArrayList<>();
        itemsCombo.add(producto1);
        itemsCombo.add(producto2);
        
        combo = new Combo("Combo 1", 0.8, itemsCombo);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    void testGetNombre() {
        assertEquals("Combo 1", combo.getNombre(), "El nombre del combo no es correcto.");
    }
	
	@Test
    void testGetPrecio() {
        int precioEsperado = (int) ((10000 + 5000) * 0.8); 
        assertEquals(precioEsperado, combo.getPrecio(), "El precio del combo no es correcto.");
    }
	
	@Test
    void testGenerarTextoFactura() {
        String expectedFactura = "Combo Combo 1\n" + 
                                 " Descuento: 0.8\n" + 
                                 "            " + combo.getPrecio() + "\n";
        assertEquals(expectedFactura, combo.generarTextoFactura(), "El texto de la factura no es correcto.");
    }

}
