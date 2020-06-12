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

	}
}
