package br.com.unesp.agencia_viagens_facade.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe do subsistema do Facade: realiza, consulta e cancela a contratação de transporte.
 * Não conhece a Facade.
 */
public class ServicoTransporte {

    private static final String SERVICO = "Traslado aeroporto - hotel";

    // detalhes de cada transporte contratado, pelo código da reserva
    private final Map<Integer, String> reservas = new HashMap<>();

    /**
     * Contrata o traslado aeroporto - hotel no destino.
     *
     * @param codigo código da reserva de viagem
     * @param passageiros quantidade de passageiros
     */
    public void reservar(int codigo, int passageiros) {
        String detalhes = "Serviço: " + SERVICO + "\n"
                + "Passageiros: " + passageiros;
        reservas.put(codigo, detalhes);

        System.out.println("[TRANSPORTE]");
        System.out.println("Reserva de transporte realizada com sucesso!");
        System.out.println(detalhes);
        System.out.println();
    }

    /**
     * Imprime os dados do transporte contratado.
     *
     * @param codigo código da reserva de viagem
     */
    public void consultar(int codigo) {
        System.out.println("[TRANSPORTE]");
        System.out.println(reservas.get(codigo));
        System.out.println();
    }

    /**
     * Cancela o transporte contratado.
     *
     * @param codigo código da reserva de viagem
     */
    public void cancelar(int codigo) {
        reservas.remove(codigo);

        System.out.println("[TRANSPORTE]");
        System.out.println("Reserva de transporte cancelada.");
        System.out.println();
    }
}
