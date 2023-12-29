import java.util.Scanner;

public class Intercalados {
    public static void main(String args[]){
        Scanner scanner= new Scanner(System.in);
        int[]a1=new int[6];
        int[]a2=new int[6];
        for(int i=0;i<6;i++){
            System.out.println("Digite 2 valores: ");
            a1[i]=scanner.nextInt();
            a2[i]=scanner.nextInt();
        }
        for(int i=0;i<6;i++){
            System.out.println(a1[i]+"e"+a2[i]);

        }
        scanner.close();
    }
}
