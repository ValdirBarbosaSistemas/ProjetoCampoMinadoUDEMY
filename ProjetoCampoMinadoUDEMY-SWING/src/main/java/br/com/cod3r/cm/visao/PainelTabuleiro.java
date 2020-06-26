package br.com.cod3r.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	// Construtor.
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getQuantLinhas(), tabuleiro.getQuantColunas()));
		/*
		 * int total = tabuleiro.getQuantLinhas() * tabuleiro.getQuantColunas();
		 * 
		 * for (int i = 0; i < total; i++) { add(new BotaoCampo(null)); }
		 */
		tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));
		tabuleiro.registrarObservadores(e -> {

			// Com o SWINGUTILITIES a mensagem só aparece depois do clique do mouse

			SwingUtilities.invokeLater(() -> {
				if (e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "VOCE GANHOU!!!");
				} else {
					JOptionPane.showMessageDialog(this, "VOCE PERDEU!!!");
				}
				tabuleiro.reiniciar();
			});

		});
	}
}