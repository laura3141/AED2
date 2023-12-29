import java.util.Scanner;

public class Transposta {
    static int n,c;
    static void mostraTransposta(int a[][]){
        for(int i=0;i<c;i++){
            for(int j=0;j<n;j++){
                System.out.print(a[j][i]+" ");
            }
            System.out.println(" ");
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
        mostraTransposta(a);
        scanner.close();
    }
    
}
