package java;
public class CiframentoRecursivo {
    static String Cifra(String x,int i){
        String fim="";
        if(i>=x.length()){
            return fim;
        }
        else{
            fim=(char)(x.charAt(i) + 3)+Cifra(x, i+1);
            return fim;
        }
        
    }
    public static void main(String[] args) {
        String inicio;
        String fim;
        do{
            inicio="";
            fim="";
            inicio=MyIO.readLine();
            if(!inicio.equals("FIM")){
                fim=Cifra(inicio, 0);
                MyIO.println(fim);
            }
        }while(!inicio.equals("FIM"));

    }
}
