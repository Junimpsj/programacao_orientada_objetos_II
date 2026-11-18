import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import br.com.unesp.agencia_viagens_facade.Aplication.App;
import br.com.unesp.agencia_viagens_facade.Facade.AgenciaViagensFacade;

/**
 * Testes do padrão Facade: reserva, consulta e cancelamento de um pacote de viagem.
 */
public class AgenciaViagensFacadeTest {

    private static final LocalDate IDA = LocalDate.of(2026, 12, 10);
    private static final LocalDate RETORNO = LocalDate.of(2026, 12, 15);

    /**
     * Captura o que for impresso no console durante a ação.
     *
     * @param acao trecho de código a ser executado
     * @return a saída do console, sem espaços nas pontas
     */
    private String capturarSaida(Runnable acao) {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            acao.run();
        } finally {
            System.setOut(original);
        }

        return saida.toString().trim().replace("\r\n", "\n");
    }

    /**
     * Cria um {@link Random} que devolve os valores informados, em ordem.
     *
     * @param valores valores que o nextInt vai devolver
     * @return o Random com sorteio previsível
     */
    // Random "viciado" pra saber de antemão o código gerado (1000 + valor)
    private Random randomFixo(int... valores) {
        Iterator<Integer> iterator = Arrays.stream(valores).iterator();

        return new Random() {
            @Override
            public int nextInt(int bound) {
                return iterator.next();
            }
        };
    }

    /**
     * Verifica que o código gerado tem 4 dígitos.
     */
    @Test
    void deveGerarCodigoComQuatroDigitos() {

        // given
        AgenciaViagensFacade agencia = new AgenciaViagensFacade();

        // when
        int codigo = agencia.contratarPacote("Salvador", IDA, RETORNO, 1);

        // then
        assertTrue(codigo >= 1000 && codigo <= 9999);

    }

    /**
     * Verifica que um código já em uso é sorteado de novo.
     */
    @Test
    void naoDeveRepetirCodigoDeReserva() {

        // given
        AgenciaViagensFacade agencia = new AgenciaViagensFacade(randomFixo(1, 1, 2));

        // when
        int primeiro = agencia.contratarPacote("Salvador", IDA, RETORNO, 1);
        int segundo = agencia.contratarPacote("Recife", IDA, RETORNO, 1);

        // then
        assertEquals(1001, primeiro);
        assertEquals(1002, segundo);

    }

    /**
     * Verifica a saída da reserva, igual ao exemplo do enunciado.
     */
    @Test
    void deveRealizarReservaComOsTresServicos() {

        // given
        AgenciaViagensFacade agencia = new AgenciaViagensFacade(randomFixo(1));

        // when
        String saida = capturarSaida(() -> agencia.contratarPacote("Rio de Janeiro", IDA, RETORNO, 2));

        // then
        assertEquals("""
                Destino: Rio de Janeiro
                Data de ida: 10/12/2026
                Data de retorno: 15/12/2026
                Quantidade de passageiros: 2
                [PASSAGEM AÉREA]
                Reserva de passagem realizada com sucesso!
                Origem: São Paulo
                Destino: Rio de Janeiro
                Passageiros: 2

                [HOSPEDAGEM]
                Reserva de hospedagem realizada com sucesso!
                Hotel: Hotel Copacabana
                Período: 10/12/2026 a 15/12/2026
                Hóspedes: 2

                [TRANSPORTE]
                Reserva de transporte realizada com sucesso!
                Serviço: Traslado aeroporto - hotel
                Passageiros: 2

                ----------------------------------------
                PACOTE DE VIAGEM CONTRATADO COM SUCESSO!
                Código da reserva: 1001""", saida);

    }

    /**
     * Verifica que a consulta mostra os três serviços contratados.
     */
    @Test
    void deveConsultarServicosContratados() {

        // given
        AgenciaViagensFacade agencia = new AgenciaViagensFacade(randomFixo(1));
        agencia.contratarPacote("Recife", IDA, RETORNO, 3);

        // when
        String saida = capturarSaida(() -> agencia.consultarPacote(1001));

        // then
        assertEquals("""
                Código da reserva: 1001
                [PASSAGEM AÉREA]
                Origem: São Paulo
                Destino: Recife
                Passageiros: 3

                [HOSPEDAGEM]
                Hotel: Hotel Central
                Período: 10/12/2026 a 15/12/2026
                Hóspedes: 3

                [TRANSPORTE]
                Serviço: Traslado aeroporto - hotel
                Passageiros: 3""", saida);

    }

    /**
     * Verifica que o cancelamento aciona os serviços na ordem inversa e libera a reserva.
     */
    @Test
    void deveCancelarNaOrdemInversa() {

        // given
        AgenciaViagensFacade agencia = new AgenciaViagensFacade(randomFixo(1));
        agencia.contratarPacote("Salvador", IDA, RETORNO, 1);

        // when
        String saida = capturarSaida(() -> agencia.cancelarPacote(1001));

        // then
        assertEquals("""
                Código da reserva: 1001
                [TRANSPORTE]
                Reserva de transporte cancelada.

                [HOSPEDAGEM]
                Reserva de hospedagem cancelada.

                [PASSAGEM AÉREA]
                Reserva de passagem cancelada.

                ----------------------------------------
                PACOTE DE VIAGEM CANCELADO COM SUCESSO!""", saida);
        assertThrows(IllegalArgumentException.class, () -> agencia.consultarPacote(1001));

    }

    /**
     * Verifica as validações de entrada.
     */
    @Test
    void deveRejeitarEntradasInvalidas() {

        AgenciaViagensFacade agencia = new AgenciaViagensFacade();

        assertThrows(IllegalArgumentException.class, () -> agencia.contratarPacote(" ", IDA, RETORNO, 1));
        assertThrows(IllegalArgumentException.class, () -> agencia.contratarPacote("Salvador", null, RETORNO, 1));
        assertThrows(IllegalArgumentException.class, () -> agencia.contratarPacote("Salvador", RETORNO, IDA, 1));
        assertThrows(IllegalArgumentException.class, () -> agencia.contratarPacote("Salvador", IDA, RETORNO, 0));
        assertThrows(IllegalArgumentException.class, () -> agencia.consultarPacote(1234));
        assertThrows(IllegalArgumentException.class, () -> agencia.cancelarPacote(1234));

    }

    /**
     * Verifica a saída completa de {@link App#main(String[])}.
     */
    @Test
    void deveExecutarDemonstracaoCompleta() {

        // when
        String saida = capturarSaida(() -> App.main(new String[0]));

        // then
        // o código é aleatório, então troca por #### antes de comparar
        assertEquals("""
                ========================================
                SISTEMA DE RESERVA DE VIAGENS
                ========================================

                1. REALIZAR RESERVA DE VIAGEM
                ----------------------------------------
                Destino: Rio de Janeiro
                Data de ida: 10/12/2026
                Data de retorno: 15/12/2026
                Quantidade de passageiros: 2
                [PASSAGEM AÉREA]
                Reserva de passagem realizada com sucesso!
                Origem: São Paulo
                Destino: Rio de Janeiro
                Passageiros: 2

                [HOSPEDAGEM]
                Reserva de hospedagem realizada com sucesso!
                Hotel: Hotel Copacabana
                Período: 10/12/2026 a 15/12/2026
                Hóspedes: 2

                [TRANSPORTE]
                Reserva de transporte realizada com sucesso!
                Serviço: Traslado aeroporto - hotel
                Passageiros: 2

                ----------------------------------------
                PACOTE DE VIAGEM CONTRATADO COM SUCESSO!
                Código da reserva: ####

                2. CONSULTAR SERVIÇOS CONTRATADOS
                ----------------------------------------
                Código da reserva: ####
                [PASSAGEM AÉREA]
                Origem: São Paulo
                Destino: Rio de Janeiro
                Passageiros: 2

                [HOSPEDAGEM]
                Hotel: Hotel Copacabana
                Período: 10/12/2026 a 15/12/2026
                Hóspedes: 2

                [TRANSPORTE]
                Serviço: Traslado aeroporto - hotel
                Passageiros: 2

                3. CANCELAR RESERVA DE VIAGEM
                ----------------------------------------
                Código da reserva: ####
                [TRANSPORTE]
                Reserva de transporte cancelada.

                [HOSPEDAGEM]
                Reserva de hospedagem cancelada.

                [PASSAGEM AÉREA]
                Reserva de passagem cancelada.

                ----------------------------------------
                PACOTE DE VIAGEM CANCELADO COM SUCESSO!""", saida.replaceAll("Código da reserva: \\d{4}", "Código da reserva: ####"));

    }
}
