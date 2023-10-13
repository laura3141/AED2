import java.util.Scanner;

public class MaioreMenorArray {
    public static void main(String args[]){
         Scanner scanner= new Scanner(System.in);
         System.out.println("Digite n:");
        int n=scanner.nextInt();
        float[]array=new float[n];
        float maior=0,menor=0;
        for(int i=0;i<n;i++){
            if(i==0){
                array[i]=scanner.nextFloat();
                maior=menor=array[i];
            }
            else{
                array[i]=scanner.nextFloat();
                if(array[i]>maior)maior=array[i];
                if(array[i]<menor)menor=array[i];
            }
        }
         System.out.println("MAIOR: "+maior);
        System.out.println("MENOR: "+menor);
        scanner.close();
    }
}
