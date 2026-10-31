package br.com.unesp.checkout_ecommerce.Models;

import br.com.unesp.checkout_ecommerce.Factory.PaisCheckoutFactory;
import br.com.unesp.checkout_ecommerce.Interface.CalculadoraImposto;
import br.com.unesp.checkout_ecommerce.Interface.ProcessadorFrete;

/**
 * Fecha pedidos de e-commerce somando produto, imposto e frete.
 * Recebe uma {@link PaisCheckoutFactory} e usa apenas as interfaces dos componentes.
 */
// cliente do abstract factory: só conhece as interfaces, então nunca mistura imposto de um país com frete de outro
public class Checkout {

    private final CalculadoraImposto calculadoraImposto;
    private final ProcessadorFrete processadorFrete;

    /**
     * Cria um checkout com os componentes fornecidos pela fábrica do país.
     *
     * @param factory fábrica que define imposto e frete usados no cálculo
     */
    public Checkout(PaisCheckoutFactory factory) {
        this.calculadoraImposto = factory.criarCalculadoraImposto();
        this.processadorFrete = factory.criarProcessadorFrete();
    }

    /**
     * Calcula o valor total do pedido (produto + imposto + frete), arredondado em duas casas.
     *
     * @param valorProduto valor do produto, sem impostos
     * @param pesoKg peso do pedido em quilogramas
     * @return o valor total do pedido
     */
    public double calcularTotal(double valorProduto, double pesoKg) {
        return arredondar(valorProduto + calculadoraImposto.calcular(valorProduto) + processadorFrete.calcularFrete(pesoKg));
    }

    /**
     * Imprime no console o resumo do pedido com produto, imposto, frete e total.
     *
     * @param valorProduto valor do produto, sem impostos
     * @param pesoKg peso do pedido em quilogramas
     */
    public void fecharPedido(double valorProduto, double pesoKg) {
        System.out.println("=== FECHAMENTO DO PEDIDO ===");
        System.out.println("Produto: R$ " + valorProduto);
        System.out.println("Imposto: R$ " + arredondar(calculadoraImposto.calcular(valorProduto)));
        System.out.println("Frete: R$ " + arredondar(processadorFrete.calcularFrete(pesoKg)));
        System.out.println("VALOR TOTAL: R$ " + calcularTotal(valorProduto, pesoKg));
    }

    /**
     * Arredonda um valor para duas casas decimais.
     *
     * @param valor valor a arredondar
     * @return o valor arredondado
     */
    private double arredondar(double valor) {
        return Math.round(valor * 100) / 100.0;
    }
}
