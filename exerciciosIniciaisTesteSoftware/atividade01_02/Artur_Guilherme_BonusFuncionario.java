package exerciciosIniciaisTesteSoftware.atividade01_02;

import java.util.Scanner;

/**
 * Calcula o bônus de um funcionário a partir dos dias de meta batida, lidos do teclado.
 * Faixas: até 32 dias sem bônus; de 33 a 40, R$ 325 por dia acima de 32;
 * de 41 a 48, R$ 550 por dia acima de 40 mais R$ 2.600; acima de 48, R$ 600 por dia acima de 48 mais R$ 7.000.
 */
public class Artur_Guilherme_BonusFuncionario {

/**
 * Leitor da entrada padrão.
 */
private static Scanner scan = new Scanner(System.in);

/**
 * Calcula e imprime o bônus correspondente à quantidade de dias.
 *
 * @param dias dias de meta batida; valores negativos são considerados inválidos
 */
private static void fDias(int dias) {

    if(dias<0) {
        System.out.println("Tempo inválido!");
        return;
    }

    if(dias<=32) {
        System.out.println("Total de bônus: R$0");
        return;
    }

    int tot=0;

    if(dias >= 33 && dias < 41) {

        int bonus1 = dias-32;

        tot = bonus1*325;

        System.out.println("Total de bônus: R$"+tot);
        return;
    }

    if(dias >= 41 && dias <= 48) {

        int bonus2 = dias-40;

        tot = (bonus2*550) + 2600;

        System.out.println("Total de bônus: R$"+tot);
        return;
    }

    int bonus3 = dias-48;

    tot = (bonus3*600) + 7000;

    System.out.println("Total de bônus: R$"+tot);

}

/**
 * Lê os dias de meta batida (repetindo enquanto for zero) e imprime o bônus.
 *
 * @param args argumentos de linha de comando (não utilizados)
 */
public static void main(String[] args) {

    int dias=0;

    while(true) {
        System.out.println("Digite a quantidade de dias de bônus: ");

        dias=Integer.parseInt(scan.nextLine());

        if(dias!=0) {
            break;
        }
    }
    
    fDias(dias);

    scan.close();
}

}