import java.util.Scanner;

class ExemploScanner{
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Digite uma string: ");
        String str=scanner.nextLine();
        System.out.println("Digite um caracter: ");
        char c=scanner.next().charAt(0);
        System.out.println("Digite um inteiro: ");
        int x=scanner.nextInt();
        System.out.println("Inteiro: "+x+" String: "+str+" Char: "+c);
        scanner.close();
    }
}