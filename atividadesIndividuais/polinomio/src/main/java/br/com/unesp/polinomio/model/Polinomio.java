package br.com.unesp.polinomio.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.unesp.polinomio.interfaces.Calculavel;

public class Polinomio implements Calculavel {

    private List<Termo> termos;

    public Polinomio(Termo termo) {

        if (termo == null) {
            throw new IllegalArgumentException("O termo inicial não pode ser nulo.");
        }

        this.termos = new ArrayList<>();
        this.termos.add(termo);
    }

    public List<Termo> getTermos() {
        return termos;
    }

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

    @Override
    public BigDecimal calcula(BigDecimal x) {

        BigDecimal resultado = BigDecimal.ZERO;

        for (Termo termo : termos) {
            resultado = resultado.add(termo.calcula(x));
        }

        return resultado;
    }

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
