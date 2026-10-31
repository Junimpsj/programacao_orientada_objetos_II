import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.avaliacao.Participante;
import br.com.unesp.avaliacao.Venda;

/**
 * Testes da classe {@link Venda}.
 */
public class VendaTest{

    /**
     * Exibe o cabeçalho da suíte de testes.
     */
    @BeforeAll
    static void iniciarTestes(){
        System.out.println("=== INICIANDO TESTES DE VENDAS ===");
    }

    /**
     * Exibe uma mensagem antes de cada teste.
     */
    @BeforeEach
    void preparar() {
        System.out.println("Preparando teste...");
    }

    /**
     * Verifica que valor de venda negativo lança {@link IllegalStateException}.
     */
    @Test
    public void casoVendaNegativa() {

        //Given
        Participante part = new Participante("Wilson", 2);
        

        //When and Then 
        assertThrows(IllegalStateException.class, () -> {
            Venda venda = new Venda(part, -1500);
        });
    }

    /**
     * Exibe o rodapé da suíte de testes.
     */
    @AfterAll
    static void finalizarTestes(){
        System.out.println("=== FINALIZANDO TESTES DE VENDAS ===");
    }
}