package br.com.unesp.editor_documento_command.Interface;

/**
 * Command: declara a interface comum para executar e desfazer uma operação.
 * Os invocadores (botões) só conhecem esta interface, não sabem o que o comando faz.
 */
public interface Command {
    /**
     * Executa a operação encapsulada pelo comando.
     */
    void executar();
    /**
     * Reverte a última execução do comando.
     */
    void desfazer();
}
