import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.com.unesp.compras_mercado.Model.Pedido;
import br.com.unesp.compras_mercado.Model.ItemPedido;
import br.com.unesp.compras_mercado.Model.Produto;
import br.com.unesp.compras_mercado.Model.PagamentoDinheiro;

public class PedidoTest {

    @Test
    void deveAdicionarItemNaLista() {

        // given
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Feijões Mágicos HP", 48.95, 72);
        ItemPedido item = new ItemPedido(produto, 2);

        // when
        pedido.adicionarItem(item);

        // then
        assertEquals(1, pedido.getItens().size());
        // testando se "1" item foi adicionado na lista de pedidos
        // se retornar 0 ou um valor != 1, quebra

    }

    @Test
    void deveCalcularTotalCorretamente() {

        // given
        Pedido pedido = new Pedido();
        Produto sapoChocolate = new Produto("Sapo de Chocolate HP", 37.49, 50);
        Produto feijaoMagico = new Produto("Feijões Mágicos HP", 48.95, 72);
        pedido.adicionarItem(new ItemPedido(sapoChocolate, 4));
        pedido.adicionarItem(new ItemPedido(feijaoMagico, 3));
        double totalEsperado = (37.49*4) + (48.95*3);

        // when
        double totalCalculado = pedido.calcularTotal();

        // then
        assertEquals(totalEsperado, totalCalculado, 0.0001);

    }

    @Test
    void deveBaixarEstoqueDeTodosProdutosAoFinalizar() {

        // given
        Pedido pedido = new Pedido();
        Produto sapoChocolate = new Produto("Sapo de Chocolate HP", 37.49, 50);
        Produto feijaoMagico = new Produto("Feijões Mágicos HP", 48.95, 72);
        pedido.adicionarItem(new ItemPedido(sapoChocolate, 5));
        pedido.adicionarItem(new ItemPedido(feijaoMagico, 18));
        pedido.definirFormaPagamento(new PagamentoDinheiro());

        // when
        pedido.finalizar();

        // then
        assertEquals(45, sapoChocolate.getQtdEstoque());
        assertEquals(54, feijaoMagico.getQtdEstoque());

    }

    @Test
    void deveRetornarFormaPagamentoCorreta() {

        // given
        PagamentoDinheiro pagamento = new PagamentoDinheiro();
        Pedido pedido = new Pedido();

        // when
        pedido.definirFormaPagamento(pagamento);

        // then
        assertEquals(pagamento, pedido.getFormaPagamento());

    }

    @Test
    void deveLancarExcecaoQuandoFinalizarSemFormaPagamento() {

        // given
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Sanduiche Perfeito", 30, 5);
        ItemPedido item = new ItemPedido(produto,2);
        pedido.adicionarItem(item);
        // propositalmente, nao estamos definindo uma forma de pagamento aqui

        // when - then
        assertThrows(IllegalStateException.class, () -> {
            pedido.finalizar();
        });

    }

}