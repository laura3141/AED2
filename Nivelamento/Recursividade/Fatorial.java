public class Fatorial {
    static int calculaFatorial(int n){
        int fat=1;
        if(n>1){ //entra 3// entra 2
            fat=n*calculaFatorial(n-1);//fat=3*fat(2) //fat(2)=2*fat(1)//fat(1)=1
        }
       return fat;
    }
    public static void main(String[] args) {
        calculaFatorial(5);
    }
}
