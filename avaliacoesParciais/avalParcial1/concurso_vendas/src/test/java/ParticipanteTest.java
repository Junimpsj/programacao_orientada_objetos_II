import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.avaliacao.Participante;

/**
 * Testes da classe {@link Participante}.
 */
public class ParticipanteTest {

    /**
     * Exibe o cabeçalho da suíte de testes.
     */
    @BeforeAll
    static void iniciarTestes(){
        System.out.println("=== INICIANDO TESTES DE PARTICIPANTE ===");
    }

    /**
     * Exibe uma mensagem antes de cada teste.
     */
    @BeforeEach
    void preparar() {
        System.out.println("Preparando teste...");
    }

    /**
     * Verifica que uma categoria inválida lança {@link IllegalStateException}.
     */
    @Test
    public void erroOpcaoParticipante() {

        //Given
        int opcao = -3;
        String nome = "Bárbaro";

        //When and Then
        assertThrows(IllegalStateException.class, () -> {
            Participante participante = new Participante(nome, opcao);
        });
    }

    /**
     * Exibe o rodapé da suíte de testes.
     */
    @AfterAll
    static void finalizarTestes(){
        System.out.println("=== FINALIZANDO TESTES DE PARTICIPANTE ===");
    }

}