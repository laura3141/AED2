import java.util.Scanner;

public class FibonacciIterativo {
    static int fibonacci(int x){
        int[]a=new int[x];
        a[0]=a[1]=1;
        for(int i=2;i<x;i++){
            a[i]=a[i-1]+a[i-2];
        }
        return a[x-1];
        
    }
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Digite a posicao buscada: ");
        int posicao=scanner.nextInt();
        int fibo=fibonacci(posicao);
        System.out.println(fibo);
        scanner.close();
    }
}
