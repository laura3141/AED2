package java;

public class Is {
    static boolean consoante(String str){
        boolean eh=true;
        String mstr=str.toUpperCase();
        for(int i=0;i<str.length();i++){
            if(mstr.charAt(i)>64&&mstr.charAt(i)<91){
                if(mstr.charAt(i)!='A'&&mstr.charAt(i)!='E'&&mstr.charAt(i)!='I'&&mstr.charAt(i)!='O'&&mstr.charAt(i)!='U');
                else {
                    eh=false;
                    break;
                }
            }
            else{
                eh=false;
                break;
            }
        }
        return eh;
    }
    static boolean vogal(String str){
        boolean eh=true;
        String mstr=str.toUpperCase();
        for(int i=0;i<str.length();i++){
            if(mstr.charAt(i)!='A'&&mstr.charAt(i)!='E'&&mstr.charAt(i)!='I'&&mstr.charAt(i)!='O'&&mstr.charAt(i)!='U'){
                eh=false;
                break;
            }
        }
        return eh;
    }
     
    static boolean inteiro(String str){
        boolean eh=true;
        for(int i=0;i<str.length();i++){
            if((int)str.charAt(i)<48||(int)str.charAt(i)>57){
                eh=false;
                i=str.length();
            }
        }
        return eh;
    }
    static boolean real(String str){
        boolean eh=true;
        int v=0;
        for(int i=0;i<str.length();i++){
            if(((int)str.charAt(i)<48||(int)str.charAt(i)>57)&&str.charAt(i)!=','){
                eh=false;
                i=str.length();
            }
            else{
                if(str.charAt(i)==','||str.charAt(i)=='.')v++;
            }
        }
        if(v<1)eh=false;
        return eh;
    }
    
    public static void main(String[] args) {
        String str;
        do{
            str=MyIO.readLine();
            if(vogal(str))System.out.print("SIM ");
            else System.out.print("NAO ");
            if(consoante(str))System.out.print("SIM ");
            else System.out.print("NAO ");
            if(inteiro(str))System.out.print("SIM ");
            else System.out.print("NAO ");
            if(real(str))System.out.print("SIM ");
            else System.out.print("NAO ");
            System.out.println("");

        }while(!str.equals("FIM"));
        
    }
}
