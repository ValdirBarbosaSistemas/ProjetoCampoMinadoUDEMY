package br.com.cod3r.cm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteFinal {

	@Test
	void testarSeIgualaDois() {

		int a = 1 + 1;

		assertEquals(2, a);
		/*
		 * Para se fazer um teste é necessário criar o método que quer ser executado com
		 * a declaração das variáveis e etc, e depois colocar abaixo a palavra 'assert'.
		 * Existem vários 'asserts' a disposição. O primeiro parâmetro é o que se espera
		 * como resultado e o segundo é a variável criada. Todo teste tem que ser
		 * colocado o annotation '@Teste' para ser executado, pois sem ele não irá
		 * funcionar. Com o JUnit voce não precisa se preocupar em fazer vários métodos
		 * 'mains' para testar, basta criar o Junit e chamar os métodos que quer
		 * executar o teste. Voce pode criar várias funções em uma única classe.
		 */
	}

	@Test
	void testarSeIgualaTres() {
		int x = 2 + 10 - 9;

		assertEquals(3, x);
	}
}
