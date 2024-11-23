package test.logic;

import static org.junit.Assert.*;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;

	@Before
	public void setUp1() {
		modelo = new Modelo(); // Usamos el constructor por defecto
	}

	@Test
	public void testModelo() {
		assertTrue(modelo != null); // Verifica que la instancia no sea nula
	}

	@Test
	public void testCalculateDistance() {
		// Verifica que el cálculo de distancia funcione correctamente
		float distancia = modelo.calculateDistance(-74.08175, 4.60971, -73.1198, 7.1218); // Bogotá a Bucaramanga
		assertTrue("La distancia no es correcta", distancia > 0);
	}
}
