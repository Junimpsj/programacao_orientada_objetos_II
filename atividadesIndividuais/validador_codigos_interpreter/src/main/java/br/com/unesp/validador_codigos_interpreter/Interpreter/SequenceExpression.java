package br.com.unesp.validador_codigos_interpreter.Interpreter;

import br.com.unesp.validador_codigos_interpreter.Models.Context;

/**
 * NonterminalExpression: sequência de duas expressões ({@code expressao1 expressao2}).
 * É reconhecida quando a primeira e, logo em seguida, a segunda forem reconhecidas.
 */
public class SequenceExpression extends RegularExpression {

    private RegularExpression expressao1;
    private RegularExpression expressao2;

    /**
     * Cria a sequência.
     *
     * @param expressao1 expressão que deve aparecer primeiro
     * @param expressao2 expressão que deve aparecer logo depois
     */
    public SequenceExpression(
            RegularExpression expressao1,
            RegularExpression expressao2) {

        this.expressao1 = expressao1;
        this.expressao2 = expressao2;
    }

    /**
     * Interpreta as duas expressões em ordem; se alguma falhar, restaura a
     * posição inicial (backtracking) para não deixar o contexto pela metade.
     *
     * @param context entrada sendo interpretada e posição atual da leitura
     * @return {@code true} se as duas expressões foram reconhecidas em sequência
     */
    @Override
    public boolean interpret(Context context) {

        int posicao = context.getPosicao();

        if (expressao1.interpret(context)
                && expressao2.interpret(context)) {
            return true;
        }
        context.setPosicao(posicao);
        return false;
    }
}
