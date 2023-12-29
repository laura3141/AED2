import java.util.Scanner;

public class Nimpares {
    public static void main(String args[]){
        System.out.println("Digite um numero: ");
        Scanner scanner= new Scanner(System.in);
        int n=scanner.nextInt();
        int c=0;
        while(c<2*n){
            if(c%2!=0){
                System.out.println(c);
            }
            c++;
        }
        scanner.close();
    }
}
