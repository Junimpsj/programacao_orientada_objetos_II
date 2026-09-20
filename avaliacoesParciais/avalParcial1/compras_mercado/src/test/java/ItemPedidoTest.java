import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.com.unesp.compras_mercado.Model.ItemPedido;
import br.com.unesp.compras_mercado.Model.Produto;

public class ItemPedidoTest {

    @Test
    void deveLancarEscecaoParaQuantidadeZeroOuNegativa() {
        
        // given
        Produto produto = new Produto("Milho Enlatado 200g", 4, 100);

        // when - then
        assertThrows(IllegalArgumentException.class, () -> {
            new ItemPedido(produto, 0);
        });

    }
    
    @Test
    void NaoDeveLancarExcecaoParaQuantidadeValida() {

        // given
        Produto produto = new Produto ("Milho Enlatado 200g", 4, 100);

        // when - then
        assertDoesNotThrow(() -> {
            new ItemPedido(produto, 10);
        });
    }

    @Test
    void deveCalcularSubtotalCorretamente() {

        // given
        Produto produto = new Produto("Caixa de Morango 200g", 10.5, 30);
        ItemPedido item = new ItemPedido(produto, 3);
        double subtotalEsperado = 31.5;

        // when
        double subtotalCalculado = item.calcularSubtotal();

        // then
        // obs: o terceiro argumento do assertEquals aqui é o "delta"
        // ele é responsável por definir uma margem de tolerância
        // utilizado para floats e doubles por causa dos erros de arredendamento binários
        // uma comparação exata quebraria, então damos essa margem de segurança
        assertEquals(subtotalEsperado, subtotalCalculado, 0.0001);

    }

    @Test void deveRetornarProdutoAssociado() {

        // given
        Produto produto = new Produto("Caixa de Morango 200g", 10.5, 30);
        ItemPedido item = new ItemPedido(produto, 3);

        // when
        Produto produtoRetornado = item.getProduto();

        // then
        assertEquals(produto, produtoRetornado);

    }

}