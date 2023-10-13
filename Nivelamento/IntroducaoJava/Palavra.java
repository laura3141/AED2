import java.util.Scanner;

public class Palavra {
    public static int letras (String str){
        int qt=0;
        String maiuscula=str.toUpperCase();
        for(int i=0;i<str.length();i++){
            if(maiuscula.charAt(i)>='A'&&maiuscula.charAt(i)<='Z')qt++;
        }
        return qt;
    }
    public static int vogais (String str){
        int qt=0;
        String maiuscula=str.toUpperCase();
        for(int i=0;i<str.length();i++){
            if(maiuscula.charAt(i)=='A'||maiuscula.charAt(i)=='E'||maiuscula.charAt(i)=='I'||maiuscula.charAt(i)=='O'||maiuscula.charAt(i)=='U')qt++;
        }
        return qt;
    }
    public static void main(String args[]){
         Scanner scanner=new Scanner(System.in);
         String str=scanner.nextLine();
         int caracteres=0,letra=0,outros=0,vogal=0,consoantes=0;
         caracteres=str.length();
         letra=letras(str);
         outros=caracteres-letra;
         vogal=vogais(str);
         consoantes=letra-vogal;
         System.out.println("Total de caracteres: " + caracteres);
         System.out.println("Total de letras: " + letra);
         System.out.println("Total de outros caracteres: " + outros);
         System.out.println("Total de vogais: " + vogal);
         System.out.println("Total de consoantes: " + consoantes);
        scanner.close();
    }
    
}
