class Celula {
    int elemento;
    Celula prox;

    public Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
    public Celula() {
        this.prox = null;
    }
}

public class Fila{
    Celula primeiro,ultimo;

    public Fila() {
        primeiro=new Celula();
        ultimo=primeiro;
    }

    public static Celula celulaTofila(Celula topo) {
        Celula i=topo;
        Celula topoAUx=null;
        while(i!=null){
            Celula tmp=new Celula(i.elemento);
            tmp.prox=topoAUx;
            topoAUx=tmp;
            tmp=null;
            i=i.prox;
        }
        Fila fila=new Fila();
        while(topoAUx!=null){
            fila.ultimo.prox=new Celula(topoAUx.elemento);
            fila.ultimo=fila.ultimo.prox;
            topoAUx=topoAUx.prox;
        }
        return fila.primeiro;
    }
    public void inverte(){
        Celula j=ultimo;
        while(primeiro.prox!=ultimo){
            ultimo.prox=new Celula(i.elemento);
            ultimo=ultimo.prox;
            i=i.prox;
        }
        primeiro=i;
    }
    public static void main(String[] args) {
        /*Celula topo=new Celula(5);
        topo.prox=new Celula(3);
        topo.prox.prox=new Celula(2);
        for(Celula i=topo;i!=null;i=i.prox){
            System.out.println(i.elemento);
        }
        Celula x=celulaTofila(topo);
        for(Celula i=x;i!=null;i=i.prox){
            System.out.println(i.elemento);
        }*/
        Fila fila=new Fila();
        fila.ultimo.prox=new Celula(5);
        fila.ultimo=fila.ultimo.prox;
         fila.ultimo.prox=new Celula(6);
        fila.ultimo=fila.ultimo.prox;
         fila.ultimo.prox=new Celula(7);
        fila.ultimo=fila.ultimo.prox;
        for(Celula i=fila.primeiro.prox;i!=null;i=i.prox){
            System.out.println(i.elemento);
        }
        fila.inverte();
         for(Celula i=fila.primeiro.prox;i!=null;i=i.prox){
            System.out.println(i.elemento);
        }
        
    }
}