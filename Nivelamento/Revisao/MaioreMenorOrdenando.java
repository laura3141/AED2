public class MaioreMenorOrdenando{
    static void ordena(int a[]){
        int aux;
        for(int i=0;i<a.length-1;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i]>a[j]){
                    aux=a[i];
                    a[i]=a[j];
                    a[j]=aux;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[]a={7,5,10,9,47,3,8};
        for(int i=0;i<a.length;i++){
            System.out.print(" "+a[i]);
        }
        ordena(a);
        System.out.println("");
        for(int i=0;i<a.length;i++){
            
            System.out.print(" "+a[i]);
        }

    }
}