import java.util.Scanner;
public class ExemploIf {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Digite sua nota: ");
        float nota= scanner.nextFloat();
        if(nota>=80)System.out.println("Parabéns");
        else{
            if(nota>=70)System.out.println("Aprovado");
            else System.out.println("Reprovado");
        }
        scanner.close();
    }
    
}
