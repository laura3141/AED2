import java.util.*;

public class Filarecreio{
    static int selecao(int a[],int tam){
        int x=0;
        for(int i=0;i<tam-1;i++){
            int maior=i;
            for(int j=i+1;j<tam;j++){
                if(a[j]>a[maior]){
                    maior=j;
                    x++;
                }   
            }
            int aux=a[maior];
            a[maior]=a[i];
            a[i]=aux;
        }
        return x;
    }
    public static void main(String args[]){
        int n,k;
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        for(int i=0;i<casos;i++){
            alunos=scanner.nextInt();
            int notas[]=new int[alunos];
            
            for(int j=0;j<alunos;j++){
                notas[j]=scanner.nextInt();
            }
            System.out.println(selecao(notas,alunos));
            int x=selecao(notas,alunos);
            System.out.println((alunos-2*x));
            
            
        }
        
    }
}
