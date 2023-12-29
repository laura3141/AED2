import java.util.Scanner;

public class Digitos {
    public static void main(String args[]){
        Scanner scanner= new Scanner(System.in);
        String str=scanner.nextLine();
        boolean eh=true;
        for(int i=0;i<str.length();i++){
            if(!(str.charAt(i)>='0'&&str.charAt(i)<='9'))eh=false;
        }
        if(eh)System.out.println("SIM");
        else System.out.println("NAO");
        scanner.close();
    }
}
