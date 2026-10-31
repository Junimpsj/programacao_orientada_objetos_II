package br.com.unesp.avaliacao;

/**
 * Venda realizada por um {@link Participante}.
 */
public class Venda {

    /**
     * Participante que realizou a venda.
     */
    private Participante participante;
    /**
     * Valor da venda.
     */
    private double valorVenda;

    /**
     * Cria uma venda.
     *
     * @param participante participante que realizou a venda
     * @param valorVenda valor da venda, deve ser maior que zero
     * @throws IllegalStateException se o valor for menor ou igual a zero
     */
    public Venda(Participante participante, double valorVenda) {

        this.participante = participante;

        if(valorVenda <= 0) {
            throw new IllegalStateException("Valor de venda inaceitável!");
        }
        
        this.valorVenda = valorVenda;
    }

    /**
     * Retorna o participante da venda.
     *
     * @return o participante
     */
    public Participante getParticipante() {
        return this.participante;

    }

    /**
     * Retorna o valor da venda.
     *
     * @return o valor da venda
     */
    public double getValorVenda() {
        return this.valorVenda;
    }

    /**
     * Define o valor da venda.
     *
     * @param valorVenda novo valor da venda
     */
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}