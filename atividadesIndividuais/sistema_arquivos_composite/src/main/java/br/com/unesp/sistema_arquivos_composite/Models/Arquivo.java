package br.com.unesp.sistema_arquivos_composite.Models;

import br.com.unesp.sistema_arquivos_composite.Interface.ElementoSistema;

/**
 * Leaf do Composite: arquivo individual, que não possui elementos filhos.
 */
public class Arquivo implements ElementoSistema {

    private final String nome;
    private final long tamanho;

    /**
     * Cria um arquivo.
     *
     * @param nome nome do arquivo
     * @param tamanho tamanho do arquivo em KB, não pode ser negativo
     * @throws IllegalArgumentException se o nome for nulo/vazio ou o tamanho negativo
     */
    public Arquivo(String nome, long tamanho) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do arquivo não pode ser vazio.");
        }

        if (tamanho < 0) {
            throw new IllegalArgumentException("O tamanho do arquivo não pode ser negativo.");
        }

        this.nome = nome;
        this.tamanho = tamanho;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTamanho() {
        return tamanho;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exibir(int nivel) {
        System.out.println("  ".repeat(nivel) + "[Arquivo] " + nome + " (" + tamanho + " KB)");
    }
}
