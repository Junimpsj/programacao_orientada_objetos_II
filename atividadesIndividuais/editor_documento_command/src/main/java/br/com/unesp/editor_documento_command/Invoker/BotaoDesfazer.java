package br.com.unesp.editor_documento_command.Invoker;

import br.com.unesp.editor_documento_command.Interface.Command;

/**
 * Invoker: botão que reverte a última alteração feita pelo comando associado,
 * acionando {@link Command#desfazer()}.
 */
// mesmo papel do Botao, só que chama desfazer() em vez de executar(), o cliente não precisa mais chamar o comando direto
public class BotaoDesfazer {

    private Command comando;

    /**
     * Associa o comando cuja última alteração será desfeita.
     *
     * @param comando comando a ser desfeito ao clicar
     */
    public void setComando(Command comando) {
        this.comando = comando;
    }

    /**
     * Desfaz a última alteração realizada pelo comando associado.
     */
    public void clicar() {
        comando.desfazer();
    }
}
