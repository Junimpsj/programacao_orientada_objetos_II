package br.com.unesp.validador_codigos_interpreter.Models;

/**
 * Context: guarda a entrada que está sendo interpretada e a posição atual da
 * leitura, compartilhadas por todas as expressões da árvore.
 */
public class Context {

    private String entrada;
    private int posicao;

    /**
     * Cria o contexto com a leitura no início da entrada.
     *
     * @param entrada código a ser interpretado
     */
    public Context(String entrada) {
        this.entrada = entrada;
        this.posicao = 0;
    }

    /**
     * Retorna a entrada sendo interpretada.
     *
     * @return a entrada
     */
    public String getEntrada() {
        return entrada;
    }

    /**
     * Retorna a posição atual da leitura.
     *
     * @return o índice do próximo caractere a ser lido
     */
    public int getPosicao() {
        return posicao;
    }

    /**
     * Altera a posição da leitura. Usado pelas expressões para voltar atrás (backtracking).
     *
     * @param posicao nova posição
     */
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    /**
     * Informa se toda a entrada já foi lida.
     *
     * @return {@code true} se não há mais caracteres
     */
    public boolean isFim() {
        return posicao >= entrada.length();
    }

    /**
     * Retorna o caractere na posição atual.
     *
     * @return o caractere atual
     */
    public char getAtual() {
        return entrada.charAt(posicao);
    }

    /**
     * Avança a leitura em uma posição.
     */
    public void avancar() {
        posicao++;
    }
}
