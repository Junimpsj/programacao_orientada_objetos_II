package br.com.unesp.sistema_arquivos_composite.Interface;

/**
 * Component do Composite: abstração comum a arquivos e pastas,
 * permitindo que ambos sejam tratados de forma uniforme.
 */
// component do composite, quem usa não precisa saber se é arquivo ou pasta
public interface ElementoSistema {
    /**
     * Retorna o nome do elemento.
     *
     * @return o nome do elemento
     */
    String getNome();
    /**
     * Retorna o tamanho ocupado pelo elemento, em KB.
     *
     * @return o tamanho em KB
     */
    long getTamanho();
    /**
     * Imprime no console o elemento, recuado de acordo com o nível na hierarquia.
     *
     * @param nivel profundidade do elemento na hierarquia (0 para a raiz)
     */
    void exibir(int nivel);
}
