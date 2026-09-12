import java.util.Scanner;

public class Artur_Guilherme_BonusFuncionario {

private static Scanner scan = new Scanner(System.in);

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