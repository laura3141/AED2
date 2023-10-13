import java.util.Scanner;

public class Array {
    public static void main(String args[]){
        Scanner scanner= new Scanner(System.in);
        int n=scanner.nextInt();
        float[]array=new float[n];
        for(int i=0;i<n;i++){
            array[i]=scanner.nextFloat();
        }
        for(int i=0;i<n;i++){
            if(2*i+1<n)
            System.out.println(array[i]+array[i*2+1]);
            else break;
        }
        scanner.close();
    }
}
