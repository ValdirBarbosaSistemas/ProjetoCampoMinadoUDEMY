package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

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

	// Lógica de abrir o campo

	void alterarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}

			if (vizinhancaSegura()) {
				// Passando um consumer
				vizinhos.forEach(v -> v.abrir());
			}
			return true;

		} else {
			return false;
		}
	}

	boolean vizinhancaSegura() {
		// Passando um predicate
		// Nenhum vizinho pode bater ou nenhum vizinho pode estar minado
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	public boolean isMarcado() {
		return marcado;
	}

	void minar() {
		minado = true;
	}

	public boolean isAberto() {
		return aberto;
	}

	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isMinado() {
		return minado;
	}

	/*
	 * Como os atributos são constantes, ou seja, 'final', eles não tem como serem
	 * modificados e devido a isso só se é permitido fazer o 'GET'.
	 */
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}

	long minasNaVizinhanca() {
		// Aqui ele vai filtrar todas as minas no campo e mostrar
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}

	public String toString() {
		// Para mostrar as minas no terminal
		if (marcado) {
			return "X";
		} else if (aberto && minado) {
			return "*";
		} else if (aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
		} else if (aberto) {
			return " ";
		} else {
			return "?";
		}
	}
}
