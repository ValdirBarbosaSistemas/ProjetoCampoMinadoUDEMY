package br.com.cod3r.cm.visao;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class BotaoCampo extends JButton implements CampoObservador {
    
    private final Color BG_PADRAO = new Color(184, 184, 184); //BG quer dizer BackGroud
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);
    
    private Campo campo;

    //Construtor
    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBorder(BorderFactory.createBevelBorder(0));
        campo.registrarObservador(this);//Agora quando o evento for chamado ele será sinalizado
    }
    
    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento) {
            case ABRIR:
                aplicarEstiloAbrir();//Chamando os métodos que foram criados
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
                break;
        }
    }

    //Métodos
    private void aplicarEstiloAbrir() {
        
    }
    
    private void aplicarEstiloMarcar() {
        
    }
    
    private void aplicarEstiloExplodir() {
        
    }
    
    private void aplicarEstiloPadrao() {
        
    }
}
