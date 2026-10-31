package br.unesp.loja_virtual.interfaces;

/**
 * Prototype: contrato de objetos que sabem se clonar.
 */
public interface ProdutoPrototype {
    /**
     * Cria uma cópia do objeto.
     *
     * @return uma nova instância com os mesmos dados
     */
    ProdutoPrototype clone();
}