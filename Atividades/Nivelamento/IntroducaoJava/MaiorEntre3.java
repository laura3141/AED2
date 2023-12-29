import java.util.Scanner;

public class MaiorEntre3 {
    public static void main(String args[]){
        System.out.println("Digite 3 valores: ");
        Scanner scanner =new Scanner(System.in);
        int a,b,c,maior,menor;
        a=scanner.nextInt();
        b=scanner.nextInt();
        c=scanner.nextInt();
        maior=menor=a;
        if(b>maior)maior=b;
        if(b<menor)menor=b;
        if(c>maior)maior=c;
        if(c<menor)menor=c;
        System.out.println("Maior: "+maior);
        System.out.println("Menor: "+menor);
        scanner.close();
    }
}
