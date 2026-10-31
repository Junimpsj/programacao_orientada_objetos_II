package br.unesp.loja_virtual.application;

import br.unesp.loja_virtual.model.Configuracao;
import br.unesp.loja_virtual.model.Produto;
import br.unesp.loja_virtual.service.GerenciadorProdutos;

/**
 * Ponto de entrada que demonstra os padrões Singleton ({@link Configuracao})
 * e Prototype ({@link Produto}).
 */
public class App {
    /**
     * Testa a instância única de configuração e a clonagem de produtos.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        
        GerenciadorProdutos gerenciador = new GerenciadorProdutos();

        //Testando Padrão Singleton
        Configuracao configuracao1 = Configuracao.getInstance();
        configuracao1.setNomeLoja("Junim Calltech");
        configuracao1.setDescontoPadrao(10);
        System.out.println("Configuração 1: " + configuracao1.getNomeLoja() + " - desconto " + configuracao1.getDescontoPadrao() + "%");

        Configuracao configuracao2 = Configuracao.getInstance();
        System.out.println("Configuração 2: " + configuracao2.getNomeLoja() + " - desconto " + configuracao2.getDescontoPadrao() + "%");

        System.out.println("Teste EXTRA: É a mesma configurção? -> " + (configuracao1 == configuracao2));

        configuracao2.setDescontoPadrao(15);
        System.out.println("Desconto da configuração 1 depois de alterar pela 2: " + configuracao1.getDescontoPadrao() + "%");

        System.out.println();

        //Testando Padrão Prototype
        Produto produtoOriginal = new Produto("Notebook Dell Comum", 3100, "Junim Calltech", "Informática");
        gerenciador.adicionarProduto(produtoOriginal);

        Produto novoProduto = gerenciador.criarProduto(produtoOriginal);
        novoProduto.setNome("Notebook Dell Gamer");
        novoProduto.setPreco(5200);

        System.out.println("Produtos Cadastrados:");
        for (Produto produto : gerenciador.getProdutos()) {

            System.out.println("Nome: " + produto.getNome() + " | Preço: " + produto.getPreco() + " | Vendedor: " + produto.getVendedor() + " | Categoria: " + produto.getCategoria());

        }

        System.out.println("Teste extra final: São o mesmo produto? -> " + (produtoOriginal == novoProduto));
        
    }
}