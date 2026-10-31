package br.com.unesp.loja_virtual.Aplication;

import br.com.unesp.loja_virtual.Models.PagamentoFactory;
import br.com.unesp.loja_virtual.Interface.Pagamento;

/**
 * Ponto de entrada que demonstra o padrão Factory realizando pagamentos de vários tipos.
 */
public class App {

    /**
     * Realiza um pagamento de cada tipo pedindo a instância à {@link PagamentoFactory}.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        PagamentoFactory factory = new PagamentoFactory();

        // Aqui só pede o tipo e ai quem decide qual classe instanciar é a Factory
        Pagamento pagamento = factory.criar(PagamentoFactory.PIX);
        pagamento.pagar(150.00);

        factory.criar(PagamentoFactory.CREDITO).pagar(1299.90);
        factory.criar(PagamentoFactory.DEBITO).pagar(89.50);
        factory.criar(PagamentoFactory.BOLETO).pagar(420.00);

        System.out.println("Compra realizada!");
    }
}