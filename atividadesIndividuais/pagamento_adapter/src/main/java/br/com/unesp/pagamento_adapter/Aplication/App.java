package br.com.unesp.pagamento_adapter.Aplication;

import br.com.unesp.pagamento_adapter.Adapter.SuperPayAdapter;
import br.com.unesp.pagamento_adapter.Interface.ProcessadorPagamento;
import br.com.unesp.pagamento_adapter.Models.ProcessadorLegado;
import br.com.unesp.pagamento_adapter.Models.SuperPayAPI;

/**
 * Ponto de entrada que demonstra o padrão Adapter usando o processador legado
 * e a nova API de pagamento através do mesmo contrato.
 */
public class App {

    /**
     * Testa o processador legado e a SuperPay (via adapter).
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== 1. Testando com o Processador Legado ===");
        testar(new ProcessadorLegado(), 150.50, "LEG-998877");

        System.out.println("=== 2. Testando com a Nova API via Adapter ===");
        testar(new SuperPayAdapter(new SuperPayAPI()), 450.00, "SUPER-554433");
    }

    /**
     * Executa pagamento e cancelamento usando apenas o contrato {@link ProcessadorPagamento}.
     *
     * @param processador processador a ser testado
     * @param valor valor do pagamento
     * @param idTransacao identificador da transação a cancelar
     */
    // cliente só conhece o contrato, não sabe se é o legado ou a SuperPay
    private static void testar(ProcessadorPagamento processador, double valor, String idTransacao) {
        processador.realizarPagamento(valor);
        processador.cancelarPagamento(idTransacao);
    }
}
