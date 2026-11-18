package br.com.unesp.editor_documento_command.Invoker;

import br.com.unesp.editor_documento_command.Interface.Command;

/**
 * Invoker: botão que dispara a execução do comando associado a ele,
 * sem saber qual operação está sendo feita nem sobre qual objeto.
 */
public class Botao {

    private Command comando;

    /**
     * Associa um comando ao botão.
     *
     * @param comando comando executado ao clicar
     */
    public void setComando(Command comando) {
        this.comando = comando;
    }

    /**
     * Executa o comando associado.
     */
    public void clicar() {
        comando.executar();
    }
}
