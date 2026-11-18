package br.com.unesp.agencia_viagens_facade.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe do subsistema do Facade: realiza, consulta e cancela reservas de hospedagem.
 * Não conhece a Facade.
 */
public class ServicoHospedagem {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // hotel parceiro de cada destino, os demais ficam no hotel padrão
    private static final Map<String, String> HOTEIS = Map.of(
            "Rio de Janeiro", "Hotel Copacabana",
            "Salvador", "Hotel Pelourinho");
    private static final String HOTEL_PADRAO = "Hotel Central";

    // detalhes de cada hospedagem reservada, pelo código da reserva
    private final Map<Integer, String> reservas = new HashMap<>();

    /**
     * Reserva a hospedagem no destino para o período da viagem.
     *
     * @param codigo código da reserva de viagem
     * @param destino cidade da hospedagem
     * @param dataEntrada data de entrada no hotel
     * @param dataSaida data de saída do hotel
     * @param hospedes quantidade de hóspedes
     */
    public void reservar(int codigo, String destino, LocalDate dataEntrada, LocalDate dataSaida, int hospedes) {
        String detalhes = "Hotel: " + HOTEIS.getOrDefault(destino, HOTEL_PADRAO) + "\n"
                + "Período: " + dataEntrada.format(FORMATO) + " a " + dataSaida.format(FORMATO) + "\n"
                + "Hóspedes: " + hospedes;
        reservas.put(codigo, detalhes);

        System.out.println("[HOSPEDAGEM]");
        System.out.println("Reserva de hospedagem realizada com sucesso!");
        System.out.println(detalhes);
        System.out.println();
    }

    /**
     * Imprime os dados da hospedagem reservada.
     *
     * @param codigo código da reserva de viagem
     */
    public void consultar(int codigo) {
        System.out.println("[HOSPEDAGEM]");
        System.out.println(reservas.get(codigo));
        System.out.println();
    }

    /**
     * Cancela a hospedagem reservada.
     *
     * @param codigo código da reserva de viagem
     */
    public void cancelar(int codigo) {
        reservas.remove(codigo);

        System.out.println("[HOSPEDAGEM]");
        System.out.println("Reserva de hospedagem cancelada.");
        System.out.println();
    }
}
