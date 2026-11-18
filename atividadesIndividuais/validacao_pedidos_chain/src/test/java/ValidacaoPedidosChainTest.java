import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.validacao_pedidos_chain.Aplication.App;
import br.com.unesp.validacao_pedidos_chain.Chain.LiberacaoPedido;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoEndereco;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoEstoque;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoPagamento;
import br.com.unesp.validacao_pedidos_chain.Chain.ValidacaoPedido;
import br.com.unesp.validacao_pedidos_chain.Models.Produto;

/**
 * Testes do padrão Chain of Responsibility: o pedido avança só se passar em cada
 * etapa, e a primeira etapa que encontra problema interrompe a cadeia.
 */
public class ValidacaoPedidosChainTest {

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
     * Monta a cadeia completa: estoque, pagamento, endereço e liberação.
     *
     * @return o primeiro elo da cadeia
     */
    private ValidacaoPedido montarCadeia() {
        ValidacaoPedido estoque = new ValidacaoEstoque();
        ValidacaoPedido pagamento = new ValidacaoPagamento();
        ValidacaoPedido endereco = new ValidacaoEndereco();

        estoque.setProximo(pagamento);
        pagamento.setProximo(endereco);
        endereco.setProximo(new LiberacaoPedido());

        return estoque;
    }

    /**
     * Verifica que um pedido sem problemas passa por todas as etapas e é liberado.
     */
    @Test
    void deveLiberarPedidoAprovadoEmTodasAsEtapas() {

        // when
        String saida = capturarSaida(() -> montarCadeia().validar(new Produto("Notebook", true, true, true)));

        // then
        assertEquals("""
                [ESTOQUE] Produto disponível.
                [PAGAMENTO] Pagamento aprovado.
                [ENDEREÇO] Endereço informado.
                [LIBERAÇÃO] Pedido de Notebook liberado para processamento.""", saida);

    }

    /**
     * Verifica que a falha no estoque interrompe a cadeia logo no primeiro elo.
     */
    @Test
    void deveInterromperNoEstoque() {

        // when
        String saida = capturarSaida(() -> montarCadeia().validar(new Produto("Monitor", false, true, true)));

        // then
        assertEquals("[ESTOQUE] Pedido interrompido: produto fora de estoque.", saida);

    }

    /**
     * Verifica que a falha no pagamento interrompe a cadeia depois do estoque.
     */
    @Test
    void deveInterromperNoPagamento() {

        // when
        String saida = capturarSaida(() -> montarCadeia().validar(new Produto("Teclado", true, false, true)));

        // then
        assertEquals("""
                [ESTOQUE] Produto disponível.
                [PAGAMENTO] Pedido interrompido: pagamento não aprovado.""", saida);

    }

    /**
     * Verifica que a falha no endereço interrompe a cadeia antes da liberação.
     */
    @Test
    void deveInterromperNoEndereco() {

        // when
        String saida = capturarSaida(() -> montarCadeia().validar(new Produto("Mouse", true, true, false)));

        // then
        assertEquals("""
                [ESTOQUE] Produto disponível.
                [PAGAMENTO] Pagamento aprovado.
                [ENDEREÇO] Pedido interrompido: endereço de entrega não informado.""", saida);

    }

    /**
     * Verifica que só a primeira falha é informada quando há mais de um problema.
     */
    @Test
    void deveInformarApenasAPrimeiraFalha() {

        // when
        String saida = capturarSaida(() -> montarCadeia().validar(new Produto("Cadeira", false, false, false)));

        // then
        assertEquals("[ESTOQUE] Pedido interrompido: produto fora de estoque.", saida);

    }

    /**
     * Verifica a validação do nome do produto.
     */
    @Test
    void deveRejeitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Produto(" ", true, true, true));
        assertThrows(IllegalArgumentException.class, () -> new Produto("Notebook", true, true, true).setNome(null));
    }

    /**
     * Verifica a saída completa de {@link App#main(String[])}.
     */
    @Test
    void deveExecutarDemonstracaoCompleta() {

        // when
        String saida = capturarSaida(() -> App.main(new String[0]));

        // then
        assertEquals("""
                ========================================
                VALIDAÇÃO DE PEDIDOS
                ========================================

                ----------------------------------------
                1. PEDIDO APROVADO
                ----------------------------------------

                Produto: Notebook

                [ESTOQUE] Produto disponível.
                [PAGAMENTO] Pagamento aprovado.
                [ENDEREÇO] Endereço informado.
                [LIBERAÇÃO] Pedido de Notebook liberado para processamento.

                ----------------------------------------
                2. PRODUTO FORA DE ESTOQUE
                ----------------------------------------

                Produto: Monitor

                [ESTOQUE] Pedido interrompido: produto fora de estoque.

                ----------------------------------------
                3. PAGAMENTO RECUSADO
                ----------------------------------------

                Produto: Teclado

                [ESTOQUE] Produto disponível.
                [PAGAMENTO] Pedido interrompido: pagamento não aprovado.

                ----------------------------------------
                4. ENDEREÇO NÃO INFORMADO
                ----------------------------------------

                Produto: Mouse

                [ESTOQUE] Produto disponível.
                [PAGAMENTO] Pagamento aprovado.
                [ENDEREÇO] Pedido interrompido: endereço de entrega não informado.

                ========================================""", saida);

    }
}
