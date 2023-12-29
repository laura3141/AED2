import java.util.Scanner;
public class AlgebraBooleana {


    /*Os seguintes métodos "or", "and", e "Not" possuem o mesmo fundamento e apenas se diferenciam na saída que cada um resulta. Ambos recebem uma string expressao denominada 'resultado' e a posiçao da primeira ocorrencia da operaçao na mesma. Em primeiro momento, um laço for é executado para copiar
 toda a parte da operaçao para uma string auxiliar. Em segundo momento é feito replace na string correspondente a expressao, substituindo toda ocorrencia 
 da string auxiliar pela soluçao da operaçao, ou seja 0 ou 1.
    */
    static String or(int pos,String resultado){
        String aux="";
        for(int i=pos;i<resultado.length();i++){
            if(resultado.charAt(i)!=')'){
                aux=aux+resultado.charAt(i);
            }
            else i=resultado.length();
        }
        aux=aux+')';
        int um=0;
        for(int i=0;i<aux.length();i++){
            if(aux.charAt(i)=='1'){
                resultado=resultado.replace(aux, "1");
                i=aux.length();
            }
            else if (aux.charAt(i)=='0'){
                for(int j=i;j<aux.length();j++){
                    if(aux.charAt(j)=='1')um++;
                    i=aux.length();
                }
                if(um>0)resultado=resultado.replace(aux, "1");
                else resultado=resultado.replace(aux, "0");
                
            }
        } 
        return resultado;
    }
    
    static String and(int pos,String resultado){
        int z=0;
        String aux="";
        for(int i=pos;i<resultado.length();i++){
            if(resultado.charAt(i)!=')'){
                aux=aux+resultado.charAt(i);
            }
            else i=resultado.length();
        }
        aux=aux+')';
        for(int i=0;i<aux.length();i++){
            if(aux.charAt(i)=='0'){
                resultado=resultado.replace(aux, "0");
                i=aux.length();
            }
            else if (aux.charAt(i)=='1'){
                for(int j=i;j<aux.length();j++){
                    if(aux.charAt(j)=='0'){
                        z++;
                        j=aux.length();
                }   }
                if(z>0)resultado=resultado.replace(aux, "0");
                else resultado=resultado.replace(aux, "1");
                
            }
        }
        return resultado; 
    }
    
    static String not(int pos,String resultado){
        String aux="";
        for(int i=pos;i<resultado.length();i++){
            if(resultado.charAt(i)!=')'){
                aux=aux+resultado.charAt(i);
            }
            else i=resultado.length();
        }
        aux=aux+')';

        for(int i=0;i<aux.length();i++){
            if(aux.charAt(i)=='0'){
                resultado=resultado.replace(aux, "1");
            }
            else if (aux.charAt(i)=='1'){
                resultado=resultado.replace(aux, "0");
            }
        } 
        return resultado;
    }
    // Método para contar quantas aberturas de funções tem na expressão, ou seja operaçoes a serem resolvidas
    static int contaparenteses(String resultado){
        int ab=0;
        for(int i=0;i<resultado.length();i++){
            if(resultado.charAt(i)=='('){
                ab++;
            }
        }
        return ab;
    }
    public static void main(String[] args) {
        int ab;
        int n,a,b,c=0;
        String exp, resultado="";
        do{   
            exp=MyIO.readLine();
            Scanner scanner = new Scanner(exp);
            if(!exp.equals("0")){
                n = scanner.nextInt();
                a = scanner.nextInt();
                b = scanner.nextInt();
                String expMenor="";
                // Ler a 3 variavel se necessário
                if(n == 3){
                    c= scanner.nextInt();
                }
                // Isola apenas a expressão da linha obtida pela primeira leitura
                if(n == 2){
                    for(int i = 6; i<exp.length();i++){
                        expMenor = expMenor + exp.charAt(i);
                    }
                }
                else if(n == 3){
                    for(int i=8;i<exp.length();i++){
                        expMenor=expMenor+exp.charAt(i);
                    }
                }

                resultado = expMenor.replace("A", String.valueOf(a))
                .replace("B", String.valueOf(b))
                .replace("and", ("&"))
                .replace("or", ("|"))
                .replace("not", ("!"))
                .replace("C", String.valueOf(c));
                ab = contaparenteses(resultado);
                
                while(ab!=0){
                    /*Laço executado de forma a percorrer a String correspondente a expressao a ser resolvida de tras para frente.
                     Ao encontrar '&', "!" ou '|' o caracter e a posiçao são armazenados em variaveis, afim de controlar o fluxo de 
                     resoluçao da operação por partes.
                     */
                    String op="";
                    int posi=0;
                    for(int i=resultado.length()-1;i>=0;i--){
                        if(resultado.charAt(i)=='!'||resultado.charAt(i)=='&'||resultado.charAt(i)=='|'){
                            posi=i;
                            op=op+resultado.charAt(i);
                            i=0;
                        }
                    }
                    ab=contaparenteses(resultado);
                    
                    if(op.charAt(0)=='&'){  // Caso a proxima operaçao a ser resolvida seja and
                        resultado=and(posi, resultado);
                        ab=contaparenteses(resultado);
                    }
                    else if(op.charAt(0)=='!'){ // Caso a proxima operaçao a ser resolvida seja not
                        resultado=not(posi,resultado);
                        ab=contaparenteses(resultado);
                    }
                    else if(op.charAt(0)=='|'){ /// Caso a proxima operaçao a ser resolvida seja or
                        resultado=or(posi,resultado);
                        ab=contaparenteses(resultado);
                    }
                }
                System.out.println(resultado);
            }
            
            scanner.close();
        }while(!exp.equals("0"));
    }
}
