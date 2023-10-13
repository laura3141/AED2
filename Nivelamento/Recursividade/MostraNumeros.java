public class MostraNumeros{
    static void mostra(int n){
        if(n<4){
            System.out.println(n);
            mostra(n+1);
        }
        else return;
        
    }
    public static void main(String[] args) {
        mostra(0);
    }
}