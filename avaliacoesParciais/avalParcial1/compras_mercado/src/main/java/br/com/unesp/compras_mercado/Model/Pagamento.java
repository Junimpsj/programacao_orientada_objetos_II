package br.com.unesp.compras_mercado.Model;

public abstract class Pagamento {
    
    // método abstrato que deve retornar uma String com o texto do método de pagamento escolhido
    protected abstract String metodoPagamento();

}