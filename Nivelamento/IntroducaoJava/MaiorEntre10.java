import java.util.Scanner;

public class MaiorEntre10 {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int num,maior=0;
        
        for(int i=0;i<10;i++){
            num=scanner.nextInt();
            if(i==0)maior=num;
            else{
                if(num>maior)maior=num;
            }

        }
        System.out.println("Maior: "+maior);
        scanner.close();
    }
    
}
