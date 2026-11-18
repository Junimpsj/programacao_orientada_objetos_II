package br.com.unesp.loja_virtual_decorator.Aplication;

import java.math.BigDecimal;
import java.util.Locale;

import br.com.unesp.loja_virtual_decorator.Decorator.EmbalagemPresente;
import br.com.unesp.loja_virtual_decorator.Decorator.GarantiaEstendida;
import br.com.unesp.loja_virtual_decorator.Decorator.SeguroContraDanos;
import br.com.unesp.loja_virtual_decorator.Interface.Produto;
import br.com.unesp.loja_virtual_decorator.Models.ProdutoBasico;

/**
 * Ponto de entrada que demonstra o padrão Decorator combinando serviços adicionais
 * sobre produtos básicos.
 */
public class App {

    /**
     * Monta três combinações de produto e serviços e imprime descrição e preço total.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Produto notebook = new EmbalagemPresente(
                new ProdutoBasico("Notebook", new BigDecimal("3500.00")));

        Produto smartphone = new SeguroContraDanos(
                new GarantiaEstendida(
                        new ProdutoBasico("Smartphone", new BigDecimal("2000.00"))));

        Produto fone = new SeguroContraDanos(
                new GarantiaEstendida(
                        new EmbalagemPresente(
                                new ProdutoBasico("Fone de ouvido", new BigDecimal("199.90")))));

        System.out.println("=== COMBINAÇÃO 1 ===");
        imprimir(notebook);

        System.out.println("=== COMBINAÇÃO 2 ===");
        imprimir(smartphone);

        System.out.println("=== COMBINAÇÃO 3 ===");
        imprimir(fone);
    }

    /**
     * Imprime no console a descrição final e o preço total do produto.
     *
     * @param produto produto (básico ou decorado) a ser impresso
     */
    // cliente só conhece Produto, não sabe quantos decoradores tem por baixo
    private static void imprimir(Produto produto) {
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Preço total: R$ " + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", produto.getPreco()));
    }
}
