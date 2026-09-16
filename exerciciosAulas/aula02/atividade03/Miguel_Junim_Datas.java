import java.util.Scanner;

public class Miguel_Junim_Datas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n----- AVISO: Insira todas as entradas em numeros interos! -----");
        System.out.println("----- AVISO: Insira Mes entre 1 e 12 | Insira Ano entre 0 e 2026 (ano atual) ----- \n");

        while (true) {

            System.out.println("\n");
            System.out.print("Insira o mes: ");
            int mes = scanner.nextInt();

            if (mes < 1 || mes > 12) {
                System.out.println("Mês inválido: " + mes + ", o programa vai reiniciar!");
                continue;
            }

            System.out.println("\n");
            System.out.print("Inseria ano desejado: ");
            int ano = scanner.nextInt();

            if (ano < 0 || ano > 2026) {
                System.out.println("Ano inválido: " + ano + ", o programa vai reiniciar!");
                continue;
            }

            int resultado = dias(ano, mes);
            System.out.println("\n");
            if(anoBissexto(ano)) {
                System.out.println("O mes [" + mes + "] do ano [" + ano + "] tem " + resultado + " dias [ANO BISSEXTO].");
            } else {
                System.out.println("O mes [" + mes + "] do ano [" + ano + "] tem " + resultado + " dias.");
            }

            break;

        }

        scanner.close();

    }

    private static boolean anoBissexto(int ano) {

        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);

    }

    private static int dias(int ano, int mes) {

        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (mes == 2 && anoBissexto(ano)) {
            return 29;
        }

        return diasPorMes[mes - 1];

    }

}