import java.util.Scanner;

class No {
    int elemento;
    No esq, dir;

    No(int elemento) {
        this.elemento = elemento;
        dir = esq = null;
    }

    No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
    }
}
class TreeSort{
    ArvoreBinaria arvore;
    int a[];
    int n;
    public TreeSort(int x){
        arvore=new ArvoreBinaria();
        a=new int[x];
        n=0;
    }
    int []sort(int b[],int n){
        for(int i=0;i<n;i++){
            arvore.inserir(b[i]);
        }
        ordena(arvore.raiz);
        return a;

    }
    void ordena(No i){
        if(i!=null){
            ordena(i.dir);
            a[n++]=i.elemento;
            ordena(i.esq);
        }
    }
}

public class ArvoreBinaria {
    public No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    No inserir(int x, No aux) {
        if (aux == null) {
            aux = new No(x);
        } else {
            if(aux.elemento==x){
                System.out.println("Erro");
            }
            else if (x > aux.elemento) {
                aux.dir = inserir(x, aux.dir); 
            } else {
                aux.esq = inserir(x, aux.esq); 
            }
        }
        return aux;
    }

    boolean pesquisar(int x, No aux) {
        boolean resp=false;
        if(aux!=null){
            if(aux.elemento==x){
                resp=true;
            }
            else if(x>aux.elemento){
                resp=pesquisar(x, aux.dir);
            }
            else if(x<aux.elemento){
                resp=pesquisar(x, aux.esq);
            }
        }
        return resp;
    }
    void caminharCentral(No i){
        if(i!=null){
            caminharCentral(i.esq);
            System.out.println(i.elemento);
            caminharCentral(i.dir);
        }
    }
    int [] treeSort(No i,int a[],int c){
        if(i!=null){
            treeSort(i.esq, a, c);
            a[c]=i.elemento;
            treeSort(i.dir,a,c+1);
        }
        return a;
    }
    int removerMaiorEsq(){
        int elemento=removerMaiorEsq(raiz,raiz.esq);
        return elemento;
    }
    int removerMaiorEsq(No anterior,No i){
        if(i.dir==null){
            int elemento=i.elemento;
            anterior.dir=i.esq;
            return elemento;
        }
        else return removerMaiorEsq(i, i.dir);
    }
    int getAltura(No i) {
        int h=-1;
        if (i != null) {
            int alturaEsq = 1+getAltura(i.esq);
            int alturaDir = 1+getAltura(i.dir);
            if(alturaDir>alturaEsq)h=alturaDir;
            else h=alturaEsq;
        }
        return h;
    }
    int contaElementos(No i) {
        int n=0;
        if (i != null) {
            n=1+contaElementos(i.dir);
            n=n+contaElementos(i.esq);
        }
        return n;
    }
    int somaTotal(No i){
        int soma=0;
        if(i!=null){
            soma=i.elemento+somaTotal(i.dir);
            soma=soma+somaTotal(i.esq);

        }
        return soma;
    }
    int pares(No i){
        int n=0;
        if(i!=null){
            if(i.elemento%2==0){
                n=1+pares(i.dir);
                n=n+pares(i.esq);
            }
            else{
                n=pares(i.dir);
                n=n+pares(i.esq);
            }

        }
        return n;
    }
    static boolean iguais(ArvoreBinaria a, ArvoreBinaria b,No iA,No iB){
        boolean ehIgual=true;
        if(iA!=null&&iB!=null){
            if(iA.elemento!=iB.elemento){
                ehIgual=false;
            }
            else{
                ehIgual=iguais(a, b, iA.dir, iB.dir);
                ehIgual=ehIgual&&iguais(a, b, iA.esq, iB.esq);
            }
        }
        return ehIgual;
    }
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        ArvoreBinaria arvore2 = new ArvoreBinaria();
        Scanner scanner=new Scanner(System.in);
        int a[]=new int[10];
        for (int i = 0; i <10; i++) 
        {
           a[i]=scanner.nextInt();
        }
        for (int i = 0; i <10; i++) 
        {
           System.out.print(a[i]+" ");
        }
        System.out.println(" end");
        TreeSort sortt=new TreeSort(10);
        a=sortt.sort(a,10);
        for (int i = 0; i <10; i++) 
        {
           System.out.print(a[i]+" ");
        }
        
       
}
}