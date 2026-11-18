package br.com.unesp.agencia_viagens_facade.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe do subsistema do Facade: realiza, consulta e cancela reservas de passagens aéreas.
 * Não conhece a Facade.
 */
public class ServicoPassagemAerea {

    // a agência fica em São Paulo, então todo voo sai daqui
    private static final String ORIGEM = "São Paulo";

    // detalhes de cada passagem reservada, pelo código da reserva
    private final Map<Integer, String> reservas = new HashMap<>();

    /**
     * Reserva a passagem aérea saindo de São Paulo.
     *
     * @param codigo código da reserva de viagem
     * @param destino destino da viagem
     * @param passageiros quantidade de passageiros
     */
    public void reservar(int codigo, String destino, int passageiros) {
        String detalhes = "Origem: " + ORIGEM + "\n"
                + "Destino: " + destino + "\n"
                + "Passageiros: " + passageiros;
        reservas.put(codigo, detalhes);

        System.out.println("[PASSAGEM AÉREA]");
        System.out.println("Reserva de passagem realizada com sucesso!");
        System.out.println(detalhes);
        System.out.println();
    }

    /**
     * Imprime os dados da passagem reservada.
     *
     * @param codigo código da reserva de viagem
     */
    public void consultar(int codigo) {
        System.out.println("[PASSAGEM AÉREA]");
        System.out.println(reservas.get(codigo));
        System.out.println();
    }

    /**
     * Cancela a passagem reservada.
     *
     * @param codigo código da reserva de viagem
     */
    public void cancelar(int codigo) {
        reservas.remove(codigo);

        System.out.println("[PASSAGEM AÉREA]");
        System.out.println("Reserva de passagem cancelada.");
        System.out.println();
    }
}
