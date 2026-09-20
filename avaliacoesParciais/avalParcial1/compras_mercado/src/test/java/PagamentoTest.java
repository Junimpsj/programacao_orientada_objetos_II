import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.com.unesp.compras_mercado.Model.PagamentoDinheiro;
import br.com.unesp.compras_mercado.Model.PagamentoCheque;
import br.com.unesp.compras_mercado.Model.PagamentoCartao;

public class PagamentoTest {

    @Test
    void deveRetornarMetodoDinheiro() {

        // given
        PagamentoDinheiro pagamento = new PagamentoDinheiro();
        String descricaoEsperada = "Dinheiro";

        // when
        String descricaoCalculada = pagamento.metodoPagamento();

        // then
        assertEquals(descricaoEsperada, descricaoCalculada);

    }

    @Test
    void deveRetornarMetodoCheque() {

        // given
        PagamentoCheque pagamento = new PagamentoCheque();
        String descricaoEsperada = "Cheque";

        // when
        String descricaoCalculada = pagamento.metodoPagamento();

        // then
        assertEquals(descricaoEsperada, descricaoCalculada);

    }

    @Test
    void deveRetornarMetodoCartao() {

        // given
        PagamentoCartao pagamento = new PagamentoCartao();
        String descricaoEsperada = "Cartão";

        // when
        String descricaoCalculada = pagamento.metodoPagamento();

        // then
        assertEquals(descricaoEsperada, descricaoCalculada);

    }
    
}