import java.util.*;

public class Fila{
    int selecao(int a[],int tam){
        int x=0;
        for(int i=0;i<tam-1;i++){
            int menor=a[i];
            for(int j=i+1;j<tam;j++){
                if(a[j]<a[i]){
                    menor=j;
                    x++;
                }   
            }
            int aux=menor;
            menor=a[i];
            a[i]=aux;
        }
        return x;
    }
    public static void Main(String args[]){
        int casos,alunos;
        Scanner scanner=new Scanner(System.in);
        casos=scanner.nextInt();
        for(int i=0;i<casos;i++){
            alunos=scanner.nextInt();
            int notas[]=new int[alunos];
            for(int i=0;i<alunos;i++){
                notas[i]=scanner.nextInt();
            }
        }
        System.out.println(alunos-selecao(notas));
    }
}
