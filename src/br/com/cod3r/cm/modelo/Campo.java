package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	private final int linha;
	private final int coluna;
	private boolean aberto; // Por padrão ele vai receber falso
	private boolean minado; // Por padrão ele vai receber falso
	private boolean marcado; // Por padrão ele vai receber falso

	// Criado uma lista do tipo campo para ser adicionado os elementos
	private List<Campo> vizinhos = new ArrayList<Campo>();

	// Construtor
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	// Métodos
	boolean adicionarVizinho(Campo vizinho) {
		/*
		 * Para calcular nessa fase tem que ter em mente que para calcular a coluna, ela
		 * tem que ser igual a 2 e a linha tem que ser igual a 1 conforme explicação do
		 * projeto.
		 */

		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		// Cálculo da linha e coluna
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;

		// Lógia da linha e coluna

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}
}
