import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.checkout_ecommerce.Factory.BrasilCheckoutFactory;
import br.com.unesp.checkout_ecommerce.Factory.EuaCheckoutFactory;
import br.com.unesp.checkout_ecommerce.Factory.PaisCheckoutFactory;
import br.com.unesp.checkout_ecommerce.Models.Checkout;
import br.com.unesp.checkout_ecommerce.Models.FreteBrasil;
import br.com.unesp.checkout_ecommerce.Models.FreteEUA;
import br.com.unesp.checkout_ecommerce.Models.ImpostoBrasil;
import br.com.unesp.checkout_ecommerce.Models.ImpostoEUA;

/**
 * Testes do {@link Checkout} e das fábricas do padrão Abstract Factory (Brasil e EUA).
 */
public class CheckoutTest {

    /**
     * Captura o que {@link Checkout#fecharPedido(double, double)} imprime no console.
     *
     * @param checkout checkout que fechará o pedido de teste (100,0 e 2,0 kg)
     * @return a saída do console, sem espaços nas pontas
     */
    // mesma ideia do SistemaRelatorioTest, captura o System.out do fecharPedido()
    private String capturarSaida(Checkout checkout) {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            checkout.fecharPedido(100.0, 2.0);
        } finally {
            System.setOut(original);
        }

        return saida.toString().trim().replace("\r\n", "\n");
    }

    /**
     * Verifica que a fábrica do Brasil cria imposto e frete brasileiros.
     */
    @Test
    void fabricaBrasilDeveCriarFamiliaBrasil() {

        // given
        PaisCheckoutFactory factory = new BrasilCheckoutFactory();

        // when / then
        assertInstanceOf(ImpostoBrasil.class, factory.criarCalculadoraImposto());
        assertInstanceOf(FreteBrasil.class, factory.criarProcessadorFrete());

    }

    /**
     * Verifica que a fábrica dos EUA cria imposto e frete americanos.
     */
    @Test
    void fabricaEuaDeveCriarFamiliaEua() {

        // given
        PaisCheckoutFactory factory = new EuaCheckoutFactory();

        // when / then
        assertInstanceOf(ImpostoEUA.class, factory.criarCalculadoraImposto());
        assertInstanceOf(FreteEUA.class, factory.criarProcessadorFrete());

    }

    /**
     * Verifica o resumo de um pedido fechado com os componentes do Brasil.
     */
    @Test
    void deveFecharPedidoNoBrasil() {

        // given
        Checkout checkout = new Checkout(new BrasilCheckoutFactory());

        // when
        String saida = capturarSaida(checkout);

        // then
        assertEquals("""
                === FECHAMENTO DO PEDIDO ===
                Produto: R$ 100.0
                Imposto: R$ 20.0
                Frete: R$ 25.0
                VALOR TOTAL: R$ 145.0""", saida);

    }

    /**
     * Verifica o resumo de um pedido fechado com os componentes dos EUA.
     */
    @Test
    void deveFecharPedidoNosEua() {

        // given
        Checkout checkout = new Checkout(new EuaCheckoutFactory());

        // when
        String saida = capturarSaida(checkout);

        // then
        assertEquals("""
                === FECHAMENTO DO PEDIDO ===
                Produto: R$ 100.0
                Imposto: R$ 7.0
                Frete: R$ 16.0
                VALOR TOTAL: R$ 123.0""", saida);

    }
}
