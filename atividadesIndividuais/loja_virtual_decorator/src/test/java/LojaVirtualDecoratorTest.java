import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import br.com.unesp.loja_virtual_decorator.Aplication.App;
import br.com.unesp.loja_virtual_decorator.Decorator.EmbalagemPresente;
import br.com.unesp.loja_virtual_decorator.Decorator.GarantiaEstendida;
import br.com.unesp.loja_virtual_decorator.Decorator.SeguroContraDanos;
import br.com.unesp.loja_virtual_decorator.Interface.Produto;
import br.com.unesp.loja_virtual_decorator.Models.ProdutoBasico;

/**
 * Testes do padrão Decorator: serviços adicionais combinados sobre um produto básico.
 */
public class LojaVirtualDecoratorTest {

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
     * Verifica que o produto básico mantém nome e preço originais.
     */
    @Test
    void produtoBasicoDeveManterPrecoOriginal() {

        // given
        Produto produto = new ProdutoBasico("Livro", new BigDecimal("50.00"));

        // then
        assertEquals("Livro", produto.getDescricao());
        assertEquals(0, new BigDecimal("50.00").compareTo(produto.getPreco()));

    }

    /**
     * Verifica a soma de todos os serviços sem erro de ponto flutuante nos centavos.
     */
    @Test
    void deveSomarTodosOsServicos() {

        // given
        Produto produto = new SeguroContraDanos(
                new GarantiaEstendida(
                        new EmbalagemPresente(
                                new ProdutoBasico("Fone", new BigDecimal("0.10")))));

        // then
        assertEquals("Fone + Embalagem para presente + Garantia estendida + Seguro contra danos", produto.getDescricao());
        assertEquals(0, new BigDecimal("51.60").compareTo(produto.getPreco()));

    }

    /**
     * Verifica que o mesmo serviço pode ser aplicado mais de uma vez.
     */
    @Test
    void deveEmpilharMesmoServico() {

        // given
        Produto produto = new EmbalagemPresente(new EmbalagemPresente(new ProdutoBasico("Caneca", BigDecimal.ZERO)));

        // then
        assertEquals(0, new BigDecimal("3.00").compareTo(produto.getPreco()));

    }

    /**
     * Verifica as validações de entrada.
     */
    @Test
    void deveRejeitarEntradasInvalidas() {
        assertThrows(IllegalArgumentException.class, () -> new ProdutoBasico("Livro", new BigDecimal("-1")));
        assertThrows(IllegalArgumentException.class, () -> new ProdutoBasico("Livro", null));
        assertThrows(IllegalArgumentException.class, () -> new GarantiaEstendida(null));
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
                === COMBINAÇÃO 1 ===
                Descrição: Notebook + Embalagem para presente
                Preço total: R$ 3501,50
                === COMBINAÇÃO 2 ===
                Descrição: Smartphone + Garantia estendida + Seguro contra danos
                Preço total: R$ 2050,00
                === COMBINAÇÃO 3 ===
                Descrição: Fone de ouvido + Embalagem para presente + Garantia estendida + Seguro contra danos
                Preço total: R$ 251,40""", saida);

    }
}
