package br.com.cod3r.cm.visao;

import br.com.cod3r.cm.modelo.Tabuleiro;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	// Construtor
	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);

		// Adicionando o painel do próprio TABULEIRO
		PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);
		add(painelTabuleiro);

		setVisible(true);
		setTitle("Campo Minado");
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TelaPrincipal();
	}

	/*
	 * CONFIGURAÇÕES DA TELA DO JOGO Visibilidade da tela Título da tela Tamanho da
	 * tela Localização da tela Fechamento da tela
	 * 
	 */
}
