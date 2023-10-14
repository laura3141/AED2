package java;
import java.util.Scanner;

public class PalindromoRecursivo {
    
    static boolean ePalindromo(String x, int i ){
        boolean eh; 
        if(i>=x.length()){
                eh=true; 
        }
        else{
                if (x.charAt(i) != x.charAt(x.length() - 1 - i)){
                return eh=false;
                }
                else{
                eh=ePalindromo(x, i+1);
                }
        }
        return eh; 
    }
    public static void main(String[] args) {
		String x;
		Scanner scanner = new Scanner(System.in);
		do {
			x = scanner.nextLine();
            if(!x.equals("FIM")){
            boolean eh = ePalindromo(x, 0);
			   if (eh)
				   System.out.println("SIM");
			   else
				   System.out.println("NAO");
            }
			
		} while (!x.equals("FIM"));

		scanner.close();
	}
}

