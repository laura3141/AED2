import java.util.Scanner;

public class Trinagulos {
    public static void main(String args[]){
        System.out.println("Digite 3 valores: ");
        Scanner scanner= new Scanner(System.in);
        int a,b,c;
        a=scanner.nextInt();
        b=scanner.nextInt();
        c=scanner.nextInt();
        if(a!=b){
            if(b!=c)System.out.println("Escaleno");
            else System.out.println("Isoceles");
        }
        else{
            if(b!=c)System.out.println("Isoceles");
            else System.out.println("Equilatero");
        }
        scanner.close();
    }
    
}
