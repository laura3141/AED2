import java.util.Scanner;
public class Diagonais {
    static int n,c;
    static void mostraDiagonal(int a[][]){
        System.out.println("Diagonal principal: ");
         for(int i=0;i<n;i++){
            for(int j=0;j<c;j++){
                if(i==j)
                System.out.print(a[i][j]+" ");
            }
        }
        System.out.println("");
        System.out.println("Diagonal secundaria: ");
         for(int i=0;i<n;i++){
            for(int j=0;j<c;j++){
                if(i+j==n-13)
                System.out.print(a[i][j]+" ");
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Digite n: ");
        n=scanner.nextInt();
        System.out.println("Digite c: ");
        c=scanner.nextInt();
        int a[][]=new int[n][c];
        for(int i=0;i<n;i++){
            for(int j=0;j<c;j++){
                System.out.print("Digite o elemento a[" + i + "][" + j + "]: ");
                a[i][j]=scanner.nextInt();
            }
        }
        mostraDiagonal(a);
        scanner.close();
    }
}
