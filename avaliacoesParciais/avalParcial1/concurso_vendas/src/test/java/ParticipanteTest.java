import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.unesp.avaliacao.Participante;

public class ParticipanteTest {

    @BeforeAll
    static void iniciarTestes(){
        System.out.println("=== INICIANDO TESTES DE PARTICIPANTE ===");
    }

    @BeforeEach
    void preparar() {
        System.out.println("Preparando teste...");
    }

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

    @AfterAll
    static void finalizarTestes(){
        System.out.println("=== FINALIZANDO TESTES DE PARTICIPANTE ===");
    }

}