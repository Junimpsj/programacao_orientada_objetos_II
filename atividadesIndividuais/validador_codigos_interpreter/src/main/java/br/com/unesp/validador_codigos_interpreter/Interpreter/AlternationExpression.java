package br.com.unesp.validador_codigos_interpreter.Interpreter;

import br.com.unesp.validador_codigos_interpreter.Models.Context;

/**
 * NonterminalExpression: alternância entre duas expressões ({@code expressao1 | expressao2}).
 * É reconhecida quando qualquer uma das duas for reconhecida.
 */
public class AlternationExpression extends RegularExpression {

    private RegularExpression expressao1;
    private RegularExpression expressao2;

    /**
     * Cria a alternância.
     *
     * @param expressao1 primeira opção, tentada antes
     * @param expressao2 segunda opção, tentada se a primeira falhar
     */
    public AlternationExpression(
            RegularExpression expressao1,
            RegularExpression expressao2) {

        this.expressao1 = expressao1;
        this.expressao2 = expressao2;
    }

    /**
     * Tenta a primeira expressão; se falhar, volta para a posição inicial
     * (backtracking) e tenta a segunda.
     *
     * @param context entrada sendo interpretada e posição atual da leitura
     * @return {@code true} se uma das duas expressões foi reconhecida
     */
    @Override
    public boolean interpret(Context context) {

        // salva a posição para voltar caso a primeira opção consuma algo e falhe
        int posicao = context.getPosicao();
        if (expressao1.interpret(context)) {
            return true;
        }
        context.setPosicao(posicao);
        return expressao2.interpret(context);
    }
}
