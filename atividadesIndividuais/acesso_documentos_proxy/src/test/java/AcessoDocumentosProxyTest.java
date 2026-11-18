import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import br.com.unesp.acesso_documentos_proxy.Aplication.App;
import br.com.unesp.acesso_documentos_proxy.Interface.Documento;
import br.com.unesp.acesso_documentos_proxy.Models.Usuario;
import br.com.unesp.acesso_documentos_proxy.Proxy.DocumentoProxy;

/**
 * Testes do padrão Proxy: controle de acesso, carregamento adiado e reutilização do documento.
 */
public class AcessoDocumentosProxyTest {

    private static final Usuario COMUM = new Usuario("Ana", Usuario.Perfil.COMUM);
    private static final Usuario ADMIN = new Usuario("Carlos", Usuario.Perfil.ADMINISTRADOR);

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
     * Verifica que criar o proxy não carrega o documento.
     */
    @Test
    void naoDeveCarregarDocumentoAoCriarProxy() {

        // when
        String saida = capturarSaida(() -> new DocumentoProxy("Manual", false));

        // then
        assertEquals("", saida);

    }

    /**
     * Verifica que o usuário comum não acessa documento confidencial e nada é carregado.
     */
    @Test
    void deveNegarDocumentoConfidencialParaUsuarioComum() {

        // given
        Documento relatorio = new DocumentoProxy("Relatório", true);

        // when
        String saida = capturarSaida(() -> relatorio.exibir(COMUM));

        // then
        assertEquals("""
                [PROXY] Verificando permissões...
                [PROXY] Acesso negado!
                O usuário não possui autorização para acessar este documento.""", saida);

    }

    /**
     * Verifica que o documento é carregado só no primeiro acesso e reutilizado depois.
     */
    @Test
    void deveCarregarApenasNoPrimeiroAcesso() {

        // given
        Documento manual = new DocumentoProxy("Manual", false);

        // when
        String primeiro = capturarSaida(() -> manual.exibir(COMUM));
        String segundo = capturarSaida(() -> manual.exibir(ADMIN));

        // then
        assertEquals("""
                [PROXY] Verificando permissões...
                [PROXY] Acesso autorizado.
                [PROXY] Carregando documento...

                [DOCUMENTO REAL] Carregamento concluído.
                [DOCUMENTO REAL] Exibindo conteúdo do documento.""", primeiro);
        assertEquals("""
                [PROXY] Verificando permissões...
                [PROXY] Acesso autorizado.
                [PROXY] Documento já carregado. Reutilizando instância.

                [DOCUMENTO REAL] Exibindo conteúdo do documento.""", segundo);

    }

    /**
     * Verifica que o administrador acessa documento confidencial.
     */
    @Test
    void devePermitirDocumentoConfidencialParaAdministrador() {

        // given
        Documento relatorio = new DocumentoProxy("Relatório", true);

        // when
        String saida = capturarSaida(() -> relatorio.exibir(ADMIN));

        // then
        assertTrue(saida.contains("[PROXY] Acesso autorizado."));
        assertTrue(saida.endsWith("[DOCUMENTO REAL] Exibindo conteúdo do documento."));

    }

    /**
     * Verifica as validações de entrada.
     */
    @Test
    void deveRejeitarEntradasInvalidas() {
        assertThrows(IllegalArgumentException.class, () -> new DocumentoProxy(" ", false));
        assertThrows(IllegalArgumentException.class, () -> new DocumentoProxy("Manual", false).exibir(null));
        assertThrows(IllegalArgumentException.class, () -> new Usuario("", Usuario.Perfil.COMUM));
        assertThrows(IllegalArgumentException.class, () -> new Usuario("Ana", null));
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
                ========================================
                SISTEMA DE ACESSO A DOCUMENTOS
                ========================================

                USUÁRIO: Ana
                Perfil: Usuário comum

                ----------------------------------------
                1. ACESSANDO DOCUMENTO PÚBLICO
                ----------------------------------------

                Documento: Manual de utilização
                Tipo: Público

                [PROXY] Verificando permissões...
                [PROXY] Acesso autorizado.
                [PROXY] Carregando documento...

                [DOCUMENTO REAL] Carregamento concluído.
                [DOCUMENTO REAL] Exibindo conteúdo do documento.

                ----------------------------------------
                2. ACESSANDO DOCUMENTO CONFIDENCIAL
                ----------------------------------------

                Documento: Relatório financeiro
                Tipo: Confidencial

                [PROXY] Verificando permissões...
                [PROXY] Acesso negado!
                O usuário não possui autorização para acessar este documento.

                ----------------------------------------
                3. NOVO ACESSO AO DOCUMENTO PÚBLICO
                ----------------------------------------

                Documento: Manual de utilização

                [PROXY] Verificando permissões...
                [PROXY] Acesso autorizado.
                [PROXY] Documento já carregado. Reutilizando instância.

                [DOCUMENTO REAL] Exibindo conteúdo do documento.

                ========================================
                USUÁRIO: Carlos
                Perfil: Administrador

                ----------------------------------------
                4. ACESSANDO DOCUMENTO CONFIDENCIAL
                ----------------------------------------

                Documento: Relatório financeiro
                Tipo: Confidencial

                [PROXY] Verificando permissões...
                [PROXY] Acesso autorizado.
                [PROXY] Carregando documento...

                [DOCUMENTO REAL] Carregamento concluído.
                [DOCUMENTO REAL] Exibindo conteúdo do documento.

                ----------------------------------------
                5. NOVO ACESSO AO DOCUMENTO CONFIDENCIAL
                ----------------------------------------

                Documento: Relatório financeiro

                [PROXY] Verificando permissões...
                [PROXY] Acesso autorizado.
                [PROXY] Documento já carregado. Reutilizando instância.

                [DOCUMENTO REAL] Exibindo conteúdo do documento.

                ========================================""", saida);

    }
}
