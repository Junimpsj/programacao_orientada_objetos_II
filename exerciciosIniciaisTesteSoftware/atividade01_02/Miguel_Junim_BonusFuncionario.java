package exerciciosIniciaisTesteSoftware.atividade01_02;

import java.util.Scanner;

/**
 * Calcula o bônus de um funcionário a partir dos dias de meta batida, lidos do teclado.
 * Faixas: até 32 dias sem bônus; de 33 a 40, R$ 325 por dia; de 41 a 48, R$ 550 por dia; acima de 48, R$ 600 por dia.
 */
public class Miguel_Junim_BonusFuncionario{

    /**
     * Calcula o bônus dos dias de meta batida, dia a dia, conforme a faixa de cada dia.
     *
     * @param dias dias de meta batida, de 0 a 90
     * @return o valor do bônus em reais
     * @throws RuntimeException se os dias estiverem fora do intervalo de 0 a 90
     */
    public static float defineBonus(int dias){
        int bonus = 0;
        if(dias>90 || dias<0){
            throw new RuntimeException("Erro, número fora do escopo, 0 <= x <= 90");
        }
        if(dias<=32){
            return 0;
        }
        while(dias>32){
            if(dias>48) {
                bonus += 600;
                dias -= 1;
            }
            else if(dias>40){
                bonus+=550;
                dias-=1;
            }
            else{
                bonus+=325;
                dias-=1;
            }
        }

        return bonus;
    }

    /**
     * Lê os dias de meta batida e imprime o bônus.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int dias = scanner.nextInt();
        System.out.println("O bônus por "+dias+" dias de meta batida é: "+ defineBonus(dias));
    }
}