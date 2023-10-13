import java.util.Scanner;

public class ExemploString {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();
        char c=scanner.next().charAt(0);
        int iguais=0;
        System.out.println(str.length());
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==c)iguais++;
        }
        System.out.println(iguais);
        scanner.close();
    }
}
