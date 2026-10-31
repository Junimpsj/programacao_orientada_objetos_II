package br.com.unesp.polinomio.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.unesp.polinomio.interfaces.Calculavel;

/**
 * Polinômio formado por uma lista de {@link Termo}, sem expoentes repetidos.
 */
public class Polinomio implements Calculavel {

    /**
     * Termos do polinômio.
     */
    private List<Termo> termos;

    /**
     * Cria um polinômio com um termo inicial.
     *
     * @param termo termo inicial
     * @throws IllegalArgumentException se o termo for nulo
     */
    public Polinomio(Termo termo) {

        if (termo == null) {
            throw new IllegalArgumentException("O termo inicial não pode ser nulo.");
        }

        this.termos = new ArrayList<>();
        this.termos.add(termo);
    }

    /**
     * Retorna os termos do polinômio.
     *
     * @return a lista de termos
     */
    public List<Termo> getTermos() {
        return termos;
    }

    /**
     * Insere um termo, somando os coeficientes se já existir termo com o mesmo expoente.
     *
     * @param termo termo a inserir
     * @throws IllegalArgumentException se o termo for nulo
     */
    // se já existe termo com mesmo expoente, unifica os coeficientes (soma) usando o insere do Termo
    // senão, adiciona o termo novo na lista
    public void inserir(Termo termo) {

        if (termo == null) {
            throw new IllegalArgumentException("O termo não pode ser nulo.");
        }

        for (Termo existente : termos) {
            if (existente.getExpoente() == termo.getExpoente()) {
                BigDecimal coeficienteUnificado = existente.getCoeficiente().add(termo.getCoeficiente());
                existente.insere(new Termo(coeficienteUnificado, termo.getExpoente()));
                return;
            }
        }

        termos.add(termo);
    }

    /**
     * Funde outro polinômio a este, inserindo cada um dos seus termos.
     *
     * @param outro polinômio a ser fundido
     * @throws IllegalArgumentException se o polinômio for nulo
     */
    // ta pedindo fusao(Polinomio) na classe Termo (la no enunciado), mas o exemplo (P1 fusao P2 -> altera P1) só faz
    // sentido entre polinômios, então implementei aqui reaproveitando o inserir pra unificar os termos
    public void fusao(Polinomio outro) {

        if (outro == null) {
            throw new IllegalArgumentException("O polinômio não pode ser nulo.");
        }

        for (Termo termo : outro.termos) {
            inserir(termo);
        }
    }

    /**
     * Calcula o valor do polinômio somando o valor de cada termo.
     *
     * @param x valor de x
     * @return o resultado do polinômio para o x informado
     */
    @Override
    public BigDecimal calcula(BigDecimal x) {

        BigDecimal resultado = BigDecimal.ZERO;

        for (Termo termo : termos) {
            resultado = resultado.add(termo.calcula(x));
        }

        return resultado;
    }

    /**
     * Representa o polinômio como a soma dos seus termos (ex.: {@code 3x^2 + 5x^1}).
     *
     * @return a representação textual do polinômio
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < termos.size(); i++) {
            if (i > 0) {
                sb.append(" + ");
            }
            sb.append(termos.get(i).toStringTermo());
        }

        return sb.toString();
    }
}
