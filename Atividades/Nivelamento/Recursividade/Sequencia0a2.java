public class Sequencia0a2 {
    static void printa(int n){
        if(n>=0){//entra 2//entra 1// entra 0// -1 nao entra
            System.out.println(n);// printa 2//printa 1 //printa 0
            printa(n-1);//chama a funçao passando 1// chama a funcao passando 0// chama a funcao passando -1
            System.out.println(n);// 2 empilhado// 1 empilhado// 0 empilhado *** printa 0, 1 e 2
        }
        
    }
    public static void main(String[] args) {
        printa(2);//manda 2
    }
}
