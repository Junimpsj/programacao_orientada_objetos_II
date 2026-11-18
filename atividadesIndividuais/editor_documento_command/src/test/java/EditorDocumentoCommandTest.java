import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.editor_documento_command.Aplication.App;
import br.com.unesp.editor_documento_command.Command.SubstituirTextoCommand;
import br.com.unesp.editor_documento_command.Interface.Command;
import br.com.unesp.editor_documento_command.Invoker.Botao;
import br.com.unesp.editor_documento_command.Invoker.BotaoDesfazer;
import br.com.unesp.editor_documento_command.Models.Documento;

/**
 * Testes do padrão Command: o botão executa a substituição e o
 * {@link BotaoDesfazer} reverte a última alteração do documento.
 */
public class EditorDocumentoCommandTest {

    /**
     * Captura o que for impresso no console durante a ação.
     *
     * @param acao trecho de código a ser executado
     * @return a saída do console, sem espaços nas pontas
     */
    private String capturarSaida(Runnable acao) {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            acao.run();
        } finally {
            System.setOut(original);
        }

        return saida.toString().trim().replace("\r\n", "\n");
    }

    /**
     * Verifica que o botão de substituir altera o documento pelo comando.
     */
    @Test
    void deveSubstituirTextoAoClicarNoBotao() {

        // given
        Documento documento = new Documento("original");
        Botao botao = new Botao();
        botao.setComando(new SubstituirTextoCommand(documento, "novo"));

        // when
        botao.clicar();

        // then
        assertEquals("novo", documento.getConteudo());

    }

    /**
     * Verifica que o botão de desfazer restaura o texto anterior à substituição.
     */
    @Test
    void deveDesfazerUltimaAlteracaoAoClicarNoBotaoDesfazer() {

        // given
        Documento documento = new Documento("original");
        Command comando = new SubstituirTextoCommand(documento, "novo");

        Botao botao = new Botao();
        botao.setComando(comando);

        BotaoDesfazer botaoDesfazer = new BotaoDesfazer();
        botaoDesfazer.setComando(comando);

        botao.clicar();

        // when
        botaoDesfazer.clicar();

        // then
        assertEquals("original", documento.getConteudo());

    }

    /**
     * Verifica que só a última alteração é desfeita quando o comando executa mais de uma vez.
     */
    @Test
    void deveDesfazerApenasAUltimaExecucao() {

        // given
        Documento documento = new Documento("original");
        Command comando = new SubstituirTextoCommand(documento, "novo");

        BotaoDesfazer botaoDesfazer = new BotaoDesfazer();
        botaoDesfazer.setComando(comando);

        comando.executar();
        documento.substituirTexto("editado à mão");
        comando.executar();

        // when
        botaoDesfazer.clicar();

        // then
        assertEquals("editado à mão", documento.getConteudo());

    }

    /**
     * Verifica a saída completa de {@link App#main(String[])}.
     */
    @Test
    void deveExecutarDemonstracaoCompleta() {

        // when
        String saida = capturarSaida(() -> App.main(new String[0]));

        // then
        assertEquals("""
                Texto original:
                Engenharia de Software é importante.

                Após a substituição:
                Engenharia de Software é fundamental.

                Após desfazer:
                Engenharia de Software é importante.""", saida);

    }
}
