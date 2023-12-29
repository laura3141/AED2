import java.util.Scanner;

public class Nsequencia {
    public static void main(String args[]){
        Scanner scanner= new Scanner(System.in);
        int n=scanner.nextInt();
        int c=1;
        System.out.println(c);
        for(int i=0;i<n;i++){
            if(i%2==0){
                c=c+4;
                System.out.println(c);
            }
            else{
                c=c+7;
                System.out.println(c);
            }
        }
        scanner.close();
    }
}
