package br.com.unesp.pagamento_adapter.Adapter;

import br.com.unesp.pagamento_adapter.Interface.ProcessadorPagamento;
import br.com.unesp.pagamento_adapter.Models.SuperPayAPI;

/**
 * Adapter que permite usar a {@link SuperPayAPI} onde o sistema espera um
 * {@link ProcessadorPagamento}, traduzindo as chamadas por composição.
 */
// adapter, implementa o contrato do sistema e traduz as chamadas para a SuperPayAPI por composição
public class SuperPayAdapter implements ProcessadorPagamento {

    private final SuperPayAPI superPay;

    /**
     * Cria o adapter.
     *
     * @param superPay API de terceiros que será adaptada
     */
    public SuperPayAdapter(SuperPayAPI superPay) {
        this.superPay = superPay;
    }

    /**
     * Traduz o pagamento para {@link SuperPayAPI#efetuarTransacao(double)}.
     *
     * @param valor valor do pagamento em reais
     */
    // print sem quebra de linha, a saída exigida na atividade junta o log do adapter com o da API
    @Override
    public void realizarPagamento(double valor) {
        System.out.print("[Adapter] Traduzindo chamada para a nova biblioteca (SuperPay)... ");
        superPay.efetuarTransacao(valor);
    }

    /**
     * Traduz o cancelamento para {@link SuperPayAPI#estornarTransacao(String)}.
     *
     * @param idTransacao identificador da transação a ser cancelada
     */
    @Override
    public void cancelarPagamento(String idTransacao) {
        System.out.print("[Adapter] Traduzindo chamada de cancelamento para a nova biblioteca (SuperPay)... ");
        superPay.estornarTransacao(idTransacao);
    }
}
