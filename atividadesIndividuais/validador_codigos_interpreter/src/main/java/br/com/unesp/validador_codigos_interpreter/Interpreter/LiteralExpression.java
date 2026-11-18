package br.com.unesp.validador_codigos_interpreter.Interpreter;

import br.com.unesp.validador_codigos_interpreter.Models.Context;

/**
 * TerminalExpression: representa um único símbolo da gramática (ex.: A, B, C, J).
 * São as folhas da árvore sintática.
 */
public class LiteralExpression extends RegularExpression {

    private char simbolo;

    /**
     * Cria a expressão para um símbolo.
     *
     * @param simbolo caractere que esta expressão reconhece
     */
    public LiteralExpression(char simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Reconhece o símbolo se ele for o caractere atual da entrada, avançando uma posição.
     *
     * @param context entrada sendo interpretada e posição atual da leitura
     * @return {@code true} se o caractere atual for o símbolo; {@code false} se for
     *         outro caractere ou se a entrada já acabou
     */
    @Override
    public boolean interpret(Context context) {

        // entrada acabou, não tem o que comparar
        if (context.isFim()) {
            return false;
        }

        if (context.getAtual() == simbolo) {
            context.avancar();
            return true;
        }

        // não avança quando falha, então não precisa restaurar a posição
        return false;
    }
}
