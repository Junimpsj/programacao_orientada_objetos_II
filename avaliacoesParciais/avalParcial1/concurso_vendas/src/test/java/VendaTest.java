import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.avaliacao.Participante;
import br.com.unesp.avaliacao.Venda;

public class VendaTest{

    @BeforeAll
    static void iniciarTestes(){
        System.out.println("=== INICIANDO TESTES DE VENDAS ===");
    }

    @BeforeEach
    void preparar() {
        System.out.println("Preparando teste...");
    }

    @Test
    public void casoVendaNegativa() {

        //Given
        Participante part = new Participante("Wilson", 2);
        

        //When and Then 
        assertThrows(IllegalStateException.class, () -> {
            Venda venda = new Venda(part, -1500);
        });
    }

    @AfterAll
    static void finalizarTestes(){
        System.out.println("=== FINALIZANDO TESTES DE VENDAS ===");
    }
}