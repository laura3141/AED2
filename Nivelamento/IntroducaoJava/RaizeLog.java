import java.util.Scanner;

public class RaizeLog {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        double x,y;
        x=scanner.nextDouble();
        y=scanner.nextDouble();
        if(x>y){
            System.out.println("Raiz cubica: "+Math.cbrt(y));
            System.out.println("Log: "+Math.log(y)/Math.log(x));
        }
        else{
            System.out.println("Raiz cubica: "+Math.cbrt(x));
            System.out.println("Log: "+Math.log(x)/Math.log(y));
        }
        scanner.close();
    }
    
}
