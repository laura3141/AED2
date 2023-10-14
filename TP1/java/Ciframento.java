package java;

public class Ciframento{
    public static void main(String[] args) {
        String inicio;
        String fim;
        char x='á';
        System.out.println((int)x);
        do{
            inicio="";
            fim="";
            inicio=MyIO.readLine();
            if(!inicio.equals("FIM")){

                for(int i=0;i<inicio.length();i++){
                        fim += (char)(inicio.charAt(i) + 3);
                }
                MyIO.println(fim);
            }
        }while(!inicio.equals("FIM"));

    }
}