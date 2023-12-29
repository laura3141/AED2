public class ArrayIntercalado {
    static void mostra(int a[],int b[]){
       if(a.length>b.length)
        {
            for(int i=0;i<b.length;i++){
                System.out.println(a[i]+ " ");
                System.out.println(b[i]+ " ");
            }
            for(int i=b.length;i<a.length;i++){
                System.out.println(a[i]+ " ");
            }
            
        }
       else {
            for(int i=0;i<a.length;i++){
                System.out.println(a[i]+ " ");
                System.out.println(b[i]+ " ");
            }
            for(int i=a.length;i<b.length;i++){
                System.out.println(b[i]+ " ");
            }
            
       }

    }
    public static void main(String[] args) {
        int[]a={1,5,3,4,5};
        int[]b={0,2,7,8,9,10,18,19,20};
        mostra(a,b);
    }
}
