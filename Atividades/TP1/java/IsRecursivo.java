package java;

public class IsRecursivo
{
    static boolean consoante(String str,int i){
        boolean eh;
        String mstr=str.toUpperCase();
        if(i>=str.length()){
             eh=true;
        }
        else{
            if(mstr.charAt(i)>64&&mstr.charAt(i)<91&&(mstr.charAt(i)!='A'&&mstr.charAt(i)!='E'&&mstr.charAt(i)!='I'&&mstr.charAt(i)!='O'&&mstr.charAt(i)!='U'))
                    eh=consoante(mstr, i+1);
            else{
                   return eh=false; 
            }
        }
        return eh;
    }
    static boolean vogal(String str,int i){
        boolean eh;
        String mstr=str.toUpperCase();
        if(i>=str.length()){
            eh=true;
        }
        else{
            if(mstr.charAt(i)!='A'&&mstr.charAt(i)!='E'&&mstr.charAt(i)!='I'&&mstr.charAt(i)!='O'&&mstr.charAt(i)!='U'){
                return eh=false;
            }
            else eh=vogal(mstr, i+1);

        }
        return eh;
    }
     
    static boolean inteiro(String str,int i){
        boolean eh;
        if(i>=str.length()){
            eh=true;
        }
        else{
            if((int)str.charAt(i)<48||(int)str.charAt(i)>57){
                return eh=false;
            }
            else eh=inteiro(str, i+1);
        }
        return eh;
    }

    static boolean real(String str, int i, int v) {
        boolean eh;
        if (i >= str.length()) {
            if (v > 1) {
                eh = false;
            } else {
                eh = true;
            }
        } else {
            char currentChar = str.charAt(i);
    
            if (((int) currentChar < 48 || (int) currentChar > 57) && (currentChar != ',' && currentChar != '.')) {
                return eh = false;
            } else {
                if (currentChar == ',' || currentChar == '.') {
                    eh = real(str, i + 1, v + 1);
                } else {
                    eh = real(str, i + 1, v);
                }
            }
        }
        return eh;
    }
    public static void main(String[] args) {
        String str;
        do{
            str=MyIO.readLine();
            if(vogal(str,0))System.out.print("SIM ");
            else System.out.print("NAO ");
            if(consoante(str,0))System.out.print("SIM ");
            else System.out.print("NAO ");
            if(inteiro(str,0))System.out.print("SIM ");
            else System.out.print("NAO ");
            if(real(str,0,0))System.out.print("SIM ");
            else System.out.print("NAO ");
            System.out.println("");
        }while(!str.equals("FIM"));
        
    }
}
