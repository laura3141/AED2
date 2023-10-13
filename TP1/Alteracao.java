import java.util.Random;

public class Alteracao {
    public static void main(String[] args) {
        String str;
        Random gerador = new Random();
        gerador.setSeed(4);
        char a,b;
        do{
            str=MyIO.readLine();
            if(!str.equals("FIM")){
                int c[]=new int[str.length()];
                a=((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
                b=((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
                for(int i=0;i<str.length();i++){
                    if(str.charAt(i)==(char)a)c[i]=b;
                    else c[i]=str.charAt(i);
                    MyIO.print((char)c[i]);
                }
            MyIO.println("");
            }
           
        }while(!str.equals("FIM"));  
    }
}
