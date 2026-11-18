package br.com.unesp.validador_codigos_interpreter.Aplication;

import br.com.unesp.validador_codigos_interpreter.Interpreter.AlternationExpression;
import br.com.unesp.validador_codigos_interpreter.Interpreter.LiteralExpression;
import br.com.unesp.validador_codigos_interpreter.Interpreter.RegularExpression;
import br.com.unesp.validador_codigos_interpreter.Interpreter.RepetitionExpression;
import br.com.unesp.validador_codigos_interpreter.Interpreter.SequenceExpression;
import br.com.unesp.validador_codigos_interpreter.Models.Context;

/**
 * Client do Interpreter: monta a árvore sintática abstrata da gramática
 * {@code A(B|C)*J} e interpreta uma lista de códigos.
 */
public class App {

    /**
     * Monta a árvore sintática da gramática {@code A(B|C)*J}.
     *
     * @return a raiz da árvore
     */
    public static RegularExpression criarExpressao() {

        // Expressões terminais
        RegularExpression a = new LiteralExpression('A');
        RegularExpression b = new LiteralExpression('B');
        RegularExpression c = new LiteralExpression('C');
        RegularExpression j = new LiteralExpression('J');

        // Alternância: B ou C
        RegularExpression alternancia =
                new AlternationExpression(b, c);

        // Repetição: zero ou mais ocorrências de B ou C
        RegularExpression repeticao =
                new RepetitionExpression(alternancia);

        // Nova regra: zero ou mais B ou C seguido obrigatoriamente de J
        RegularExpression repeticaoComJ =
                new SequenceExpression(repeticao, j);

        // Sequência: A seguido de (B|C)* e terminando em J
        return new SequenceExpression(a, repeticaoComJ);
    }

    /**
     * Interpreta um código. Ele só é válido se a expressão for reconhecida
     * e toda a entrada tiver sido consumida.
     *
     * @param expressao raiz da árvore sintática
     * @param codigo código a ser interpretado
     * @return {@code true} se o código pertence à gramática
     */
    public static boolean validar(RegularExpression expressao, String codigo) {
        Context context = new Context(codigo);

        // isFim() barra sobra no final, ex.: AJB reconhece AJ mas deixa o B
        return expressao.interpret(context)
                && context.isFim();
    }

    /**
     * Interpreta códigos válidos e inválidos para a gramática {@code A(B|C)*J}.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {

        String[] codigos = {
            "AJ", "ABJ", "ACJ", "ABCBCJ",
            "A", "J", "ABC", "AJB", "AJJ", "BJ", "ADJ", "ABDJ", ""
        };

        RegularExpression expressao = criarExpressao();

        // Interpretação dos códigos
        for (String codigo : codigos) {

            boolean resultado = validar(expressao, codigo);

            System.out.println(
                    "\"" + codigo + "\" -> " + (resultado ? "Válido" : "Inválido")
            );
        }
    }
}
