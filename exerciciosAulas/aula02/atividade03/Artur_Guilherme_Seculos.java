import java.util.Scanner;

public class Artur_Guilherme_Seculos {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int ano=0;

        while(true) {
        
            System.out.println("Digite o ano: ");
            ano = Integer.parseInt(scan.nextLine());

            if(ano > 0 && ano <= 2026) {
                break;
            } else {
                System.out.println("Digite um ano válido!");
            }
        }

        Seculo maquinaResolvedora = new Seculo(ano);

        maquinaResolvedora.encontraSeculo();

        scan.close();
    }


    public static class Seculo{

        private int ano;

        public Seculo(int ano) {

            this.ano = ano;
        }

        public void encontraSeculo() {

            int doisPrimeirosDigitos =  this.ano/100;
            int doisUltimosDigitos = this.ano%100;

            System.out.print("Século: ");

            if(doisUltimosDigitos == 0) {

                if(doisPrimeirosDigitos / 10 == 2) {
                    System.out.println("XX");
                }

                if(doisPrimeirosDigitos / 10 == 1) {
                    System.out.print("X");

                    if(doisPrimeirosDigitos % 10 == 9) {
                        System.out.print("IX");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 8) {
                        System.out.print("VIII");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 7) {
                        System.out.print("VII");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 6) {
                        System.out.print("VI");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 5) {
                        System.out.print("V");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 4) {
                        System.out.print("IV");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 3) {
                        System.out.print("III");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 2) {
                        System.out.print("II");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 1) {
                        System.out.print("I");
                        return;
                    }
                }

                if(doisPrimeirosDigitos / 10 == 0) {

                    if(doisPrimeirosDigitos / 9 == 1 ) {
                        System.out.println("IX");
                        return;
                    }

                    if(doisPrimeirosDigitos / 8 == 1 ) {
                        System.out.println("VIII");
                        return;
                    }

                    if(doisPrimeirosDigitos / 7 == 1 ) {
                        System.out.println("VII");
                        return;
                    }

                    if(doisPrimeirosDigitos / 6 == 1 ) {
                        System.out.println("VI");
                        return;
                    }

                    if(doisPrimeirosDigitos / 5 == 1 ) {
                        System.out.println("V");
                        return;
                    }

                    if(doisPrimeirosDigitos / 4 == 1 ) {
                        System.out.println("IV");
                        return;
                    }

                    if(doisPrimeirosDigitos / 3 == 1 ) {
                        System.out.println("III");
                        return;
                    }

                    if(doisPrimeirosDigitos / 2 == 1 ) {
                        System.out.println("II");
                        return;
                    }

                    if(doisPrimeirosDigitos / 1 == 1 ) {
                        System.out.println("I");
                        return;
                    }
                }

            } else {

                if(doisPrimeirosDigitos / 10 == 2) {
                    System.out.println("XXI");
                }

                if(doisPrimeirosDigitos / 10 == 1) {

                    if(doisPrimeirosDigitos % 10 == 9) {
                        System.out.print("XX");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 8) {
                        System.out.print("XIX");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 7) {
                        System.out.print("XVIII");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 6) {
                        System.out.print("XVII");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 5) {
                        System.out.print("XVI");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 4) {
                        System.out.print("XV");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 3) {
                        System.out.print("XIV");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 2) {
                        System.out.print("XIII");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 1) {
                        System.out.print("XII");
                        return;
                    }

                    if(doisPrimeirosDigitos % 10 == 0) {
                        System.out.print("XI");
                    }
                }

                if(doisPrimeirosDigitos / 10 == 0) {

                    if(doisPrimeirosDigitos / 9 == 1 ) {
                        System.out.println("X");
                        return;
                    }

                    if(doisPrimeirosDigitos / 8 == 1 ) {
                        System.out.println("IX");
                        return;
                    }

                    if(doisPrimeirosDigitos / 7 == 1 ) {
                        System.out.println("VIII");
                        return;
                    }

                    if(doisPrimeirosDigitos / 6 == 1 ) {
                        System.out.println("VII");
                        return;
                    }

                    if(doisPrimeirosDigitos / 5 == 1 ) {
                        System.out.println("VI");
                        return;
                    }

                    if(doisPrimeirosDigitos / 4 == 1 ) {
                        System.out.println("V");
                        return;
                    }

                    if(doisPrimeirosDigitos / 3 == 1 ) {
                        System.out.println("IV");
                        return;
                    }

                    if(doisPrimeirosDigitos / 2 == 1 ) {
                        System.out.println("III");
                        return;
                    }

                    if(doisPrimeirosDigitos / 1 == 1 ) {
                        System.out.println("II");
                        return;
                    }

                    if(doisPrimeirosDigitos / 1 == 0) {
                        System.out.println("I");
                        return;
                    }
                }
            }
        }
    } //break

}

