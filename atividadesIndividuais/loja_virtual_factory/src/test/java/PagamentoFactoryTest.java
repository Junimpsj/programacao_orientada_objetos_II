import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.loja_virtual.Interface.Pagamento;
import br.com.unesp.loja_virtual.Models.PagamentoBoleto;
import br.com.unesp.loja_virtual.Models.PagamentoCartaoCredito;
import br.com.unesp.loja_virtual.Models.PagamentoCartaoDebito;
import br.com.unesp.loja_virtual.Models.PagamentoFactory;
import br.com.unesp.loja_virtual.Models.PagamentoPix;

/**
 * Testes da {@link PagamentoFactory}: criação de cada tipo de pagamento e tipo inválido.
 */
public class PagamentoFactoryTest {

    /**
     * Factory usada em todos os testes.
     */
    private final PagamentoFactory factory = new PagamentoFactory();

    /**
     * Verifica que o tipo PIX cria um {@link PagamentoPix}.
     */
    @Test
    void deveCriarPagamentoPix() {

        // given / when
        Pagamento pagamento = factory.criar(PagamentoFactory.PIX);

        // then
        assertInstanceOf(PagamentoPix.class, pagamento);

    }

    /**
     * Verifica que o tipo CREDITO cria um {@link PagamentoCartaoCredito}.
     */
    @Test
    void deveCriarPagamentoCartaoCredito() {

        // given / when
        Pagamento pagamento = factory.criar(PagamentoFactory.CREDITO);

        // then
        assertInstanceOf(PagamentoCartaoCredito.class, pagamento);

    }

    /**
     * Verifica que o tipo DEBITO cria um {@link PagamentoCartaoDebito}.
     */
    @Test
    void deveCriarPagamentoCartaoDebito() {

        // given / when
        Pagamento pagamento = factory.criar(PagamentoFactory.DEBITO);

        // then
        assertInstanceOf(PagamentoCartaoDebito.class, pagamento);

    }

    /**
     * Verifica que o tipo BOLETO cria um {@link PagamentoBoleto}.
     */
    @Test
    void deveCriarPagamentoBoleto() {

        // given / when
        Pagamento pagamento = factory.criar(PagamentoFactory.BOLETO);

        // then
        assertInstanceOf(PagamentoBoleto.class, pagamento);

    }

    /**
     * Verifica que um tipo desconhecido lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaTipoNaoSuportado() {

        // given / when / then
        assertThrows(IllegalArgumentException.class, () -> {
            factory.criar("CHEQUE");
        });

    }

    /**
     * Verifica a mensagem impressa ao pagar com PIX.
     */
    @Test
    void deveImprimirMensagemAoPagarComPix() {

        // given
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        // when
        try {
            factory.criar(PagamentoFactory.PIX).pagar(150.0);
        } finally {
            System.setOut(original);
        }

        // then
        assertEquals("Pagamento de R$ 150.0 realizado via PIX.", saida.toString().trim());

    }
}
