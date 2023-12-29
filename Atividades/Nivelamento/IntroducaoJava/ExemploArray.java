import java.util.Scanner;

public class ExemploArray {
    public static void main(String args[]){
        Scanner scanner= new Scanner(System.in);
        double[] nota=new double[5];
        String[]nome=new String[5];
        double soma=0;
        for(int i=0;i<5;i++){
            System.out.println("Digite o nome: ");
            nome[i]=scanner.nextLine();
            System.out.println("Digite a nota: ");
            nota[i]=scanner.nextDouble();
            scanner.nextLine();
            soma+=nota[i];
        }
        double media=soma/5;
        for(int i=0;i<5;i++){
            if(nota[i]>media)System.out.println(nome[i]);
        }
        scanner.close();
    }
    
}
