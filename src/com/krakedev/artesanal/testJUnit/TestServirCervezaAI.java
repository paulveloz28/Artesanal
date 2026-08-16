package com.krakedev.artesanal.testJUnit;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestServirCervezaAI {

	@Test
	public void testServirCantidadMenorDisponible() {

		// Valida que, cuando hay suficiente cerveza, se descuente
		// la cantidad solicitada y se retorne el valor a pagar.
		Maquina maquina = new Maquina("Pilsener", "Cerveza artesanal", 0.05, 5000);

		maquina.llenarMaquina();

		double cantidadAntes = maquina.getCantidadActual();

		double valor = maquina.servirCerveza(1000);

		double cantidadEsperada = cantidadAntes - 1000;
		double valorEsperado = 1000 * maquina.getPrecioPorMl();

		assertEquals(cantidadEsperada, maquina.getCantidadActual(), 0.0001);
		assertEquals(valorEsperado, valor, 0.0001);
	}

	@Test
	public void testServirTodaLaCantidadDisponible() {

		// Valida que se pueda servir exactamente toda la cerveza disponible,
		// dejando la cantidad actual en cero y retornando el valor correcto.
		Maquina maquina = new Maquina("Club", "Cerveza artesanal", 0.04, 5000);

		maquina.llenarMaquina();

		double cantidadDisponible = maquina.getCantidadActual();

		double valor = maquina.servirCerveza(cantidadDisponible);

		double valorEsperado = cantidadDisponible * maquina.getPrecioPorMl();

		assertEquals(0.0, maquina.getCantidadActual(), 0.0001);
		assertEquals(valorEsperado, valor, 0.0001);
	}

	@Test
	public void testNoServirCuandoCantidadSolicitadaEsMayor() {

		// Valida que, cuando se solicita más cerveza de la disponible,
		// no se sirva nada, se retorne cero y la cantidad actual no cambie.
		Maquina maquina = new Maquina("Pilsener", "Cerveza artesanal", 0.05, 5000);

		maquina.llenarMaquina();

		double cantidadAntes = maquina.getCantidadActual();

		double valor = maquina.servirCerveza(cantidadAntes + 1);

		assertEquals(0.0, valor, 0.0001);
		assertEquals(cantidadAntes, maquina.getCantidadActual(), 0.0001);
	}

	@Test
	public void testServirCervezaUsandoConstructorSinCapacidad() {

		// Valida el comportamiento usando el segundo constructor,
		// que establece una capacidad máxima de 10000 ml.
		Maquina maquina = new Maquina("Stout", "Cerveza oscura", 0.03);

		maquina.llenarMaquina();

		double cantidadAntes = maquina.getCantidadActual();

		double valor = maquina.servirCerveza(500);

		double cantidadEsperada = cantidadAntes - 500;
		double valorEsperado = 500 * maquina.getPrecioPorMl();

		assertEquals(cantidadEsperada, maquina.getCantidadActual(), 0.0001);
		assertEquals(valorEsperado, valor, 0.0001);
	}

	@Test
	public void testNoServirCuandoNoHayCerveza() {

		// Valida que, cuando la máquina no tiene cerveza disponible,
		// no se sirva nada, la cantidad permanezca en cero y se retorne cero.
		Maquina maquina = new Maquina("IPA", "Cerveza artesanal", 0.06);

		double valor = maquina.servirCerveza(500);

		assertEquals(0.0, valor, 0.0001);
		assertEquals(0.0, maquina.getCantidadActual(), 0.0001);
	}
}
