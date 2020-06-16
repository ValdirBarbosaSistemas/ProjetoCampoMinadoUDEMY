package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Tabuleiro {
	private int quantLinhas;
	private int quantColunas;
	private int quantMinas;

	private final List<Campo> campos = new ArrayList<>();

	// Construtor
	public Tabuleiro(int quantLinhasTabuleiro, int quantColunasTabuleiro, int quantMinasTabuleiro) {
		this.quantLinhas = quantLinhasTabuleiro;
		this.quantColunas = quantColunasTabuleiro;
		this.quantMinas = quantMinasTabuleiro;

		// Gerar campos através das linhas e colunas
		gerarCampos();

		// Gerar os vizinhos
		associarVizinhos();

		// Criação das minas
		sortearMinas();
	}

	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream()
				  .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
				  .findFirst()
				  .ifPresent(c -> c.abrir());
		} catch (ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}

	public void alterarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.alterarMarcacao());
	}

	private void gerarCampos() {
		for (int l = 0; l < quantLinhas; l++) {
			for (int c = 0; c < quantColunas; c++) {
				campos.add(new Campo(l, c));
			}
		}
	}

	private void associarVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;

		Predicate<Campo> minado = c -> c.isMinado();

		do {
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while (minasArmadas < quantMinas);
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (int linha = 0; linha < quantLinhas; linha++) {
			for (int coluna = 0; coluna < quantColunas; coluna++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
