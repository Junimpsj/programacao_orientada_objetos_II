package br.com.unesp.validacao_pedidos_chain.Aplication;

import br.com.unesp.validacao_pedidos_chain.Chain.LiberacaoPedido;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoEndereco;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoEstoque;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoPagamento;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoPedido;
import br.com.unesp.validacao_pedidos_chain.Models.Produto;

/**
 * Client do Chain of Responsibility: monta a cadeia de validação
 * (estoque, pagamento, endereço e liberação) e envia pedidos para o primeiro elo.
 */
public class App {

    private static final String LINHA = "----------------------------------------";
    private static final String LINHA_DUPLA = "========================================";

    /**
     * Valida um pedido aprovado e um pedido com falha em cada etapa.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {

        // etapas da cadeia
        ValidacaoPedido estoque = new ValidacaoEstoque();
        ValidacaoPedido pagamento = new ValidacaoPagamento();
        ValidacaoPedido endereco = new ValidacaoEndereco();
        ValidacaoPedido liberacao = new LiberacaoPedido();

        // montagem da cadeia, para incluir uma etapa nova basta criar a classe e encaixar aqui
        estoque.setProximo(pagamento);
        pagamento.setProximo(endereco);
        endereco.setProximo(liberacao);

        System.out.println(LINHA_DUPLA);
        System.out.println("VALIDAÇÃO DE PEDIDOS");
        System.out.println(LINHA_DUPLA);
        System.out.println();

        // o cliente sempre envia para o primeiro elo, sem saber quem vai interromper
        processar("1. PEDIDO APROVADO", new Produto("Notebook", true, true, true), estoque);
        processar("2. PRODUTO FORA DE ESTOQUE", new Produto("Monitor", false, true, true), estoque);
        processar("3. PAGAMENTO RECUSADO", new Produto("Teclado", true, false, true), estoque);
        processar("4. ENDEREÇO NÃO INFORMADO", new Produto("Mouse", true, true, false), estoque);

        System.out.println(LINHA_DUPLA);
    }

    /**
     * Imprime o cabeçalho do pedido e o envia para a cadeia.
     *
     * @param titulo título do pedido
     * @param produto produto do pedido
     * @param cadeia primeira etapa da cadeia de validação
     */
    private static void processar(String titulo, Produto produto, ValidacaoPedido cadeia) {
        System.out.println(LINHA);
        System.out.println(titulo);
        System.out.println(LINHA);
        System.out.println();

        System.out.println("Produto: " + produto.getNome());
        System.out.println();
        cadeia.validar(produto);
        System.out.println();
    }
}
