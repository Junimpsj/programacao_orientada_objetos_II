import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.pagamento_adapter.Adapter.SuperPayAdapter;
import br.com.unesp.pagamento_adapter.Aplication.App;
import br.com.unesp.pagamento_adapter.Interface.ProcessadorPagamento;
import br.com.unesp.pagamento_adapter.Models.ProcessadorLegado;
import br.com.unesp.pagamento_adapter.Models.SuperPayAPI;

/**
 * Testes do padrão Adapter: processador legado e SuperPay acessados pelo mesmo contrato.
 */
public class PagamentoAdapterTest {

    /**
     * Captura o que for impresso no console durante a ação.
     *
     * @param acao trecho de código a ser executado
     * @return a saída do console, sem espaços nas pontas
     */
    // captura o System.out de qualquer trecho, mesma ideia do CheckoutTest
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
     * Verifica que o adapter pode ser usado como {@link ProcessadorPagamento}.
     */
    @Test
    void adapterDeveSerUmProcessadorPagamento() {

        // given / when
        ProcessadorPagamento processador = new SuperPayAdapter(new SuperPayAPI());

        // then
        assertInstanceOf(ProcessadorPagamento.class, processador);

    }

    /**
     * Verifica que o adapter traduz o pagamento para a chamada da SuperPay.
     */
    @Test
    void adapterDeveTraduzirPagamentoParaSuperPay() {

        // given
        ProcessadorPagamento processador = new SuperPayAdapter(new SuperPayAPI());

        // when
        String saida = capturarSaida(() -> processador.realizarPagamento(450.00));

        // then
        assertEquals("[Adapter] Traduzindo chamada para a nova biblioteca (SuperPay)... [SuperPay API] Efetivando transação no valor de R$ 450.0", saida);

    }

    /**
     * Verifica o cancelamento feito pelo processador legado.
     */
    @Test
    void legadoDeveCancelarPagamento() {

        // given
        ProcessadorPagamento processador = new ProcessadorLegado();

        // when
        String saida = capturarSaida(() -> processador.cancelarPagamento("LEG-998877"));

        // then
        assertEquals("[Legado] Cancelando transação LEG-998877 no sistema legado.", saida);

    }

    /**
     * Verifica a saída completa de {@link App#main(String[])}.
     */
    @Test
    void deveExecutarSimulacaoCompleta() {

        // when
        String saida = capturarSaida(() -> App.main(new String[0]));

        // then
        assertEquals("""
                === 1. Testando com o Processador Legado ===
                [Legado] Processando pagamento de R$ 150.5 via sistema legado.
                [Legado] Cancelando transação LEG-998877 no sistema legado.
                === 2. Testando com a Nova API via Adapter ===
                [Adapter] Traduzindo chamada para a nova biblioteca (SuperPay)... [SuperPay API] Efetivando transação no valor de R$ 450.0
                [Adapter] Traduzindo chamada de cancelamento para a nova biblioteca (SuperPay)... [SuperPay API] Estornando transação com código SUPER-554433""", saida);

    }
}
