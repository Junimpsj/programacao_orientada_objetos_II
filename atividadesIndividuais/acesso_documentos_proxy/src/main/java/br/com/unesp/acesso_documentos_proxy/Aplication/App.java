package br.com.unesp.acesso_documentos_proxy.Aplication;

import br.com.unesp.acesso_documentos_proxy.Interface.Documento;
import br.com.unesp.acesso_documentos_proxy.Models.Usuario;
import br.com.unesp.acesso_documentos_proxy.Proxy.DocumentoProxy;

/**
 * Ponto de entrada que demonstra o padrão Proxy: um usuário comum e um
 * administrador acessam documentos públicos e confidenciais pelo proxy.
 */
public class App {

    private static final String LINHA = "----------------------------------------";
    private static final String LINHA_DUPLA = "========================================";

    /**
     * Executa os cinco acessos do exemplo, com Ana (usuário comum) e Carlos (administrador).
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    // cliente só conhece a interface Documento, não sabe que por trás tem um proxy
    public static void main(String[] args) {
        Documento manual = new DocumentoProxy("Manual de utilização", false);
        Documento relatorio = new DocumentoProxy("Relatório financeiro", true);

        Usuario ana = new Usuario("Ana", Usuario.Perfil.COMUM);
        Usuario carlos = new Usuario("Carlos", Usuario.Perfil.ADMINISTRADOR);

        System.out.println(LINHA_DUPLA);
        System.out.println("SISTEMA DE ACESSO A DOCUMENTOS");
        System.out.println(LINHA_DUPLA);
        System.out.println();

        apresentar(ana);
        acessar("1. ACESSANDO DOCUMENTO PÚBLICO", manual, ana, true);
        acessar("2. ACESSANDO DOCUMENTO CONFIDENCIAL", relatorio, ana, true);
        acessar("3. NOVO ACESSO AO DOCUMENTO PÚBLICO", manual, ana, false);

        System.out.println(LINHA_DUPLA);
        apresentar(carlos);
        acessar("4. ACESSANDO DOCUMENTO CONFIDENCIAL", relatorio, carlos, true);
        acessar("5. NOVO ACESSO AO DOCUMENTO CONFIDENCIAL", relatorio, carlos, false);

        System.out.println(LINHA_DUPLA);
    }

    /**
     * Imprime o nome e o perfil do usuário.
     *
     * @param usuario usuário a ser apresentado
     */
    private static void apresentar(Usuario usuario) {
        System.out.println("USUÁRIO: " + usuario.getNome());
        System.out.println("Perfil: " + usuario.getPerfil().getDescricao());
        System.out.println();
    }

    /**
     * Imprime o cabeçalho da etapa e acessa o documento.
     *
     * @param titulo título da etapa
     * @param documento documento a ser acessado
     * @param usuario usuário que está acessando
     * @param mostrarTipo se a linha "Tipo" deve ser impressa (o exemplo omite nos novos acessos)
     */
    private static void acessar(String titulo, Documento documento, Usuario usuario, boolean mostrarTipo) {
        System.out.println(LINHA);
        System.out.println(titulo);
        System.out.println(LINHA);
        System.out.println();

        System.out.println("Documento: " + documento.getNome());

        if (mostrarTipo) {
            System.out.println("Tipo: " + (documento.isConfidencial() ? "Confidencial" : "Público"));
        }

        System.out.println();
        documento.exibir(usuario);
        System.out.println();
    }
}
