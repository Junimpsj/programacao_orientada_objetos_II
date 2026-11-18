package br.com.unesp.validador_codigos_interpreter.Interpreter;

import br.com.unesp.validador_codigos_interpreter.Models.Context;

/**
 * NonterminalExpression: zero ou mais repetições de uma expressão ({@code expressao*}).
 */
public class RepetitionExpression extends RegularExpression {

    private RegularExpression expressao;

    /**
     * Cria a repetição.
     *
     * @param expressao expressão que pode se repetir
     */
    public RepetitionExpression(RegularExpression expressao) {
        this.expressao = expressao;
    }

    /**
     * Reconhece a expressão quantas vezes conseguir. Quando uma tentativa falha,
     * restaura a posição de antes dela e para. Sempre retorna {@code true},
     * já que zero ocorrências também é válido.
     *
     * @param context entrada sendo interpretada e posição atual da leitura
     * @return sempre {@code true}
     */
    // gulosa: consome tudo que der. Na gramática A(B|C)*J funciona porque o J não casa com (B|C), então a repetição para nele
    @Override
    public boolean interpret(Context context) {

        while (true) {

            int posicao = context.getPosicao();

            if (!expressao.interpret(context)) {
                context.setPosicao(posicao);
                break;
            }
            // se a expressão foi reconhecida sem consumir nada, para para não entrar em loop infinito
            if (context.getPosicao() == posicao) {
                break;
            }
        }
        return true;
    }
}
