public class MultiplicacaoRecursiva {
    public static int multiplica(int a,int b){
        int soma=a;//soma=3 e b=2
        if(b>1){//entra 2//
            soma=soma+multiplica(a,b-1);//soma(3,2)=3+multiplica(3,1)//soma(3,1)= nao entra e retorna 3 
        }
        return soma;
    }
    public static void main(String[] args) {
        System.out.println(multiplica(3,2));
    }
}
