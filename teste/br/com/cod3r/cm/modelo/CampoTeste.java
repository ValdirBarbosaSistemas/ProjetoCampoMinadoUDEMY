package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

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

	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlterarMarcacao() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
		// Aqui ele vai abrir o campo e não vai acontecer nada pois não tem mina
	}

	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alterarMarcacao();
		assertFalse(campo.abrir());
		// Ele não deve abrir o campo, pois está como marcado
	}

	@Test
	void testeAbrirMinadoMarcado() {
		campo.alterarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
		// Aqui ele vai lancar o exception quando abrir o campo minado
	}

	@Test
	void testeAbrirComVizinhos() {
		Campo vizinhoDoVizinho1 = new Campo(1, 1);

		Campo vizinho1 = new Campo(2, 2);
		vizinho1.adicionarVizinho(vizinhoDoVizinho1);
		
		campo.adicionarVizinho(vizinho1);
		campo.abrir();
		
		assertTrue(vizinho1.isAberto() && vizinhoDoVizinho1.isAberto());
	}
}
