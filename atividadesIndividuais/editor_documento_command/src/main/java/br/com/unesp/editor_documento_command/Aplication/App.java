package br.com.unesp.editor_documento_command.Aplication;

import br.com.unesp.editor_documento_command.Command.SubstituirTextoCommand;
import br.com.unesp.editor_documento_command.Interface.Command;
import br.com.unesp.editor_documento_command.Invoker.Botao;
import br.com.unesp.editor_documento_command.Invoker.BotaoDesfazer;
import br.com.unesp.editor_documento_command.Models.Documento;

/**
 * Client do Command: cria o documento (receiver), o comando de substituição
 * e associa o mesmo comando ao botão de substituir e ao botão de desfazer.
 */
public class App {

    /**
     * Substitui o texto do documento e depois desfaz a alteração pelos botões.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {

        Documento documento = new Documento(
            "Engenharia de Software é importante."
        );

        System.out.println("Texto original:");
        System.out.println(documento.getConteudo());

        // Criando o comando
        Command comando = new SubstituirTextoCommand(
            documento,
            "Engenharia de Software é fundamental."
        );

        // Botão de substituir
        Botao botaoSubstituir = new Botao();
        botaoSubstituir.setComando(comando);

        // Botão de desfazer, recebe o mesmo comando para reverter o que ele fez
        BotaoDesfazer botaoDesfazer = new BotaoDesfazer();
        botaoDesfazer.setComando(comando);

        // Executa a substituição
        botaoSubstituir.clicar();

        System.out.println("\nApós a substituição:");
        System.out.println(documento.getConteudo());

        // Desfaz a operação pelo invoker, no exemplo original era comando.desfazer() direto
        botaoDesfazer.clicar();

        System.out.println("\nApós desfazer:");
        System.out.println(documento.getConteudo());
    }
}
