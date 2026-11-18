package br.com.unesp.validador_codigos_interpreter.Interpreter;

import br.com.unesp.validador_codigos_interpreter.Models.Context;

/**
 * AbstractExpression: declara a operação {@code interpret} comum a todos os nós
 * da árvore sintática abstrata (símbolos terminais e não terminais da gramática).
 */
public abstract class RegularExpression {

    /**
     * Tenta reconhecer esta expressão a partir da posição atual do contexto.
     * Se reconhecer, avança a posição e retorna {@code true}; se falhar,
     * retorna {@code false}.
     *
     * @param context entrada sendo interpretada e posição atual da leitura
     * @return {@code true} se a expressão foi reconhecida
     */
    public abstract boolean interpret(Context context);
}
