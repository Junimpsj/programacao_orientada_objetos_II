package br.com.unesp.agencia_viagens_facade.Aplication;

import java.time.LocalDate;

import br.com.unesp.agencia_viagens_facade.Facade.AgenciaViagensFacade;

/**
 * Ponto de entrada que demonstra o padrão Facade: o cliente contrata, consulta
 * e cancela um pacote de viagem usando apenas a {@link AgenciaViagensFacade}.
 */
public class App {

    private static final String LINHA = "----------------------------------------";

    /**
     * Contrata, consulta e cancela um pacote de viagem pela facade.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    // cliente não importa nenhum serviço, só a facade
    public static void main(String[] args) {
        AgenciaViagensFacade agencia = new AgenciaViagensFacade();

        System.out.println("========================================");
        System.out.println("SISTEMA DE RESERVA DE VIAGENS");
        System.out.println("========================================");
        System.out.println();

        System.out.println("1. REALIZAR RESERVA DE VIAGEM");
        System.out.println(LINHA);
        int codigo = agencia.contratarPacote("Rio de Janeiro", LocalDate.of(2026, 12, 10), LocalDate.of(2026, 12, 15), 2);
        System.out.println();

        System.out.println("2. CONSULTAR SERVIÇOS CONTRATADOS");
        System.out.println(LINHA);
        agencia.consultarPacote(codigo);

        System.out.println("3. CANCELAR RESERVA DE VIAGEM");
        System.out.println(LINHA);
        agencia.cancelarPacote(codigo);
    }
}
