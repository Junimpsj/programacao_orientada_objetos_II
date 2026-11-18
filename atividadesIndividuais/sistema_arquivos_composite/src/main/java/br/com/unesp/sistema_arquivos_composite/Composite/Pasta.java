package br.com.unesp.sistema_arquivos_composite.Composite;

import java.util.ArrayList;
import java.util.List;

import br.com.unesp.sistema_arquivos_composite.Interface.ElementoSistema;

/**
 * Composite do Composite: pasta que agrupa outros {@link ElementoSistema},
 * podendo conter tanto arquivos quanto outras pastas.
 */
// composite, guarda a lista de ElementoSistema então aceita arquivo e pasta igual
// tamanho e exibição são recursivos, cada filho resolve o seu
public class Pasta implements ElementoSistema {

    private final String nome;
    private final List<ElementoSistema> elementos = new ArrayList<>();

    /**
     * Cria uma pasta vazia.
     *
     * @param nome nome da pasta
     * @throws IllegalArgumentException se o nome for nulo ou vazio
     */
    public Pasta(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da pasta não pode ser vazio.");
        }

        this.nome = nome;
    }

    /**
     * Adiciona um elemento (arquivo ou pasta) a esta pasta.
     *
     * @param elemento elemento a ser adicionado
     * @throws IllegalArgumentException se o elemento for nulo ou a própria pasta
     */
    // a pasta não pode conter ela mesma, senão o getTamanho entra em recursão infinita
    public void adicionar(ElementoSistema elemento) {

        if (elemento == null) {
            throw new IllegalArgumentException("O elemento não pode ser nulo.");
        }

        if (elemento == this) {
            throw new IllegalArgumentException("Uma pasta não pode conter ela mesma.");
        }

        elementos.add(elemento);
    }

    /**
     * Remove um elemento desta pasta.
     *
     * @param elemento elemento a ser removido
     * @return {@code true} se o elemento estava na pasta e foi removido
     * @throws IllegalArgumentException se o elemento for nulo
     */
    public boolean remover(ElementoSistema elemento) {

        if (elemento == null) {
            throw new IllegalArgumentException("O elemento não pode ser nulo.");
        }

        return elementos.remove(elemento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * Calcula o tamanho da pasta somando o tamanho de todos os seus elementos.
     *
     * @return o tamanho total em KB (0 para pasta vazia)
     */
    @Override
    public long getTamanho() {

        long total = 0;

        for (ElementoSistema elemento : elementos) {
            total += elemento.getTamanho();
        }

        return total;
    }

    /**
     * Imprime a pasta e, recursivamente, todos os seus elementos um nível abaixo.
     *
     * @param nivel profundidade da pasta na hierarquia (0 para a raiz)
     */
    @Override
    public void exibir(int nivel) {
        System.out.println("  ".repeat(nivel) + "[Pasta] " + nome + " (" + getTamanho() + " KB)");

        for (ElementoSistema elemento : elementos) {
            elemento.exibir(nivel + 1);
        }
    }
}
