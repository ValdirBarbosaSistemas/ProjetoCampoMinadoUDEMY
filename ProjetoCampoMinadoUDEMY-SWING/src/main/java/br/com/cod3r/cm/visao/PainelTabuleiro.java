package br.com.cod3r.cm.visao;

import br.com.cod3r.cm.modelo.Tabuleiro;
import java.awt.GridLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

    // Construtor.
    public PainelTabuleiro(Tabuleiro tabuleiro) {
        setLayout(new GridLayout(tabuleiro.getQuantLinhas(), tabuleiro.getQuantColunas()));
        /*
        int total = tabuleiro.getQuantLinhas() * tabuleiro.getQuantColunas();
        
        for (int i = 0; i < total; i++) {
            add(new BotaoCampo(null));
        }
         */
        tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));
        tabuleiro.registrarObservadores(e -> {
            //TODO mostrar resultado para o usuário
        });
    }
}
