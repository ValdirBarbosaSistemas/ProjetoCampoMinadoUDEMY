package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CampoTeste {
	// Criação do campo
	private Campo campo = new Campo(3, 3);

	// Linha
	@Test
	void testeVizinhoDistancia1Esquerda() {
		// Criação do vizinho
		Campo vizinho = new Campo(3, 2);

		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDistancia1Direita() {
		// Criação do vizinho
		Campo vizinho = new Campo(3, 4);

		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDistancia1EmCima() {
		// Criação do vizinho
		Campo vizinho = new Campo(2, 3);

		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDistancia1EmBaixo() {
		// Criação do vizinho
		Campo vizinho = new Campo(4, 3);

		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);
	}

	// Diagonal
	@Test
	void testeVizinhoDistancia2() {
		// Criação do vizinho
		Campo vizinho = new Campo(2, 2);

		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);
	}

	// Teste de NÃO VIZINHO
	@Test
	void testeNaoVizinho() {
		// Criação do vizinho
		Campo vizinho = new Campo(1, 1);

		// Como não é vizinho tem que retornar FALSE
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertFalse(resultado);
	}

}
