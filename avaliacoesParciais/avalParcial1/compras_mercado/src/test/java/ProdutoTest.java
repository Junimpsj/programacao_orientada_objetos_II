import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.com.unesp.compras_mercado.Model.Produto;

/**
 * Testes da classe {@link Produto}.
 */
public class ProdutoTest {

    /**
     * Verifica que preço negativo lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaPrecoNegativoOuZero() {

        // given - when - then
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Pacote Arroz 1KG", -10, 50);
        });

    }

    /**
     * Verifica que estoque negativo lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoParaEstoqueNegativo() {
        
        // given - when - then
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Pacote Feijao 1KG", 10, -5);
        });

    }

    /**
     * Verifica que valores válidos não lançam exceção.
     */
    @Test
    void naoDeveLancarExcecaoParaValoresValidos() {

        // given - when - then
        // esse assertDoesNotThrow eu descobri agora durante a implementação pesquisando
        // achei ele muito bom e válido pois testa se o lambda roda até o fim sem erro
        // se for disparada qualquer exceção para essa execução, o teste falha
        // para casos como esse que queremos testar "NaoDeveLancar" muito útil
        assertDoesNotThrow(() -> {
            new Produto("Choco Trio Nestle", 8, 40);
        });

    }

    /**
     * Verifica que a baixa reduz o estoque na quantidade pedida.
     */
    @Test
    void deveBaixarEstoqueCorretamente() {
        
        // given
        Produto produto = new Produto("Picanha Friboi 1KG", 80, 50);
        int estoqueEsperado = 40;

        // when
        produto.baixarEstoque(10);

        // then
        assertEquals(estoqueEsperado, produto.getQtdEstoque());

    }

    /**
     * Verifica que baixa maior que o estoque lança {@link IllegalArgumentException}.
     */
    @Test
    void deveLancarExcecaoQuandoBaixarEstoqueMaiorQueDisponivel() {

        // given
        Produto produto = new Produto("Picanha Friboi 1KG", 80, 10);

        // when - then
        assertThrows(IllegalArgumentException.class, () -> {
            produto.baixarEstoque(15);
        });

    }
    
}