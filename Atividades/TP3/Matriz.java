import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Classe Celula
class Celula{
    public Celula sup,inf,dir,esq;
    public int elemento;
    public Celula(int x){
        sup=inf=dir=esq=null;
        this.elemento=x;
    }
    public Celula(){
        sup=inf=dir=esq=null;
    }
} 
public class Matriz{
    static Scanner scanner=new Scanner(System.in);
    private Celula inicio;
    private int lin,col;

    public Matriz(int colu,int l){
        this.lin=l;
        this.col=colu;
        inicio=new Celula();
        Celula c=inicio;
        Celula x=inicio;
        Celula y=inicio;

        //primeira linha
        for(int i=0;i<lin-1;i++){
            c.dir=new Celula();
            c.dir.esq=c;
            c=c.dir;            
        }
        c=inicio;
        for(int i=1;i<lin;i++){
            while(c!=null){
                c.inf=new Celula();
                c.inf.sup=c;
                c=c.dir;
            }
            c=x.inf;
            while(y.dir!=null){
                c.dir=y.dir.inf;
                c.dir.esq=c;
                y=y.dir;
                c=c.dir;
            }
            y=x.inf;
            x=x.inf;
            c=y;
        }
        
    }
    void preenche(){
        Celula c=inicio;
        Celula y=inicio;
        int numero;
        for(int j=0;j<lin;j++){
            for(int i=0;i<lin;i++){
                numero=scanner.nextInt();
                c.elemento=numero;
                c=c.dir;
            }
            c=y.inf;
            y=y.inf;
        }
    }
    void percorre(){
        Celula c=inicio;
        Celula y=inicio;
        for(int j=0;j<lin;j++){
            for(int i=0;i<lin;i++){
                System.out.print(c.elemento+" ");
                c=c.dir;
            }
            System.out.println("");
            c=y.inf;
            y=y.inf;
        }
    }
    void soma(Matriz a,Matriz b){
        Celula c=inicio;
        Celula cA=a.inicio;
        Celula cB=b.inicio;
        Celula yA=a.inicio;
        Celula yB=b.inicio;
        Celula y=inicio;
        for(int j=0;j<lin;j++){
            for(int i=0;i<lin;i++){
                c.elemento=cA.elemento+cB.elemento;
                cB=cB.dir;
                cA=cA.dir;
                c=c.dir;
            }

            cA=yA.inf;
            cB=yB.inf;
            c=y.inf;
            y=y.inf;
            yA=yA.inf;
            yB=yB.inf;
        }
    }
    void multiplica(Matriz a, Matriz b) {
        Celula c = inicio;
        Celula cA = a.inicio;
        Celula cB = b.inicio;
    
        Celula fA = a.inicio;
        Celula fB = b.inicio;
    
        // variação de colunas
        for (int j = 0; j < a.col; j++) {
            int soma = 0;
    
            // um elemento
            while (cA != null && cB != null) {
                soma = soma + cA.elemento * cB.elemento;
                cA = cA.dir;
                cB = cB.inf;
            }
    
            c.elemento = soma;
            c = c.inf; // Move to the next row in the result matrix
            cA = fA; // Reset cA to the beginning of the current row in the first matrix
            cB = b.inicio; // Reset cB to the beginning of the second matrix
    
            // Move to the next column in both matrices
            fA = fA.dir;
            fB = fB.dir;
        }
    }
    void mostra() {
        Celula c = inicio;
        Celula y = inicio;
        for (int j = 0; j < lin; j++) {
            for (int i = 0; i < col; i++) {
                System.out.print(c.elemento + " ");
                c = c.dir;
            }
            System.out.println("");
            c = y.inf;
            y = y.inf;
        }
    }
    public static void main(String[] args) {
       
        int nlin,ncol;
        int casosTeste=scanner.nextInt();
        for(int i=0;i<casosTeste;i++){
            nlin=scanner.nextInt();
            ncol=scanner.nextInt();
            Matriz matriz=new Matriz(nlin,ncol);
            matriz.preenche();
            Matriz matriz2=new Matriz(nlin,ncol);
            matriz2.preenche();
            Matriz resultadoS=new Matriz(nlin,ncol);
            resultadoS.soma(matriz,matriz2);
            Matriz resultadoM=new Matriz(nlin,ncol);
            resultadoM.multiplica(matriz, matriz2);
            resultadoM.mostra();
        }
            
            
        
    }
    
}
