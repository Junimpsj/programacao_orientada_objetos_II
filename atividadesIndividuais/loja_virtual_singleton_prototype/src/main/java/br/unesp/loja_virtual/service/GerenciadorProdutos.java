package br.unesp.loja_virtual.service;

import java.util.ArrayList;

import br.unesp.loja_virtual.interfaces.ProdutoPrototype;
import br.unesp.loja_virtual.model.Produto;

/**
 * Gerencia a lista de produtos e cria novos produtos a partir de protótipos.
 */
public class GerenciadorProdutos {

    /**
     * Produtos cadastrados.
     */
    private ArrayList<Produto> produtos;

    /**
     * Cria um gerenciador sem produtos.
     */
    public GerenciadorProdutos() {
        produtos = new ArrayList<>();
    }

    /**
     * Retorna os produtos cadastrados.
     *
     * @return a lista de produtos
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Cadastra um produto.
     *
     * @param produto produto a ser cadastrado
     */
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    /**
     * Clona o protótipo e cadastra a cópia.
     *
     * @param prototipo produto a ser clonado
     * @return o novo produto cadastrado
     */
    public Produto criarProduto(ProdutoPrototype prototipo) {
        Produto novoProduto = (Produto) prototipo.clone();
        produtos.add(novoProduto);
        return novoProduto;
    }
}