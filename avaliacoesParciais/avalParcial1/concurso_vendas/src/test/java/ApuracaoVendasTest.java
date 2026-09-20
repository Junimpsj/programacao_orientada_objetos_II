import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.avaliacao.ApuracaoVendas;
import br.com.unesp.avaliacao.Participante;
import br.com.unesp.avaliacao.Venda;

public class ApuracaoVendasTest {

    @BeforeAll
    static void iniciarTestes(){
        System.out.println("=== INICIANDO TESTES DE APURACAO DE VENDAS ===");
    }

    @BeforeEach
    void preparar() {
        System.out.println("Preparando teste...");
    }

    @Test
    public void testEleventhSale() {

        //Given
        Participante part = new Participante("Winson", 1);
        double valor = 1533.22;
        Venda venda = new Venda(part, valor);
        ApuracaoVendas apuracaoVendas = new ApuracaoVendas();

        //When
        for(int i=0; i<10; i++) {
            apuracaoVendas.adicionarVenda(venda);
        }

        //Then
        assertThrows(IllegalStateException.class, () -> {
            apuracaoVendas.adicionarVenda(venda);
        });
    }

    @Test
    public void testAparacao() {

        //Given
        Participante part = new Participante("Winson", 2);
        double valor = 1000.00;
        Venda venda = new Venda(part, valor);
        ApuracaoVendas apuracaoVendas = new ApuracaoVendas();
        double saidaEsperada = 1250.00;

        //When
        apuracaoVendas.adicionarVenda(venda);

        //Then
        assertEquals(saidaEsperada, venda.getValorVenda());
    }

    @AfterAll
    static void finalizarTestes(){
        System.out.println("=== FINALIZANDO TESTES DE APURACAO DE VENDAS ===");
    }
    
}