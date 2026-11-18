package br.com.unesp.editor_documento_command.Command;

import br.com.unesp.editor_documento_command.Interface.Command;
import br.com.unesp.editor_documento_command.Models.Documento;

/**
 * ConcreteCommand: encapsula a substituição do texto de um {@link Documento}
 * como objeto, guardando o texto anterior para poder desfazer.
 */
// liga a ação (substituir texto) ao receiver que realmente faz o trabalho (Documento)
public class SubstituirTextoCommand implements Command {

    private Documento documento;
    private String novoTexto;

    // estado salvo no executar(), é o que permite o desfazer()
    private String textoAnterior;

    /**
     * Cria o comando de substituição.
     *
     * @param documento documento que recebe a alteração (receiver)
     * @param novoTexto texto que vai substituir o conteúdo atual
     */
    public SubstituirTextoCommand(
            Documento documento, String novoTexto) {
        this.documento = documento;
        this.novoTexto = novoTexto;
    }

    /**
     * Salva o conteúdo atual do documento e o substitui pelo novo texto.
     */
    @Override
    public void executar() {
        textoAnterior = documento.getConteudo();
        documento.substituirTexto(novoTexto);
    }

    /**
     * Restaura o conteúdo que o documento tinha antes do último {@link #executar()}.
     */
    @Override
    public void desfazer() {
        documento.substituirTexto(textoAnterior);
    }
}
