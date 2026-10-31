package br.com.unesp.pagamento_adapter.Models;

/**
 * Adaptee do Adapter: biblioteca fechada de terceiros, com interface incompatível
 * com o sistema e que não pode ser alterada.
 */
// adaptee, biblioteca fechada de terceiros então não pode ser alterada
public class SuperPayAPI {

    /**
     * Efetiva uma transação.
     *
     * @param montanteEmReais valor da transação em reais
     */
    public void efetuarTransacao(double montanteEmReais) {
        System.out.println("[SuperPay API] Efetivando transação no valor de R$ " + montanteEmReais);
    }

    /**
     * Estorna uma transação.
     *
     * @param codigoTransacao código da transação a ser estornada
     */
    public void estornarTransacao(String codigoTransacao) {
        System.out.println("[SuperPay API] Estornando transação com código " + codigoTransacao);
    }
}
