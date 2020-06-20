package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador {

    private int quantLinhas;
    private int quantColunas;
    private int quantMinas;

    private final List<Campo> campos = new ArrayList<>();
    private List<Consumer<Boolean>> observadores = new ArrayList<>();

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
        } catch (Exception e) {
            //FIXME ajustar a implementação do método abrir
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }

    public void alterarMarcacao(int linha, int coluna) {
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alterarMarcacao());
    }

    private void gerarCampos() {
        for (int l = 0; l < quantLinhas; l++) {
            for (int c = 0; c < quantColunas; c++) {
                Campo campo = new Campo(l, c);
                campo.registrarObservador(this);
                campos.add(campo);
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
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
        } while (minasArmadas < quantMinas);
    }

    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reiniciar() {
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        if (evento == CampoEvento.EXPLODIR) {
            System.out.println("PERDEU...");
        } else if (objetivoAlcancado()) {
            System.out.println("VOCE GANHOU");
        }
    }
}
