package br.com.unesp.avaliacao;

public class Venda {

    private Participante participante;
    private double valorVenda;

    public Venda(Participante participante, double valorVenda) {

        this.participante = participante;

        if(valorVenda <= 0) {
            throw new IllegalStateException("Valor de venda inaceitável!");
        }
        
        this.valorVenda = valorVenda;
    }

    public Participante getParticipante() {
        return this.participante;

    }

    public double getValorVenda() {
        return this.valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}