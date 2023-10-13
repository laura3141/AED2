import java.util.Scanner;

public class ExemploFor {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        float nota;
        float soma=0;
        for(int i=0;i<5;i++){
            nota=scanner.nextInt();
            soma=soma+nota;
        }
        System.out.println((float)soma/5);
        scanner.close();
    }
    
}
