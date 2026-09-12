import java.util.Scanner;

public class Miguel_Junim_BonusFuncionario{

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

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int dias = scanner.nextInt();
        System.out.println("O bônus por "+dias+" dias de meta batida é: "+ defineBonus(dias));
    }
}