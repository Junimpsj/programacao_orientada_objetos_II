package br.com.unesp.editor_documento_command.Models;

/**
 * Receiver: o documento que de fato sofre a alteração. Não conhece comandos
 * nem botões, só sabe substituir e devolver o próprio conteúdo.
 */
public class Documento {

    private String conteudo;

    /**
     * Cria o documento com o conteúdo inicial.
     *
     * @param conteudo texto inicial do documento
     */
    public Documento(String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * Substitui todo o conteúdo do documento.
     *
     * @param novoTexto novo conteúdo
     */
    public void substituirTexto(String novoTexto) {
        conteudo = novoTexto;
    }

    /**
     * Retorna o conteúdo atual do documento.
     *
     * @return o conteúdo do documento
     */
    public String getConteudo() {
        return conteudo;
    }
}
